package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A matrix tier of the new set (from_value → price) — null falls back to 0, position derives from the array order.
 */
data class ShippingRateTierReplaceItem(
    /**
     * Tier threshold (default 0) — the tier with the highest from_value at or below the measured value wins.
     */
    @SerializedName("from_value")
    var from_value: Double?,

    /**
     * Ignored — derived from the array index.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Price of this tier (default 0).
     */
    @SerializedName("price")
    var price: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "from_value" to from_value as Any,
        "position" to position as Any,
        "price" to price as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRateTierReplaceItem(
            from_value = (map["from_value"] as? Number)?.toDouble(),
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
        )
    }
}