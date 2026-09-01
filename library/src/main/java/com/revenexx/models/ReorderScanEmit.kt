package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReorderScanEmit(
    /**
     * The event id on the bus. Stable per (row, day), which is what makes a re-run harmless.
     */
    @SerializedName("event_id")
    val event_id: String,

    /**
     * The stock row the event is about.
     */
    @SerializedName("stock_level_id")
    val stock_level_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "event_id" to event_id as Any,
        "stock_level_id" to stock_level_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReorderScanEmit(
            event_id = map["event_id"] as String,
            stock_level_id = map["stock_level_id"] as String,
        )
    }
}