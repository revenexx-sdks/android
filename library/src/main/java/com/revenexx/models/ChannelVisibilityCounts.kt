package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The three tallies, so a caller can log or alert on a batch without walking it.
 */
data class ChannelVisibilityCounts(
    /**
     * How many must not be. A batch where this equals `total` and the reason is no_channel_context means the channel did not resolve, not that the assortment is empty.
     */
    @SerializedName("hidden")
    var hidden: Long?,

    /**
     * How many rows were decided — the length of the `items` sent.
     */
    @SerializedName("total")
    var total: Long?,

    /**
     * How many may be shown.
     */
    @SerializedName("visible")
    var visible: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "hidden" to hidden as Any,
        "total" to total as Any,
        "visible" to visible as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelVisibilityCounts(
            hidden = (map["hidden"] as? Number)?.toLong(),
            total = (map["total"] as? Number)?.toLong(),
            visible = (map["visible"] as? Number)?.toLong(),
        )
    }
}