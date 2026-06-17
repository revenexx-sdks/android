package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class StockMovement(
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
    @SerializedName("location_id")
    var location_id: String?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("order_ref")
    var order_ref: String?,

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
    @SerializedName("reason")
    var reason: String?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * 
     */
    @SerializedName("type")
    var type: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "location_id" to location_id as Any,
        "metadata" to metadata as Any,
        "order_ref" to order_ref as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "reason" to reason as Any,
        "sku" to sku as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = StockMovement(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            location_id = map["location_id"] as? String,
            metadata = map["metadata"] as? Any,
            order_ref = map["order_ref"] as? String,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            reason = map["reason"] as? String,
            sku = map["sku"] as? String,
            type = map["type"] as? String,
        )
    }
}