package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Token
 */
data class Token(
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
     * Security phrase of a token. Empty if security phrase was not requested when creating a token. It includes randomly generated phrase which is also sent in the external resource such as email.
     */
    @SerializedName("phrase")
    val phrase: String,

    /**
     * Token secret key. This will return an empty string unless the response is returned using an API key or as part of a webhook payload.
     */
    @SerializedName("secret")
    val secret: String,

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
        "phrase" to phrase as Any,
        "secret" to secret as Any,
        "userId" to userId as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Token(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            expire = map["expire"] as String,
            phrase = map["phrase"] as String,
            secret = map["secret"] as String,
            userId = map["userId"] as String,
        )
    }
}