package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.SegmentRulesTarget

/**
 * The selector that decides membership, stored verbatim. Null means the segment is manual-only. The same rule language product categories use, evaluated over organization columns, `setting:<key>` entries and the organization_metrics projection — so 'no order in 365 days' is expressible without joining the orders app. Null makes the segment manual-only. Changing it does not move a single membership — run the recompute.
 */
data class SegmentRules(
    /**
     * The conditions, combined by `rule_match`. At least one, at most 25.
     */
    @SerializedName("conditions")
    val conditions: List<SegmentRuleCondition>,

    /**
     * Only 'organizations' is supported; any other value is rejected. A segment groups COMPANIES — the people are reached through them.
     */
    @SerializedName("target")
    var target: SegmentRulesTarget?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "conditions" to conditions.map { it.toMap() } as Any,
        "target" to target?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentRules(
            conditions = (map["conditions"] as List<Map<String, Any>>).map { SegmentRuleCondition.from(map = it) },
            target = SegmentRulesTarget.values().find { it.value == (map["target"] as? String) } ?: null,
        )
    }
}