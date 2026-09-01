package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One reusable block. Every page that references it renders THIS tree, so editing the item changes every placement at once.
 */
data class LibraryItem(
    /**
     * The block type this item instantiates. The library picker filters by it, so an item only ever appears where its bundle is allowed. Theme-defined.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * When the item entered the library.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The user id that made the block reusable.
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * The tombstone. A soft-deleted item is never listed or handed out, and a block still referencing it keeps rendering its own last state rather than breaking.
     */
    @SerializedName("deleted_at")
    var deleted_at: String?,

    /**
     * The library item id. A block references it to become an instance of the item rather than a copy.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * What the item is called in the library picker. This is the only thing an editor sees before inserting it, so it carries the whole description.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * The block and everything under it, serialized. This is the payload: every page that references the item renders THIS tree, so editing it here changes every placement at once.
     */
    @SerializedName("tree")
    var tree: PageBlockTree?,

    /**
     * When the item last changed — i.e. when every page referencing it last changed with it.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "deleted_at" to deleted_at as Any,
        "id" to id as Any,
        "label" to label as Any,
        "tree" to tree?.toMap() as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LibraryItem(
            bundle = map["bundle"] as? String,
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            deleted_at = map["deleted_at"] as? String,
            id = map["id"] as? String,
            label = map["label"] as? String,
            tree = PageBlockTree.from(map = map["tree"] as Map<String, Any>),
            updated_at = map["updated_at"] as? String,
        )
    }
}