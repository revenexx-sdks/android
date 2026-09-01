package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PageStatus

/**
 * Partial update — only title, slug, status, meta and bundle are applied; other keys are ignored. The page's CONTENT is never edited here: blocks change through the editor's mutation log.
 */
data class PageUpdateRequest(
    /**
     * The page type. Changing it changes which template the theme renders.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * The page's metadata bag. Replaced wholesale, not merged.
     */
    @SerializedName("meta")
    var meta: Any?,

    /**
     * The path segment the storefront routes it under. Sending a slug another live page holds answers 409; sending null makes the page unreachable by path.
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * The lifecycle status. Setting `published` here does NOT publish content — delivery still needs a revision, which only `POST /pages/editor/{page_id}/publish` writes.
     */
    @SerializedName("status")
    var status: PageStatus?,

    /**
     * The page title in its source language.
     */
    @SerializedName("title")
    var title: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "meta" to meta as Any,
        "slug" to slug as Any,
        "status" to status?.value as Any,
        "title" to title as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageUpdateRequest(
            bundle = map["bundle"] as? String,
            meta = map["meta"] as? Any,
            slug = map["slug"] as? String,
            status = PageStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            title = map["title"] as? String,
        )
    }
}