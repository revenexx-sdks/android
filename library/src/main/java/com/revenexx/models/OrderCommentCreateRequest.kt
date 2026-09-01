package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderCommentVisibility

/**
 * 
 */
data class OrderCommentCreateRequest(
    /**
     * Who wrote it, as the caller reported it. Free text; not resolved against a user directory.
     */
    @SerializedName("author")
    var author: String?,

    /**
     * The comment itself. Plain text; this app neither renders nor sanitizes it.
     */
    @SerializedName("body")
    val body: String,

    /**
     * Who may see it: 'internal' is a note between operators, 'customer' is meant to be shown in the customer's order view. Nothing here enforces that — this app labels the comment and the client showing it decides. Defaults to the tenant's default_comment_visibility. Defaults to the tenant's default_comment_visibility setting, which is 'internal' out of the box.
     */
    @SerializedName("visibility")
    var visibility: OrderCommentVisibility?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "author" to author as Any,
        "body" to body as Any,
        "visibility" to visibility?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCommentCreateRequest(
            author = map["author"] as? String,
            body = map["body"] as String,
            visibility = OrderCommentVisibility.values().find { it.value == (map["visibility"] as? String) } ?: null,
        )
    }
}