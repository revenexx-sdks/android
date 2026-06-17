package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMeResponse(
    /**
     * 
     */
    @SerializedName("contact")
    var contact: Contact?,

    /**
     * 
     */
    @SerializedName("user")
    var user: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact" to contact.toMap() as Any,
        "user" to user as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthMeResponse(
            contact = Contact.from(map = map["contact"] as Map<String, Any>),
            user = map["user"] as? Any,
        )
    }
}