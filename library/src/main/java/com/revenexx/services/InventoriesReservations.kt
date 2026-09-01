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
 * Stock promised to an order, and the three ways that promise ends. A reservation is order-scoped: POST /inventories/reserve creates it against an `order_ref`, and nothing else does — there is no create, update or delete route here, because the lifecycle IS the API. Reserving raises `reserved` on a stock row and leaves `on_hand` alone (the goods are still in the building); committing ships them and takes them out of both; releasing gives them back; and the sweep is a release on a timer, for the checkouts nobody finished. `reserved` is the only reason a stock row's two numbers ever differ, which is what makes this a group and not a footnote to the stock one. Which location a hold lands at is not decided here — that is the tenant's allocation strategy choosing between locations, and it is described with them.
 */
class InventoriesReservations(client: Client) : Service(client) {

    /**
     * Call this when the goods leave the building, and not before. Reserving only promised them — `reserved` went up and `on_hand` did not move, because the stock was still on the shelf; committing is the moment they are gone, so it lowers BOTH on each stock row and writes one `shipment` booking per hold, with a SIGNED negative quantity, as the ledger's record that they left. It takes the whole `order_ref` and every hold still active on it: there is no partial commit and no per-line id, so a part shipment means reserving the parts separately in the first place. It is also final — 'committed' ends the lifecycle and nothing moves a hold out of it, so goods coming back are POST /inventories/restock (a new receipt), never an undo of this. An order with nothing active is a 422 rather than a quiet zero, because it means the hold was already released or already shipped; /release answers the same situation with a 200 on purpose, since cancelling twice is harmless and shipping twice is not.
     *
     * @param orderRef The order this hold belongs to. The caller supplies it — this app mints nothing — and it is the handle POST /inventories/release and POST /inventories/commit act on, so it has to be the same string the order carries elsewhere. At least one character (CHECK `length(order_ref) > 0`). Not unique: an order holds one reservation per item, and they are released or committed together. Every ACTIVE hold under this reference ships: `on_hand` and `reserved` both fall and a `shipment` booking is written for each. Unlike release, committing an order that has nothing active is a 422 — it means the hold was already released or already shipped, and shipping twice is worth saying out loud.
     * @return [com.revenexx.models.Error]
     */
    suspend fun inventoriesCommit(
        orderRef: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/commit"

        val apiParams = mutableMapOf<String, Any?>(
            "order_ref" to orderRef,
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
     * The cancellation end of the reserve → commit | release lifecycle: it takes an `order_ref`, ends every hold still active on it, gives the stock back and writes a 'release' booking for each one, exactly like the expiry sweeper. Idempotent: an order with nothing active answers released:0 — which is why it is a 200 and not the 422 commit answers.
     *
     * @param orderRef The order this hold belongs to. The caller supplies it — this app mints nothing — and it is the handle POST /inventories/release and POST /inventories/commit act on, so it has to be the same string the order carries elsewhere. At least one character (CHECK `length(order_ref) > 0`). Not unique: an order holds one reservation per item, and they are released or committed together. Every ACTIVE hold under this reference is given back; ones already committed or released are left alone. A reference no reservation carries releases nothing and answers `released: 0` — not an error, which is what makes a retried cancellation safe.
     * @return [com.revenexx.models.Error]
     */
    suspend fun inventoriesRelease(
        orderRef: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/release"

        val apiParams = mutableMapOf<String, Any?>(
            "order_ref" to orderRef,
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
     * A reservation is stock promised to an `order_ref`. It is created only by POST /inventories/reserve and moved only by /commit, /release and the expiry sweep — there is no create, update or delete route, because the lifecycle IS the API. Only an 'active' hold counts towards a stock row's `reserved`; 'released' and 'committed' rows stay for the audit trail and hold nothing. This is the answer to "what is this order actually holding" (`?order_ref=…`) and to "what is holding this stock" (`?status=active&location_id=…`) — the second is the only way to see WHY a row's `reserved` is what it is, since a stock row reports the total and never who asked for it. `expires_at` filters on an exact timestamp and not a range, so this cannot answer "what expires today"; the deadline is acted on by POST /inventories/reservations/sweep, not by reading it here.
     *
     * @param limit Page size (default 50, max 200). A larger value is clamped rather than refused.
     * @param offset Row offset for pagination (default 0). Page with `page.total` and `page.hasMore`.
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc' — a bare column sorts ascending. The column has to be one this entity has; anything else is refused with 400.
     * @param id Exact-match filter on `id`. The row's own id, generated by the database.
     * @param locationId Exact-match filter on `location_id`. The holds served by one location.
     * @param productId Exact-match filter on `product_id`. The product being held, copied from the reserve call.
     * @param sku Exact-match filter on `sku`. The article number being held, copied from the reserve call.
     * @param quantity Exact-match filter on `quantity`. How much is being held, ALWAYS POSITIVE — the database CHECK is `quantity > 0`, because a hold of nothing is not a hold.
     * @param orderRef Exact-match filter on `order_ref`. Every hold an order carries. This is the lookup POST /inventories/release and /commit act on.
     * @param status Exact-match filter on `status`. Where the hold stands in the reserve → commit | release lifecycle. Only 'active' counts towards `reserved`, so `?status=active` is the set that is really holding stock.
     * @param expiresAt Exact-match filter on `expires_at`. Exact deadline, not a range — this cannot answer "what expires today". The sweeper is what acts on deadlines (POST /inventories/reservations/sweep).
     * @param metadata Exact-match filter on `metadata`. Free-form, and one key this app writes itself: `backordered` — how much of this hold was not covered by stock on hand when it was taken. The WHOLE jsonb document is compared, serialized as JSON — this is equality, not a key lookup or a containment query, and a value that does not parse is answered 400.
     * @param createdAt Exact-match filter on `created_at`. When the row was created.
     * @param updatedAt Exact-match filter on `updated_at`. When the hold last changed — in practice, when it moved out of `active`..
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesReservationsList(
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
        id: String? = null,
        locationId: String? = null,
        productId: String? = null,
        sku: String? = null,
        quantity: Double? = null,
        orderRef: String? = null,
        status: com.revenexx.enums.InventoriesReservationsListStatus? = null,
        expiresAt: String? = null,
        metadata: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/reservations"

        val apiParams = mutableMapOf<String, Any?>(
            "limit" to limit,
            "offset" to offset,
            "order" to order,
            "id" to id,
            "location_id" to locationId,
            "product_id" to productId,
            "sku" to sku,
            "quantity" to quantity,
            "order_ref" to orderRef,
            "status" to status,
            "expires_at" to expiresAt,
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
     * The expiry sweeper, also run by the 'expire-reservations' schedule every 15 minutes. Releases reservations past their own expires_at and — once reservation_ttl_minutes is above 0 — reservations older than that lifetime which never carried a deadline. Each release gives the stock back and writes a 'release' booking, exactly like a cancellation. Idempotent: a second run finds nothing.
     *
     * @param data Request body
     * @return [com.revenexx.models.ReservationSweepResult]
     */
    suspend fun inventoriesReservationsSweep(
        data: Any,
    ): com.revenexx.models.ReservationSweepResult {
        val apiPath = "/v1/inventories/reservations/sweep"

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ReservationSweepResult = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ReservationSweepResult.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ReservationSweepResult::class.java,
            converter,
        )
    }


    /**
     * A reservation is stock promised to an `order_ref`. It is created only by POST /inventories/reserve and moved only by /commit, /release and the expiry sweep — there is no create, update or delete route, because the lifecycle IS the API. Only an 'active' hold counts towards a stock row's `reserved`; 'released' and 'committed' rows stay for the audit trail and hold nothing. One hold, with the three facts that are not on the order it belongs to: which location it was allocated to, when it expires, and — in `metadata.backordered` — how much of it was never covered by stock, which is how a promise made under a permissive backorder policy stays visible afterwards. The id is for reading only. Every transition acts on the whole `order_ref` (/commit, /release, the sweep), so there is no route that takes this id and no way to release one line of an order on its own.
     *
     * @param id The reservation.
     * @return [com.revenexx.models.Error]
     */
    suspend fun inventoriesReservationsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/reservations/{id}"
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
     * Takes a hold against an `order_ref`, and plans the whole call before writing anything, so a reservation that cannot be satisfied changes nothing. WHICH location serves an item is not the caller's to choose: the tenant's allocation_strategy decides it ('priority', walking the enabled locations by their priority; 'nearest', matching ship_to against a location's country; or 'single_location' for the whole order); backorder_policy decides what happens when none can — refuse (422), or reserve anyway and let availability go negative. expires_at defaults from reservation_ttl_minutes and the sweeper enforces it.
     *
     * @param orderRef The order this hold belongs to. The caller supplies it — this app mints nothing — and it is the handle POST /inventories/release and POST /inventories/commit act on, so it has to be the same string the order carries elsewhere. At least one character (CHECK `length(order_ref) > 0`). Not unique: an order holds one reservation per item, and they are released or committed together. Reserving twice under the same reference ADDS holds rather than replacing them — release first if you mean to replace.
     * @param expiresAt When this hold lapses. The sweeper — POST /inventories/reservations/sweep, and the 'expire-reservations' schedule that runs it every 15 minutes — releases everything past this moment exactly as a cancellation would, so an abandoned checkout stops holding stock on its own. Null means the row named no deadline: it is swept on its AGE instead once `reservation_ttl_minutes` is above 0, which is what makes turning that setting on retroactive. Omit it to let the `reservation_ttl_minutes` setting stamp one (0 — its default — means no deadline at all); send one to hold this order for a window of its own, e.g. a quote that stands until Friday.
     * @param items The items to hold, at most 200 in one call — a whole cart in one request. The call is planned before anything is written, so either every item is placed or nothing is.
     * @param locationCode Where a BACKORDERED item is booked when no location holds a stock row for it at all — the last fallback, not the allocator: which location serves an item that IS in stock comes from `allocation_strategy`. Omitted, the `default_location_code` setting decides.
     * @param productId Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     * @param quantity Inline single-item form: how many to hold. Positive — the hold is expressed as a positive reservation, while the ledger booking it writes carries the negative.
     * @param shipTo Where the order is going. Read ONLY when the tenant's `allocation_strategy` is 'nearest' — under 'priority' or 'single_location' it is accepted and ignored, so sending it is never wrong, it is just not always heard.
     * @param sku Inline single-item form: the article number to move (instead of `product_id`).
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun inventoriesReserve(
        orderRef: String,
        expiresAt: String? = null,
        items: List<com.revenexx.models.InventoryStockItem>? = null,
        locationCode: String? = null,
        productId: String? = null,
        quantity: Double? = null,
        shipTo: Any? = null,
        sku: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/inventories/reserve"

        val apiParams = mutableMapOf<String, Any?>(
            "expires_at" to expiresAt,
            "items" to items,
            "location_code" to locationCode,
            "order_ref" to orderRef,
            "product_id" to productId,
            "quantity" to quantity,
            "ship_to" to shipTo,
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


}