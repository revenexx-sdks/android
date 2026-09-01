package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMagicLinkConfirmRequest(
    /**
     * The one-time secret the mailed link carried. Spent on first use and expiring, so a second attempt with the same one is a 401 rather than a second session.
     */
    @SerializedName("secret")
    val secret: String,

    /**
     * The `userId` the mailed link carried.
     */
    @SerializedName("user_id")
    val user_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "secret" to secret as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthMagicLinkConfirmRequest(
            secret = map["secret"] as String,
            user_id = map["user_id"] as String,
        )
    }
}