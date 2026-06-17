package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Continents List
 */
data class ContinentList(
    /**
     * List of continents.
     */
    @SerializedName("continents")
    val continents: List<Continent>,

    /**
     * Total number of continents that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "continents" to continents.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContinentList(
            continents = (map["continents"] as List<Map<String, Any>>).map { Continent.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}