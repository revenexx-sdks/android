package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.LifecycleStageUpdateRequestTone

/**
 * Everything but `code`. Sending a different one is a 400 rather than a silent no-op, because records already store it.
 */
data class LifecycleStageUpdateRequest(
    /**
     * One line of help for whoever picks this value.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Localized descriptions, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `description`.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * Promote this value; the previous default is demoted.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Where it sits in the set, ascending.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The fallback name shown when no locale matches.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Semantic badge colour.
     */
    @SerializedName("tone")
    var tone: LifecycleStageUpdateRequestTone?,

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
        ) = LifecycleStageUpdateRequest(
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as? String,
            tone = LifecycleStageUpdateRequestTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}