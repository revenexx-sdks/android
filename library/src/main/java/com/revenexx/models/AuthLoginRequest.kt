package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthLoginRequest(
    /**
     * The buyer's login address — the same one the contact carries.
     */
    @SerializedName("email")
    val email: String,

    /**
     * The password from registration or recovery. Wrong credentials are a 401; a correct one on an undecided application is a 403.
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