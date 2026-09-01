package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.InventoryVocabularyDefaultTone
import com.revenexx.enums.InventoryVocabularySource

/**
 * 
 */
data class InventoryVocabulary(
    /**
     * This app's name — the part before the dot in the qualified id.
     */
    @SerializedName("app")
    var app: String?,

    /**
     * True when these values are the complete permitted set, because they were read out of a CHECK constraint. A value outside a closed set is therefore stale data, not a missing label — which is what lets a client show it as an error instead of inventing a title for it.
     */
    @SerializedName("closed")
    var closed: Boolean?,

    /**
     * The tone a value gets when nobody has labelled it — a value added to the CHECK constraint is served with its key humanized and this tone, rather than not being served at all.
     */
    @SerializedName("default_tone")
    var default_tone: InventoryVocabularyDefaultTone?,

    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * The vocabulary name, echoed — the part after the dot in the qualified id.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Where the words come from: 'schema' — the app's own, read from the constraint. Nothing here is renameable per tenant, so a client may cache it per app version.
     */
    @SerializedName("source")
    var source: InventoryVocabularySource?,

    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Every permitted value, IN CONSTRAINT ORDER — which is lifecycle order for a status, so a UI can render the steps in the order they happen.
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
        ) = InventoryVocabulary(
            app = map["app"] as? String,
            closed = map["closed"] as? Boolean,
            default_tone = InventoryVocabularyDefaultTone.values().find { it.value == (map["default_tone"] as? String) } ?: null,
            description = map["description"] as? Any,
            name = map["name"] as? String,
            source = InventoryVocabularySource.values().find { it.value == (map["source"] as? String) } ?: null,
            title = map["title"] as? Any,
            values = map["values"] as? List<Any>,
        )
    }
}