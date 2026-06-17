package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderComment(
    /**
     * 
     */
    @SerializedName("author")
    var author: String?,

    /**
     * 
     */
    @SerializedName("body")
    var body: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * 
     */
    @SerializedName("visibility")
    var visibility: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "author" to author as Any,
        "body" to body as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "order_id" to order_id as Any,
        "visibility" to visibility as Any,
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
            visibility = map["visibility"] as? String,
        )
    }
}