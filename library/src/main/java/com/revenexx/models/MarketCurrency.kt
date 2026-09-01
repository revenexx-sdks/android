package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One currency a market accepts, as opposed to the single base currency on the market row that its prices are quoted in. The base currency must be registered here or the market cannot serve.
 */
data class MarketCurrency(
    /**
     * ISO 4217 code, unique per market — one entry in the set of currencies this market TRADES in, as opposed to the single base currency on the market row that its prices are quoted in. The base currency must appear here or the market cannot serve; clone and backfill register it for you.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the currency was registered on this market. Set by the database; never writable.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of this currency registration. The currency is named by `code` everywhere else.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The currency offered first to a buyer who states no preference. At most one per market, and it should be the market's base currency — readiness reports it as a warning when it is not.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * The market this currency belongs to. Filled from the route path on write and never read out of the body; ON DELETE CASCADE, so deleting the market deletes this row.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Sort position among this market's currencies, ascending, default 0 — the order a currency switcher lists them in.
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "market_id" to market_id as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCurrency(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            market_id = map["market_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}