package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class VocabularyRef(
    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * The name to pass to `GET /products/vocabularies/{name}`.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
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
        ) = VocabularyRef(
            description = map["description"] as? Any,
            name = map["name"] as? String,
            title = map["title"] as? Any,
        )
    }
}