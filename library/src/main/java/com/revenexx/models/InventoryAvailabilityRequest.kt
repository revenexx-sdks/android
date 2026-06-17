package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryAvailabilityRequest(
    /**
     * The items to check (batch, at most 200).
     */
    @SerializedName("items")
    val items: List<InventoryAvailabilityItem>,

    /**
     * Restrict the check to one location (default: all enabled locations).
     */
    @SerializedName("location_code")
    var location_code: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items.map { it.toMap() } as Any,
        "location_code" to location_code as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryAvailabilityRequest(
            items = (map["items"] as List<Map<String, Any>>).map { InventoryAvailabilityItem.from(map = it) },
            location_code = map["location_code"] as? String,
        )
    }
}