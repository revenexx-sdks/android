package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketsVocabularyTone

/**
 * One permitted value, with the copy and the badge tone a client renders it as.
 */
data class MarketsVocabularyValue(
    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("description")
    var description: String?,

    /**
     * A terminal state nothing moves out of.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * The value as stored in the column.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Semantic badge tone — the client decides what it looks like.
     */
    @SerializedName("tone")
    var tone: MarketsVocabularyTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "final" to xfinal as Any,
        "key" to key as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketsVocabularyValue(
            description = map["description"] as? String,
            xfinal = map["final"] as? Boolean,
            key = map["key"] as? String,
            title = map["title"] as? String,
            tone = MarketsVocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}