package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Topic list
 */
data class TopicList(
    /**
     * List of topics.
     */
    @SerializedName("topics")
    val topics: List<Topic>,

    /**
     * Total number of topics that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "topics" to topics.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TopicList(
            topics = (map["topics"] as List<Map<String, Any>>).map { Topic.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}