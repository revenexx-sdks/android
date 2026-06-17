package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMeRequest(
    /**
     * Optional session to verify — answers 401 when the session is expired or revoked.
     */
    @SerializedName("session_id")
    var session_id: String?,

    /**
     * 
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