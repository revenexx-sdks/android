package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One rung of the winning list’s quantity ladder for this item.
 */
data class PriceTier(
    /**
     * The quantity this rung applies from. The rung with the highest `quantity_min` at or below the requested quantity is the one `unit_price` on the item was taken from.
     */
    @SerializedName("quantity_min")
    var quantity_min: Double?,

    /**
     * Unit of measure the rung’s price is per. Absent when the entry names none.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * The rung’s price for ONE unit, in the answer’s `currency` and on the item’s `tax_basis` — decimal major units, exactly as stored. Tiers are NOT tax-adjusted: only the chosen price gets `unit_price_net`/`unit_price_gross`.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "quantity_min" to quantity_min as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceTier(
            quantity_min = (map["quantity_min"] as? Number)?.toDouble(),
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
        )
    }
}