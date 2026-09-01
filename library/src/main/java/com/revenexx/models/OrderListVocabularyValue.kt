package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderListVocabularyTone

/**
 * 
 */
data class OrderListVocabularyValue(
    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("description")
    var description: Any?,

    /**
     * Localized descriptions of a tenant-owned value, keyed by locale.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * The value ends the lifecycle. Always false for `kinds` — a list kind is not a state.
     */
    @SerializedName("xfinal")
    var xfinal: Boolean?,

    /**
     * The value a create falls back to, so a client can mark it without reading the settings as well.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Seeded on install rather than created by the tenant. Still renameable and retirable.
     */
    @SerializedName("is_system")
    var is_system: Boolean?,

    /**
     * The value as the database stores and enforces it — for `kinds`, the `code` a list carries.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * Localized titles of a tenant-owned value, keyed by locale.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * A plain string, or a locale map keyed by language tag ({"en": …, "de": …}). Read the requested tag, fall back to `en`.
     */
    @SerializedName("title")
    var title: Any?,

    /**
     * Semantic badge colour. The client owns what each tone looks like.
     */
    @SerializedName("tone")
    var tone: OrderListVocabularyTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "descriptions" to descriptions as Any,
        "final" to xfinal as Any,
        "is_default" to is_default as Any,
        "is_system" to is_system as Any,
        "key" to key as Any,
        "labels" to labels as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListVocabularyValue(
            description = map["description"] as? Any,
            descriptions = map["descriptions"] as? Any,
            xfinal = map["final"] as? Boolean,
            is_default = map["is_default"] as? Boolean,
            is_system = map["is_system"] as? Boolean,
            key = map["key"] as? String,
            labels = map["labels"] as? Any,
            title = map["title"] as? Any,
            tone = OrderListVocabularyTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}