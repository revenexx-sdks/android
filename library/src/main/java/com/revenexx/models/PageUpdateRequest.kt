package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PageStatus

/**
 * Partial update — only title, slug, status, meta and bundle are applied; other keys are ignored.
 */
data class PageUpdateRequest(
    /**
     * 
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * 
     */
    @SerializedName("meta")
    var meta: Any?,

    /**
     * 
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * 
     */
    @SerializedName("status")
    var status: PageStatus?,

    /**
     * 
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