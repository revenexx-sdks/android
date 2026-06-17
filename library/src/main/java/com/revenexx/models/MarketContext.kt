package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class MarketContext(
    /**
     * 
     */
    @SerializedName("currencies")
    var currencies: List<MarketCurrency>?,

    /**
     * 
     */
    @SerializedName("locales")
    var locales: List<MarketLocale>?,

    /**
     * 
     */
    @SerializedName("market")
    var market: Market?,

    /**
     * 
     */
    @SerializedName("tax_classes")
    var tax_classes: List<MarketTaxClass>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currencies" to currencies.map { it.toMap() } as Any,
        "locales" to locales.map { it.toMap() } as Any,
        "market" to market.toMap() as Any,
        "tax_classes" to tax_classes.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketContext(
            currencies = (map["currencies"] as List<Map<String, Any>>).map { MarketCurrency.from(map = it) },
            locales = (map["locales"] as List<Map<String, Any>>).map { MarketLocale.from(map = it) },
            market = Market.from(map = map["market"] as Map<String, Any>),
            tax_classes = (map["tax_classes"] as List<Map<String, Any>>).map { MarketTaxClass.from(map = it) },
        )
    }
}