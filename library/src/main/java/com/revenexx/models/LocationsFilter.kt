package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `locations` — a typo, a filter another entity has, `?q=` — is DROPPED and cannot appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class LocationsFilter<T>(
    /**
     * The literal `?address=` value this call was understood to carry.
     */
    @SerializedName("address")
    var address: String?,

    /**
     * The literal `?code=` value this call was understood to carry.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?enabled=` value this call was understood to carry.
     */
    @SerializedName("enabled")
    var enabled: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?labels=` value this call was understood to carry.
     */
    @SerializedName("labels")
    var labels: String?,

    /**
     * The literal `?metadata=` value this call was understood to carry.
     */
    @SerializedName("metadata")
    var metadata: String?,

    /**
     * The literal `?name=` value this call was understood to carry.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The literal `?priority=` value this call was understood to carry.
     */
    @SerializedName("priority")
    var priority: String?,

    /**
     * The literal `?type=` value this call was understood to carry.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * The literal `?updated_at=` value this call was understood to carry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "address" to address as Any,
        "code" to code as Any,
        "created_at" to created_at as Any,
        "enabled" to enabled as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "priority" to priority as Any,
        "type" to type as Any,
        "updated_at" to updated_at as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            address: String?,
            code: String?,
            created_at: String?,
            enabled: String?,
            id: String?,
            labels: String?,
            metadata: String?,
            name: String?,
            priority: String?,
            type: String?,
            updated_at: String?,
            data: Map<String, Any>
        ) = LocationsFilter<Map<String, Any>>(
            address,
            code,
            created_at,
            enabled,
            id,
            labels,
            metadata,
            name,
            priority,
            type,
            updated_at,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = LocationsFilter<T>(
            address = map["address"] as? String,
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            enabled = map["enabled"] as? String,
            id = map["id"] as? String,
            labels = map["labels"] as? String,
            metadata = map["metadata"] as? String,
            name = map["name"] as? String,
            priority = map["priority"] as? String,
            type = map["type"] as? String,
            updated_at = map["updated_at"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}