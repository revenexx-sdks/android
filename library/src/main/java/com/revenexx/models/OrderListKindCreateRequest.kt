package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderListKindTone

/**
 * 
 */
data class OrderListKindCreateRequest(
    /**
     * What `lists.kind` will store. Lowercased on the way in and immutable afterwards — a merchant who wants a different code creates a new kind and moves the lists over.
     */
    @SerializedName("code")
    val code: String,

    /**
     * What this kind is for, in one sentence — the line a select shows under the title.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Localized descriptions, keyed by language tag.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * Promote this kind; the previous default is demoted.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized titles, keyed by language tag.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Where the kind sits in a select, ascending. Omitted means 0, which puts it first among the unpositioned.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * What a person reads. `labels` adds the localized forms on top; this one is the fallback.
     */
    @SerializedName("title")
    val title: String,

    /**
     * Semantic badge colour. The client owns what each tone looks like; omitted means `neutral`.
     */
    @SerializedName("tone")
    var tone: OrderListKindTone?,

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
        ) = OrderListKindCreateRequest(
            code = map["code"] as String,
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as String,
            tone = OrderListKindTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}