package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ContactInviteResponse(
    /**
     * Who was invited.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * Always true when this answers — a failure to send is a 502, not a false here.
     */
    @SerializedName("invited")
    var invited: Boolean?,

    /**
     * The company they were invited into.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact_id" to contact_id as Any,
        "invited" to invited as Any,
        "organization_id" to organization_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContactInviteResponse(
            contact_id = map["contact_id"] as? String,
            invited = map["invited"] as? Boolean,
            organization_id = map["organization_id"] as? String,
        )
    }
}