package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthRecoveryRequest(
    /**
     * Who to send the recovery mail to. An address nobody holds is not distinguished here — do not build an account-existence check on the answer.
     */
    @SerializedName("email")
    val email: String,

    /**
     * Where the mailed link points. `userId`, `secret` and `expire` are appended as query parameters — the first two are what the confirm call takes. Same shape the identity service's own mail used, so a storefront that already handles that link needs no change.
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