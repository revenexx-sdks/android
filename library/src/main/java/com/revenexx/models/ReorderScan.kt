package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReorderScan(
    /**
     * One entry per published event, in the order they went out. Re-running the scan on the same day returns the SAME ids and publishes nothing a second time — the event id is derived from the row and the day, and the bus drops the repeat.
     */
    @SerializedName("emitted")
    val emitted: List<ReorderScanEmit>,

    /**
     * false when reorder_alert_enabled is off — nothing was published, and not because nothing is low.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * How many rows were at or below their point when the scan ran.
     */
    @SerializedName("scanned")
    val scanned: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "emitted" to emitted.map { it.toMap() } as Any,
        "enabled" to enabled as Any,
        "scanned" to scanned as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReorderScan(
            emitted = (map["emitted"] as List<Map<String, Any>>).map { ReorderScanEmit.from(map = it) },
            enabled = map["enabled"] as Boolean,
            scanned = (map["scanned"] as Number).toLong(),
        )
    }
}