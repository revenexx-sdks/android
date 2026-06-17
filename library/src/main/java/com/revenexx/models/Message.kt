package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MessageStatus

/**
 * Message
 */
data class Message(
    /**
     * Message creation time in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Message ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Message update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Data of the message.
     */
    @SerializedName("data")
    val data: Any,

    /**
     * The time when the message was delivered.
     */
    @SerializedName("deliveredAt")
    var deliveredAt: String?,

    /**
     * Number of recipients the message was delivered to.
     */
    @SerializedName("deliveredTotal")
    val deliveredTotal: Long,

    /**
     * Delivery errors if any.
     */
    @SerializedName("deliveryErrors")
    var deliveryErrors: List<String>?,

    /**
     * Message provider type.
     */
    @SerializedName("providerType")
    val providerType: String,

    /**
     * The scheduled time for message.
     */
    @SerializedName("scheduledAt")
    var scheduledAt: String?,

    /**
     * Status of delivery.
     */
    @SerializedName("status")
    val status: MessageStatus,

    /**
     * Target IDs set as recipients.
     */
    @SerializedName("targets")
    val targets: List<String>,

    /**
     * Topic IDs set as recipients.
     */
    @SerializedName("topics")
    val topics: List<String>,

    /**
     * User IDs set as recipients.
     */
    @SerializedName("users")
    val users: List<String>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "data" to data as Any,
        "deliveredAt" to deliveredAt as Any,
        "deliveredTotal" to deliveredTotal as Any,
        "deliveryErrors" to deliveryErrors as Any,
        "providerType" to providerType as Any,
        "scheduledAt" to scheduledAt as Any,
        "status" to status.value as Any,
        "targets" to targets as Any,
        "topics" to topics as Any,
        "users" to users as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Message(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            data = map["data"] as Any,
            deliveredAt = map["deliveredAt"] as? String,
            deliveredTotal = (map["deliveredTotal"] as Number).toLong(),
            deliveryErrors = map["deliveryErrors"] as? List<String>,
            providerType = map["providerType"] as String,
            scheduledAt = map["scheduledAt"] as? String,
            status = MessageStatus.values().find { it.value == map["status"] as String }!!,
            targets = map["targets"] as List<String>,
            topics = map["topics"] as List<String>,
            users = map["users"] as List<String>,
        )
    }
}