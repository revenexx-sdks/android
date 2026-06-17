package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * MFA Challenge
 */
data class MfaChallenge(
    /**
     * Token creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Token ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Token expiration date in ISO 8601 format.
     */
    @SerializedName("expire")
    val expire: String,

    /**
     * User ID.
     */
    @SerializedName("userId")
    val userId: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "expire" to expire as Any,
        "userId" to userId as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MfaChallenge(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            expire = map["expire"] as String,
            userId = map["userId"] as String,
        )
    }
}