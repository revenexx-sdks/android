package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingWeightUnitRowTone

/**
 * 
 */
data class ShippingWeightUnitRow(
    /**
     * What a rate request names in `weight_unit`, and what a market's `weight_unit` setting stores. Immutable once created — renaming it would orphan every row carrying it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the row was created (UTC).
     */
    @SerializedName("created_at")
    var created_at: String?,

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
     * Row id, assigned by the database on insert.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The anchor every other factor is expressed in. Exactly one row, fixed at install, not writable and not deletable — moving it would silently reprice every weight matrix.
     */
    @SerializedName("is_base")
    var is_base: Boolean?,

    /**
     * The unit a market whose `weight_unit` setting is unset keys its tiers in. Exactly one row carries it.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Seeded on install rather than typed by the merchant. Still renameable and still deletable; it only says where the row came from.
     */
    @SerializedName("is_system")
    var is_system: Boolean?,

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
    var tone: ShippingWeightUnitRowTone?,

    /**
     * When the row was last written (UTC).
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "description" to description as Any,
        "descriptions" to descriptions as Any,
        "factor" to factor as Any,
        "id" to id as Any,
        "is_base" to is_base as Any,
        "is_default" to is_default as Any,
        "is_system" to is_system as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "title" to title as Any,
        "tone" to tone?.value as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingWeightUnitRow(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            description = map["description"] as? String,
            descriptions = map["descriptions"] as? Any,
            factor = (map["factor"] as? Number)?.toDouble(),
            id = map["id"] as? String,
            is_base = map["is_base"] as? Boolean,
            is_default = map["is_default"] as? Boolean,
            is_system = map["is_system"] as? Boolean,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            title = map["title"] as? String,
            tone = ShippingWeightUnitRowTone.values().find { it.value == (map["tone"] as? String) } ?: null,
            updated_at = map["updated_at"] as? String,
        )
    }
}