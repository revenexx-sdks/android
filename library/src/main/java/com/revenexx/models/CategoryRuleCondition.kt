package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CategoryRuleOperator

/**
 * 
 */
data class CategoryRuleCondition(
    /**
     * A product column (sku, kind, enabled, family_id, parent_id) or 'attribute:<code>' for the common bucket of attribute_values. An attribute code is [A-Za-z0-9_]+. Locale-/channel-scoped attributes are not supported.
     */
    @SerializedName("xfield")
    val xfield: String,

    /**
     * How to compare. 'eq'/'neq' are equality, 'gt'/'gte'/'lt'/'lte' order (numerically for a number, as text for a string), 'in' membership, 'contains'/'starts_with'/'ends_with' substring, 'is_empty'/'is_not_empty' presence — those last two take no `value`.
     */
    @SerializedName("xoperator")
    val xoperator: CategoryRuleOperator,

    /**
     * Comparison value. An array for 'in' — non-empty, at most 200 entries, all of the same type; omitted for 'is_empty'/'is_not_empty'; a non-empty string for 'contains'/'starts_with'/'ends_with'; a string or number for gt/gte/lt/lte. Numbers compare numerically (jsonb), strings as text.
     */
    @SerializedName("value")
    var value: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "field" to xfield as Any,
        "operator" to xoperator.value as Any,
        "value" to value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CategoryRuleCondition(
            xfield = map["field"] as String,
            xoperator = CategoryRuleOperator.values().find { it.value == map["operator"] as String }!!,
            value = map["value"] as? String,
        )
    }
}