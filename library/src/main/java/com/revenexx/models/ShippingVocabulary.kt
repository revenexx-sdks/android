package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingVocabularyDefaultTone
import com.revenexx.enums.ShippingVocabularySource

/**
 * 
 */
data class ShippingVocabulary(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * The set is exhaustive, so a value outside it is stale data rather than a missing label. True either way — what differs is who may extend it.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The badge colour a value that names none falls back to.
     */
    @SerializedName("default_tone")
    var default_tone: ShippingVocabularyDefaultTone?,

    /**
     * What the vocabulary is for. Either one string or a locale map keyed by locale (e.g. {en, de}) — curated copy carries the map, a value falling back to its own key carries the string.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * The vocabulary name — the part after the dot in the qualified id.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 'schema' — the values are a CHECK constraint's, so the served set IS the enforced set. 'table' — the values are the tenant's own rows, read per request.
     */
    @SerializedName("source")
    var source: ShippingVocabularySource?,

    /**
     * What the vocabulary is called. Either one string or a locale map keyed by locale (e.g. {en, de}) — curated copy carries the map, a value falling back to its own key carries the string.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Every permitted value, in the order a select should offer them — constraint order for a schema vocabulary, `position` for a table one.
     */
    @SerializedName("values")
    var values: List<ShippingVocabularyValue>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "closed" to closed as Any,
        "default_tone" to default_tone?.value as Any,
        "description" to description as Any,
        "name" to name as Any,
        "source" to source?.value as Any,
        "title" to title as Any,
        "values" to values?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = ShippingVocabularyDefaultTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? String,
            name = map["name"] as? String,
            source = ShippingVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? String,
            values = (map["values"] as List<Map<String, Any>>).map { ShippingVocabularyValue.from(map = it) },
        )
    }
}