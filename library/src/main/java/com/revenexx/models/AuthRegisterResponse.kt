package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthRegisterResponse(
    /**
     * 
     */
    @SerializedName("contact")
    var contact: Contact?,

    /**
     * 
     */
    @SerializedName("user_id")
    var user_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact" to contact.toMap() as Any,
        "user_id" to user_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthRegisterResponse(
            contact = Contact.from(map = map["contact"] as Map<String, Any>),
            user_id = map["user_id"] as? String,
        )
    }
}