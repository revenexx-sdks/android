package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `categories` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class CategoriesFilter<T>(
    /**
     * The literal `?code=` value this call was understood to carry.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?labels=` value this call was understood to carry.
     */
    @SerializedName("labels")
    var labels: String?,

    /**
     * The literal `?parent_id=` value this call was understood to carry.
     */
    @SerializedName("parent_id")
    var parent_id: String?,

    /**
     * The literal `?path=` value this call was understood to carry.
     */
    @SerializedName("xpath")
    var xpath: String?,

    /**
     * The literal `?position=` value this call was understood to carry.
     */
    @SerializedName("position")
    var position: String?,

    /**
     * The literal `?rule_match=` value this call was understood to carry.
     */
    @SerializedName("rule_match")
    var rule_match: String?,

    /**
     * The literal `?rules=` value this call was understood to carry.
     */
    @SerializedName("rules")
    var rules: String?,

    /**
     * The literal `?rules_computed_at=` value this call was understood to carry.
     */
    @SerializedName("rules_computed_at")
    var rules_computed_at: String?,

    /**
     * The literal `?updated_at=` value this call was understood to carry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * The literal `?values=` value this call was understood to carry.
     */
    @SerializedName("values")
    var values: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "parent_id" to parent_id as Any,
        "path" to xpath as Any,
        "position" to position as Any,
        "rule_match" to rule_match as Any,
        "rules" to rules as Any,
        "rules_computed_at" to rules_computed_at as Any,
        "updated_at" to updated_at as Any,
        "values" to values as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            code: String?,
            created_at: String?,
            id: String?,
            labels: String?,
            parent_id: String?,
            xpath: String?,
            position: String?,
            rule_match: String?,
            rules: String?,
            rules_computed_at: String?,
            updated_at: String?,
            values: String?,
            data: Map<String, Any>
        ) = CategoriesFilter<Map<String, Any>>(
            code,
            created_at,
            id,
            labels,
            parent_id,
            xpath,
            position,
            rule_match,
            rules,
            rules_computed_at,
            updated_at,
            values,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = CategoriesFilter<T>(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            labels = map["labels"] as? String,
            parent_id = map["parent_id"] as? String,
            xpath = map["path"] as? String,
            position = map["position"] as? String,
            rule_match = map["rule_match"] as? String,
            rules = map["rules"] as? String,
            rules_computed_at = map["rules_computed_at"] as? String,
            updated_at = map["updated_at"] as? String,
            values = map["values"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}