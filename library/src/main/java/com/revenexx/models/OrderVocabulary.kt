package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderVocabularyTone
import com.revenexx.enums.OrderVocabularyName
import com.revenexx.enums.OrderVocabularySource

/**
 * 
 */
data class OrderVocabulary(
    /**
     * This app's name — the part before the dot in the qualified id.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * True when the values are the complete permitted set — always, since the routes enforce the ones the schema does not.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone an unlabelled value gets.
     */
    @SerializedName("default_tone")
    var default_tone: OrderVocabularyTone?,

    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Which vocabulary this is — echoed from the path, and the part after the dot in the qualified id.
     */
    @SerializedName("name")
    var name: OrderVocabularyName?,

    /**
     * Who enforces the set: 'schema' = a CHECK constraint, 'app' = the routes.
     */
    @SerializedName("source")
    var source: OrderVocabularySource?,

    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Every permitted value, in CONSTRAINT order — which for a status is lifecycle order, so a client can render them as a sequence without knowing one.
     */
    @SerializedName("values")
    var values: List<OrderVocabularyValue>?,

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
        ) = OrderVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = OrderVocabularyTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? String,
            name = OrderVocabularyName.values().find { it.value == (map["name"] as? String) } ?: null,
            source = OrderVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? String,
            values = (map["values"] as List<Map<String, Any>>).map { OrderVocabularyValue.from(map = it) },
        )
    }
}