package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMfaChallengeRequest(
    /**
     * Which factor to challenge. Defaults to `email`, the only one this route mails.
     */
    @SerializedName("factor")
    var factor: String?,

    /**
     * The platform user being challenged.
     */
    @SerializedName("user_id")
    val user_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "factor" to factor as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthMfaChallengeRequest(
            factor = map["factor"] as? String,
            user_id = map["user_id"] as String,
        )
    }
}