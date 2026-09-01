package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMagicLinkRequest(
    /**
     * Who to send the link to. An address that has never been seen creates an account rather than failing.
     */
    @SerializedName("email")
    val email: String,

    /**
     * Where the mailed link points. `userId`, `secret` and `expire` are appended as query parameters; the first two are what the confirm call takes.
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
        ) = AuthMagicLinkRequest(
            email = map["email"] as String,
            url = map["url"] as String,
        )
    }
}