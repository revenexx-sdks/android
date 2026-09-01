package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `family_attributes` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class FamilyAttributesFilter<T>(
    /**
     * The literal `?attribute_id=` value this call was understood to carry.
     */
    @SerializedName("attribute_id")
    var attribute_id: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?family_id=` value this call was understood to carry.
     */
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?is_required=` value this call was understood to carry.
     */
    @SerializedName("is_required")
    var is_required: String?,

    /**
     * The literal `?position=` value this call was understood to carry.
     */
    @SerializedName("position")
    var position: String?,

    /**
     * The literal `?required_channels=` value this call was understood to carry.
     */
    @SerializedName("required_channels")
    var required_channels: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_id" to attribute_id as Any,
        "created_at" to created_at as Any,
        "family_id" to family_id as Any,
        "id" to id as Any,
        "is_required" to is_required as Any,
        "position" to position as Any,
        "required_channels" to required_channels as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            attribute_id: String?,
            created_at: String?,
            family_id: String?,
            id: String?,
            is_required: String?,
            position: String?,
            required_channels: String?,
            data: Map<String, Any>
        ) = FamilyAttributesFilter<Map<String, Any>>(
            attribute_id,
            created_at,
            family_id,
            id,
            is_required,
            position,
            required_channels,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FamilyAttributesFilter<T>(
            attribute_id = map["attribute_id"] as? String,
            created_at = map["created_at"] as? String,
            family_id = map["family_id"] as? String,
            id = map["id"] as? String,
            is_required = map["is_required"] as? String,
            position = map["position"] as? String,
            required_channels = map["required_channels"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}