package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Subscriber list
 */
data class SubscriberList(
    /**
     * List of subscribers.
     */
    @SerializedName("subscribers")
    val subscribers: List<Subscriber>,

    /**
     * Total number of subscribers that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "subscribers" to subscribers.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SubscriberList(
            subscribers = (map["subscribers"] as List<Map<String, Any>>).map { Subscriber.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}