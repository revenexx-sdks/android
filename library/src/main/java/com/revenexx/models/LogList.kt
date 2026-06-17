package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Logs List
 */
data class LogList(
    /**
     * List of logs.
     */
    @SerializedName("logs")
    val logs: List<Log>,

    /**
     * Total number of logs that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "logs" to logs.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LogList(
            logs = (map["logs"] as List<Map<String, Any>>).map { Log.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}