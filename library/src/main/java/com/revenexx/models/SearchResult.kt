package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A Typesense search response, passed through verbatim.
 */
data class SearchResult<T>(
    /**
     * 
     */
    @SerializedName("facet_counts")
    var facet_counts: List<FacetCount<T>>?,

    /**
     * Total matching documents.
     */
    @SerializedName("found")
    var found: Long?,

    /**
     * 
     */
    @SerializedName("hits")
    var hits: List<SearchHit<T>>?,

    /**
     * Documents searched.
     */
    @SerializedName("out_of")
    var out_of: Long?,

    /**
     * 1-based page this result is for.
     */
    @SerializedName("page")
    var page: Long?,

    /**
     * 
     */
    @SerializedName("search_time_ms")
    var search_time_ms: Long?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "facet_counts" to facet_counts?.map { it.toMap() } as Any,
        "found" to found as Any,
        "hits" to hits?.map { it.toMap() } as Any,
        "out_of" to out_of as Any,
        "page" to page as Any,
        "search_time_ms" to search_time_ms as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            facet_counts: List<FacetCount<Map<String, Any>>>?,
            found: Long?,
            hits: List<SearchHit<Map<String, Any>>>?,
            out_of: Long?,
            page: Long?,
            search_time_ms: Long?,
            data: Map<String, Any>
        ) = SearchResult<Map<String, Any>>(
            facet_counts,
            found,
            hits,
            out_of,
            page,
            search_time_ms,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = SearchResult<T>(
            facet_counts = (map["facet_counts"] as List<Map<String, Any>>).map { FacetCount.from(map = it, nestedType) },
            found = (map["found"] as? Number)?.toLong(),
            hits = (map["hits"] as List<Map<String, Any>>).map { SearchHit.from(map = it, nestedType) },
            out_of = (map["out_of"] as? Number)?.toLong(),
            page = (map["page"] as? Number)?.toLong(),
            search_time_ms = (map["search_time_ms"] as? Number)?.toLong(),
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}