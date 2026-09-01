package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketsVocabularyTone
import com.revenexx.enums.MarketsVocabularyName
import com.revenexx.enums.MarketsVocabularySource

/**
 * One closed value set this app owns, parsed out of the CHECK constraint in schema.json — the served set IS the enforced set. `closed: true` means a client may treat anything outside `values` as stale data.
 */
data class MarketsVocabulary(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Always true here: the values come from a CHECK constraint, so the list is exhaustive.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone a value that carries none falls back to.
     */
    @SerializedName("default_tone")
    var default_tone: MarketsVocabularyTone?,

    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: MarketsVocabularyName?,

    /**
     * Where the values came from. 'schema' = a CHECK constraint in this app's own schema.json.
     */
    @SerializedName("source")
    var source: MarketsVocabularySource?,

    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Every value the column may hold, in the order the CHECK constraint lists them — which is the order a select box should offer them in. Exhaustive, because `closed` is true.
     */
    @SerializedName("values")
    var values: List<MarketsVocabularyValue>?,

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
        ) = MarketsVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = MarketsVocabularyTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? String,
            name = MarketsVocabularyName.values().find { it.value == (map["name"] as? String) } ?: null,
            source = MarketsVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? String,
            values = (map["values"] as List<Map<String, Any>>).map { MarketsVocabularyValue.from(map = it) },
        )
    }
}