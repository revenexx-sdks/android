package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEndingRule
import com.revenexx.enums.PriceRoundingMode

/**
 * The generated ladder as stored, plus the rounding policy that shaped it.
 */
data class PriceEntriesLadderResponse(
    /**
     * The generated rungs, one per requested quantity, ascending — this IS the item's ladder in this list.
     */
    @SerializedName("entries")
    var entries: List<PriceEntry>?,

    /**
     * Decimals each tier was rounded to before snapping — the tenant's price_precision.
     */
    @SerializedName("precision")
    var precision: Long?,

    /**
     * true when the item's existing entries in this list were removed first (the default), so the answer is the whole ladder rather than an addition to one.
     */
    @SerializedName("replaced")
    var replaced: Boolean?,

    /**
     * The price ending each tier was snapped to — the request's, or the tenant's bulk_adjust_rounding.
     */
    @SerializedName("rounding")
    var rounding: PriceEndingRule?,

    /**
     * How they landed on the last decimal — the tenant's rounding_mode.
     */
    @SerializedName("rounding_mode")
    var rounding_mode: PriceRoundingMode?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "entries" to entries?.map { it.toMap() } as Any,
        "precision" to precision as Any,
        "replaced" to replaced as Any,
        "rounding" to rounding?.value as Any,
        "rounding_mode" to rounding_mode?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesLadderResponse(
            entries = (map["entries"] as List<Map<String, Any>>).map { PriceEntry.from(map = it) },
            precision = (map["precision"] as? Number)?.toLong(),
            replaced = map["replaced"] as? Boolean,
            rounding = PriceEndingRule.values().find { it.value == (map["rounding"] as? String) } ?: null,
            rounding_mode = PriceRoundingMode.values().find { it.value == (map["rounding_mode"] as? String) } ?: null,
        )
    }
}