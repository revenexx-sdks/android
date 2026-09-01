package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The blocks to freeze, and where the template should be offered.
 */
data class PageTemplateCreateRequest(
    /**
     * A sentence about when to reach for it.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * The field this template should be offered in. Null offers it in every field.
     */
    @SerializedName("fieldName")
    var fieldName: String?,

    /**
     * Whether a new page of that type should start from this template.
     */
    @SerializedName("isDefault")
    var isDefault: Boolean?,

    /**
     * What the template is called in the picker.
     */
    @SerializedName("label")
    val label: String,

    /**
     * The page type this template should be offered on. Omit to take the current page's own type.
     */
    @SerializedName("pageBundle")
    var pageBundle: String?,

    /**
     * The blocks to serialize into the template, each with its whole subtree. They are read from the CURRENT edit state, so unpublished changes are included.
     */
    @SerializedName("uuids")
    val uuids: List<String>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "fieldName" to fieldName as Any,
        "isDefault" to isDefault as Any,
        "label" to label as Any,
        "pageBundle" to pageBundle as Any,
        "uuids" to uuids as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageTemplateCreateRequest(
            description = map["description"] as? String,
            fieldName = map["fieldName"] as? String,
            isDefault = map["isDefault"] as? Boolean,
            label = map["label"] as String,
            pageBundle = map["pageBundle"] as? String,
            uuids = map["uuids"] as List<String>,
        )
    }
}