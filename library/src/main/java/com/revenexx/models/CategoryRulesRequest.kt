package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CategoryRuleMatch

/**
 * 
 */
data class CategoryRulesRequest(
    /**
     * Between 1 and 25 conditions — a rule is a selector, not a query language. An empty list is a 400, not "everything".
     */
    @SerializedName("conditions")
    val conditions: List<CategoryRuleCondition>,

    /**
     * 'all' ANDs every condition (default), 'any' ORs them.
     */
    @SerializedName("rule_match")
    var rule_match: CategoryRuleMatch?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "conditions" to conditions.map { it.toMap() } as Any,
        "rule_match" to rule_match?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CategoryRulesRequest(
            conditions = (map["conditions"] as List<Map<String, Any>>).map { CategoryRuleCondition.from(map = it) },
            rule_match = CategoryRuleMatch.values().find { it.value == (map["rule_match"] as? String) } ?: null,
        )
    }
}