package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Every closed value set this app owns, by name — enough to build a menu of them without fetching each one.
 */
data class MarketsVocabularyIndex(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Every vocabulary this app publishes, named and titled but without its values — fetch one by name for those.
     */
    @SerializedName("vocabularies")
    var vocabularies: List<MarketsVocabularySummary>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "vocabularies" to vocabularies?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketsVocabularyIndex(
            app = map["app"] as? String,
            vocabularies = (map["vocabularies"] as List<Map<String, Any>>).map { MarketsVocabularySummary.from(map = it) },
        )
    }
}