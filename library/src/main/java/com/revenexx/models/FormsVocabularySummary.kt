package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.FormsVocabularySummaryName

/**
 * 
 */
data class FormsVocabularySummary(
    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: FormsVocabularySummaryName?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "name" to name?.value as Any,
        "title" to title as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormsVocabularySummary(
            description = map["description"] as? Any,
            name = FormsVocabularySummaryName.values().find { it.value == (map["name"] as? String) } ?: null,
            title = map["title"] as? Any,
        )
    }
}