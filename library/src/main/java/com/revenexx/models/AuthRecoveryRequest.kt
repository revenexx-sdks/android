package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthRecoveryRequest(
    /**
     * 
     */
    @SerializedName("email")
    val email: String,

    /**
     * Redirect URL carrying userId + secret.
     */
    @SerializedName("url")
    val url: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
        "url" to url as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthRecoveryRequest(
            email = map["email"] as String,
            url = map["url"] as String,
        )
    }
}