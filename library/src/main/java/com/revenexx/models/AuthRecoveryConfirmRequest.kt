package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthRecoveryConfirmRequest(
    /**
     * 
     */
    @SerializedName("password")
    val password: String,

    /**
     * 
     */
    @SerializedName("secret")
    val secret: String,

    /**
     * 
     */
    @SerializedName("user_id")
    val user_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "password" to password as Any,
        "secret" to secret as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthRecoveryConfirmRequest(
            password = map["password"] as String,
            secret = map["secret"] as String,
            user_id = map["user_id"] as String,
        )
    }
}