package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Columns List
 */
data class ColumnList(
    /**
     * List of columns.
     */
    @SerializedName("columns")
    val columns: List<Any>,

    /**
     * Total number of columns in the given table.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "columns" to columns as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ColumnList(
            columns = map["columns"] as List<Any>,
            total = (map["total"] as Number).toLong(),
        )
    }
}