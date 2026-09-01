package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelStatus
import com.revenexx.enums.ChannelUnassignedVisibility

/**
 * Partial update — omitted fields keep their current value. At least one field is required.
 */
data class ChannelUpdateRequest(
    /**
     * Stable channel code, unique per tenant (e.g. shop, punchout-acme). It is the scope slug Baseline matches channel assignments on, so it is held to Baseline's own shape: lowercase a-z/0-9 first, then a-z/0-9/_/-, up to 63 characters. Anything else is refused — a code that cannot be a scope slug leaves the channel unable to filter.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Mark as the default channel (default false). At most one channel carries it — setting it demotes the previous holder.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized display names. A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Display name.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort position (default 0).
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Lifecycle status (default 'active'). Whether the channel is in service. What 'inactive' DOES is the tenant's inactive_channel_behavior setting: on 'serve' it is a label and the channel still resolves, on 'block' /channels/context answers resolved:false with reason 'channel_inactive'. Served as the 'channels.statuses' vocabulary.
     */
    @SerializedName("status")
    var status: ChannelStatus?,

    /**
     * Which channel type this is. One of the codes the tenant keeps under GET /channels/types — served with labels as the 'channels.types' vocabulary. Deliberately NOT an enum: the set is the tenant's own rows, not a CHECK constraint this repo could quote. A fresh install starts with storefront, punchout, marketplace, api, pos, which is why 'storefront' is the example here, but a merchant may rename or retire any of them and add their own (a feed or a print channel), so read the list rather than assuming it. Omitted on create it falls back to the type the tenant flagged as their default, never to a hardcoded value; a code the tenant does not keep is a 400 that names the ones they do.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * Default 'inherit'. What it means, IN THIS CHANNEL, that a row carries no channel assignment at all — the per-channel override of the tenant-wide unassigned_channel_visibility setting. 'inherit' (the default) takes the tenant's answer and changes nothing. 'all' shows unassigned rows: everything is on sale unless somebody carved it out, which is what an open storefront wants and what Baseline's is_visible() does today. 'assigned_only' hides them until they are explicitly assigned — the negotiated assortment a punchout contract describes, and the one answer the generated _scoped view has no way to express, which is why POST /channels/visibility exists to apply it. Rows that DO carry assignments are unaffected either way. Served with its labels as the 'channels.unassigned-visibility' vocabulary.
     */
    @SerializedName("unassigned_visibility")
    var unassigned_visibility: ChannelUnassignedVisibility?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "status" to status?.value as Any,
        "type" to type as Any,
        "unassigned_visibility" to unassigned_visibility?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelUpdateRequest(
            code = map["code"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            status = ChannelStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            type = map["type"] as? String,
            unassigned_visibility = ChannelUnassignedVisibility.values().find { it.value == (map["unassigned_visibility"] as? String) } ?: null,
        )
    }
}