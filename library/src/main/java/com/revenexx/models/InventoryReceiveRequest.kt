package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryReceiveRequest(
    /**
     * The goods that arrived, at most 200 in one call — a delivery, a production batch, an opening balance.
     */
    @SerializedName("items")
    var items: List<InventoryStockItem>?,

    /**
     * Which location took the delivery. Omitted, the `default_location_code` setting decides; a code no location carries is answered 400 rather than booked somewhere else.
     */
    @SerializedName("location_code")
    var location_code: String?,

    /**
     * Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Inline single-item form: how many arrived. Positive.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * What the ledger should record about this receipt — a delivery note number, a production order. Owed only when `movement_reason_required` is 'all'; the contract does not require it, because whether it is owed is the tenant's setting and not this route's rule.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * Inline single-item form: the article number to move (instead of `product_id`).
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items?.map { it.toMap() } as Any,
        "location_code" to location_code as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "reason" to reason as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryReceiveRequest(
            items = (map["items"] as List<Map<String, Any>>).map { InventoryStockItem.from(map = it) },
            location_code = map["location_code"] as? String,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            reason = map["reason"] as? String,
            sku = map["sku"] as? String,
        )
    }
}