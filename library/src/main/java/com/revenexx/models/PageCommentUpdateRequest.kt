package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The new body. Nothing else about a comment is editable.
 */
data class PageCommentUpdateRequest(
    /**
     * The comment, as editor HTML. Replaces the old body completely.
     */
    @SerializedName("body")
    val body: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "body" to body as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageCommentUpdateRequest(
            body = map["body"] as String,
        )
    }
}