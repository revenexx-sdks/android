package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class StockLevel(
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
    @SerializedName("on_hand")
    var on_hand: Double?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("reorder_point")
    var reorder_point: Double?,

    /**
     * 
     */
    @SerializedName("reserved")
    var reserved: Double?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "location_id" to location_id as Any,
        "metadata" to metadata as Any,
        "on_hand" to on_hand as Any,
        "product_id" to product_id as Any,
        "reorder_point" to reorder_point as Any,
        "reserved" to reserved as Any,
        "sku" to sku as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = StockLevel(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            location_id = map["location_id"] as? String,
            metadata = map["metadata"] as? Any,
            on_hand = (map["on_hand"] as? Number)?.toDouble(),
            product_id = map["product_id"] as? String,
            reorder_point = (map["reorder_point"] as? Number)?.toDouble(),
            reserved = (map["reserved"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}