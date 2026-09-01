package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * How much of a market this market actually is. All three at zero is a market that is a row and nothing else — the state two of the three live markets on the platform were left in, and the reason /clone and /backfill exist.
 */
data class MarketReadinessCounts(
    /**
     * Traded currencies registered on this market.
     */
    @SerializedName("currencies")
    var currencies: Long?,

    /**
     * Locales registered on this market.
     */
    @SerializedName("locales")
    var locales: Long?,

    /**
     * Tax classes registered on this market.
     */
    @SerializedName("tax_classes")
    var tax_classes: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currencies" to currencies as Any,
        "locales" to locales as Any,
        "tax_classes" to tax_classes as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketReadinessCounts(
            currencies = (map["currencies"] as? Number)?.toLong(),
            locales = (map["locales"] as? Number)?.toLong(),
            tax_classes = (map["tax_classes"] as? Number)?.toLong(),
        )
    }
}