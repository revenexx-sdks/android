package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReorderAlerts(
    /**
     * The rows at or below their reorder point, worst first (by `shortfall`). Computed on read, so it is never stale — and never empty because of caching: an empty list means nothing is low, unless `enabled` is false.
     */
    @SerializedName("alerts")
    var alerts: List<ReorderAlert>?,

    /**
     * false when reorder_alert_enabled is off — the list is then empty by policy, not because nothing is low.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * The threshold applied to rows carrying none of their own.
     */
    @SerializedName("reorder_point_default")
    var reorder_point_default: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "alerts" to alerts?.map { it.toMap() } as Any,
        "enabled" to enabled as Any,
        "reorder_point_default" to reorder_point_default as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReorderAlerts(
            alerts = (map["alerts"] as List<Map<String, Any>>).map { ReorderAlert.from(map = it) },
            enabled = map["enabled"] as? Boolean,
            reorder_point_default = (map["reorder_point_default"] as? Number)?.toDouble(),
        )
    }
}