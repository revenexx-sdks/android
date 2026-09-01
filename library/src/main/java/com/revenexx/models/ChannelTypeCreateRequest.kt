package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ChannelTypeTone

/**
 * 
 */
data class ChannelTypeCreateRequest(
    /**
     * What `channels.type` will store. Lowercased and trimmed before it is written, and fixed from then on — a rename would orphan every channel carrying it.
     */
    @SerializedName("code")
    val code: String,

    /**
     * One sentence on what kind of place this type of channel is, for the merchant choosing between them. Plain text, in the tenant's primary language; `descriptions` carries the per-locale ones.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * Promote this type; the previous default is demoted. The default is the type a channel created without one gets.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * A locale map keyed by language tag: {"en": …, "de": …}. Read the requested tag and fall back to the plain column beside it.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Sort position (default 0). GET /channels/types answers in this order; ties fall back to the code.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The fallback name. `labels` carries the per-locale ones.
     */
    @SerializedName("title")
    val title: String,

    /**
     * Badge colour (default 'neutral'). A value outside the palette is ignored rather than refused.
     */
    @SerializedName("tone")
    var tone: ChannelTypeTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
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
        ) = ChannelTypeCreateRequest(
            code = map["code"] as String,
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as String,
            tone = ChannelTypeTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}