package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthOtpRequest(
    /**
     * Who to send the code to. As with the sign-in link, an unknown address creates an account rather than failing.
     */
    @SerializedName("email")
    val email: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthOtpRequest(
            email = map["email"] as String,
        )
    }
}