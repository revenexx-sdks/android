package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingTaxUnresolvedReason
import com.revenexx.enums.ShippingTaxMarketSource
import com.revenexx.enums.ShippingTaxContextVia

/**
 * Tax resolution status of this answer. resolved=false ⇒ tax_class/tax_rate are unknown, NOT zero.
 */
data class ShippingTaxContext(
    /**
     * The market whose tax classes were applied.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Human-readable form of `reason`, safe to log or show an operator. One sentence per reason; the example is the `no_markets` wording.
     */
    @SerializedName("message")
    var message: String?,

    /**
     * Only when resolved=false — why no rate could be applied.
     */
    @SerializedName("reason")
    var reason: ShippingTaxUnresolvedReason?,

    /**
     * Whether a tax rate could be applied at all. FALSE means every rate's tax_class and tax_rate are UNKNOWN — not zero, and not tax-free. A checkout that adds 0 % on this is wrong; read `reason` and either ask for a market or refuse to quote.
     */
    @SerializedName("resolved")
    var resolved: Boolean?,

    /**
     * Where the market came from: 'request' (market_id), 'header' (x-revenexx-market), 'country' (the market matching the destination) or 'sole_market' (the tenant has exactly one).
     */
    @SerializedName("source")
    var source: ShippingTaxMarketSource?,

    /**
     * Present when the market is known but registers no tax classes and the tenant's default_shipping_tax_rate supplied the number instead.
     */
    @SerializedName("via")
    var via: ShippingTaxContextVia?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "market_id" to market_id as Any,
        "message" to message as Any,
        "reason" to reason?.value as Any,
        "resolved" to resolved as Any,
        "source" to source?.value as Any,
        "via" to via?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingTaxContext(
            market_id = map["market_id"] as? String,
            message = map["message"] as? String,
            reason = ShippingTaxUnresolvedReason.values().find { it.value == (map["reason"] as? String) } ?: null,
            resolved = map["resolved"] as? Boolean,
            source = ShippingTaxMarketSource.values().find { it.value == (map["source"] as? String) } ?: null,
            via = ShippingTaxContextVia.values().find { it.value == (map["via"] as? String) } ?: null,
        )
    }
}