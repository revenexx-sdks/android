package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketsVocabularySummaryName

/**
 * One vocabulary, enough to list it in a menu.
 */
data class MarketsVocabularySummary(
    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: MarketsVocabularySummaryName?,

    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("title")
    var title: String?,

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
        ) = MarketsVocabularySummary(
            description = map["description"] as? String,
            name = MarketsVocabularySummaryName.values().find { it.value == (map["name"] as? String) } ?: null,
            title = map["title"] as? String,
        )
    }
}