package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Child rows copied from the source, per collection. A flag left false is a zero here, and so is a source that had none of that kind.
 */
data class MarketCloneCopied(
    /**
     * Traded currencies copied from the source market.
     */
    @SerializedName("currencies")
    var currencies: Long?,

    /**
     * Locales copied from the source market.
     */
    @SerializedName("locales")
    var locales: Long?,

    /**
     * Tax classes copied from the source market.
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
        ) = MarketCloneCopied(
            currencies = (map["currencies"] as? Number)?.toLong(),
            locales = (map["locales"] as? Number)?.toLong(),
            tax_classes = (map["tax_classes"] as? Number)?.toLong(),
        )
    }
}