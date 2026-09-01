package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Message list
 */
data class MessageList(
    /**
     * List of messages.
     */
    @SerializedName("messages")
    val messages: List<Message2>,

    /**
     * Total number of messages that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "messages" to messages.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MessageList(
            messages = (map["messages"] as List<Map<String, Any>>).map { Message2.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}