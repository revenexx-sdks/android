package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PriceEntry(
    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("price_list_id")
    var price_list_id: String?,

    /**
     * 
     */
    @SerializedName("price_type")
    var price_type: String?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("quantity_min")
    var quantity_min: Double?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * 
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * 
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * 
     */
    @SerializedName("valid_until")
    var valid_until: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "metadata" to metadata as Any,
        "price_list_id" to price_list_id as Any,
        "price_type" to price_type as Any,
        "product_id" to product_id as Any,
        "quantity_min" to quantity_min as Any,
        "sku" to sku as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "updated_at" to updated_at as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntry(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            metadata = map["metadata"] as? Any,
            price_list_id = map["price_list_id"] as? String,
            price_type = map["price_type"] as? String,
            product_id = map["product_id"] as? String,
            quantity_min = (map["quantity_min"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}