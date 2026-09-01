package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEntriesAdjustResponseRounding
import com.revenexx.enums.PriceEntriesAdjustResponseRoundingMode

/**
 * What the change did (or would do, on a dry run), plus the rounding policy it was computed under — so a dialog can show a merchant the before/after before it commits.
 */
data class PriceEntriesAdjustResponse(
    /**
     * Echo of the request: true means nothing was written.
     */
    @SerializedName("dry_run")
    var dry_run: Boolean?,

    /**
     * Priced entries the filter selected. On-request entries are never counted — a percentage of "ask us" is not a number.
     */
    @SerializedName("matched")
    var matched: Long?,

    /**
     * Decimals the new prices were rounded to before snapping — the tenant’s price_precision.
     */
    @SerializedName("precision")
    var precision: Long?,

    /**
     * The first 50 changes, before and after. `matched` says how many there were in total.
     */
    @SerializedName("preview")
    var preview: List<PriceAdjustPreviewRow>?,

    /**
     * true when more than 50 entries changed, so `preview` is a sample rather than the whole set.
     */
    @SerializedName("preview_truncated")
    var preview_truncated: Boolean?,

    /**
     * The price list this answer came out of — enough to link to it or to explain the number to a merchant ("this came from the dealer list").
     */
    @SerializedName("price_list")
    var price_list: PriceListRef?,

    /**
     * The price ending the results were snapped to — the request’s, or the tenant’s bulk_adjust_rounding where it sent none.
     */
    @SerializedName("rounding")
    var rounding: PriceEntriesAdjustResponseRounding?,

    /**
     * How they landed on the last decimal — the tenant’s rounding_mode.
     */
    @SerializedName("rounding_mode")
    var rounding_mode: PriceEntriesAdjustResponseRoundingMode?,

    /**
     * Rows actually written — 0 on a dry run, and a price that came out unchanged is not rewritten.
     */
    @SerializedName("updated")
    var updated: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "dry_run" to dry_run as Any,
        "matched" to matched as Any,
        "precision" to precision as Any,
        "preview" to preview?.map { it.toMap() } as Any,
        "preview_truncated" to preview_truncated as Any,
        "price_list" to price_list?.toMap() as Any,
        "rounding" to rounding?.value as Any,
        "rounding_mode" to rounding_mode?.value as Any,
        "updated" to updated as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesAdjustResponse(
            dry_run = map["dry_run"] as? Boolean,
            matched = (map["matched"] as? Number)?.toLong(),
            precision = (map["precision"] as? Number)?.toLong(),
            preview = (map["preview"] as List<Map<String, Any>>).map { PriceAdjustPreviewRow.from(map = it) },
            preview_truncated = map["preview_truncated"] as? Boolean,
            price_list = PriceListRef.from(map = map["price_list"] as Map<String, Any>),
            rounding = PriceEntriesAdjustResponseRounding.values().find { it.value == (map["rounding"] as? String) } ?: null,
            rounding_mode = PriceEntriesAdjustResponseRoundingMode.values().find { it.value == (map["rounding_mode"] as? String) } ?: null,
            updated = (map["updated"] as? Number)?.toLong(),
        )
    }
}