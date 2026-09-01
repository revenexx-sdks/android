package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One item and its SIGNED correction: 'product_id' or 'sku', plus a non-zero delta.
 */
data class InventoryAdjustItem(
    /**
     * The product to move, as the products app knows it. Give this OR `sku` — an item that names neither is answered 400. Matching is exact: a stock row keyed by SKU is not found by product id.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The SIGNED correction to `on_hand`: −3 writes off three, +3 finds three. It is a delta, not the new balance. Zero is refused (400) because a correction of nothing is a mistake, not a booking — the rule is the handler's, not a database CHECK, which is why it is stated here rather than declared as a bound.
     */
    @SerializedName("quantity")
    val quantity: Double,

    /**
     * The article number to move, when the item has no product id. Give this OR `product_id`.
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryAdjustItem(
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as Number).toDouble(),
            sku = map["sku"] as? String,
        )
    }
}