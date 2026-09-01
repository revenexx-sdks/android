package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderVocabularySummaryName

/**
 * One vocabulary, named and titled but without its values.
 */
data class OrderVocabularySummary(
    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Vocabulary name, unique within the app.
     */
    @SerializedName("name")
    var name: OrderVocabularySummaryName?,

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
        ) = OrderVocabularySummary(
            description = map["description"] as? String,
            name = OrderVocabularySummaryName.values().find { it.value == (map["name"] as? String) } ?: null,
            title = map["title"] as? String,
        )
    }
}