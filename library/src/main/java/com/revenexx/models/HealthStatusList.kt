package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Status List
 */
data class HealthStatusList(
    /**
     * List of statuses.
     */
    @SerializedName("statuses")
    val statuses: List<HealthStatus>,

    /**
     * Total number of statuses that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "statuses" to statuses.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = HealthStatusList(
            statuses = (map["statuses"] as List<Map<String, Any>>).map { HealthStatus.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}