package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderListVocabularyDefaultTone
import com.revenexx.enums.OrderListVocabularyName
import com.revenexx.enums.OrderListVocabularySource

/**
 * 
 */
data class OrderListVocabulary(
    /**
     * The app that owns this vocabulary.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * The set is exhaustive: a value outside it is stale data, not a missing label.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The badge colour a value carries when it names none of its own.
     */
    @SerializedName("default_tone")
    var default_tone: OrderListVocabularyDefaultTone?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: OrderListVocabularyName?,

    /**
     * 'schema' — a CHECK constraint owns the set; 'table' — the tenant's own rows do.
     */
    @SerializedName("source")
    var source: OrderListVocabularySource?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, in the order a select should offer them.
     */
    @SerializedName("values")
    var values: List<OrderListVocabularyValue>?,

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
        ) = OrderListVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = OrderListVocabularyDefaultTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = OrderListVocabularyName.values().find { it.value == (map["name"] as? String) } ?: null,
            source = OrderListVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? Any,
            values = (map["values"] as List<Map<String, Any>>).map { OrderListVocabularyValue.from(map = it) },
        )
    }
}