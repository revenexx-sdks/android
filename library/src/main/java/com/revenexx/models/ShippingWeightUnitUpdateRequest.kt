package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingWeightUnitUpdateRequestTone

/**
 * Everything but the code. Sending a different code is a 400 rather than a silent no-op: renaming it would orphan every row that carries it.
 */
data class ShippingWeightUnitUpdateRequest(
    /**
     * The sentence under the title, explaining when to pick this weight unit. Null when the title says enough.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Localized descriptions. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     */
    @SerializedName("descriptions")
    var descriptions: Any?,

    /**
     * How many BASE units (kilograms) one of this unit weighs — a tonne is 1000, a gram 0.001, a pound 0.45359237. This number prices parcels: every weight matrix converts a request through it. Must be > 0; the base unit is fixed at 1 and rejects a change.
     */
    @SerializedName("factor")
    var factor: Double?,

    /**
     * Promote this value; the previous default is demoted. POST …/make-default does the same thing without an edit.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized titles. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Sort order in a select — the collection is returned in it.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * What an operator reads in a select. The name a merchant renames; the code underneath never moves.
     */
    @SerializedName("title")
    var title: String?,

    /**
     * Semantic badge colour for a UI listing the set. The client owns what each tone looks like.
     */
    @SerializedName("tone")
    var tone: ShippingWeightUnitUpdateRequestTone?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "description" to description as Any,
        "descriptions" to descriptions as Any,
        "factor" to factor as Any,
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
        ) = ShippingWeightUnitUpdateRequest(
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            factor = (map["factor"] as? Number)?.toDouble(),
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as? String,
            tone = ShippingWeightUnitUpdateRequestTone.values().find { it.value == (map["tone"] as? String) } ?: null,
        )
    }
}