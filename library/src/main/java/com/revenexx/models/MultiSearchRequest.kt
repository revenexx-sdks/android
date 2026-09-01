package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Envelope for a federated search. Top-level search parameters outside `searches` are forwarded to Typesense unchanged and act as defaults for every entry.
 */
data class MultiSearchRequest<T>(
    /**
     * The searches to run, in order. Must not be empty.
     */
    @SerializedName("searches")
    val searches: List<MultiSearchEntry<T>>,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "searches" to searches.map { it.toMap() } as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            searches: List<MultiSearchEntry<Map<String, Any>>>,
            data: Map<String, Any>
        ) = MultiSearchRequest<Map<String, Any>>(
            searches,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = MultiSearchRequest<T>(
            searches = (map["searches"] as List<Map<String, Any>>).map { MultiSearchEntry.from(map = it, nestedType) },
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}