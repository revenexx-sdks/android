package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.LifecycleStageCreateRequestTone

/**
 * Add one value to the lifecycle stages set. It is available to `organizations.lifecycle_stage` immediately.
 */
data class LifecycleStageCreateRequest(
    /**
     * What `organizations.lifecycle_stage` will store. Lowercase, starting with a letter; immutable afterwards.
     */
    @SerializedName("code")
    val code: String,

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
     * Promote this value; the previous default is demoted in the same call.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized titles, keyed by language tag ({ "en": …, "de": … }). Null when nobody translated this value — a client then falls back to `title`.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Where it sits in the set, ascending. Default 0.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The fallback name shown when no locale matches.
     */
    @SerializedName("title")
    val title: String,

    /**
     * Semantic badge colour.
     */
    @SerializedName("tone")
    var tone: LifecycleStageCreateRequestTone?,

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
        ) = LifecycleStageCreateRequest(
            code = map["code"] as String,
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as String,
            tone = LifecycleStageCreateRequestTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}