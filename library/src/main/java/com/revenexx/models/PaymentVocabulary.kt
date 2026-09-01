package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PaymentVocabularyTone

/**
 * One enum this app owns, with every permitted value.
 */
data class PaymentVocabulary(
    /**
     * The app that owns this vocabulary — always `payments` here. Together with `name` it forms the platform-wide key `payments.statuses`.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * True when the set comes from a CHECK constraint and is therefore exhaustive — a client may treat anything outside it as stale data rather than a missing label.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone a permitted value nobody labelled falls back to, so every value is renderable.
     */
    @SerializedName("default_tone")
    var default_tone: PaymentVocabularyTone?,

    /**
     * What this set of values is about. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * The vocabulary name, as it appears in the URL.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Where the values come from. `schema` means they were parsed out of the CHECK constraint, so what is served is what the database enforces.
     */
    @SerializedName("source")
    var source: String?,

    /**
     * The vocabulary's own label, for a filter heading or a column title. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, in constraint order — which is the lifecycle order an author wrote, and the order a select should offer.
     */
    @SerializedName("values")
    var values: List<PaymentVocabularyValue>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "closed" to closed as Any,
        "default_tone" to default_tone?.value as Any,
        "description" to description as Any,
        "name" to name as Any,
        "source" to source as Any,
        "title" to title as Any,
        "values" to values?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = PaymentVocabularyTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = map["name"] as? String,
            source = map["source"] as? String,
            title = map["title"] as? Any,
            values = (map["values"] as List<Map<String, Any>>).map { PaymentVocabularyValue.from(map = it) },
        )
    }
}