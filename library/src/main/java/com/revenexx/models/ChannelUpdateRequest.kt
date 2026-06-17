package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelStatus
import com.revenexx.enums.ChannelType

/**
 * Partial update — omitted fields keep their current value.
 */
data class ChannelUpdateRequest(
    /**
     * Stable channel code, unique per tenant (e.g. shop, punchout-acme).
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Mark as the default channel (default false).
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized display names keyed by locale.
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
     * Lifecycle status (default 'active').
     */
    @SerializedName("status")
    var status: ChannelStatus?,

    /**
     * Where business happens (default 'storefront').
     */
    @SerializedName("type")
    var type: ChannelType?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "status" to status?.value as Any,
        "type" to type?.value as Any,
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
            type = ChannelType.values().find { it.value == (map["type"] as? String) } ?: null,
        )
    }
}