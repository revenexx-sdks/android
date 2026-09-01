package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ContactInviteRequest(
    /**
     * Who did the inviting, as the recipient should read it. Absent, the company name is used — "Beispiel GmbH invited you" reads better than the name of somebody they have never heard of.
     */
    @SerializedName("invited_by")
    var invited_by: String?,

    /**
     * Where the invitation points — the storefront sign-in, normally. There is no token in it: the person is already a member and only has to sign in.
     */
    @SerializedName("url")
    val url: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "invited_by" to invited_by as Any,
        "url" to url as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContactInviteRequest(
            invited_by = map["invited_by"] as? String,
            url = map["url"] as String,
        )
    }
}