package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One vocabulary, named and titled.
 */
data class ShippingVocabularyIndexEntry(
    /**
     * What the vocabulary is for. Either one string or a locale map keyed by locale (e.g. {en, de}) — curated copy carries the map, a value falling back to its own key carries the string.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * The part after the dot in the qualified id — what GET /shipping/vocabularies/{name} takes.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * What the vocabulary is called. Either one string or a locale map keyed by locale (e.g. {en, de}) — curated copy carries the map, a value falling back to its own key carries the string.
     */
    @SerializedName("title")
    var title: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "name" to name as Any,
        "title" to title as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingVocabularyIndexEntry(
            description = map["description"] as? String,
            name = map["name"] as? String,
            title = map["title"] as? String,
        )
    }
}