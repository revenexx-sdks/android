package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceTaxUnresolvedReason
import com.revenexx.enums.PriceTaxMarketSource

/**
 * Tax resolution status of this answer. resolved=false ⇒ tax_class/tax_rate are unknown, NOT zero.
 */
data class PriceTaxContext(
    /**
     * The market whose tax classes were applied.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Human-readable form of `reason`, in English. Safe to log; not phrased for a buyer.
     */
    @SerializedName("message")
    var message: String?,

    /**
     * Only when resolved=false — why no rate could be applied.
     */
    @SerializedName("reason")
    var reason: PriceTaxUnresolvedReason?,

    /**
     * true ⇒ every priced item carries `tax_class`, `tax_rate`, `unit_price_net` and `unit_price_gross`. false ⇒ those are null because the rate could not be established — read `reason`, and never as "no tax due".
     */
    @SerializedName("resolved")
    var resolved: Boolean?,

    /**
     * Where the market came from: 'request' (market_id), 'header' (x-revenexx-market) or 'sole_market' (the tenant has exactly one).
     */
    @SerializedName("source")
    var source: PriceTaxMarketSource?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "market_id" to market_id as Any,
        "message" to message as Any,
        "reason" to reason?.value as Any,
        "resolved" to resolved as Any,
        "source" to source?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceTaxContext(
            market_id = map["market_id"] as? String,
            message = map["message"] as? String,
            reason = PriceTaxUnresolvedReason.values().find { it.value == (map["reason"] as? String) } ?: null,
            resolved = map["resolved"] as? Boolean,
            source = PriceTaxMarketSource.values().find { it.value == (map["source"] as? String) } ?: null,
        )
    }
}