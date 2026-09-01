package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Identify by 'product_id' or 'sku' — an item without identity resolves to on_request with a per-item error rather than failing the call.
 */
data class PriceResolveItem(
    /**
     * Product to price.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Requested quantity, counted in the entry’s `unit`. It picks the tier (the highest `quantity_min` at or below it) and multiplies into `line_total`. Default 1; a non-positive value falls back to 1.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * SKU to price (alternative to product_id). Matched exactly against the entries’ own `sku`.
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