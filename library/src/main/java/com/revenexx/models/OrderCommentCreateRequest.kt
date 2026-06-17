package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderCommentVisibility

/**
 * 
 */
data class OrderCommentCreateRequest(
    /**
     * 
     */
    @SerializedName("author")
    var author: String?,

    /**
     * 
     */
    @SerializedName("body")
    val body: String,

    /**
     * Default 'internal'.
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