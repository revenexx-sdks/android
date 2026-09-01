package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelVocabularyTone

/**
 * 
 */
data class ChannelVocabularyValue(
    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * Table-backed vocabularies only: the localized descriptions. A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * The value ends the lifecycle.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * Table-backed vocabularies only: the value a create falls back to.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Table-backed vocabularies only: seeded on install rather than added by the tenant. Still renameable and retirable.
     */
    @SerializedName("is_system")
    var is_system: Boolean?,

    /**
     * The value as the database stores and enforces it.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * Table-backed vocabularies only: the localized titles. `title` stays the fallback. A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Semantic badge colour. The client owns what each tone looks like.
     */
    @SerializedName("tone")
    var tone: ChannelVocabularyTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "descriptions" to descriptions as Any,
        "final" to xfinal as Any,
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
        ) = ChannelVocabularyValue(
            description = map["description"] as? Any,
            descriptions = map["descriptions"] as? Any,
            xfinal = map["final"] as? Boolean,
            is_default = map["is_default"] as? Boolean,
            is_system = map["is_system"] as? Boolean,
            key = map["key"] as? String,
            labels = map["labels"] as? Any,
            title = map["title"] as? Any,
            tone = ChannelVocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}