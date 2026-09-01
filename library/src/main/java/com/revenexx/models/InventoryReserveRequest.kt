package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryReserveRequest(
    /**
     * When this hold lapses. The sweeper — POST /inventories/reservations/sweep, and the 'expire-reservations' schedule that runs it every 15 minutes — releases everything past this moment exactly as a cancellation would, so an abandoned checkout stops holding stock on its own. Null means the row named no deadline: it is swept on its AGE instead once `reservation_ttl_minutes` is above 0, which is what makes turning that setting on retroactive. Omit it to let the `reservation_ttl_minutes` setting stamp one (0 — its default — means no deadline at all); send one to hold this order for a window of its own, e.g. a quote that stands until Friday.
     */
    @SerializedName("expires_at")
    var expires_at: String?,

    /**
     * The items to hold, at most 200 in one call — a whole cart in one request. The call is planned before anything is written, so either every item is placed or nothing is.
     */
    @SerializedName("items")
    var items: List<InventoryStockItem>?,

    /**
     * Where a BACKORDERED item is booked when no location holds a stock row for it at all — the last fallback, not the allocator: which location serves an item that IS in stock comes from `allocation_strategy`. Omitted, the `default_location_code` setting decides.
     */
    @SerializedName("location_code")
    var location_code: String?,

    /**
     * The order this hold belongs to. The caller supplies it — this app mints nothing — and it is the handle POST /inventories/release and POST /inventories/commit act on, so it has to be the same string the order carries elsewhere. At least one character (CHECK `length(order_ref) > 0`). Not unique: an order holds one reservation per item, and they are released or committed together. Reserving twice under the same reference ADDS holds rather than replacing them — release first if you mean to replace.
     */
    @SerializedName("order_ref")
    val order_ref: String,

    /**
     * Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Inline single-item form: how many to hold. Positive — the hold is expressed as a positive reservation, while the ledger booking it writes carries the negative.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Where the order is going. Read ONLY when the tenant's `allocation_strategy` is 'nearest' — under 'priority' or 'single_location' it is accepted and ignored, so sending it is never wrong, it is just not always heard.
     */
    @SerializedName("ship_to")
    var ship_to: InventoryShipTo?,

    /**
     * Inline single-item form: the article number to move (instead of `product_id`).
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "expires_at" to expires_at as Any,
        "items" to items?.map { it.toMap() } as Any,
        "location_code" to location_code as Any,
        "order_ref" to order_ref as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "ship_to" to ship_to?.toMap() as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryReserveRequest(
            expires_at = map["expires_at"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { InventoryStockItem.from(map = it) },
            location_code = map["location_code"] as? String,
            order_ref = map["order_ref"] as String,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            ship_to = InventoryShipTo.from(map = map["ship_to"] as Map<String, Any>),
            sku = map["sku"] as? String,
        )
    }
}