package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value. Every page that references this item renders the new tree the next time it is delivered, which is the whole point of the library and the whole risk of editing one.
 */
data class PageLibraryItemUpdateRequest(
    /**
     * The block type this item instantiates. Changing it moves the item to a different part of the picker.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * What the item is called in the picker.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * A block and its whole subtree, serialized. Produced by the editor when a selection is made reusable or saved as a template, and instantiated back into real blocks when one is inserted.
     */
    @SerializedName("tree")
    var tree: PageBlockTree?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "label" to label as Any,
        "tree" to tree?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageLibraryItemUpdateRequest(
            bundle = map["bundle"] as? String,
            label = map["label"] as? String,
            tree = PageBlockTree.from(map = map["tree"] as Map<String, Any>),
        )
    }
}