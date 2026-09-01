package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceVocabularyTone
import com.revenexx.enums.PriceVocabularyName
import com.revenexx.enums.PriceVocabularySource

/**
 * One closed value set with the words a human reads for it — so a UI never keeps its own copy of an enum this app enforces.
 */
data class PriceVocabulary(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Always true here: the values come from a CHECK constraint, so the list is exhaustive and a value outside it is stale data rather than a missing label.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone a value that carries none falls back to.
     */
    @SerializedName("default_tone")
    var default_tone: PriceVocabularyTone?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: PriceVocabularyName?,

    /**
     * Where the values came from. 'schema' = a CHECK constraint in this app's own schema.json.
     */
    @SerializedName("source")
    var source: PriceVocabularySource?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, in CHECK-constraint order — which is the order an author wrote and the order a select should offer.
     */
    @SerializedName("values")
    var values: List<PriceVocabularyValue>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "closed" to closed as Any,
        "default_tone" to default_tone?.value as Any,
        "description" to description as Any,
        "name" to name?.value as Any,
        "source" to source?.value as Any,
        "title" to title as Any,
        "values" to values?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = PriceVocabularyTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = PriceVocabularyName.values().find { it.value == (map["name"] as? String) } ?: null,
            source = PriceVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? Any,
            values = (map["values"] as List<Map<String, Any>>).map { PriceVocabularyValue.from(map = it) },
        )
    }
}