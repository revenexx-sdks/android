package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.VocabularyDefaultTone
import com.revenexx.enums.VocabularySource

/**
 * 
 */
data class Vocabulary(
    /**
     * This app's name — the part before the dot in the qualified id.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * True when the values are the complete permitted set. For a CHECK-backed vocabulary the constraint guarantees it; for a table-backed one the app refuses a value outside the rows, and for `locales` outside the configured list — the same guarantee by three mechanisms.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone an unlabelled value gets.
     */
    @SerializedName("default_tone")
    var default_tone: VocabularyDefaultTone?,

    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`. A curated label is a map; a value nobody labelled is humanized into a plain string.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * The vocabulary this is.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 'schema' — a CHECK constraint owns the set. 'table' — the tenant's own rows do. 'defaults' — a table-backed set the tenant never wrote down, answered from the built-ins. 'tenant' — the merchant configured the values through a setting (locales).
     */
    @SerializedName("source")
    var source: VocabularySource?,

    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`. A curated label is a map; a value nobody labelled is humanized into a plain string.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, in the order a select should offer them.
     */
    @SerializedName("values")
    var values: List<Any>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "closed" to closed as Any,
        "default_tone" to default_tone?.value as Any,
        "description" to description as Any,
        "name" to name as Any,
        "source" to source?.value as Any,
        "title" to title as Any,
        "values" to values as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Vocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = VocabularyDefaultTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = map["name"] as? String,
            source = VocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? Any,
            values = map["values"] as? List<Any>,
        )
    }
}