package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMeResponse(
    /**
     * The customer record mirrored against this user, or null. A user with no contact resolves perfectly well — that is not the 404.
     */
    @SerializedName("contact")
    var contact: Contact?,

    /**
     * A contact's effective grants, derived from its role on every read — nothing here is stored, so a role change can never leave a stale grant behind. Null when there is no contact to derive them from.
     */
    @SerializedName("permissions")
    var permissions: ContactPermissions?,

    /**
     * The platform identity record, forwarded verbatim from the identity service. This app neither reshapes nor validates it, so treat unknown fields as forward-compatible; the ones named here are the ones this app itself writes and reads.
     */
    @SerializedName("user")
    var user: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact" to contact?.toMap() as Any,
        "permissions" to permissions?.toMap() as Any,
        "user" to user as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthMeResponse(
            contact = Contact.from(map = map["contact"] as Map<String, Any>),
            permissions = ContactPermissions.from(map = map["permissions"] as Map<String, Any>),
            user = map["user"] as? Any,
        )
    }
}