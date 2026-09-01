package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Rows this call added that were copied from nowhere, because the new market would otherwise have been left unable to trade: the tenant `fallback_locale` when neither market had a locale, and the base currency when it is not in the copied set. Zero on both is the normal, healthy answer — it means nothing had to be invented.
 */
data class MarketCloneSeeded(
    /**
     * 1 when the market's own base currency was registered because the copied set did not contain it; 0 otherwise.
     */
    @SerializedName("currencies")
    var currencies: Long?,

    /**
     * 1 when the tenant's fallback_locale was written as this market's only locale, marked default; 0 otherwise.
     */
    @SerializedName("locales")
    var locales: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currencies" to currencies as Any,
        "locales" to locales as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCloneSeeded(
            currencies = (map["currencies"] as? Number)?.toLong(),
            locales = (map["locales"] as? Number)?.toLong(),
        )
    }
}