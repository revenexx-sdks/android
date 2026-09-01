package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthRecoveryConfirmRequest(
    /**
     * The new password. It replaces the old one immediately; existing sessions are the identity service's business, not this app's.
     */
    @SerializedName("password")
    val password: String,

    /**
     * The one-time secret from the mailed link. Only that value works — it is spent on first use and expires, and anything else is a 401, so no example here would be anything but a call that fails.
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
        "password" to password as Any,
        "secret" to secret as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthRecoveryConfirmRequest(
            password = map["password"] as String,
            secret = map["secret"] as String,
            user_id = map["user_id"] as String,
        )
    }
}