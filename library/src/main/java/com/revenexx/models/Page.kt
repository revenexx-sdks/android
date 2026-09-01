package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PageStatus

/**
 * One addressable page of the storefront: its metadata and publish pointer. Its CONTENT is not here — blocks live behind the editor and delivery routes.
 */
data class Page(
    /**
     * Identifiers of findings the blökkli analyze feature was told to stop reporting for this page. Written by the `set_ignored_analyze` mutation and carried through publish, so dismissing a finding survives the next edit.
     */
    @SerializedName("analyze_ignored")
    var analyze_ignored: List<String>?,

    /**
     * The page TYPE, e.g. `standard` or a landing-page type the theme defines. It decides which fields the editor offers and which template the theme renders; the value set belongs to the active theme, not to this app.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * When the page was created.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The user id that created the page.
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * The tombstone. A soft-deleted page is never listed, never delivered and answers 404 — and it drops out of the unique slug index at once, so deleting a page frees its slug immediately.
     */
    @SerializedName("deleted_at")
    var deleted_at: String?,

    /**
     * Page-level blökkli display options, as a flat `option key → value` map — the options that belong to the PAGE rather than to a block (background, width, whether the header is shown). The keys are defined by the theme; this app stores whatever the `update_host_options` mutation set.
     */
    @SerializedName("host_options")
    var host_options: Any?,

    /**
     * The page id. Every editor and delivery route addresses a page by it, and it never changes — publishing replaces a page's blocks, never the page.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The page's free-form metadata bag — SEO fields, social preview data, whatever the theme asks the editor for. Nothing in this app reads a key of it: it is stored, versioned into revisions and handed back to the renderer untouched, so the theme owns its shape.
     */
    @SerializedName("meta")
    var meta: Any?,

    /**
     * The revision the storefront is currently serving. `null` means nothing has ever been published, and delivery answers 404 for the page even when `status` says `published`.
     */
    @SerializedName("published_revision_id")
    var published_revision_id: String?,

    /**
     * The path segment the storefront routes this page under, without a leading slash. Unique per tenant among live pages, and `null` for a page that is only ever reached by id. `GET /pages/delivery/page?slug=` matches it first and the translations second.
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * The language the page was authored in. It is the fallback for every field a translation leaves empty, so a page never renders as a hole.
     */
    @SerializedName("source_language")
    var source_language: String?,

    /**
     * Where the page sits in the editorial lifecycle. Only `published` is ever delivered, and only together with a `published_revision_id`.
     */
    @SerializedName("status")
    var status: PageStatus?,

    /**
     * The page title as an editor typed it, in the page's source language. Publishing overwrites it with the title the edit state carries, so this is always the last published (or last saved) wording.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * When the page last changed. The default sort of `GET /pages/pages` is this column descending, because "what did we touch last" is the question an editorial list is opened with.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * The user id that last changed the page — set by an update, a soft delete and by publishing.
     */
    @SerializedName("updated_by")
    var updated_by: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "analyze_ignored" to analyze_ignored as Any,
        "bundle" to bundle as Any,
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "deleted_at" to deleted_at as Any,
        "host_options" to host_options as Any,
        "id" to id as Any,
        "meta" to meta as Any,
        "published_revision_id" to published_revision_id as Any,
        "slug" to slug as Any,
        "source_language" to source_language as Any,
        "status" to status?.value as Any,
        "title" to title as Any,
        "updated_at" to updated_at as Any,
        "updated_by" to updated_by as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Page(
            analyze_ignored = map["analyze_ignored"] as? List<String>,
            bundle = map["bundle"] as? String,
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            deleted_at = map["deleted_at"] as? String,
            host_options = map["host_options"] as? Any,
            id = map["id"] as? String,
            meta = map["meta"] as? Any,
            published_revision_id = map["published_revision_id"] as? String,
            slug = map["slug"] as? String,
            source_language = map["source_language"] as? String,
            status = PageStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            title = map["title"] as? String,
            updated_at = map["updated_at"] as? String,
            updated_by = map["updated_by"] as? String,
        )
    }
}