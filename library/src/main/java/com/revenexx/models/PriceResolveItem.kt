package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Identify by 'product_id' or 'sku' — an item without identity resolves to on_request with a per-item error.
 */
data class PriceResolveItem(
    /**
     * Product to price.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Requested quantity for tier selection and line_total (default 1; non-positive values fall back to 1).
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * SKU to price (alternative to product_id).
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
        ) = PriceResolveItem(
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
        )
    }
}