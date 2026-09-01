package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelInactiveBehavior
import com.revenexx.enums.ChannelPolicySource
import com.revenexx.enums.ChannelPolicyTenantDefault
import com.revenexx.enums.ChannelUnassignedPolicy

/**
 * The visibility policy in force for the resolved channel.
 */
data class ChannelPolicy(
    /**
     * Always 'channel' — the scope dimension this app provides.
     */
    @SerializedName("dimension")
    var dimension: String?,

    /**
     * The header name Baseline uses for this dimension. Through api.revenexx.com it does NOT reach the app — the gateway builds a fresh request downstream and forwards only its own headers — so use `?channel=` (or `channel` in the body of POST /channels/visibility) instead. The header path applies to a direct in-cluster call to the app.
     */
    @SerializedName("header")
    var header: String?,

    /**
     * The tenant setting, echoed: what `status = 'inactive'` DOES. 'serve' makes it a label and the channel still resolves; 'block' makes resolution fail with reason 'channel_inactive', and the policy then falls back to the tenant answer.
     */
    @SerializedName("inactive_channel_behavior")
    var inactive_channel_behavior: ChannelInactiveBehavior?,

    /**
     * The claim path in the forwarded identity token that names the active channel, tried after the query and the header and before the default channel.
     */
    @SerializedName("jwt_path")
    var jwt_path: String?,

    /**
     * How Baseline matches the dimension — 'single': a request is in exactly one channel at a time, never a set.
     */
    @SerializedName("match_mode")
    var match_mode: String?,

    /**
     * The tenant setting, echoed: whether a request naming no channel is refused rather than falling back to the default channel. On POST /channels/visibility that refusal is the single 400 this app makes of its own accord.
     */
    @SerializedName("require_channel_context")
    var require_channel_context: Boolean?,

    /**
     * Whether the answer came from the tenant setting or this channel's own override. Only a channel that actually resolved gets a say — a blocked or unknown channel falls back to 'tenant'.
     */
    @SerializedName("source")
    var source: ChannelPolicySource?,

    /**
     * The tenant-wide baseline, so a caller can see what this channel overrode. Equal to `unassigned_visibility` whenever `source` is 'tenant'.
     */
    @SerializedName("tenant_default")
    var tenant_default: ChannelPolicyTenantDefault?,

    /**
     * What a row with NO channel assignment means. 'all' is Baseline's open-by-default semantic, reproduced exactly; 'assigned_only' is the closed assortment the _scoped view cannot express.
     */
    @SerializedName("unassigned_visibility")
    var unassigned_visibility: ChannelUnassignedPolicy?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "dimension" to dimension as Any,
        "header" to header as Any,
        "inactive_channel_behavior" to inactive_channel_behavior?.value as Any,
        "jwt_path" to jwt_path as Any,
        "match_mode" to match_mode as Any,
        "require_channel_context" to require_channel_context as Any,
        "source" to source?.value as Any,
        "tenant_default" to tenant_default?.value as Any,
        "unassigned_visibility" to unassigned_visibility?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelPolicy(
            dimension = map["dimension"] as? String,
            header = map["header"] as? String,
            inactive_channel_behavior = ChannelInactiveBehavior.values().find { it.value == (map["inactive_channel_behavior"] as? String) } ?: null,
            jwt_path = map["jwt_path"] as? String,
            match_mode = map["match_mode"] as? String,
            require_channel_context = map["require_channel_context"] as? Boolean,
            source = ChannelPolicySource.values().find { it.value == (map["source"] as? String) } ?: null,
            tenant_default = ChannelPolicyTenantDefault.values().find { it.value == (map["tenant_default"] as? String) } ?: null,
            unassigned_visibility = ChannelUnassignedPolicy.values().find { it.value == (map["unassigned_visibility"] as? String) } ?: null,
        )
    }
}