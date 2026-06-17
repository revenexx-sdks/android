package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Indexes List
 */
data class IndexList(
    /**
     * List of indexes.
     */
    @SerializedName("indexes")
    val indexes: List<Index>,

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
        ) = IndexList(
            indexes = (map["indexes"] as List<Map<String, Any>>).map { Index.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}