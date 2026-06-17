package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Executions List
 */
data class ExecutionList(
    /**
     * List of executions.
     */
    @SerializedName("executions")
    val executions: List<Execution>,

    /**
     * Total number of executions that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "executions" to executions.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ExecutionList(
            executions = (map["executions"] as List<Map<String, Any>>).map { Execution.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}