package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderCommentVisibility

/**
 * A note on an order, either internal between operators or meant for the customer to see.
 */
data class OrderComment(
    /**
     * Who wrote it, as the caller reported it. Free text; not resolved against a user directory.
     */
    @SerializedName("author")
    var author: String?,

    /**
     * The comment itself. Plain text; this app neither renders nor sanitizes it.
     */
    @SerializedName("body")
    var body: String?,

    /**
     * When the comment was written. Comments come back oldest first.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the comment.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The order the comment hangs on.
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * Who may see it: 'internal' is a note between operators, 'customer' is meant to be shown in the customer's order view. Nothing here enforces that — this app labels the comment and the client showing it decides. Defaults to the tenant's default_comment_visibility.
     */
    @SerializedName("visibility")
    var visibility: OrderCommentVisibility?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "author" to author as Any,
        "body" to body as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "order_id" to order_id as Any,
        "visibility" to visibility?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderComment(
            author = map["author"] as? String,
            body = map["body"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            order_id = map["order_id"] as? String,
            visibility = OrderCommentVisibility.values().find { it.value == (map["visibility"] as? String) } ?: null,
        )
    }
}