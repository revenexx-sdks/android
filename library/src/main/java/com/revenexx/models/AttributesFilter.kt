package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `attributes` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class AttributesFilter<T>(
    /**
     * The literal `?code=` value this call was understood to carry.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The literal `?config=` value this call was understood to carry.
     */
    @SerializedName("config")
    var config: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?entity_ref=` value this call was understood to carry.
     */
    @SerializedName("entity_ref")
    var entity_ref: String?,

    /**
     * The literal `?entity_type=` value this call was understood to carry.
     */
    @SerializedName("entity_type")
    var entity_type: String?,

    /**
     * The literal `?group_id=` value this call was understood to carry.
     */
    @SerializedName("group_id")
    var group_id: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?is_filterable=` value this call was understood to carry.
     */
    @SerializedName("is_filterable")
    var is_filterable: String?,

    /**
     * The literal `?is_unique=` value this call was understood to carry.
     */
    @SerializedName("is_unique")
    var is_unique: String?,

    /**
     * The literal `?labels=` value this call was understood to carry.
     */
    @SerializedName("labels")
    var labels: String?,

    /**
     * The literal `?localizable=` value this call was understood to carry.
     */
    @SerializedName("localizable")
    var localizable: String?,

    /**
     * The literal `?position=` value this call was understood to carry.
     */
    @SerializedName("position")
    var position: String?,

    /**
     * The literal `?scopable=` value this call was understood to carry.
     */
    @SerializedName("scopable")
    var scopable: String?,

    /**
     * The literal `?type=` value this call was understood to carry.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * The literal `?updated_at=` value this call was understood to carry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * The literal `?usable_in_grid=` value this call was understood to carry.
     */
    @SerializedName("usable_in_grid")
    var usable_in_grid: String?,

    /**
     * The literal `?validation=` value this call was understood to carry.
     */
    @SerializedName("validation")
    var validation: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "config" to config as Any,
        "created_at" to created_at as Any,
        "entity_ref" to entity_ref as Any,
        "entity_type" to entity_type as Any,
        "group_id" to group_id as Any,
        "id" to id as Any,
        "is_filterable" to is_filterable as Any,
        "is_unique" to is_unique as Any,
        "labels" to labels as Any,
        "localizable" to localizable as Any,
        "position" to position as Any,
        "scopable" to scopable as Any,
        "type" to type as Any,
        "updated_at" to updated_at as Any,
        "usable_in_grid" to usable_in_grid as Any,
        "validation" to validation as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            code: String?,
            config: String?,
            created_at: String?,
            entity_ref: String?,
            entity_type: String?,
            group_id: String?,
            id: String?,
            is_filterable: String?,
            is_unique: String?,
            labels: String?,
            localizable: String?,
            position: String?,
            scopable: String?,
            type: String?,
            updated_at: String?,
            usable_in_grid: String?,
            validation: String?,
            data: Map<String, Any>
        ) = AttributesFilter<Map<String, Any>>(
            code,
            config,
            created_at,
            entity_ref,
            entity_type,
            group_id,
            id,
            is_filterable,
            is_unique,
            labels,
            localizable,
            position,
            scopable,
            type,
            updated_at,
            usable_in_grid,
            validation,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = AttributesFilter<T>(
            code = map["code"] as? String,
            config = map["config"] as? String,
            created_at = map["created_at"] as? String,
            entity_ref = map["entity_ref"] as? String,
            entity_type = map["entity_type"] as? String,
            group_id = map["group_id"] as? String,
            id = map["id"] as? String,
            is_filterable = map["is_filterable"] as? String,
            is_unique = map["is_unique"] as? String,
            labels = map["labels"] as? String,
            localizable = map["localizable"] as? String,
            position = map["position"] as? String,
            scopable = map["scopable"] as? String,
            type = map["type"] as? String,
            updated_at = map["updated_at"] as? String,
            usable_in_grid = map["usable_in_grid"] as? String,
            validation = map["validation"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}