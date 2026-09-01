package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class FamilyVariantsUpdateRequest(
    /**
     * The attribute codes a product model splits its variants on. Two shapes are in the wild and both are read: a bare list of codes, or one entry per level, outermost first — `[{"level": 1, "axes": ["colour"]}, {"level": 2, "axes": ["size"]}]`. An attribute named here is READ-ONLY on the model and set on each variant, which is what `AttributeField.readonly_reason` reports.
     */
    @SerializedName("axes")
    var axes: Any?,

    /**
     * The variant structure's stable identifier — how this family splits, not which product it splits. Unique per tenant.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The family this variant structure belongs to. A family may carry several, and a product names the one it follows through `family_variant_id`.
     */
    @SerializedName("family_id")
    var family_id: String?,

    /**
     * What the variant structure is called, per language tag.
     */
    @SerializedName("labels")
    var labels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "axes" to axes as Any,
        "code" to code as Any,
        "family_id" to family_id as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FamilyVariantsUpdateRequest(
            axes = map["axes"] as? Any,
            code = map["code"] as? String,
            family_id = map["family_id"] as? String,
            labels = map["labels"] as? Any,
        )
    }
}