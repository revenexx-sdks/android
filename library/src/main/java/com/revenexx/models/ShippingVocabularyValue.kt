package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingVocabularyTone

/**
 * 
 */
data class ShippingVocabularyValue(
    /**
     * What the value means. Either one string or a locale map keyed by locale (e.g. {en, de}) — curated copy carries the map, a value falling back to its own key carries the string.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Table-backed only: localized descriptions, keyed by locale.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * weight-units only: kilograms per unit. A weight vocabulary without it is a list of names you cannot convert with.
     */
    @SerializedName("factor")
    var factor: Double?,

    /**
     * The value ends the lifecycle.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * weight-units only: the unit every other factor is expressed in.
     */
    @SerializedName("is_base")
    var is_base: Boolean?,

    /**
     * Table-backed only: the value a caller falls back to, so a client can mark it without reading the settings as well.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Table-backed only: seeded on install. Still renameable and retirable.
     */
    @SerializedName("is_system")
    var is_system: Boolean?,

    /**
     * The value as the database stores it — what a column carries and what a filter matches. The only field a machine should compare on.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * Table-backed only: localized titles, keyed by locale. Absent for a vocabulary whose values come from a CHECK constraint — those carry their copy in `title` instead.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * What a person reads. Falls back to a humanized key. Either one string or a locale map keyed by locale (e.g. {en, de}) — curated copy carries the map, a value falling back to its own key carries the string.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Semantic badge colour. The client owns what each tone looks like.
     */
    @SerializedName("tone")
    var tone: ShippingVocabularyTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "descriptions" to descriptions as Any,
        "factor" to factor as Any,
        "final" to xfinal as Any,
        "is_base" to is_base as Any,
        "is_default" to is_default as Any,
        "is_system" to is_system as Any,
        "key" to key as Any,
        "labels" to labels as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingVocabularyValue(
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            factor = (map["factor"] as? Number)?.toDouble(),
            xfinal = map["final"] as? Boolean,
            is_base = map["is_base"] as? Boolean,
            is_default = map["is_default"] as? Boolean,
            is_system = map["is_system"] as? Boolean,
            key = map["key"] as? String,
            labels = map["labels"] as? Any,
            title = map["title"] as? String,
            tone = ShippingVocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}