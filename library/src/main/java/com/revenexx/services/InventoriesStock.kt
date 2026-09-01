package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * How much is there, what may still be sold, and every call that changes the number. A stock level is one item at one location and it carries two figures, neither of which is the sellable one: `on_hand` counts what is physically there INCLUDING everything already promised, `reserved` counts the promises and never reduces `on_hand`, and what a shop may sell is the difference — derived on read, never stored, so there is no `available` column to filter or order by. The balance is not editable either: every change is a booking in the movements ledger, which is why `receive` (goods in), `adjust` (a signed correction with a reason), `restock` (a return coming back) and the row-scoped adjust are the only things that move a number, and why the ledger reads sit in this same group rather than a section of their own — a movement is the receipt for the call above it, not a subject. POST /inventories/availability is the read side of all of it, and the one capability an ERP-stocked tenant replaces wholesale through the gateway override. The vocabulary routes are here because the code a caller cannot guess is a movement's `type`: it decides the SIGN of the quantity.
 */
class InventoriesStock(client: Client) : Service(client) {

    /**
     * The batch correction route — a stocktake, breakage, shrinkage — and the manual way `on_hand` is ever put right. Quantities are SIGNED: a positive one adds to the balance, a negative one takes it away, and neither is written onto the row directly. Each item is booked into the movements ledger as an `adjustment` and the balance follows, so a correction leaves a record of who changed what and why instead of a number that silently differs from yesterday's. A reason is mandatory unless movement_reason_required is 'none'.
     *
     * @param items The corrections, at most 200 in one call — a stocktake, breakage, shrinkage. Quantities are SIGNED deltas, not new balances.
     * @param locationCode Which location is being corrected. Omitted, the `default_location_code` setting decides. A correction is per location: the same SKU in two warehouses is two corrections.
     * @param productId Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     * @param quantity Inline single-item form: the SIGNED correction (negative writes stock off, positive finds it). Non-zero.
     * @param reason Why the stock is being corrected — this is the audit trail a stocktake leaves behind. Owed unless `movement_reason_required` is 'none' (its default, 'adjustments', asks for one exactly here); missing where it is owed, the call is 400.
     * @param sku Inline single-item form: the article number to move (instead of `product_id`).
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesAdjust(
        items: List<com.revenexx.models.InventoryAdjustItem>? = null,
        locationCode: String? = null,
        productId: String? = null,
        quantity: Double? = null,
        reason: String? = null,
        sku: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/adjust"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
            "product_id" to productId,
            "quantity" to quantity,
            "reason" to reason,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * THE stock call of this app, and a batch one: name any number of items and each comes back with `on_hand`, `reserved` and the derived `available` (their difference, computed on read and stored nowhere), summed across the locations in scope and broken down per location, plus `orderable` — whether this much of it can be promised at this moment. An item this app has never seen is NOT an error: it comes back tracked:false, and the storefront decides whether an untracked item sells freely. It is also the most customised surface this product has in the field. A tenant whose stock really lives in an ERP — SAP live stock is the ordinary case, not the exotic one — replaces exactly this one capability, 1:1, with a custom app through the gateway's capability override, while every other route here keeps doing the stock-keeping CRUD unchanged. That is why the request and response shapes below read as a contract to be implemented rather than as an implementation detail: whatever ends up answering this path has to answer in these terms.
     *
     * @param items The items to check, at most 200 in one call. A cart, a category page, a feed row — one call answers them all, which is why this route is the batch one.
     * @param locationCode Restrict the check to ONE location, by its code — the stock a click-and-collect store can promise today. Omitted, every ENABLED location is summed; a disabled one is never counted either way.
     * @param productId Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     * @param quantity Inline single-item form: how many are wanted (default 1). It decides `orderable` and nothing else.
     * @param sku Inline single-item form: the article number to move (instead of `product_id`).
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesAvailability(
        items: List<com.revenexx.models.InventoryAvailabilityItem>? = null,
        locationCode: String? = null,
        productId: String? = null,
        quantity: Double? = null,
        sku: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/availability"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
            "product_id" to productId,
            "quantity" to quantity,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * The movements ledger, read end to end. Every stock change this app has ever made is a booking row in it — a receipt, a correction, a hold, a release, a shipment, a return — which is what lets one list be an audit trail and an event feed at the same time: these are the rows the `stock_movement.created` event carries, so a consumer that missed an event catches up by paging here. Append-only: the ledger has no update and no delete, because a correction is another booking. `order=created_at.desc` is the feed order.
     *
     * @param limit Page size (default 50, max 200). A larger value is clamped rather than refused.
     * @param offset Row offset for pagination (default 0). Page with `page.total` and `page.hasMore`.
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc' — a bare column sorts ascending. The column has to be one this entity has; anything else is refused with 400.
     * @param id Exact-match filter on `id`. The row's own id, generated by the database.
     * @param locationId Exact-match filter on `location_id`. Every booking at one location.
     * @param productId Exact-match filter on `product_id`. The product this booking is for, copied from the call.
     * @param sku Exact-match filter on `sku`. Every booking for one SKU.
     * @param type Exact-match filter on `type`. What the booking records. The permitted set is the CHECK constraint — GET /inventories/vocabularies/movement-types has the words for it.
     * @param quantity Exact-match filter on `quantity`. Exact signed quantity, which is a needle-in-a-haystack filter rather than a range: `?quantity=-5` finds the bookings that moved exactly five out.
     * @param orderRef Exact-match filter on `order_ref`. One order's whole stock history: its reserve, release, shipment and restock bookings.
     * @param reason Exact-match filter on `reason`. Why the booking happened, in a person's words — a delivery note number, 'stocktake 2026-03', 'damaged in transit'.
     * @param metadata Exact-match filter on `metadata`. Free-form, and two keys this app writes itself: `backordered` — on a `reserve` booking, how much of the hold was not covered by stock on hand; `shortfall` — on a `shipment` booking, how much was committed that was not physically there (`on_hand` floors at 0, so the difference is recorded here instead of vanishing). The WHOLE jsonb document is compared, serialized as JSON — this is equality, not a key lookup or a containment query, and a value that does not parse is answered 400.
     * @param createdAt Exact-match filter on `created_at`. Exact timestamp. There is no range filter on the ledger — page it with `?order=created_at.desc` instead.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesMovementsList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        id: String? = null,
        locationId: String? = null,
        productId: String? = null,
        sku: String? = null,
        type: com.revenexx.enums.InventoriesMovementsListType? = null,
        quantity: Double? = null,
        orderRef: String? = null,
        reason: String? = null,
        metadata: String? = null,
        createdAt: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/movements"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "id" to id,
            "location_id" to locationId,
            "product_id" to productId,
            "sku" to sku,
            "type" to type,
            "quantity" to quantity,
            "order_ref" to orderRef,
            "reason" to reason,
            "metadata" to metadata,
            "created_at" to createdAt,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A movement is one booking row in the ledger, and the ledger is append-only: there is no update and no delete, because a correction is another booking. `quantity` is SIGNED and its sign follows the `type` — a receipt books +5 and the reserve that promises those goods books −5, even though the reservation it created carries +5 as a positive hold. GET /inventories/vocabularies/movement-types is the list of types with the words for them. A booking says what changed, not what the balance became: it carries no running total, so the row's story is read by listing the ledger for that location and item rather than by fetching one id. `location_id` is a plain uuid and not a foreign key, so a booking outlives the location it was made at and this route will happily hand back one whose location no longer resolves — that is the audit trail doing its job, not a broken row. Fixing a wrong booking is another booking (POST /inventories/adjust); nothing here can be edited or removed.
     *
     * @param id The ledger booking.
     * @return [com.revenexx.models.Error]
     */
    suspend fun inventoriesMovementsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/movements/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Books a delivery into the receiving location (the caller's location_code, else the default_location_code setting), creating the stock row if the item is new. A reason is optional unless movement_reason_required is 'all'. Takes a batch or one item inline.
     *
     * @param items The goods that arrived, at most 200 in one call — a delivery, a production batch, an opening balance.
     * @param locationCode Which location took the delivery. Omitted, the `default_location_code` setting decides; a code no location carries is answered 400 rather than booked somewhere else.
     * @param productId Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     * @param quantity Inline single-item form: how many arrived. Positive.
     * @param reason What the ledger should record about this receipt — a delivery note number, a production order. Owed only when `movement_reason_required` is 'all'; the contract does not require it, because whether it is owed is the tenant's setting and not this route's rule.
     * @param sku Inline single-item form: the article number to move (instead of `product_id`).
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesReceive(
        items: List<com.revenexx.models.InventoryStockItem>? = null,
        locationCode: String? = null,
        productId: String? = null,
        quantity: Double? = null,
        reason: String? = null,
        sku: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/receive"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
            "product_id" to productId,
            "quantity" to quantity,
            "reason" to reason,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * The replenishment worklist: the stock rows that have run down far enough that somebody has to order more, in one list rather than as a query a caller has to build. Computed on read, so it is never stale: a row alerts when available (on_hand − reserved) has fallen to or below its own reorder_point, or the reorder_point_default setting when it carries none. A point of 0 never alerts. Answers enabled:false with an empty list when reorder_alert_enabled is off — a tenant replenishing from an ERP should not be told twice.
     *
     * @return [com.revenexx.models.ReorderAlerts]
     */
    suspend fun inventoriesReorderAlerts(
    ): com.revenexx.models.ReorderAlerts {
        val apiPath = "/v1/inventories/reorder-alerts"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ReorderAlerts = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReorderAlerts.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReorderAlerts::class.java,
            converter,
        )
    }


    /**
     * Publishes `stock_level.low` on the event bus for every row GET /inventories/reorder-alerts currently lists, so replenishment can be driven by a subscriber instead of by somebody refreshing that page. Also runs hourly as the `reorder-scan` schedule; this route is for driving it on demand. The event id is derived from the stock row and the day, so a re-run — a second click, a retried cron tick — publishes nothing new and returns the ids the first run produced. Nothing is written to the app's own data: this reads the same figures the alerts list computes and hands them to the bus. Answers enabled:false without publishing when reorder_alert_enabled is off.
     *
     * @param data Request body
     * @return [com.revenexx.models.ReorderScan]
     */
    suspend fun inventoriesReorderScan(
        data: Any,
    ): com.revenexx.models.ReorderScan {
        val apiPath = "/v1/inventories/reorder-alerts/scan"

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ReorderScan = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReorderScan.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReorderScan::class.java,
            converter,
        )
    }


    /**
     * Whether a return rejoins sellable stock follows restock_on_return_default, overridable per call with 'restock'. When the answer is no the response says restocked:false and nothing moves — there is no movement to book, because no stock moved. That branch is why this route answers 200 and its sibling `receive` answers 201: a restock may legitimately create nothing.
     *
     * @param items The goods that came back, at most 200 in one call. Whether they rejoin sellable stock is `restock`, not this list.
     * @param locationCode Where the goods came back to — a returns warehouse is a location like any other. Omitted, the `default_location_code` setting decides.
     * @param orderRef The order the goods came back from. It is written onto the ledger booking, so the return shows up in that order's stock history next to its reserve and shipment — no reservation is touched by it.
     * @param productId Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     * @param quantity Inline single-item form: how many came back. Positive.
     * @param reason Why the goods came back — 'wrong size', 'damaged on arrival'. Owed only when `movement_reason_required` is 'all'.
     * @param restock Do these goods rejoin SELLABLE stock? A merchant decision, not a fact: apparel usually restocks, hygiene articles never do, many merchants inspect first. Omit it to follow the `restock_on_return_default` setting. `false` answers `restocked: false`, moves nothing and books NOTHING — there is no movement to write, because no stock moved, and that is the branch that makes this route a 200 while its sibling `receive` is a 201.
     * @param sku Inline single-item form: the article number to move (instead of `product_id`).
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesRestock(
        items: List<com.revenexx.models.InventoryStockItem>? = null,
        locationCode: String? = null,
        orderRef: String? = null,
        productId: String? = null,
        quantity: Double? = null,
        reason: String? = null,
        restock: Boolean? = null,
        sku: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/restock"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
            "order_ref" to orderRef,
            "product_id" to productId,
            "quantity" to quantity,
            "reason" to reason,
            "restock" to restock,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A stock level is ONE item at ONE location, and it carries two numbers, neither of which is the sellable one: `on_hand` is what is physically there INCLUDING everything already promised, and `reserved` is what has been promised — it never reduces `on_hand`. What may still be sold is their difference, and it is derived on read and never stored, so there is no `available` column to read, filter or order by. This is the operator's view — the whole book, filtered by location or by item — not the shop's: a storefront asking "can I sell five of this" wants POST /inventories/availability, which sums an item across locations and answers `orderable` instead of leaving the caller to subtract. Two things this list will not do: it has no range filters, so "everything running low" is GET /inventories/reorder-alerts and not a query here; and it does not promise one row per item per location — no unique index enforces that. POST /inventories/stock refuses a duplicate with a 409, but that is a check and not a constraint, so a row written past it, or one that predates the guard, still splits an item's balance in two, and the write routes find and update whichever of them the database returns first.
     *
     * @param limit Page size (default 50, max 200). A larger value is clamped rather than refused.
     * @param offset Row offset for pagination (default 0). Page with `page.total` and `page.hasMore`.
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc' — a bare column sorts ascending. The column has to be one this entity has; anything else is refused with 400.
     * @param id Exact-match filter on `id`. The row's own id, generated by the database.
     * @param locationId Exact-match filter on `location_id`. The rows held at one location. An id no location carries is an empty page, not an error.
     * @param productId Exact-match filter on `product_id`. The rows tracking one product, across every location.
     * @param sku Exact-match filter on `sku`. The rows tracking one SKU — the identity used when an item has no product id.
     * @param onHand Exact-match filter on `on_hand`. Exact balance, which is rarely what a reader wants: `?on_hand=0` finds the rows that are empty. There is no range filter here — GET /inventories/reorder-alerts is the "running low" question.
     * @param reserved Exact-match filter on `reserved`. Exact reserved quantity. `?reserved=0` finds the rows nothing is holding.
     * @param reorderPoint Exact-match filter on `reorder_point`. The available quantity at or below which this row belongs on the replenishment worklist (GET /inventories/reorder-alerts).
     * @param metadata Exact-match filter on `metadata`. Free-form data the tenant keeps on this stock row, and ONE key this app reads: `backorder`. The WHOLE jsonb document is compared, serialized as JSON — this is equality, not a key lookup or a containment query, and a value that does not parse is answered 400.
     * @param createdAt Exact-match filter on `created_at`. When the row was created.
     * @param updatedAt Exact-match filter on `updated_at`. When this row was last written.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesStockList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        id: String? = null,
        locationId: String? = null,
        productId: String? = null,
        sku: String? = null,
        onHand: Double? = null,
        reserved: Double? = null,
        reorderPoint: Double? = null,
        metadata: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/stock"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "id" to id,
            "location_id" to locationId,
            "product_id" to productId,
            "sku" to sku,
            "on_hand" to onHand,
            "reserved" to reserved,
            "reorder_point" to reorderPoint,
            "metadata" to metadata,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Registers an item at a location. The row is born at ZERO and never gets a balance from this call: `on_hand` and `reserved` are NOT accepted, because they are the running total of the movements ledger, so an opening balance is a receipt (POST /inventories/receive) rather than a field here, and the only thing that ever moves either number afterwards is another booking. What this row carries is its identity (location + `product_id`/`sku`), its `reorder_point` and its metadata. `location_id` is the only field a create cannot omit; every other column is optional or defaulted by the database. The one rule that is a CHECK rather than a column is that a row has to identify its item, so `product_id` or `sku` has to be there as well. Mostly you do not need this route at all — every stock call creates the row it is missing — and a second row for an item this location already tracks is answered 409: no unique index enforces one row per item per location, so that row would split the item's balance across two rows the write routes cannot tell apart, each of them updating whichever the database returns first. That guard is a check before the insert and not a constraint, so it closes a double click or a re-run import and does not claim to close a race between two simultaneous creates.
     *
     * @param locationId The location this balance is held at — a `locations` row of this tenant (GET /inventories/locations). There is ONE stock row per (location, item): the same SKU in three warehouses is three rows, and what a storefront shows is their sum (POST /inventories/availability). Deleting the location deletes its stock rows with it. It has to exist already (GET /inventories/locations); an id no location carries is answered 400 by the foreign key, not 404.
     * @param metadata Free-form data the tenant keeps on this stock row, and ONE key this app reads: `backorder`. A literal boolean `true` there opts this item into backorders while `backorder_policy` is 'allow_per_sku' — anything else, including the string "true", does not, and the reservation is refused with 422. That is how a merchant backorders the supplier-stocked half of a catalogue without promising the rest.
     * @param productId The product this row tracks, as the products app knows it. A row tracks a `product_id` or a `sku` — the database insists on at least one (CHECK `product_id is not null or sku is not null`) — and matching is exact: a row keyed by SKU is not found by product id.
     * @param reorderPoint The available quantity at or below which this row belongs on the replenishment worklist (GET /inventories/reorder-alerts). Null falls back to the `reorder_point_default` setting, so replenishment works without a threshold per SKU; 0 never alerts, which is how one row opts out.
     * @param sku The article number this row tracks when there is no product id, which is the normal case for an ERP-stocked catalogue. Exact match, and the identity every stock call may use instead of a uuid.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesStockCreate(
        locationId: String,
        metadata: Any? = null,
        productId: String? = null,
        reorderPoint: Double? = null,
        sku: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/stock"

        val apiParams = mutableMapOf<String, Any?>(
            "location_id" to locationId,
            "metadata" to metadata,
            "product_id" to productId,
            "reorder_point" to reorderPoint,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Stops tracking one item at one location. A stock level is ONE item at ONE location, and it carries two numbers, neither of which is the sellable one: `on_hand` is what is physically there INCLUDING everything already promised, and `reserved` is what has been promised — it never reduces `on_hand`. What may still be sold is their difference, and it is derived on read and never stored, so there is no `available` column to read, filter or order by. A deleted balance is not recoverable: the ledger is the audit trail, not the source of truth, and nothing in this app ever replays it to rebuild a number — so the next receipt for the same item here creates a FRESH row at zero, standing next to movements that say otherwise. That used to be a trap a caller discovered afterwards. It is a stated property now, because the route REFUSES while the row still holds anything, and answers 409 with what it holds. The two things that block are the location delete's two, asked of one row. A reservation still `active` against this item at this location is the sharper one: /release and /commit look their stock row up by (location, item) on the very next call and would find nothing, so the hold would lower no `reserved` and /commit would book the whole quantity as a shortfall — orphaned immediately rather than eventually. `on_hand` above zero is the stronger one: deleting a LOCATION at least meant "close this warehouse" and took the balances as a side effect of the cascade, while this row IS the balance, so the delete can only ever mean "no longer tracked here" — true once the number is zero and a lie while it is not. POST /inventories/stock/{id}/adjust to zero is the operation that makes it true, and it BOOKS the movement, so the stock leaves through the ledger instead of vanishing with the row. Nothing points at it by foreign key, so the database takes nothing else with it. History therefore never blocks and is never deleted — the ledger is keyed on (location, item) and never on this id, so its bookings survive a row that is gone, BY DESIGN, exactly as they survive a location that is gone.
     *
     * @param id The stock row.
     * @return [com.revenexx.models.Error]
     */
    suspend fun inventoriesStockDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/stock/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A stock level is ONE item at ONE location, and it carries two numbers, neither of which is the sellable one: `on_hand` is what is physically there INCLUDING everything already promised, and `reserved` is what has been promised — it never reduces `on_hand`. What may still be sold is their difference, and it is derived on read and never stored, so there is no `available` column to read, filter or order by. Read it to see one item's position at one place, and to get the id the two row-scoped routes take: POST /inventories/stock/{id}/adjust corrects this balance, and GET /inventories/reorder-alerts reports it by this id. What it does not answer is how the balance got here — that is GET /inventories/movements filtered by the location and item on this row, because a movement points at (location, item) and never at a stock row id.
     *
     * @param id The stock row.
     * @return [com.revenexx.models.Error]
     */
    suspend fun inventoriesStockGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/stock/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Partial update of everything on the row EXCEPT its balance: reorder_point, metadata, identity. on_hand and reserved are dropped from the body — every stock change is a movement, and a body carrying nothing else is answered 422 with the route that was meant (POST /inventories/stock/{id}/adjust).
     *
     * @param id The stock row.
     * @param locationId The location this balance is held at — a `locations` row of this tenant (GET /inventories/locations). There is ONE stock row per (location, item): the same SKU in three warehouses is three rows, and what a storefront shows is their sum (POST /inventories/availability). Deleting the location deletes its stock rows with it. It has to exist already (GET /inventories/locations); an id no location carries is answered 400 by the foreign key, not 404.
     * @param metadata Free-form data the tenant keeps on this stock row, and ONE key this app reads: `backorder`. A literal boolean `true` there opts this item into backorders while `backorder_policy` is 'allow_per_sku' — anything else, including the string "true", does not, and the reservation is refused with 422. That is how a merchant backorders the supplier-stocked half of a catalogue without promising the rest.
     * @param productId The product this row tracks, as the products app knows it. A row tracks a `product_id` or a `sku` — the database insists on at least one (CHECK `product_id is not null or sku is not null`) — and matching is exact: a row keyed by SKU is not found by product id.
     * @param reorderPoint The available quantity at or below which this row belongs on the replenishment worklist (GET /inventories/reorder-alerts). Null falls back to the `reorder_point_default` setting, so replenishment works without a threshold per SKU; 0 never alerts, which is how one row opts out.
     * @param sku The article number this row tracks when there is no product id, which is the normal case for an ERP-stocked catalogue. Exact match, and the identity every stock call may use instead of a uuid.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesStockUpdate(
        id: String,
        locationId: String? = null,
        metadata: Any? = null,
        productId: String? = null,
        reorderPoint: Double? = null,
        sku: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/stock/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "location_id" to locationId,
            "metadata" to metadata,
            "product_id" to productId,
            "reorder_point" to reorderPoint,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Corrects the balance of ONE stock row, and only that one. It is the row-scoped twin of POST /inventories/adjust: the row already knows its location and item, so a caller owes nothing but a SIGNED delta on `on_hand` — positive to add, negative to take away — and a reason for it. The delta is not written onto the balance either; it is booked into the movements ledger as an `adjustment` and the balance follows, which is why the answer hands back the row at its new value instead of an acknowledgement. This is the route that replaced the Cockpit's editable on_hand field.
     *
     * @param id The stock row to correct.
     * @param quantity The SIGNED correction to this row's `on_hand`: −3 writes off three, +3 finds three. A delta, not the new balance. Zero is refused (400). A correction that would take `on_hand` below zero is a 422 the database insists on; one that would take it below this row's own `reserved` is a 422 the `allow_negative_stock` setting can permit.
     * @param reason Why this row is being corrected, written onto the ledger booking. Owed unless `movement_reason_required` is 'none'.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesStockAdjust(
        id: String,
        quantity: Double,
        reason: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/stock/{id}/adjust"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "quantity" to quantity,
            "reason" to reason,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Discovery for the vocabulary routes: the enums this app publishes, each with its name, its title and its description and deliberately WITHOUT its values, so finding out what exists costs one small call and not one per vocabulary. Names: location-types, movement-types, reservation-statuses. Fetch one with GET /inventories/vocabularies/{name}; a client holding the qualified pair 'inventories.<name>' builds that URL from the pair alone.
     *
     * @return [com.revenexx.models.InventoryVocabularyIndex]
     */
    suspend fun inventoriesVocabulariesList(
    ): com.revenexx.models.InventoryVocabularyIndex {
        val apiPath = "/v1/inventories/vocabularies"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.InventoryVocabularyIndex = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.InventoryVocabularyIndex.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.InventoryVocabularyIndex::class.java,
            converter,
        )
    }


    /**
     * One vocabulary in full: every permitted value, each carrying the title and description a person reads for it and the badge tone a UI colours it with, so a client renders a status or a movement type without a hard-coded table of its own. The values are read out of the column's CHECK constraint, so the served set IS the enforced set and the two cannot drift — a value added to the constraint appears here even before anyone labels it, titled from its own key. Values come back in constraint order, which is lifecycle order for a status. 'closed' says the set is exhaustive, so a value outside it is stale data rather than a missing label. Names: location-types, movement-types, reservation-statuses.
     *
     * @param name The vocabulary name — the part after the dot in the qualified id. One of: location-types, movement-types, reservation-statuses. Anything else is a 404, so the enum is the complete set and not a suggestion.
     * @return [com.revenexx.models.Error]
     */
    suspend fun inventoriesVocabulariesGet(
        name: com.revenexx.enums.InventoriesVocabulariesGetName,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/vocabularies/{name}"
            .replace("{name}", name.value)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}