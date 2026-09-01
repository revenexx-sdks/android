package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelVocabularyTone
import com.revenexx.enums.ChannelVocabularyName
import com.revenexx.enums.ChannelVocabularySource

/**
 * 
 */
data class ChannelVocabulary(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * Always true: the set is exhaustive at this moment, so a value outside it is stale data rather than a missing label. For a table-backed vocabulary that is a statement about now, not forever — the tenant may add to it.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone a value that carries none falls back to.
     */
    @SerializedName("default_tone")
    var default_tone: ChannelVocabularyTone?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: ChannelVocabularyName?,

    /**
     * Who owns the value set. 'schema' = a CHECK constraint in this app's own schema.json; 'table' = the tenant's own rows.
     */
    @SerializedName("source")
    var source: ChannelVocabularySource?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, in author order — the order a select should offer, not alphabetical. For a CHECK-backed vocabulary that is the constraint's own order; for the table-backed `types` it is the tenant's `position` order.
     */
    @SerializedName("values")
    var values: List<ChannelVocabularyValue>?,

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
        ) = ChannelVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = ChannelVocabularyTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = ChannelVocabularyName.values().find { it.value == (map["name"] as? String) } ?: null,
            source = ChannelVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? Any,
            values = (map["values"] as List<Map<String, Any>>).map { ChannelVocabularyValue.from(map = it) },
        )
    }
}