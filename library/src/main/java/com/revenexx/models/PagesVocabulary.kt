package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PagesVocabularyApp
import com.revenexx.enums.PagesVocabularyTone
import com.revenexx.enums.PagesVocabularyName
import com.revenexx.enums.PagesVocabularySource

/**
 * One vocabulary and every value it permits.
 */
data class PagesVocabulary(
    /**
     * Always 'pages'.
     */
    @SerializedName("app")
    var app: PagesVocabularyApp?,

    /**
     * The set is exhaustive, so a value outside it is stale data rather than a missing label.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The badge colour a value nobody toned falls back to.
     */
    @SerializedName("default_tone")
    var default_tone: PagesVocabularyTone?,

    /**
     * What the set is for, or null. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * The vocabulary name, echoed.
     */
    @SerializedName("name")
    var name: PagesVocabularyName?,

    /**
     * Always 'schema' — the values are parsed from the column's CHECK constraint, which is why the served set cannot drift from the enforced one.
     */
    @SerializedName("source")
    var source: PagesVocabularySource?,

    /**
     * What this set of values is called. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, in the order the constraint lists them — which is the order a select should offer.
     */
    @SerializedName("values")
    var values: List<PagesVocabularyValue>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app?.value as Any,
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
        ) = PagesVocabulary(
            app = PagesVocabularyApp.values().find { it.value == (map["app"] as? String) } ?: null,
            closed = map["closed"] as? Boolean,
            default_tone = PagesVocabularyTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = PagesVocabularyName.values().find { it.value == (map["name"] as? String) } ?: null,
            source = PagesVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? Any,
            values = (map["values"] as List<Map<String, Any>>).map { PagesVocabularyValue.from(map = it) },
        )
    }
}