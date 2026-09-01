package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelUnresolvedReason
import com.revenexx.enums.ChannelContextSource

/**
 * 
 */
data class ChannelVisibility(
    /**
     * The channel that resolved, or null. Null on every answer where `resolved` is false — including the everyday one on a tenant that has not created a channel yet.
     */
    @SerializedName("channel")
    var channel: String?,

    /**
     * The three tallies, so a caller can log or alert on a batch without walking it.
     */
    @SerializedName("counts")
    var counts: ChannelVisibilityCounts?,

    /**
     * More than one channel claims is_default; the lowest position wins and this says so.
     */
    @SerializedName("default_ambiguous")
    var default_ambiguous: Boolean?,

    /**
     * Just the ids that must NOT be shown. The complement of `visible`; together they are every id sent, so a caller can assert nothing was dropped.
     */
    @SerializedName("hidden")
    var hidden: List<String>?,

    /**
     * One decision per row sent, in the order they were sent, so a caller can zip it back onto its own list without matching on id.
     */
    @SerializedName("items")
    var items: List<ChannelVisibilityDecision>?,

    /**
     * The visibility policy in force for the resolved channel.
     */
    @SerializedName("policy")
    var policy: ChannelPolicy?,

    /**
     * Why not, when resolved is false. Null when it resolved.
     */
    @SerializedName("reason")
    var reason: ChannelUnresolvedReason?,

    /**
     * The channel code the request named, if any — lowercased and trimmed as it was matched.
     */
    @SerializedName("requested")
    var requested: String?,

    /**
     * Whether a channel could be resolved for this request.
     */
    @SerializedName("resolved")
    var resolved: Boolean?,

    /**
     * Where the channel came from, in the order they are tried: 'body' (the `channel` field, POST /channels/visibility only), 'query' (`?channel=`), 'header' (x-revenexx-channel), 'jwt' (the scope_context.channel claim), then 'default' (the channel flagged is_default). Null when nothing resolved. Note that 'header' is not reachable through api.revenexx.com: the gateway builds a fresh request to the app and copies a fixed set of headers into it, and x-revenexx-channel is not among them — see `policy.header`.
     */
    @SerializedName("source")
    var source: ChannelContextSource?,

    /**
     * Just the ids that may be shown, ready to filter a result set with — the same rows `items` marks visible:true, without the reasons.
     */
    @SerializedName("visible")
    var visible: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel" to channel as Any,
        "counts" to counts?.toMap() as Any,
        "default_ambiguous" to default_ambiguous as Any,
        "hidden" to hidden as Any,
        "items" to items?.map { it.toMap() } as Any,
        "policy" to policy?.toMap() as Any,
        "reason" to reason?.value as Any,
        "requested" to requested as Any,
        "resolved" to resolved as Any,
        "source" to source?.value as Any,
        "visible" to visible as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelVisibility(
            channel = map["channel"] as? String,
            counts = ChannelVisibilityCounts.from(map = map["counts"] as Map<String, Any>),
            default_ambiguous = map["default_ambiguous"] as? Boolean,
            hidden = map["hidden"] as? List<String>,
            items = (map["items"] as List<Map<String, Any>>).map { ChannelVisibilityDecision.from(map = it) },
            policy = ChannelPolicy.from(map = map["policy"] as Map<String, Any>),
            reason = ChannelUnresolvedReason.values().find { it.value == (map["reason"] as? String) } ?: null,
            requested = map["requested"] as? String,
            resolved = map["resolved"] as? Boolean,
            source = ChannelContextSource.values().find { it.value == (map["source"] as? String) } ?: null,
            visible = map["visible"] as? List<String>,
        )
    }
}