package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One vocabulary, named but not unpacked.
 */
data class PagesVocabularyRef(
    /**
     * What the set is for, or null. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * The name to fetch it by — the part after the dot in the qualified id.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * What this set of values is called. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

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
        ) = PagesVocabularyRef(
            description = map["description"] as? Any,
            name = map["name"] as? String,
            title = map["title"] as? Any,
        )
    }
}