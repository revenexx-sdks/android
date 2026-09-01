package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A new page. Only the title is yours to supply — everything else has a tenant default behind it.
 */
data class PageCreateRequest(
    /**
     * The page type. Omit to take the default_page_bundle setting.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * Page-level blökkli display options as a flat `option key → value` map. Theme-defined; usually left out and set later from the editor.
     */
    @SerializedName("hostOptions")
    var hostOptions: Any?,

    /**
     * The page's metadata bag (SEO and social fields). Stored and handed back untouched — this app reads no key of it, so the theme decides what goes in.
     */
    @SerializedName("meta")
    var meta: Any?,

    /**
     * The path segment the storefront routes it under, without a leading slash. Unique per tenant among live pages; omit or send null for a page reached only by id. Nothing here derives one from the title.
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * The language you are authoring in, and the fallback for every later translation. Omit to take the default_source_language setting for the request market.
     */
    @SerializedName("sourceLanguage")
    var sourceLanguage: String?,

    /**
     * What the page is called, in its source language. Shown in the editorial list and searched by `?q=`.
     */
    @SerializedName("title")
    val title: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "hostOptions" to hostOptions as Any,
        "meta" to meta as Any,
        "slug" to slug as Any,
        "sourceLanguage" to sourceLanguage as Any,
        "title" to title as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageCreateRequest(
            bundle = map["bundle"] as? String,
            hostOptions = map["hostOptions"] as? Any,
            meta = map["meta"] as? Any,
            slug = map["slug"] as? String,
            sourceLanguage = map["sourceLanguage"] as? String,
            title = map["title"] as String,
        )
    }
}