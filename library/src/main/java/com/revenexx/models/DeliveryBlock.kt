package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One block, ready to render: props resolved for the requested language, library references already expanded, scheduled blocks already filtered out.
 */
data class DeliveryBlock(
    /**
     * The block type. This is what a theme switches its component on.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * Nested blocks keyed by the field they sit in — `{ "columns": [...] }`. Empty object on a leaf block.
     */
    @SerializedName("children")
    var children: Any?,

    /**
     * The theme fragment to render instead of a props-driven component. Theme-defined, like a bundle.
     */
    @SerializedName("fragmentName")
    var fragmentName: String?,

    /**
     * The library item this block came from, or `null`. Its content is already inlined above — this is for cache invalidation and editor links, not for a second fetch.
     */
    @SerializedName("libraryItemId")
    var libraryItemId: String?,

    /**
     * Display options for this block, as a flat `option key → value` map.
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * The block's field values for the requested language, source values already overlaid with that language's overrides. Theme-defined keys.
     */
    @SerializedName("props")
    var props: Any?,

    /**
     * The block uuid — stable across publishes, so it is safe to use as a render key or an anchor.
     */
    @SerializedName("uuid")
    var uuid: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "children" to children as Any,
        "fragmentName" to fragmentName as Any,
        "libraryItemId" to libraryItemId as Any,
        "options" to options as Any,
        "props" to props as Any,
        "uuid" to uuid as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = DeliveryBlock(
            bundle = map["bundle"] as? String,
            children = map["children"] as? Any,
            fragmentName = map["fragmentName"] as? String,
            libraryItemId = map["libraryItemId"] as? String,
            options = map["options"] as? Any,
            props = map["props"] as? Any,
            uuid = map["uuid"] as? String,
        )
    }
}