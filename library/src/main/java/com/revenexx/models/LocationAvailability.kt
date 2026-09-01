package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What one location holds of this item. Only enabled locations appear, and only those with a stock row for the item — a location that has never held it is absent rather than zero.
 */
data class LocationAvailability(
    /**
     * on_hand − reserved at this location — what this one place can still promise.
     */
    @SerializedName("available")
    var available: Double?,

    /**
     * The location CODE (`locations.code`) — the same value `location_code` takes in a request. Falls back to the raw location id in the rare case where the location row disappeared between the two reads.
     */
    @SerializedName("location")
    var location: String?,

    /**
     * Physically at this location, promised units included.
     */
    @SerializedName("on_hand")
    var on_hand: Double?,

    /**
     * Held for orders at this location.
     */
    @SerializedName("reserved")
    var reserved: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "available" to available as Any,
        "location" to location as Any,
        "on_hand" to on_hand as Any,
        "reserved" to reserved as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LocationAvailability(
            available = (map["available"] as? Number)?.toDouble(),
            location = map["location"] as? String,
            on_hand = (map["on_hand"] as? Number)?.toDouble(),
            reserved = (map["reserved"] as? Number)?.toDouble(),
        )
    }
}