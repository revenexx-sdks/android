package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelStatus
import com.revenexx.enums.ChannelUnassignedVisibility

/**
 * 
 */
data class Channel(
    /**
     * The scope slug Baseline matches channel assignments on (manifest.provides_scopes[].slug_source). Unique per tenant and, in practice, immutable — changing it orphans every assignment made against it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the row was inserted, set by the database.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Row id, and the only handle GET/PUT/DELETE /channels/{id} accept. Not the scope slug — that is `code`. No example is published because no id this app could invent names a row a tenant holds.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The channel a request that names none falls back to. At most one channel carries it.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Display name. `labels` carries the per-locale ones.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort position — ascending, and the tiebreak when two channels both claim is_default.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Whether the channel is in service. What 'inactive' DOES is the tenant's inactive_channel_behavior setting: on 'serve' it is a label and the channel still resolves, on 'block' /channels/context answers resolved:false with reason 'channel_inactive'. Served as the 'channels.statuses' vocabulary.
     */
    @SerializedName("status")
    var status: ChannelStatus?,

    /**
     * The tenant that owns this row. Added by the data plane, not by this app: it is not a column of schema.json, so it is read-only and `?tenant_id=` is not a filter — the key is silently dropped and never reaches the `filter` echo.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * One of the codes the tenant keeps under GET /channels/types — served with labels as the 'channels.types' vocabulary. Deliberately NOT an enum: the set is the tenant's own rows, not a CHECK constraint this repo could quote. A fresh install starts with storefront, punchout, marketplace, api, pos, which is why 'storefront' is the example here, but a merchant may rename or retire any of them and add their own (a feed or a print channel), so read the list rather than assuming it.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * What it means, IN THIS CHANNEL, that a row carries no channel assignment at all — the per-channel override of the tenant-wide unassigned_channel_visibility setting. 'inherit' (the default) takes the tenant's answer and changes nothing. 'all' shows unassigned rows: everything is on sale unless somebody carved it out, which is what an open storefront wants and what Baseline's is_visible() does today. 'assigned_only' hides them until they are explicitly assigned — the negotiated assortment a punchout contract describes, and the one answer the generated _scoped view has no way to express, which is why POST /channels/visibility exists to apply it. Rows that DO carry assignments are unaffected either way. Served with its labels as the 'channels.unassigned-visibility' vocabulary.
     */
    @SerializedName("unassigned_visibility")
    var unassigned_visibility: ChannelUnassignedVisibility?,

    /**
     * When the row was last written, set by the database.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "status" to status?.value as Any,
        "tenant_id" to tenant_id as Any,
        "type" to type as Any,
        "unassigned_visibility" to unassigned_visibility?.value as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Channel(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            status = ChannelStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tenant_id = map["tenant_id"] as? String,
            type = map["type"] as? String,
            unassigned_visibility = ChannelUnassignedVisibility.values().find { it.value == (map["unassigned_visibility"] as? String) } ?: null,
            updated_at = map["updated_at"] as? String,
        )
    }
}