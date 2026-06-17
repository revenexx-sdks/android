package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Rows List
 */
data class RowList<T>(
    /**
     * List of rows.
     */
    @SerializedName("rows")
    val rows: List<Row<T>>,

    /**
     * Total number of rows that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "rows" to rows.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {
        operator fun invoke(
            rows: List<Row<Map<String, Any>>>,
            total: Long,
        ) = RowList<Map<String, Any>>(
            rows,
            total,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = RowList<T>(
            rows = (map["rows"] as List<Map<String, Any>>).map { Row.from(map = it, nestedType) },
            total = (map["total"] as Number).toLong(),
        )
    }
}