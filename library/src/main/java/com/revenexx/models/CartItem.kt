package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartItem(
    /**
     * 
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * 
     */
    @SerializedName("configuration")
    var configuration: Any?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("line_total")
    var line_total: Double?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * 
     */
    @SerializedName("snapshot")
    var snapshot: Any?,

    /**
     * 
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * 
     */
    @SerializedName("type")
    var type: String?,

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

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cart_id" to cart_id as Any,
        "configuration" to configuration as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "id" to id as Any,
        "line_total" to line_total as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
        "snapshot" to snapshot as Any,
        "tax_rate" to tax_rate as Any,
        "type" to type as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartItem(
            cart_id = map["cart_id"] as? String,
            configuration = map["configuration"] as? Any,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            id = map["id"] as? String,
            line_total = (map["line_total"] as? Number)?.toDouble(),
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            snapshot = map["snapshot"] as? Any,
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            type = map["type"] as? String,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
        )
    }
}