package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Platform auth session. Treat `secret` as a credential — the trusted BFF stores it server-side (HTTP-only cookie), never in the browser.
 */
data class AuthSession(
    /**
     * 
     */
    @SerializedName("\$id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("expire")
    var expire: String?,

    /**
     * 
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * 
     */
    @SerializedName("secret")
    var secret: String?,

    /**
     * 
     */
    @SerializedName("userId")
    var userId: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$id" to id as Any,
        "expire" to expire as Any,
        "provider" to provider as Any,
        "secret" to secret as Any,
        "userId" to userId as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthSession(
            id = map["\$id"] as? String,
            expire = map["expire"] as? String,
            provider = map["provider"] as? String,
            secret = map["secret"] as? String,
            userId = map["userId"] as? String,
        )
    }
}