package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartStatus

/**
 * 
 */
data class CartConversion(
    /**
     * When the cart was abandoned — by hand, or by the cart-maintenance sweep. This is the only instant the abandonment funnel has, and nothing else in the platform writes it. carts.reopen clears it.
     */
    @SerializedName("abandoned_at")
    var abandoned_at: String?,

    /**
     * The sales channel the cart was opened in (web shop, app, agent desk), as a channel of the channels app. Carried to the order for attribution; nothing in this app reads it.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * The customer who owns this cart, as a contact of the customers app. Null on a guest cart: the database requires one of contact_id and session_key, never neither.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * When the cart was opened.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * ISO 4217 code the whole cart is priced in. A line added without a currency of its own inherits this one.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The cart, as every other route addresses it. Stable for the cart's whole life: a merge closes a cart, it never renumbers one.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * THE current cart of this owner — the flag carts.activate writes, and reading it back is what `?is_current=true` is for. At most one cart per owner carries it: activating one clears it on every sibling, and abandoning, ordering or merging a cart clears it. A storefront resuming a session asks for it together with contact_id or session_key.
     */
    @SerializedName("is_current")
    var is_current: Boolean?,

    /**
     * Total QUANTITY in the cart, not the number of lines: the sum of every line's quantity, rounded. Two lines of five pieces each answer 10, not 2. Recomputed by this app after every line write — a value a client sends is ignored.
     */
    @SerializedName("item_count")
    var item_count: Long?,

    /**
     * The market this cart is scoped to, stamped by the platform. It decides which market's settings apply — including the retention windows the sweep deletes on. Null on a cart that belongs to no market, which runs on the tenant baseline. Cart lines and io profiles carry no market of their own; a line's market is its cart's.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * The cart this one was merged into, written together with status 'merged'. The lines are in the target now and this is the trail back — the answer to 'where did my cart go'. Null on every cart that was never merged.
     */
    @SerializedName("merged_into_cart_id")
    var merged_into_cart_id: String?,

    /**
     * Free-form data the storefront hangs on the cart. Stored and returned verbatim; no key in here is read by this app, and none is indexed.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * What the buyer calls this cart. B2B customers keep several named carts side by side — 'Weekly order', 'Site B', 'Q3 budget' — which is what multi_cart_enabled turns on; a storefront with one cart per buyer leaves it at the default 'Cart'.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The order this cart became, in whatever numbering order management uses. Free text: this app stores what it is handed and never resolves it. Filtering on it is how a support agent gets from an order number back to the cart behind it.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * When the cart was handed to order management. Written once, with the status, and never cleared.
     */
    @SerializedName("ordered_at")
    var ordered_at: String?,

    /**
     * How price_snapshot_mode settled the two prices every line carries.
     */
    @SerializedName("pricing")
    var pricing: CartConversionPricing?,

    /**
     * What this app ASKED inventories for, and what it answered. This app holds no stock: inventories picks the location, applies the backorder policy and owns the hold's expiry.
     */
    @SerializedName("reservation")
    var reservation: CartConversionReservation?,

    /**
     * How a cart is identified BEFORE anyone logs in — the opaque key the storefront already keeps in its own session or cookie and sends back on every anonymous call. This app neither issues nor parses it; any non-empty string is a valid key, so its format is the storefront's own. On login carts.claim hands every active cart of one session_key to a contact, and this becomes null.
     */
    @SerializedName("session_key")
    var session_key: String?,

    /**
     * Where the cart stands in its lifecycle. 'active' is the only status that accepts a write of any kind. 'abandoned' is set by hand or by the cart-maintenance sweep and is the one reversible ending (carts.reopen). 'ordered' and 'merged' are final — the cart is a record now, not a workspace.
     */
    @SerializedName("status")
    var status: CartStatus?,

    /**
     * Sum of every line's line_total, in the cart's currency, net — before shipping, before tax. Recomputed after every line write, and written once more by carts.order when price_snapshot_mode settles which of a line's two prices is charged.
     */
    @SerializedName("subtotal")
    var subtotal: Double?,

    /**
     * The tenant this row belongs to, echoed by the data plane. Always the tenant the request was made for — it is not a way to reach another one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * The last time anything about this cart or its lines changed — every write path in this app stamps it. It is also what the maintenance sweep measures idleness with, which is why the abandonment sweep is the one write that deliberately does not touch it: noticing that a cart is idle must not reset the clock that decides how long it is kept.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "abandoned_at" to abandoned_at as Any,
        "channel_id" to channel_id as Any,
        "contact_id" to contact_id as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "id" to id as Any,
        "is_current" to is_current as Any,
        "item_count" to item_count as Any,
        "market_id" to market_id as Any,
        "merged_into_cart_id" to merged_into_cart_id as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "order_ref" to order_ref as Any,
        "ordered_at" to ordered_at as Any,
        "pricing" to pricing?.toMap() as Any,
        "reservation" to reservation?.toMap() as Any,
        "session_key" to session_key as Any,
        "status" to status?.value as Any,
        "subtotal" to subtotal as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartConversion(
            abandoned_at = map["abandoned_at"] as? String,
            channel_id = map["channel_id"] as? String,
            contact_id = map["contact_id"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            id = map["id"] as? String,
            is_current = map["is_current"] as? Boolean,
            item_count = (map["item_count"] as? Number)?.toLong(),
            market_id = map["market_id"] as? String,
            merged_into_cart_id = map["merged_into_cart_id"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            order_ref = map["order_ref"] as? String,
            ordered_at = map["ordered_at"] as? String,
            pricing = CartConversionPricing.from(map = map["pricing"] as Map<String, Any>),
            reservation = CartConversionReservation.from(map = map["reservation"] as Map<String, Any>),
            session_key = map["session_key"] as? String,
            status = CartStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            subtotal = (map["subtotal"] as? Number)?.toDouble(),
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}