package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class PageTemplateUpdateRequest(
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
     * Serialized block trees ({ bundle, props, props_i18n, options, children }).
     */
    @SerializedName("tree")
    var tree: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "field_name" to field_name as Any,
        "is_default" to is_default as Any,
        "label" to label as Any,
        "page_bundle" to page_bundle as Any,
        "tree" to tree as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageTemplateUpdateRequest(
            description = map["description"] as? String,
            field_name = map["field_name"] as? String,
            is_default = map["is_default"] as? Boolean,
            label = map["label"] as? String,
            page_bundle = map["page_bundle"] as? String,
            tree = map["tree"] as? List<Any>,
        )
    }
}