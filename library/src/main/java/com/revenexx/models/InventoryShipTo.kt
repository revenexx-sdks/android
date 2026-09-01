package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Where the order is going. Read ONLY when the tenant's `allocation_strategy` is 'nearest' — under 'priority' or 'single_location' it is accepted and ignored, so sending it is never wrong, it is just not always heard.
 */
data class InventoryShipTo(
    /**
     * ISO country code of the delivery address. Locations whose `address.country` matches are tried before the rest, which is what stops a German order pulling from an overseas warehouse that merely sorts first.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * Prefer this location above everything else — a click-and-collect store the customer picked. It is a preference, not a demand: if it cannot cover the item the allocator moves on to the next location.
     */
    @SerializedName("location_code")
    var location_code: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "country" to country as Any,
        "location_code" to location_code as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryShipTo(
            country = map["country"] as? String,
            location_code = map["location_code"] as? String,
        )
    }
}