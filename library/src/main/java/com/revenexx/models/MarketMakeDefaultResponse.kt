package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The market as it now stands, plus what had to move out of its way.
 */
data class MarketMakeDefaultResponse(
    /**
     * Codes of the markets that lost the flag. Empty when this market already held it — the call is idempotent and writes nothing on a repeat, so an empty array is a success, not a no-op that failed.
     */
    @SerializedName("demoted")
    var demoted: List<String>?,

    /**
     * A distinct business context within a tenant — a country, a region, or a storefront segment such as B2C vs B2B — with its own base currency, locales, traded currencies and tax classes. A market is also the platform's `market` SCOPE dimension: every other commerce app slices its data by one, keyed on this row's `code`. A market is never just this row: it needs at least one locale, one currency and one tax class before it can serve, which is what /readiness measures and what /clone and /backfill build.
     */
    @SerializedName("market")
    var market: Market?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "demoted" to demoted as Any,
        "market" to market?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketMakeDefaultResponse(
            demoted = map["demoted"] as? List<String>,
            market = Market.from(map = map["market"] as Map<String, Any>),
        )
    }
}