package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No required fields — send {}.
 */
data class RegistrationApproveRequest(
    /**
     * Who approved it — recorded on the contact and carried in the event. Free text (operator id or email); this app does not resolve it.
     */
    @SerializedName("decided_by")
    var decided_by: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "decided_by" to decided_by as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RegistrationApproveRequest(
            decided_by = map["decided_by"] as? String,
        )
    }
}