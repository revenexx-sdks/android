package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthLoginResponse(
    /**
     * The challenge to answer, when one was required. Send it back as `challenge_id`.
     */
    @SerializedName("challenge_id")
    var challenge_id: String?,

    /**
     * The customer record behind the login. Null when a platform user has no contact mirrored against it — a storefront should treat that as "signed in, but not a customer of this app".
     */
    @SerializedName("contact")
    var contact: Contact?,

    /**
     * Present and true when the tenant's `mfa_mode` is 'required'. The password was one of two things this buyer has to prove: a challenge has already been created and mailed, and the session above must NOT be treated as signed in until `PUT /customers/auth/mfa/challenge` confirms the code. The session travels anyway because answering needs it — the expected caller holds session material server-side, and this is the point at which that trust is used.
     */
    @SerializedName("mfa_required")
    var mfa_required: Boolean?,

    /**
     * A contact's effective grants, derived from its role on every read — nothing here is stored, so a role change can never leave a stale grant behind. Carried here so a BFF does not need a second call to decide what to render.
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
        "challenge_id" to challenge_id as Any,
        "contact" to contact?.toMap() as Any,
        "mfa_required" to mfa_required as Any,
        "permissions" to permissions?.toMap() as Any,
        "session" to session?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthLoginResponse(
            challenge_id = map["challenge_id"] as? String,
            contact = Contact.from(map = map["contact"] as Map<String, Any>),
            mfa_required = map["mfa_required"] as? Boolean,
            permissions = ContactPermissions.from(map = map["permissions"] as Map<String, Any>),
            session = AuthSession.from(map = map["session"] as Map<String, Any>),
        )
    }
}