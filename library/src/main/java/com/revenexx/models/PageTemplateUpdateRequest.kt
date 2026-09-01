package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value. A template is a COPY source, so changing it never reaches the pages already made from it.
 */
data class PageTemplateUpdateRequest(
    /**
     * A sentence about when to reach for it, shown next to the label.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * The field this template is offered in. Null offers it in every field.
     */
    @SerializedName("field_name")
    var field_name: String?,

    /**
     * Whether a new page of this bundle starts from this template.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * What the template is called in the picker.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * The page type this template is offered on. Null offers it on every page type.
     */
    @SerializedName("page_bundle")
    var page_bundle: String?,

    /**
     * The blocks the template inserts, in order. Replaces the stored tree completely.
     */
    @SerializedName("tree")
    var tree: List<PageBlockTree>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "field_name" to field_name as Any,
        "is_default" to is_default as Any,
        "label" to label as Any,
        "page_bundle" to page_bundle as Any,
        "tree" to tree?.map { it.toMap() } as Any,
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
            tree = (map["tree"] as List<Map<String, Any>>).map { PageBlockTree.from(map = it) },
        )
    }
}