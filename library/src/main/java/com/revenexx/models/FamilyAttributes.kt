package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FamilyAttributes(
    /**
     * 
     */
    @SerializedName("attribute_id")
    var attribute_id: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("is_required")
    var is_required: Boolean?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("required_channels")
    var required_channels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_id" to attribute_id as Any,
        "created_at" to created_at as Any,
        "family_id" to family_id as Any,
        "id" to id as Any,
        "is_required" to is_required as Any,
        "position" to position as Any,
        "required_channels" to required_channels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FamilyAttributes(
            attribute_id = map["attribute_id"] as? String,
            created_at = map["created_at"] as? String,
            family_id = map["family_id"] as? String,
            id = map["id"] as? String,
            is_required = map["is_required"] as? Boolean,
            position = (map["position"] as? Number)?.toLong(),
            required_channels = map["required_channels"] as? Any,
        )
    }
}