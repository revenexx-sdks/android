package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Collections List
 */
data class CollectionList(
    /**
     * List of collections.
     */
    @SerializedName("collections")
    val collections: List<Collection>,

    /**
     * Total number of collections that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "collections" to collections.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CollectionList(
            collections = (map["collections"] as List<Map<String, Any>>).map { Collection.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}