package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthLoginResponse(
    /**
     * 
     */
    @SerializedName("contact")
    var contact: Contact?,

    /**
     * 
     */
    @SerializedName("session")
    var session: AuthSession?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact" to contact.toMap() as Any,
        "session" to session.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthLoginResponse(
            contact = Contact.from(map = map["contact"] as Map<String, Any>),
            session = AuthSession.from(map = map["session"] as Map<String, Any>),
        )
    }
}