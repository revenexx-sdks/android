package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEntryType

/**
 * Partial update — omitted fields keep their current value.
 */
data class PriceEntryUpdateRequest(
    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Default 'standard'; 'on_request' is the explicit no-price marker — it stops resolution and answers "price on request".
     */
    @SerializedName("price_type")
    var price_type: PriceEntryType?,

    /**
     * Priced product.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Tier threshold (Staffelpreis): this price applies from this quantity (default 1).
     */
    @SerializedName("quantity_min")
    var quantity_min: Double?,

    /**
     * Priced SKU (alternative to product_id).
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * 
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * Per-unit price (default 0).
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * Per-entry validity start (promo prices).
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * Per-entry validity end.
     */
    @SerializedName("valid_until")
    var valid_until: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "metadata" to metadata as Any,
        "price_type" to price_type?.value as Any,
        "product_id" to product_id as Any,
        "quantity_min" to quantity_min as Any,
        "sku" to sku as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntryUpdateRequest(
            metadata = map["metadata"] as? Any,
            price_type = PriceEntryType.values().find { it.value == (map["price_type"] as? String) } ?: null,
            product_id = map["product_id"] as? String,
            quantity_min = (map["quantity_min"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}