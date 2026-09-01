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
 * The lines inside one cart, always addressed through the cart that owns them (`/carts/{cart_id}/items`) — a line is never reachable on its own, and an id from another cart answers 404 rather than the row. A line is a catalogue product, a configured product or a free position, and it carries its price twice: the working `unit_price` and the `snapshot` the buyer was shown. Adding the same article at the same price folds into the line that is already there instead of opening a second one; a configured line always stands alone. Every write here recomputes the owning cart's `item_count` and `subtotal`, so a cart can never disagree with its own lines.
 */
class CartsItems(client: Client) : Service(client) {

    /**
     * The array is still called 'items'; the response also carries 'page' and 'filter' like every other list, and an unknown cart_id answers 404 instead of an empty page. A cart with more lines than the page size is not silently truncated — 'page.hasMore' says so. Lines come back in position order unless 'order' says otherwise.
     *
     * @param cartId The cart the line belongs to, by its id. An id no cart in this tenant has answers 404 rather than an empty list, so a wrong cart is never mistaken for an empty one.
     * @param id One line, in list form.
     * @param type Product lines, configured lines or custom lines.
     * @param productId Lines for one catalogue product.
     * @param sku Exact article number — the join every ERP integration makes. Not a search: no prefix, no wildcard.
     * @param name Exact line name. Not a search.
     * @param quantity Exact quantity — equality, so it matches a line of exactly this many, never 'at least'.
     * @param unit Lines counted in one unit ('pcs', 'm').
     * @param unitPrice Exact unit price — the lines still sitting at one particular number after a repricing run.
     * @param currency Lines priced in one currency — normally the cart's, so this earns its place only where a cart mixes them.
     * @param taxRate Lines at one VAT rate.
     * @param lineTotal Exact line total. Equality only — there is no range form, so this finds `0` and little else.
     * @param position The line at one position.
     * @param createdAt Exact instant, not a range.
     * @param updatedAt Exact instant, not a range.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsItemsList(
        cartId: String,
        id: String? = null,
        type: com.revenexx.enums.CartItemType? = null,
        productId: String? = null,
        sku: String? = null,
        name: String? = null,
        quantity: Double? = null,
        unit: String? = null,
        unitPrice: Double? = null,
        currency: String? = null,
        taxRate: Double? = null,
        lineTotal: Double? = null,
        position: Long? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/{cart_id}/items"
            .replace("{cartId}", cartId)

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "type" to type,
            "product_id" to productId,
            "sku" to sku,
            "name" to name,
            "quantity" to quantity,
            "unit" to unit,
            "unit_price" to unitPrice,
            "currency" to currency,
            "tax_rate" to taxRate,
            "line_total" to lineTotal,
            "position" to position,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
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
     * Adds one line to an ACTIVE cart — the add-to-basket call. `name` or `sku` is required (a line sent with only a SKU takes the SKU as its name, so a line always has something to show) and `quantity` must be greater than zero; everything else defaults, including the currency, which falls back to the cart's. The one thing that surprises a caller: a plain product line with the same product/sku AND the same `unit_price` as a line already in the cart does not open a second row — its quantity is added to that line, and the 201 names a row that already existed. Price is part of that identity on purpose, so a changed price never averages into an old line. A configured or custom line always stands alone. The cart's `item_count` (the sum of QUANTITIES) and `subtotal` are recomputed before the answer, and `max_items_per_cart` / `max_quantity_per_line` are checked on the RESULT of the merge (422), so ten calls of one piece cannot walk past a limit one call of ten would hit.
     *
     * @param cartId The cart the line belongs to, by its id. An id no cart in this tenant has answers 404 rather than an empty list, so a wrong cart is never mistaken for an empty one.
     * @param configuration What was configured on this line, in the configurator's own vocabulary — this app stores it and reads nothing out of it. Its mere PRESENCE is behaviour: a line that carries a configuration never merges with another, because two differently configured units of the same article are not one line. Keys are the configurator's; the example is one shape, not the shape.
     * @param currency ISO 4217 code. Defaults to the cart's currency.
     * @param metadata Free-form data the storefront hangs on the line. Stored and returned verbatim; no key in here is read by this app.
     * @param name What the line reads as on the cart page. Falls back to 'sku' when omitted, so a line always has something to show.
     * @param position Sort order within the cart, ascending. Default 0 when adding a line; in a bulk replace the payload order fills it in.
     * @param productId The catalogue product, when the line comes from one. Part of the merge identity: same product, same price, one line.
     * @param quantity How much of it — default 1. Fractional is legal (2.5 m of cable); zero and negative are not. On a plain product line that merges into an existing one, this is ADDED to what is already there, and max_quantity_per_line is checked on the result.
     * @param sku The article number, exactly as the merchant knows it. Free text — this app does not resolve it against the catalogue — and part of the merge identity together with product_id and unit_price. The example only shows the shape of a real article number; nothing here enforces one.
     * @param snapshot The product as the buyer was shown it when this line was added — the cart's own copy, so it stays honest when the catalogue moves underneath it. Free-form apart from the price: conversion reads `unit_price` (or `price` as a fallback) and nothing else. A snapshot without a readable price leaves the line alone in both price modes, which is deliberate — a missing snapshot must never be read as "free".
     * @param taxRate VAT percent for this line, as a number (19 means 19 %). Stored for the order to use — no total in this app includes tax.
     * @param type Line type (default 'product'). Plain product lines merge by product+price; configurations always stand alone.
     * @param unit The unit the quantity is counted in. Display and ERP hand-over only — this app converts nothing.
     * @param unitPrice Net price of one unit — line_total is always derived from it, never sent. Part of the merge identity: the same article at a different price opens a new line rather than averaging into the old one.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsItemsCreate(
        cartId: String,
        configuration: Any? = null,
        currency: String? = null,
        metadata: Any? = null,
        name: String? = null,
        position: Long? = null,
        productId: String? = null,
        quantity: Double? = null,
        sku: String? = null,
        snapshot: Any? = null,
        taxRate: Double? = null,
        type: com.revenexx.enums.CartItemType? = null,
        unit: String? = null,
        unitPrice: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/{cart_id}/items"
            .replace("{cartId}", cartId)

        val apiParams = mutableMapOf<String, Any?>(
            "configuration" to configuration,
            "currency" to currency,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "product_id" to productId,
            "quantity" to quantity,
            "sku" to sku,
            "snapshot" to snapshot,
            "tax_rate" to taxRate,
            "type" to type,
            "unit" to unit,
            "unit_price" to unitPrice,
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
     * Set semantics: the payload IS the cart. Every existing line is dropped and the payload is written in its place, so a line left out of the array is a line removed — this is the storefront sync, not a bulk add, and carts.items.create is what adds. Lines are numbered by their place in the array unless they carry their own `position`, and nothing merges: two identical lines in one payload stay two rows. The limits are checked against the payload BEFORE a single existing line is destroyed, so a sync refused with 422 leaves the cart exactly as it was. The cart must be active, and its totals are recomputed before the answer.
     *
     * @param cartId The cart the line belongs to, by its id. An id no cart in this tenant has answers 404 rather than an empty list, so a wrong cart is never mistaken for an empty one.
     * @param items The complete new item set (set semantics).
     * @return [com.revenexx.models.Error]
     */
    suspend fun cartsItemsReplace(
        cartId: String,
        items: List<com.revenexx.models.CartItemCreateRequest<Any>>,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/{cart_id}/items"
            .replace("{cartId}", cartId)

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
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
     * Removes one line from an ACTIVE cart and recomputes the owning cart's `item_count` and `subtotal` before answering. This is how a quantity reaches zero: `quantity` is constrained to be greater than zero, so "none of it" is a DELETE and never an update to 0. The cart in the path is part of the address — a line belonging to a different cart answers 404 and is left where it is. Deleting the last line leaves an empty cart, not a deleted one; the cart itself goes through carts.delete, which takes every line with it in one call.
     *
     * @param cartId The cart the line belongs to, by its id. An id no cart in this tenant has answers 404 rather than an empty list, so a wrong cart is never mistaken for an empty one.
     * @param id The line, by its id. The cart in the path is checked too: a line that belongs to a different cart answers 404, so an id guessed from another cart never resolves here.
     * @return [com.revenexx.models.Error]
     */
    suspend fun cartsItemsDelete(
        cartId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/{cart_id}/items/{id}"
            .replace("{cartId}", cartId)
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
     * One line, addressed through the cart that owns it. Both ids are checked, not just the line's: a line that exists but belongs to a different cart answers 404 rather than the row, so an id copied out of another cart never resolves here and a caller can trust that what came back is a line of the cart they asked about. The line carries both of its prices — the working `unit_price`, which a resync or a repricing job may have moved, and the `snapshot` the buyer was shown when the line was added — and its own `line_total`, which is always quantity × unit_price and never what a payload claimed. To read a whole cart's lines, list them: this route is for one known line.
     *
     * @param cartId The cart the line belongs to, by its id. An id no cart in this tenant has answers 404 rather than an empty list, so a wrong cart is never mistaken for an empty one.
     * @param id The line, by its id. The cart in the path is checked too: a line that belongs to a different cart answers 404, so an id guessed from another cart never resolves here.
     * @return [com.revenexx.models.Error]
     */
    suspend fun cartsItemsGet(
        cartId: String,
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/{cart_id}/items/{id}"
            .replace("{cartId}", cartId)
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
     * Changes one line of an ACTIVE cart — the quantity stepper on the cart page, and the route a repricing job writes through. The fields sent are merged onto the stored line and the whole line is validated again, so `quantity` must still be greater than zero and `type` still one of the three. `line_total` is not settable: it is recomputed as quantity × unit_price, and the cart's `item_count` and `subtotal` follow before the answer. What it will NOT do is merge — only carts.items.create folds one line into another, so giving this line the same product and price as a sibling leaves two rows standing, and the next add joins whichever it matches. `max_quantity_per_line` is enforced on the result (422). A quantity of zero is not the way to remove a line; the delete is.
     *
     * @param cartId The cart the line belongs to, by its id. An id no cart in this tenant has answers 404 rather than an empty list, so a wrong cart is never mistaken for an empty one.
     * @param id The line, by its id. The cart in the path is checked too: a line that belongs to a different cart answers 404, so an id guessed from another cart never resolves here.
     * @param configuration What was configured on this line, in the configurator's own vocabulary — this app stores it and reads nothing out of it. Its mere PRESENCE is behaviour: a line that carries a configuration never merges with another, because two differently configured units of the same article are not one line. Keys are the configurator's; the example is one shape, not the shape.
     * @param currency ISO 4217 code. Defaults to the cart's currency.
     * @param metadata Free-form data the storefront hangs on the line. Stored and returned verbatim; no key in here is read by this app.
     * @param name What the line reads as on the cart page. Falls back to 'sku' when omitted, so a line always has something to show.
     * @param position Sort order within the cart, ascending. Default 0 when adding a line; in a bulk replace the payload order fills it in.
     * @param productId The catalogue product, when the line comes from one. Part of the merge identity: same product, same price, one line.
     * @param quantity How much of it — default 1. Fractional is legal (2.5 m of cable); zero and negative are not. On a plain product line that merges into an existing one, this is ADDED to what is already there, and max_quantity_per_line is checked on the result.
     * @param sku The article number, exactly as the merchant knows it. Free text — this app does not resolve it against the catalogue — and part of the merge identity together with product_id and unit_price. The example only shows the shape of a real article number; nothing here enforces one.
     * @param snapshot The product as the buyer was shown it when this line was added — the cart's own copy, so it stays honest when the catalogue moves underneath it. Free-form apart from the price: conversion reads `unit_price` (or `price` as a fallback) and nothing else. A snapshot without a readable price leaves the line alone in both price modes, which is deliberate — a missing snapshot must never be read as "free".
     * @param taxRate VAT percent for this line, as a number (19 means 19 %). Stored for the order to use — no total in this app includes tax.
     * @param type Line type (default 'product'). Plain product lines merge by product+price; configurations always stand alone.
     * @param unit The unit the quantity is counted in. Display and ERP hand-over only — this app converts nothing.
     * @param unitPrice Net price of one unit — line_total is always derived from it, never sent. Part of the merge identity: the same article at a different price opens a new line rather than averaging into the old one.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun cartsItemsUpdate(
        cartId: String,
        id: String,
        configuration: Any? = null,
        currency: String? = null,
        metadata: Any? = null,
        name: String? = null,
        position: Long? = null,
        productId: String? = null,
        quantity: Double? = null,
        sku: String? = null,
        snapshot: Any? = null,
        taxRate: Double? = null,
        type: com.revenexx.enums.CartItemType? = null,
        unit: String? = null,
        unitPrice: Double? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/carts/{cart_id}/items/{id}"
            .replace("{cartId}", cartId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "configuration" to configuration,
            "currency" to currency,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "product_id" to productId,
            "quantity" to quantity,
            "sku" to sku,
            "snapshot" to snapshot,
            "tax_rate" to taxRate,
            "type" to type,
            "unit" to unit,
            "unit_price" to unitPrice,
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


}