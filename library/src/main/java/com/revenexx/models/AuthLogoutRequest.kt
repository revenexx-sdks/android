package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthLogoutRequest(
    /**
     * The session to revoke — `session.$id` from the login.
     */
    @SerializedName("session_id")
    val session_id: String,

    /**
     * The platform user — `session.userId` from the login.
     */
    @SerializedName("user_id")
    val user_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "session_id" to session_id as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthLogoutRequest(
            session_id = map["session_id"] as String,
            user_id = map["user_id"] as String,
        )
    }
}