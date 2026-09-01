package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.SegmentRuleMatch

/**
 * Partial update — omitted fields keep their current value.
 */
data class SegmentUpdateRequest(
    /**
     * Stable identifier, unique per tenant — what other apps and integrations name the segment by. Free text, but lowercase with underscores is the convention every seeded vocabulary follows.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Localized display names keyed by language tag. Null means nobody translated it and a client falls back to showing the code.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Sort order in the cockpit, ascending. Ties fall back to insertion order. Default 0.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * How the conditions combine: 'all' (default) is AND, 'any' is OR. Null means the same as 'all'.
     */
    @SerializedName("rule_match")
    var rule_match: SegmentRuleMatch?,

    /**
     * The selector that decides membership, stored verbatim. Null means the segment is manual-only. The same rule language product categories use, evaluated over organization columns, `setting:<key>` entries and the organization_metrics projection — so 'no order in 365 days' is expressible without joining the orders app. Null makes the segment manual-only. Changing it does not move a single membership — run the recompute.
     */
    @SerializedName("rules")
    var rules: SegmentRules?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "rule_match" to rule_match?.value as Any,
        "rules" to rules?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentUpdateRequest(
            code = map["code"] as? String,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            rule_match = SegmentRuleMatch.values().find { it.value == (map["rule_match"] as? String) } ?: null,
            rules = SegmentRules.from(map = map["rules"] as Map<String, Any>),
        )
    }
}