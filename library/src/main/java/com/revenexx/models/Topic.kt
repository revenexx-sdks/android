package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Topic
 */
data class Topic(
    /**
     * Topic creation time in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Topic ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Topic update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Total count of email subscribers subscribed to the topic.
     */
    @SerializedName("emailTotal")
    val emailTotal: Long,

    /**
     * The name of the topic.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Total count of push subscribers subscribed to the topic.
     */
    @SerializedName("pushTotal")
    val pushTotal: Long,

    /**
     * Total count of SMS subscribers subscribed to the topic.
     */
    @SerializedName("smsTotal")
    val smsTotal: Long,

    /**
     * Subscribe permissions.
     */
    @SerializedName("subscribe")
    val subscribe: List<String>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "emailTotal" to emailTotal as Any,
        "name" to name as Any,
        "pushTotal" to pushTotal as Any,
        "smsTotal" to smsTotal as Any,
        "subscribe" to subscribe as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Topic(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            emailTotal = (map["emailTotal"] as Number).toLong(),
            name = map["name"] as String,
            pushTotal = (map["pushTotal"] as Number).toLong(),
            smsTotal = (map["smsTotal"] as Number).toLong(),
            subscribe = map["subscribe"] as List<String>,
        )
    }
}