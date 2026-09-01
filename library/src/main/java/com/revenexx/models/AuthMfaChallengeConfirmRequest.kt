package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMfaChallengeConfirmRequest(
    /**
     * The `$id` the send answered with.
     */
    @SerializedName("challenge_id")
    val challenge_id: String,

    /**
     * What the buyer typed.
     */
    @SerializedName("code")
    val code: String,

    /**
     * The same session the challenge was created with.
     */
    @SerializedName("session_secret")
    val session_secret: String,

    /**
     * The platform user, for the caller's own bookkeeping. The challenge already knows whose it is.
     */
    @SerializedName("user_id")
    var user_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "challenge_id" to challenge_id as Any,
        "code" to code as Any,
        "session_secret" to session_secret as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthMfaChallengeConfirmRequest(
            challenge_id = map["challenge_id"] as String,
            code = map["code"] as String,
            session_secret = map["session_secret"] as String,
            user_id = map["user_id"] as? String,
        )
    }
}