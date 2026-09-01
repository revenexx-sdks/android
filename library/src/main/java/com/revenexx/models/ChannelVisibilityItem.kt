package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ChannelVisibilityItem(
    /**
     * The row's channel scope slugs. Empty or absent means unassigned — the case the policy decides.
     */
    @SerializedName("channels")
    var channels: List<String>?,

    /**
     * The row id, echoed back on the decision. Opaque to this app — it is never looked up, so any non-empty string is accepted and nothing has to exist. In practice it is the entity id POST /api/v1/scopes/lookup answered with, which is what the example shows.
     */
    @SerializedName("id")
    val id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channels" to channels as Any,
        "id" to id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelVisibilityItem(
            channels = map["channels"] as? List<String>,
            id = map["id"] as String,
        )
    }
}