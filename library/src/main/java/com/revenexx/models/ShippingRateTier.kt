package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ShippingRateTier(
    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("from_value")
    var from_value: Double?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("method_id")
    var method_id: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "from_value" to from_value as Any,
        "id" to id as Any,
        "method_id" to method_id as Any,
        "position" to position as Any,
        "price" to price as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRateTier(
            created_at = map["created_at"] as? String,
            from_value = (map["from_value"] as? Number)?.toDouble(),
            id = map["id"] as? String,
            method_id = map["method_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
        )
    }
}