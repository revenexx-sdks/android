package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AttributesCreateRequest(
    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("config")
    var config: Any?,

    /**
     * 
     */
    @SerializedName("entity_ref")
    var entity_ref: String?,

    /**
     * 
     */
    @SerializedName("entity_type")
    var entity_type: String?,

    /**
     * 
     */
    @SerializedName("group_id")
    var group_id: String?,

    /**
     * 
     */
    @SerializedName("is_filterable")
    var is_filterable: Boolean?,

    /**
     * 
     */
    @SerializedName("is_unique")
    var is_unique: Boolean?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("localizable")
    var localizable: Boolean?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("scopable")
    var scopable: Boolean?,

    /**
     * 
     */
    @SerializedName("type")
    val type: String,

    /**
     * 
     */
    @SerializedName("usable_in_grid")
    var usable_in_grid: Boolean?,

    /**
     * 
     */
    @SerializedName("validation")
    var validation: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "config" to config as Any,
        "entity_ref" to entity_ref as Any,
        "entity_type" to entity_type as Any,
        "group_id" to group_id as Any,
        "is_filterable" to is_filterable as Any,
        "is_unique" to is_unique as Any,
        "labels" to labels as Any,
        "localizable" to localizable as Any,
        "position" to position as Any,
        "scopable" to scopable as Any,
        "type" to type as Any,
        "usable_in_grid" to usable_in_grid as Any,
        "validation" to validation as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributesCreateRequest(
            code = map["code"] as String,
            config = map["config"] as? Any,
            entity_ref = map["entity_ref"] as? String,
            entity_type = map["entity_type"] as? String,
            group_id = map["group_id"] as? String,
            is_filterable = map["is_filterable"] as? Boolean,
            is_unique = map["is_unique"] as? Boolean,
            labels = map["labels"] as? Any,
            localizable = map["localizable"] as? Boolean,
            position = (map["position"] as? Number)?.toLong(),
            scopable = map["scopable"] as? Boolean,
            type = map["type"] as String,
            usable_in_grid = map["usable_in_grid"] as? Boolean,
            validation = map["validation"] as? Any,
        )
    }
}