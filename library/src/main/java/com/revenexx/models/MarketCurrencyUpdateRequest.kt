package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class MarketCurrencyUpdateRequest(
    /**
     * ISO 4217 code, unique per market — one entry in the set of currencies this market TRADES in, as opposed to the single base currency on the market row that its prices are quoted in. The base currency must appear here or the market cannot serve; clone and backfill register it for you.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The currency offered first to a buyer who states no preference. At most one per market, and it should be the market's base currency — readiness reports it as a warning when it is not.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Sort position among this market's currencies, ascending, default 0 — the order a currency switcher lists them in.
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_default" to is_default as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCurrencyUpdateRequest(
            code = map["code"] as? String,
            is_default = map["is_default"] as? Boolean,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}