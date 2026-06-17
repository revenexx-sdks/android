package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A stock row tracks an item: 'product_id' or 'sku'.
 */
data class StockLevelCreateRequest(
    /**
     * Owning location.
     */
    @SerializedName("location_id")
    val location_id: String,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Physical stock (default 0).
     */
    @SerializedName("on_hand")
    var on_hand: Double?,

    /**
     * Tracked product.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("reorder_point")
    var reorder_point: Double?,

    /**
     * Reserved stock (default 0) — normally managed by reserve/release/commit.
     */
    @SerializedName("reserved")
    var reserved: Double?,

    /**
     * Tracked SKU (alternative to product_id).
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "location_id" to location_id as Any,
        "metadata" to metadata as Any,
        "on_hand" to on_hand as Any,
        "product_id" to product_id as Any,
        "reorder_point" to reorder_point as Any,
        "reserved" to reserved as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = StockLevelCreateRequest(
            location_id = map["location_id"] as String,
            metadata = map["metadata"] as? Any,
            on_hand = (map["on_hand"] as? Number)?.toDouble(),
            product_id = map["product_id"] as? String,
            reorder_point = (map["reorder_point"] as? Number)?.toDouble(),
            reserved = (map["reserved"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
        )
    }
}