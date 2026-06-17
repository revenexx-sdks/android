package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Target list
 */
data class TargetList(
    /**
     * List of targets.
     */
    @SerializedName("targets")
    val targets: List<Target>,

    /**
     * Total number of targets that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "targets" to targets.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TargetList(
            targets = (map["targets"] as List<Map<String, Any>>).map { Target.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}