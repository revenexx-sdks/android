package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class PageLibraryItemUpdateRequest(
    /**
     * 
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * 
     */
    @SerializedName("label")
    var label: String?,

    /**
     * Serialized block tree ({ bundle, props, props_i18n, options, children }).
     */
    @SerializedName("tree")
    var tree: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "label" to label as Any,
        "tree" to tree as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageLibraryItemUpdateRequest(
            bundle = map["bundle"] as? String,
            label = map["label"] as? String,
            tree = map["tree"] as? Any,
        )
    }
}