package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Template(
    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * 
     */
    @SerializedName("field_name")
    var field_name: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * 
     */
    @SerializedName("label")
    var label: String?,

    /**
     * 
     */
    @SerializedName("page_bundle")
    var page_bundle: String?,

    /**
     * 
     */
    @SerializedName("tree")
    var tree: Any?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "description" to description as Any,
        "field_name" to field_name as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "label" to label as Any,
        "page_bundle" to page_bundle as Any,
        "tree" to tree as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Template(
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            description = map["description"] as? String,
            field_name = map["field_name"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            label = map["label"] as? String,
            page_bundle = map["page_bundle"] as? String,
            tree = map["tree"] as? Any,
            updated_at = map["updated_at"] as? String,
        )
    }
}