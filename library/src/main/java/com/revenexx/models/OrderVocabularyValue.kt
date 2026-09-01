package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderResolutionStage
import com.revenexx.enums.OrderVocabularyTone

/**
 * One permitted value with the words and the badge tone a client should render for it.
 */
data class OrderVocabularyValue(
    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("description")
    var description: String?,

    /**
     * True when this value ENDS the lifecycle. Lets a reader ask "is this order still open?" instead of matching status names it guessed.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * The value as stored — exactly what the CHECK constraint permits.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * Only on 'return-resolutions': which return transition accepts this value. A settlement word on the refusal dialog is how the two sets got mixed up.
     */
    @SerializedName("stage")
    var stage: OrderResolutionStage?,

    /**
     * Either one string, or a map of locale to string ({"en": …, "de": …}).
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Semantic badge colour. The client owns what each tone looks like.
     */
    @SerializedName("tone")
    var tone: OrderVocabularyTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "final" to xfinal as Any,
        "key" to key as Any,
        "stage" to stage?.value as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderVocabularyValue(
            description = map["description"] as? String,
            xfinal = map["final"] as? Boolean,
            key = map["key"] as? String,
            stage = OrderResolutionStage.values().find { it.value == (map["stage"] as? String) } ?: null,
            title = map["title"] as? String,
            tone = OrderVocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}