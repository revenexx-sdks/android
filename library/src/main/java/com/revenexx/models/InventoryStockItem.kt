package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * An item and its quantity: 'product_id' or 'sku'.
 */
data class InventoryStockItem(
    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("quantity")
    val quantity: Double,

    /**
     * 
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