package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PaymentVocabularyTone

/**
 * One permitted value, with the words and the colour a human reads for it.
 */
data class PaymentVocabularyValue(
    /**
     * One sentence on what the value means, or null where the key speaks for itself. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * This value ends the lifecycle — the honest way to ask "is this still open?" instead of matching status names.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * The value exactly as the database stores it — what a filter sends and what a row carries.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * The label to show for this value. A plain string, or a locale map keyed by language tag ({ "en": …, "de": … }). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * What the state MEANS, semantically: neutral, info, success, warning or danger. The client decides what each one looks like in its own design system.
     */
    @SerializedName("tone")
    var tone: PaymentVocabularyTone?,

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
        ) = PaymentVocabularyValue(
            description = map["description"] as? Any,
            xfinal = map["final"] as? Boolean,
            key = map["key"] as? String,
            title = map["title"] as? Any,
            tone = PaymentVocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}