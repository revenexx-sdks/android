package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelVisibilityReason

/**
 * 
 */
data class ChannelVisibilityDecision(
    /**
     * The id as it was sent, verbatim.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Why the row was shown or hidden — the answer is auditable, not a bare boolean.
     */
    @SerializedName("reason")
    var reason: ChannelVisibilityReason?,

    /**
     * Whether this row may be shown in the resolved channel. The same answer as membership in `visible`; `reason` says why.
     */
    @SerializedName("visible")
    var visible: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "reason" to reason?.value as Any,
        "visible" to visible as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelVisibilityDecision(
            id = map["id"] as? String,
            reason = ChannelVisibilityReason.values().find { it.value == (map["reason"] as? String) } ?: null,
            visible = map["visible"] as? Boolean,
        )
    }
}