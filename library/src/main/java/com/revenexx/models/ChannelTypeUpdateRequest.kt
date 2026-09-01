package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelTypeTone

/**
 * 
 */
data class ChannelTypeUpdateRequest(
    /**
     * Replace the one-sentence description. Sent as null it is cleared; omitted it is kept. `descriptions` carries the per-locale ones.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * Promote this type; the previous default is demoted. Only `true` does anything — sending false does not demote this type, because some type must hold the flag.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Move the type in the order GET /channels/types answers in.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Rename the type. A blank or non-string title is ignored, not refused — the stored one is kept.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Change the badge colour. A value outside the palette is ignored rather than refused, and the stored tone is kept.
     */
    @SerializedName("tone")
    var tone: ChannelTypeTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "descriptions" to descriptions as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelTypeUpdateRequest(
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as? String,
            tone = ChannelTypeTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}