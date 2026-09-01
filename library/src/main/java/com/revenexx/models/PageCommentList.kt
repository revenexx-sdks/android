package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Every comment of the page, roots and replies flat in one list, oldest first — the editor builds the threads from `parentUuid`. Every write route answers this same full list rather than the row it changed.
 */
data class PageCommentList(
    /**
     * The page's comments, oldest first.
     */
    @SerializedName("items")
    var items: List<PageCommentItem>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageCommentList(
            items = (map["items"] as List<Map<String, Any>>).map { PageCommentItem.from(map = it) },
        )
    }
}