package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CategoriesRuleMatch

/**
 * Partial update — omitted fields keep their current value.
 */
data class CategoriesUpdateRequest(
    /**
     * The category's stable identifier — what an import and a storefront join on, and what survives a rename of the label. Unique per tenant.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The category name a person sees, per language tag. The catalog reads by name, not by code — a locale left blank falls back to the next filled one.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * The category this one hangs under. Null is a root of the tree. Deleting a parent lifts its children to the root rather than deleting them, so a mis-click never takes a subtree with it.
     */
    @SerializedName("parent_id")
    var parent_id: String?,

    /**
     * A materialized position in the tree, kept for importers that carry one (`tools/power_tools/cordless_drills`). Nothing in this app writes or reads it — `parent_id` is the structure this app navigates.
     */
    @SerializedName("xpath")
    var xpath: String?,

    /**
     * Order among the siblings under the same parent, ascending.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * How the conditions combine: 'all' ANDs them (the default), 'any' ORs them. It is a column of its own rather than a key of `rules` because the compiler reads the two separately.
     */
    @SerializedName("rule_match")
    var rule_match: CategoriesRuleMatch?,

    /**
     * The selector that makes this a RULE-DRIVEN category. Null means hand-picked. Matching products are MATERIALIZED as `product_categories` rows with source `rule`, next to the hand-picked ones a recompute never touches; `POST /products/categories/{category_id}/rules/preview` dry-runs this exact document before it is stored. Conditions address the `common` bucket of a product's values — a value held per locale or per channel has no single answer for a rule to test.
     */
    @SerializedName("rules")
    var rules: Any?,

    /**
     * When the rule last ran TO COMPLETION and its memberships were synced. Null means no pass has ever finished — a recompute is chunked, so a half-finished pass leaves this untouched.
     */
    @SerializedName("rules_computed_at")
    var rules_computed_at: String?,

    /**
     * Whatever this catalog keeps on a category beyond the model — the keys belong to the tenant, not to this app, and nothing here reads them.
     */
    @SerializedName("values")
    var values: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "labels" to labels as Any,
        "parent_id" to parent_id as Any,
        "path" to xpath as Any,
        "position" to position as Any,
        "rule_match" to rule_match?.value as Any,
        "rules" to rules as Any,
        "rules_computed_at" to rules_computed_at as Any,
        "values" to values as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CategoriesUpdateRequest(
            code = map["code"] as? String,
            labels = map["labels"] as? Any,
            parent_id = map["parent_id"] as? String,
            xpath = map["path"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            rule_match = CategoriesRuleMatch.values().find { it.value == (map["rule_match"] as? String) } ?: null,
            rules = map["rules"] as? Any,
            rules_computed_at = map["rules_computed_at"] as? String,
            values = map["values"] as? Any,
        )
    }
}