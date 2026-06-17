package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Column Indexes List
 */
data class ColumnIndexList(
    /**
     * List of indexes.
     */
    @SerializedName("indexes")
    val indexes: List<ColumnIndex>,

    /**
     * Total number of indexes that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "indexes" to indexes.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ColumnIndexList(
            indexes = (map["indexes"] as List<Map<String, Any>>).map { ColumnIndex.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}