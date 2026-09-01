package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.VocabularyTone

/**
 * 
 */
data class VocabularyValue(
    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * A terminal state — nothing moves out of it. False or absent on a vocabulary that is not a lifecycle.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * The value as it is STORED and as the CHECK admits it — what a filter or a write sends.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Which badge colour a UI should paint this value in.
     */
    @SerializedName("tone")
    var tone: VocabularyTone?,

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
        ) = VocabularyValue(
            description = map["description"] as? Any,
            xfinal = map["final"] as? Boolean,
            key = map["key"] as? String,
            title = map["title"] as? Any,
            tone = VocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}