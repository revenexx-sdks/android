package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Comment(
    /**
     * 
     */
    @SerializedName("author_id")
    var author_id: String?,

    /**
     * 
     */
    @SerializedName("author_name")
    var author_name: String?,

    /**
     * 
     */
    @SerializedName("block_uuids")
    var block_uuids: Any?,

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
    @SerializedName("page_id")
    var page_id: String?,

    /**
     * 
     */
    @SerializedName("parent_id")
    var parent_id: String?,

    /**
     * 
     */
    @SerializedName("resolved")
    var resolved: Boolean?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "author_id" to author_id as Any,
        "author_name" to author_name as Any,
        "block_uuids" to block_uuids as Any,
        "body" to body as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "page_id" to page_id as Any,
        "parent_id" to parent_id as Any,
        "resolved" to resolved as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Comment(
            author_id = map["author_id"] as? String,
            author_name = map["author_name"] as? String,
            block_uuids = map["block_uuids"] as? Any,
            body = map["body"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            page_id = map["page_id"] as? String,
            parent_id = map["parent_id"] as? String,
            resolved = map["resolved"] as? Boolean,
            updated_at = map["updated_at"] as? String,
        )
    }
}