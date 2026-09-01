package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ChannelVisibilityRequest(
    /**
     * The channel `code` (the scope slug) to evaluate against, trimmed and lowercased before it is matched. Optional, and through api.revenexx.com it is the ONLY way to name a channel explicitly: the x-revenexx-channel header is not forwarded to the app, so without this the resolution falls through to the scope_context.channel claim and then to the tenant's default channel. A code no channel carries is not an error — the answer is resolved:false with reason 'unknown_channel', so a caller can tell it from an outage.
     */
    @SerializedName("channel")
    var channel: String?,

    /**
     * The rows to decide on, each with the channel assignments Baseline holds for it. POST /api/v1/scopes/lookup?dimension=channel answers in exactly this shape. At most 500 — Baseline's own lookup ceiling.
     */
    @SerializedName("items")
    val items: List<ChannelVisibilityItem>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel" to channel as Any,
        "items" to items.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelVisibilityRequest(
            channel = map["channel"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { ChannelVisibilityItem.from(map = it) },
        )
    }
}