package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The block and everything under it, serialized. This is the payload: every page that references the item renders THIS tree, so editing it here changes every placement at once.
 */
data class PageBlockTree(
    /**
     * The block type — `hero`, `text`, `teaser`, whatever the active theme defines. It decides which component renders it and which props it carries.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * Nested blocks, keyed by the field they sit in — `{ "content": [...], "buttons": [...] }`. Absent on a leaf block.
     */
    @SerializedName("children")
    var children: Any?,

    /**
     * The theme fragment this block renders instead of a props-driven component, or `null` for an ordinary block. Theme-defined, like a bundle.
     */
    @SerializedName("fragment_name")
    var fragment_name: String?,

    /**
     * blökkli display options for this block, as a flat `option key → value` map (variant, spacing, background). Theme-defined, set by the `update_options` mutation.
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * The block's field values in the page's SOURCE language, as a flat `field name → value` map. The field names are the theme's; this app stores and replays them without reading one.
     */
    @SerializedName("props")
    var props: Any?,

    /**
     * Per-language overrides of `props`, keyed by langcode: `{ "en": { "title": "About us" } }`. A field missing for a language falls back to `props`, which is why a half-translated page still renders.
     */
    @SerializedName("props_i18n")
    var props_i18n: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "children" to children as Any,
        "fragment_name" to fragment_name as Any,
        "options" to options as Any,
        "props" to props as Any,
        "props_i18n" to props_i18n as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageBlockTree(
            bundle = map["bundle"] as? String,
            children = map["children"] as? Any,
            fragment_name = map["fragment_name"] as? String,
            options = map["options"] as? Any,
            props = map["props"] as? Any,
            props_i18n = map["props_i18n"] as? Any,
        )
    }
}