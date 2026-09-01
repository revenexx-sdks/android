package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One item and how much of it: 'product_id' or 'sku', plus a positive quantity.
 */
data class InventoryStockItem(
    /**
     * The product to move, as the products app knows it. Give this OR `sku` — an item that names neither is answered 400. Matching is exact: a stock row keyed by SKU is not found by product id.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How many units this booking moves. Always POSITIVE here — the direction is the route (receive adds, reserve holds, restock returns), not the sign. Zero or a negative number is answered 400; a signed correction is what POST /inventories/adjust is for.
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
        ) = InventoryStockItem(
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as Number).toDouble(),
            sku = map["sku"] as? String,
        )
    }
}