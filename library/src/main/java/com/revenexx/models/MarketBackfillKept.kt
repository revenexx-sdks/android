package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What this market already held BEFORE the repair, per collection — the rows that were left exactly as the merchant left them.
 */
data class MarketBackfillKept(
    /**
     * Traded currencies this market already held, untouched.
     */
    @SerializedName("currencies")
    var currencies: Long?,

    /**
     * Locales this market already held, untouched.
     */
    @SerializedName("locales")
    var locales: Long?,

    /**
     * Tax classes this market already held, untouched.
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
        ) = MarketBackfillKept(
            currencies = (map["currencies"] as? Number)?.toLong(),
            locales = (map["locales"] as? Number)?.toLong(),
            tax_classes = (map["tax_classes"] as? Number)?.toLong(),
        )
    }
}