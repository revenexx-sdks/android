package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class RegistrationRejectRequest(
    /**
     * Who rejected it — recorded on the contact and carried in the event.
     */
    @SerializedName("decided_by")
    var decided_by: String?,

    /**
     * Why the application was declined. Always stored on the contact. It only reaches the APPLICANT when the tenant's registration_reason_disclosed setting is on — the event payload then carries it, and so does the 403 the login answers.
     */
    @SerializedName("reason")
    val reason: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "decided_by" to decided_by as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RegistrationRejectRequest(
            decided_by = map["decided_by"] as? String,
            reason = map["reason"] as String,
        )
    }
}