package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Just enough of a published page to link to it. The block tree is not here — fetch it with `GET /pages/delivery/page`.
 */
data class DeliveryPageRef(
    /**
     * The page type, so a sitemap can group or a picker can filter.
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * The page id, usable as `?id=` on the delivery route.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The path segment to build the URL from. `null` for a page reachable only by id, which a sitemap should skip.
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * The page title in its source language — this projection is not language-resolved.
     */
    @SerializedName("title")
    var title: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bundle" to bundle as Any,
        "id" to id as Any,
        "slug" to slug as Any,
        "title" to title as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = DeliveryPageRef(
            bundle = map["bundle"] as? String,
            id = map["id"] as? String,
            slug = map["slug"] as? String,
            title = map["title"] as? String,
        )
    }
}