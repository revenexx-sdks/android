package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Platform auth session. Treat `secret` as a credential — the trusted BFF stores it server-side (HTTP-only cookie), never in the browser.
 */
data class AuthSession(
    /**
     * The session id. Send it back as `session_id` to log out, or to have `/auth/me` check that the session is still alive.
     */
    @SerializedName("\$id")
    var id: String?,

    /**
     * When the session stops being valid on its own.
     */
    @SerializedName("expire")
    var expire: String?,

    /**
     * How the session was created. Server-minted sessions from this route are not the browser-facing email/password ones, so this says which mechanism issued it.
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * The session CREDENTIAL. Whoever holds it is logged in — the BFF keeps it server-side (an HTTP-only cookie), never in the browser and never in a log.
     */
    @SerializedName("secret")
    var secret: String?,

    /**
     * The platform user this session belongs to — the `user_id` every other auth route takes. NOT the contact id: the contact is in `contact`.
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