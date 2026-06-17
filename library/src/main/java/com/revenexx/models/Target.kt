package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Target
 */
data class Target(
    /**
     * Target creation time in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Target ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Target update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Is the target expired.
     */
    @SerializedName("expired")
    val expired: Boolean,

    /**
     * The target identifier.
     */
    @SerializedName("identifier")
    val identifier: String,

    /**
     * Target Name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Provider ID.
     */
    @SerializedName("providerId")
    var providerId: String?,

    /**
     * The target provider type. Can be one of the following: `email`, `sms` or `push`.
     */
    @SerializedName("providerType")
    val providerType: String,

    /**
     * User ID.
     */
    @SerializedName("userId")
    val userId: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "expired" to expired as Any,
        "identifier" to identifier as Any,
        "name" to name as Any,
        "providerId" to providerId as Any,
        "providerType" to providerType as Any,
        "userId" to userId as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Target(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            expired = map["expired"] as Boolean,
            identifier = map["identifier"] as String,
            name = map["name"] as String,
            providerId = map["providerId"] as? String,
            providerType = map["providerType"] as String,
            userId = map["userId"] as String,
        )
    }
}