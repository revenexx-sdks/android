package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Runtimes List
 */
data class RuntimeList(
    /**
     * List of runtimes.
     */
    @SerializedName("runtimes")
    val runtimes: List<Runtime>,

    /**
     * Total number of runtimes that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "runtimes" to runtimes.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RuntimeList(
            runtimes = (map["runtimes"] as List<Map<String, Any>>).map { Runtime.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}