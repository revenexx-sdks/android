package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.FormsVocabularyTone

/**
 * 
 */
data class FormsVocabularyValue(
    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * The value ends the lifecycle.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * The value as the database stores and enforces it.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Semantic badge colour. The client owns what each tone looks like.
     */
    @SerializedName("tone")
    var tone: FormsVocabularyTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "final" to xfinal as Any,
        "key" to key as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormsVocabularyValue(
            description = map["description"] as? Any,
            xfinal = map["final"] as? Boolean,
            key = map["key"] as? String,
            title = map["title"] as? Any,
            tone = FormsVocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}