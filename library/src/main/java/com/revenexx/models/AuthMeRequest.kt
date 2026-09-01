package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMeRequest(
    /**
     * Optional session to verify. Pass it to ask "is this session still alive?" (a revoked one is then a 401); omit it to only ask who a user is.
     */
    @SerializedName("session_id")
    var session_id: String?,

    /**
     * The platform user to resolve — `session.userId` from the login.
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
        ) = AuthMeRequest(
            session_id = map["session_id"] as? String,
            user_id = map["user_id"] as String,
        )
    }
}