package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Subscriber
 */
data class Subscriber(
    /**
     * Subscriber creation time in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Subscriber ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Subscriber update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * The target provider type. Can be one of the following: `email`, `sms` or `push`.
     */
    @SerializedName("providerType")
    val providerType: String,

    /**
     * Target.
     */
    @SerializedName("target")
    val target: Target,

    /**
     * Target ID.
     */
    @SerializedName("targetId")
    val targetId: String,

    /**
     * Topic ID.
     */
    @SerializedName("topicId")
    val topicId: String,

    /**
     * Topic ID.
     */
    @SerializedName("userId")
    val userId: String,

    /**
     * User Name.
     */
    @SerializedName("userName")
    val userName: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "providerType" to providerType as Any,
        "target" to target.toMap() as Any,
        "targetId" to targetId as Any,
        "topicId" to topicId as Any,
        "userId" to userId as Any,
        "userName" to userName as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Subscriber(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            providerType = map["providerType"] as String,
            target = Target.from(map = map["target"] as Map<String, Any>),
            targetId = map["targetId"] as String,
            topicId = map["topicId"] as String,
            userId = map["userId"] as String,
            userName = map["userName"] as String,
        )
    }
}