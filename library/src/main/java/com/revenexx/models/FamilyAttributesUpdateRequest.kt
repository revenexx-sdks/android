package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class FamilyAttributesUpdateRequest(
    /**
     * The attribute the family carries. One row per (family, attribute); deleting either side deletes the link.
     */
    @SerializedName("attribute_id")
    var attribute_id: String?,

    /**
     * The family this link belongs to — one side of the pair that makes an attribute part of a family's form.
     */
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * The attribute has to carry a value for a product of this family to count as complete. `POST /products/{id}/completeness` measures exactly these and nothing else.
     */
    @SerializedName("is_required")
    var is_required: Boolean?,

    /**
     * The family's own ordering of this attribute, which overrides the attribute's default `position` in this family's form.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Narrows `is_required` to named channels. NULL or an empty list means required EVERYWHERE, not nowhere — that is how every required link in the wild is stored, and reading an empty list as "nowhere" reports a fully configured family as demanding nothing.
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
        ) = FamilyAttributesUpdateRequest(
            attribute_id = map["attribute_id"] as? String,
            family_id = map["family_id"] as? String,
            is_required = map["is_required"] as? Boolean,
            position = (map["position"] as? Number)?.toLong(),
            required_channels = map["required_channels"] as? Any,
        )
    }
}