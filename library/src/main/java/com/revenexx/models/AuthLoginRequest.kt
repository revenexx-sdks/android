package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthLoginRequest(
    /**
     * 
     */
    @SerializedName("email")
    val email: String,

    /**
     * 
     */
    @SerializedName("password")
    val password: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
        "password" to password as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthLoginRequest(
            email = map["email"] as String,
            password = map["password"] as String,
        )
    }
}