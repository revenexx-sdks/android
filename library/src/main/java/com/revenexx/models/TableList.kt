package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Tables List
 */
data class TableList(
    /**
     * List of tables.
     */
    @SerializedName("tables")
    val tables: List<Table>,

    /**
     * Total number of tables that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "tables" to tables.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TableList(
            tables = (map["tables"] as List<Map<String, Any>>).map { Table.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}