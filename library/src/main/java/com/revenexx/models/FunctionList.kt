package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Functions List
 */
data class FunctionList(
    /**
     * List of functions.
     */
    @SerializedName("functions")
    val functions: List<Function>,

    /**
     * Total number of functions that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "functions" to functions.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FunctionList(
            functions = (map["functions"] as List<Map<String, Any>>).map { Function.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}