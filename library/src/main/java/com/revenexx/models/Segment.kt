package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.SegmentRuleMatch

/**
 * A named group of ORGANIZATIONS — by hand, by rule, or both at once.
 */
data class Segment(
    /**
     * Stable identifier, unique per tenant — what other apps and integrations name the segment by. Free text, but lowercase with underscores is the convention every seeded vocabulary follows.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the segment was created.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the segment.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Localized display names keyed by language tag. Null means nobody translated it and a client falls back to showing the code.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Sort order in the cockpit, ascending. Ties fall back to insertion order.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * How the conditions combine: 'all' (default) is AND, 'any' is OR. Null means the same as 'all'.
     */
    @SerializedName("rule_match")
    var rule_match: SegmentRuleMatch?,

    /**
     * The selector that decides membership, stored verbatim. Null means the segment is manual-only. The same rule language product categories use, evaluated over organization columns, `setting:<key>` entries and the organization_metrics projection — so 'no order in 365 days' is expressible without joining the orders app.
     */
    @SerializedName("rules")
    var rules: Any?,

    /**
     * When the rule last finished a COMPLETE recompute. Null after a rule change, and while a chunked recompute is still running — so it doubles as "are the rule memberships trustworthy right now?".
     */
    @SerializedName("rules_computed_at")
    var rules_computed_at: String?,

    /**
     * The tenant this row belongs to — the store slug, not an id. Set by the platform from the authenticated context, never by a caller; a write that carries it is ignored, and no request can read another tenant's rows by sending a different one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * When any column of this row last changed.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "rule_match" to rule_match?.value as Any,
        "rules" to rules as Any,
        "rules_computed_at" to rules_computed_at as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Segment(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            rule_match = SegmentRuleMatch.values().find { it.value == (map["rule_match"] as? String) } ?: null,
            rules = map["rules"] as? Any,
            rules_computed_at = map["rules_computed_at"] as? String,
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}