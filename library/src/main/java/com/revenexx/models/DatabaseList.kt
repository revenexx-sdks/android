package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Databases List
 */
data class DatabaseList(
    /**
     * List of databases.
     */
    @SerializedName("databases")
    val databases: List<Database>,

    /**
     * Total number of databases that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "databases" to databases.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = DatabaseList(
            databases = (map["databases"] as List<Map<String, Any>>).map { Database.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}