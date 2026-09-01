package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.SegmentRulePreviewResponseRuleMatch
import com.revenexx.enums.SegmentRulePreviewResponseTarget

/**
 * 
 */
data class SegmentRulePreviewResponse(
    /**
     * The cap that applied (5000), or null when the rule was answered by a single count query and no cap was needed.
     */
    @SerializedName("cap")
    var cap: Long?,

    /**
     * True when the combined evaluation hit the id cap, which makes `count` a lower bound.
     */
    @SerializedName("capped")
    var capped: Boolean?,

    /**
     * How many organizations the rule selects. Exact when 'capped' is false; a LOWER BOUND when it is true.
     */
    @SerializedName("count")
    var count: Long?,

    /**
     * How the conditions were combined for this preview.
     */
    @SerializedName("rule_match")
    var rule_match: SegmentRulePreviewResponseRuleMatch?,

    /**
     * A handful of the organizations the rule selects — enough for an operator to recognise whether the rule means what they thought. Never the full set.
     */
    @SerializedName("sample")
    var sample: List<Any>?,

    /**
     * The segment named in the path. It is not read — the rule comes from the body — but it has to exist.
     */
    @SerializedName("segment_id")
    var segment_id: String?,

    /**
     * What the rule selects. Only 'organizations' exists.
     */
    @SerializedName("target")
    var target: SegmentRulePreviewResponseTarget?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cap" to cap as Any,
        "capped" to capped as Any,
        "count" to count as Any,
        "rule_match" to rule_match?.value as Any,
        "sample" to sample as Any,
        "segment_id" to segment_id as Any,
        "target" to target?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentRulePreviewResponse(
            cap = (map["cap"] as? Number)?.toLong(),
            capped = map["capped"] as? Boolean,
            count = (map["count"] as? Number)?.toLong(),
            rule_match = SegmentRulePreviewResponseRuleMatch.values().find { it.value == (map["rule_match"] as? String) } ?: null,
            sample = map["sample"] as? List<Any>,
            segment_id = map["segment_id"] as? String,
            target = SegmentRulePreviewResponseTarget.values().find { it.value == (map["target"] as? String) } ?: null,
        )
    }
}