package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.LocationType

/**
 * 
 */
data class LocationCreateRequest(
    /**
     * 
     */
    @SerializedName("address")
    var address: Any?,

    /**
     * Unique location code (per tenant).
     */
    @SerializedName("code")
    val code: String,

    /**
     * Disabled locations are skipped by availability and reserve (default true).
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Localised display names ({de, en, …}).
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("name")
    val name: String,

    /**
     * Sourcing order — lower wins (default 0).
     */
    @SerializedName("priority")
    var priority: Long?,

    /**
     * Default 'warehouse'.
     */
    @SerializedName("type")
    var type: LocationType?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "address" to address as Any,
        "code" to code as Any,
        "enabled" to enabled as Any,
        "labels" to labels as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "priority" to priority as Any,
        "type" to type?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LocationCreateRequest(
            address = map["address"] as? Any,
            code = map["code"] as String,
            enabled = map["enabled"] as? Boolean,
            labels = map["labels"] as? Any,
            metadata = map["metadata"] as? Any,
            name = map["name"] as String,
            priority = (map["priority"] as? Number)?.toLong(),
            type = LocationType.values().find { it.value == (map["type"] as? String) } ?: null,
        )
    }
}