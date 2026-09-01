package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.FormsVocabularyTone
import com.revenexx.enums.FormsVocabularyName

/**
 * 
 */
data class FormsVocabulary(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * The set is exhaustive.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone a value nobody gave one falls back to — what a badge looks like for a status that was added to the CHECK constraint before anyone styled it.
     */
    @SerializedName("default_tone")
    var default_tone: FormsVocabularyTone?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: FormsVocabularyName?,

    /**
     * Parsed from the CHECK constraint.
     */
    @SerializedName("source")
    var source: String?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, in constraint order — which is the order a select should offer them in, because it is the lifecycle order.
     */
    @SerializedName("values")
    var values: List<FormsVocabularyValue>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "closed" to closed as Any,
        "default_tone" to default_tone?.value as Any,
        "description" to description as Any,
        "name" to name?.value as Any,
        "source" to source as Any,
        "title" to title as Any,
        "values" to values?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormsVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = FormsVocabularyTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = FormsVocabularyName.values().find { it.value == (map["name"] as? String) } ?: null,
            source = map["source"] as? String,
            title = map["title"] as? Any,
            values = (map["values"] as List<Map<String, Any>>).map { FormsVocabularyValue.from(map = it) },
        )
    }
}