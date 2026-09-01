package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketPricingSource
import com.revenexx.enums.MarketTaxBasis

/**
 * Whether a stored price in this market is NET or GROSS — the market layer of an answer the prices app also holds. A price list's own tax_basis wins over this; `tax_basis: null` with `source: 'unset'` means this market declares nothing and the reader must fall through to the tenant's own default.
 */
data class MarketPricing(
    /**
     * The raw `prices_include_tax` setting resolved for this market. Null means the market declares nothing — it is NOT a false, and turning it into one is the bug this key exists to prevent.
     */
    @SerializedName("prices_include_tax")
    var prices_include_tax: Boolean?,

    /**
     * Where the value came from. 'market' — configured on this market. 'tenant' — the market holds no value of its own and the tenant baseline answered. 'unset' — nothing is configured anywhere in this app, and the reader must fall through to the prices app's tax_inclusive_default.
     */
    @SerializedName("source")
    var source: MarketPricingSource?,

    /**
     * The same answer in the prices app's own vocabulary, so the two halves of the platform use one word: 'gross' means a stored price already contains tax, 'net' means tax is added on top. Null means fall through to the tenant's own default.
     */
    @SerializedName("tax_basis")
    var tax_basis: MarketTaxBasis?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "prices_include_tax" to prices_include_tax as Any,
        "source" to source?.value as Any,
        "tax_basis" to tax_basis?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketPricing(
            prices_include_tax = map["prices_include_tax"] as? Boolean,
            source = MarketPricingSource.values().find { it.value == (map["source"] as? String) } ?: null,
            tax_basis = MarketTaxBasis.values().find { it.value == (map["tax_basis"] as? String) } ?: null,
        )
    }
}