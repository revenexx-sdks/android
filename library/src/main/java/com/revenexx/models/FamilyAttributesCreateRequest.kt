package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FamilyAttributesCreateRequest(
    /**
     * 
     */
    @SerializedName("attribute_id")
    val attribute_id: String,

    /**
     * 
     */
    @SerializedName("family_id")
    val family_id: String,

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
        "family_id" to family_id as Any,
        "is_required" to is_required as Any,
        "position" to position as Any,
        "required_channels" to required_channels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FamilyAttributesCreateRequest(
            attribute_id = map["attribute_id"] as String,
            family_id = map["family_id"] as String,
            is_required = map["is_required"] as? Boolean,
            position = (map["position"] as? Number)?.toLong(),
            required_channels = map["required_channels"] as? Any,
        )
    }
}