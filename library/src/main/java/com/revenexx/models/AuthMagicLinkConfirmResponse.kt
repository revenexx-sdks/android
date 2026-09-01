package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthMagicLinkConfirmResponse(
    /**
     * The customer record behind the login. Null when no contact is mirrored against the platform user yet — a sign-in link creates the account, not the customer.
     */
    @SerializedName("contact")
    var contact: Contact?,

    /**
     * A contact's effective grants, derived from its role on every read — nothing here is stored, so a role change can never leave a stale grant behind. Null when there is no contact to derive them from.
     */
    @SerializedName("permissions")
    var permissions: ContactPermissions?,

    /**
     * Platform auth session. Treat `secret` as a credential — the trusted BFF stores it server-side (HTTP-only cookie), never in the browser.
     */
    @SerializedName("session")
    var session: AuthSession?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact" to contact?.toMap() as Any,
        "permissions" to permissions?.toMap() as Any,
        "session" to session?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthMagicLinkConfirmResponse(
            contact = Contact.from(map = map["contact"] as Map<String, Any>),
            permissions = ContactPermissions.from(map = map["permissions"] as Map<String, Any>),
            session = AuthSession.from(map = map["session"] as Map<String, Any>),
        )
    }
}