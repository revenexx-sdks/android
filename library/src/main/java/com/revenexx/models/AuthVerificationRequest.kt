package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthVerificationRequest(
    /**
     * Where the mailed link points. `userId`, `secret` and `expire` are appended as query parameters; the first two are what the confirm call takes.
     */
    @SerializedName("url")
    val url: String,

    /**
     * The platform user whose address is being confirmed — `user_id` from the registration, or `session.userId` from a login.
     */
    @SerializedName("user_id")
    val user_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "url" to url as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthVerificationRequest(
            url = map["url"] as String,
            user_id = map["user_id"] as String,
        )
    }
}