package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class SearchHit<T>(
    /**
     * The matching document; its properties are the collection's own fields.
     */
    @SerializedName("document")
    var document: Any?,

    /**
     * Per-field highlight snippets, keyed by field name.
     */
    @SerializedName("highlight")
    var highlight: Any?,

    /**
     * Relevance score.
     */
    @SerializedName("text_match")
    var text_match: Long?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "document" to document as Any,
        "highlight" to highlight as Any,
        "text_match" to text_match as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            document: Any?,
            highlight: Any?,
            text_match: Long?,
            data: Map<String, Any>
        ) = SearchHit<Map<String, Any>>(
            document,
            highlight,
            text_match,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = SearchHit<T>(
            document = map["document"] as? Any,
            highlight = map["highlight"] as? Any,
            text_match = (map["text_match"] as? Number)?.toLong(),
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}