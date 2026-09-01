package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Child rows copied in from the source, per collection — only codes this market did not already carry. Zero everywhere on a second run: the call is idempotent.
 */
data class MarketBackfillAdded(
    /**
     * Traded currencies added from the source market.
     */
    @SerializedName("currencies")
    var currencies: Long?,

    /**
     * Locales added from the source market.
     */
    @SerializedName("locales")
    var locales: Long?,

    /**
     * Tax classes added from the source market.
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
        ) = MarketBackfillAdded(
            currencies = (map["currencies"] as? Number)?.toLong(),
            locales = (map["locales"] as? Number)?.toLong(),
            tax_classes = (map["tax_classes"] as? Number)?.toLong(),
        )
    }
}