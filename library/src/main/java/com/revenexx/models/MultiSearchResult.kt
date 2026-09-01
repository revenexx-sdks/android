package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class MultiSearchResult<T>(
    /**
     * One result per entry in `searches`, in the same order.
     */
    @SerializedName("results")
    val results: List<SearchResult<T>>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "results" to results.map { it.toMap() } as Any,
    )

    companion object {
        operator fun invoke(
            results: List<SearchResult<Map<String, Any>>>,
        ) = MultiSearchResult<Map<String, Any>>(
            results,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = MultiSearchResult<T>(
            results = (map["results"] as List<Map<String, Any>>).map { SearchResult.from(map = it, nestedType) },
        )
    }
}