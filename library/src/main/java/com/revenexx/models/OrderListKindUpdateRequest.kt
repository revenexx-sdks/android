package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderListKindTone

/**
 * 
 */
data class OrderListKindUpdateRequest(
    /**
     * What this kind is for, in one sentence. Explicit null clears it.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Localized descriptions, keyed by language tag. Replaces the whole map rather than merging into it.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * True promotes this kind and demotes the previous default — the same move POST /orderlists/kinds/{id}/make-default makes on its own.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized titles, keyed by language tag. Replaces the whole map rather than merging into it.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Where the kind sits in a select, ascending.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * What a person reads. A blank title is ignored rather than stored — a kind with no words is unreadable in every UI.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Semantic badge colour. The client owns what each tone looks like.
     */
    @SerializedName("tone")
    var tone: OrderListKindTone?,

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
        ) = OrderListKindUpdateRequest(
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as? String,
            tone = OrderListKindTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}