package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEndingRule

/**
 * The quantity ladder (Staffelpreise) for ONE item, generated instead of typed: a price at the first tier and a discount compounded per tier. Identify the item with 'product_id' or 'sku'.
 */
data class PriceEntriesLadderRequest(
    /**
     * Price for ONE unit at the FIRST tier, in the list’s currency and on the list’s tax basis — a decimal amount in major units (19.90), never minor units/cents.
     */
    @SerializedName("base_price")
    val base_price: Double,

    /**
     * Discount applied per tier, COMPOUNDED down the ladder rather than off the base price: 5 gives 19.90 / 18.91 / 17.96. Default 0.
     */
    @SerializedName("discount_percent")
    var discount_percent: Double?,

    /**
     * The item the ladder prices.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Tier thresholds, ascending — an array of numbers or a comma-separated string ('1, 10, 50'). Duplicates are collapsed and the set is sorted. Default [1, 10, 50], at most 50 tiers.
     */
    @SerializedName("quantities")
    var quantities: List<Double>?,

    /**
     * Default true: the item's existing entries in this list are removed first, so the ladder IS the ladder. false appends.
     */
    @SerializedName("replace")
    var replace: Boolean?,

    /**
     * Ending the computed prices snap to (nearest match). Omit to use the tenant's bulk_adjust_rounding setting.
     */
    @SerializedName("rounding")
    var rounding: PriceEndingRule?,

    /**
     * The item the ladder prices (alternative to product_id).
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Unit of measure carried onto every generated tier. Free text, neither validated nor converted.
     */
    @SerializedName("unit")
    var unit: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "base_price" to base_price as Any,
        "discount_percent" to discount_percent as Any,
        "product_id" to product_id as Any,
        "quantities" to quantities as Any,
        "replace" to replace as Any,
        "rounding" to rounding?.value as Any,
        "sku" to sku as Any,
        "unit" to unit as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesLadderRequest(
            base_price = (map["base_price"] as Number).toDouble(),
            discount_percent = (map["discount_percent"] as? Number)?.toDouble(),
            product_id = map["product_id"] as? String,
            quantities = map["quantities"] as? List<Double>,
            replace = map["replace"] as? Boolean,
            rounding = PriceEndingRule.values().find { it.value == (map["rounding"] as? String) } ?: null,
            sku = map["sku"] as? String,
            unit = map["unit"] as? String,
        )
    }
}