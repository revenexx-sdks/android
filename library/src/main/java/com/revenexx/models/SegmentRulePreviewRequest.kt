package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.SegmentRulePreviewRequestRuleMatch
import com.revenexx.enums.SegmentRulePreviewRequestTarget

/**
 * 
 */
data class SegmentRulePreviewRequest(
    /**
     * The conditions, combined by `rule_match`. At least one, at most 25.
     */
    @SerializedName("conditions")
    val conditions: List<SegmentRuleCondition>,

    /**
     * How the conditions combine. Default 'all'.
     */
    @SerializedName("rule_match")
    var rule_match: SegmentRulePreviewRequestRuleMatch?,

    /**
     * Only 'organizations' is supported; any other value is rejected. A segment groups COMPANIES — the people are reached through them.
     */
    @SerializedName("target")
    var target: SegmentRulePreviewRequestTarget?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "conditions" to conditions.map { it.toMap() } as Any,
        "rule_match" to rule_match?.value as Any,
        "target" to target?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentRulePreviewRequest(
            conditions = (map["conditions"] as List<Map<String, Any>>).map { SegmentRuleCondition.from(map = it) },
            rule_match = SegmentRulePreviewRequestRuleMatch.values().find { it.value == (map["rule_match"] as? String) } ?: null,
            target = SegmentRulePreviewRequestTarget.values().find { it.value == (map["target"] as? String) } ?: null,
        )
    }
}