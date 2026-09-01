package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryRestockRequest(
    /**
     * The goods that came back, at most 200 in one call. Whether they rejoin sellable stock is `restock`, not this list.
     */
    @SerializedName("items")
    var items: List<InventoryStockItem>?,

    /**
     * Where the goods came back to — a returns warehouse is a location like any other. Omitted, the `default_location_code` setting decides.
     */
    @SerializedName("location_code")
    var location_code: String?,

    /**
     * The order the goods came back from. It is written onto the ledger booking, so the return shows up in that order's stock history next to its reserve and shipment — no reservation is touched by it.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Inline single-item form: how many came back. Positive.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Why the goods came back — 'wrong size', 'damaged on arrival'. Owed only when `movement_reason_required` is 'all'.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * Do these goods rejoin SELLABLE stock? A merchant decision, not a fact: apparel usually restocks, hygiene articles never do, many merchants inspect first. Omit it to follow the `restock_on_return_default` setting. `false` answers `restocked: false`, moves nothing and books NOTHING — there is no movement to write, because no stock moved, and that is the branch that makes this route a 200 while its sibling `receive` is a 201.
     */
    @SerializedName("restock")
    var restock: Boolean?,

    /**
     * Inline single-item form: the article number to move (instead of `product_id`).
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items?.map { it.toMap() } as Any,
        "location_code" to location_code as Any,
        "order_ref" to order_ref as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "reason" to reason as Any,
        "restock" to restock as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryRestockRequest(
            items = (map["items"] as List<Map<String, Any>>).map { InventoryStockItem.from(map = it) },
            location_code = map["location_code"] as? String,
            order_ref = map["order_ref"] as? String,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            reason = map["reason"] as? String,
            restock = map["restock"] as? Boolean,
            sku = map["sku"] as? String,
        )
    }
}