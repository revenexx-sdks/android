package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.RegistrationStatus

/**
 * 
 */
data class AuthRegisterResponse(
    /**
     * True when the tenant runs registration_mode='approval_required' — do NOT log the buyer in.
     */
    @SerializedName("approval_required")
    var approval_required: Boolean?,

    /**
     * The stored customer record — this app is its system of record.
     */
    @SerializedName("contact")
    var contact: Contact?,

    /**
     * 'pending' means the login is disabled until a merchant approves.
     */
    @SerializedName("registration_status")
    var registration_status: RegistrationStatus?,

    /**
     * The platform user that was created. Keep it: logout, /auth/me and the recovery confirm all take it.
     */
    @SerializedName("user_id")
    var user_id: String?,

    /**
     * Whether an address confirmation went out. True only when the tenant's `email_verification` asks for one on registration, the registration is a finished account rather than an application, and `verification_url` was supplied.
     */
    @SerializedName("verification_sent")
    var verification_sent: Boolean?,

    /**
     * Whether the tenant's welcome mail went out. Best effort on purpose: the account exists either way, and a registration is not undone because a message service was unreachable. False for an APPLICATION, which is not an account yet and is announced by `registration.submitted` instead.
     */
    @SerializedName("welcome_sent")
    var welcome_sent: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "approval_required" to approval_required as Any,
        "contact" to contact?.toMap() as Any,
        "registration_status" to registration_status?.value as Any,
        "user_id" to user_id as Any,
        "verification_sent" to verification_sent as Any,
        "welcome_sent" to welcome_sent as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthRegisterResponse(
            approval_required = map["approval_required"] as? Boolean,
            contact = Contact.from(map = map["contact"] as Map<String, Any>),
            registration_status = RegistrationStatus.values().find { it.value == (map["registration_status"] as? String) } ?: null,
            user_id = map["user_id"] as? String,
            verification_sent = map["verification_sent"] as? Boolean,
            welcome_sent = map["welcome_sent"] as? Boolean,
        )
    }
}