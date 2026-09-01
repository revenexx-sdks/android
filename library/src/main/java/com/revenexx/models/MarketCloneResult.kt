package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What was built. `copied` and `seeded` account for every child row that now exists, and `readiness` is the verdict on the result — so the call that made the market also tells you whether it finished the job.
 */
data class MarketCloneResult(
    /**
     * Child rows copied from the source, per collection. A flag left false is a zero here, and so is a source that had none of that kind.
     */
    @SerializedName("copied")
    var copied: MarketCloneCopied?,

    /**
     * A distinct business context within a tenant — a country, a region, or a storefront segment such as B2C vs B2B — with its own base currency, locales, traded currencies and tax classes. A market is also the platform's `market` SCOPE dimension: every other commerce app slices its data by one, keyed on this row's `code`. A market is never just this row: it needs at least one locale, one currency and one tax class before it can serve, which is what /readiness measures and what /clone and /backfill build.
     */
    @SerializedName("market")
    var market: Market?,

    /**
     * Can this market actually trade? `ready` is false only when a BLOCKING check failed — no currency to quote in, no tax class to tax with. Warnings are degraded-but-serviceable.
     */
    @SerializedName("readiness")
    var readiness: MarketReadiness?,

    /**
     * Rows this call added that were copied from nowhere, because the new market would otherwise have been left unable to trade: the tenant `fallback_locale` when neither market had a locale, and the base currency when it is not in the copied set. Zero on both is the normal, healthy answer — it means nothing had to be invented.
     */
    @SerializedName("seeded")
    var seeded: MarketCloneSeeded?,

    /**
     * The market that was read from, resolved — so a caller who passed a code back gets the uuid, and one who passed a uuid gets the code the rest of the platform stores.
     */
    @SerializedName("source")
    var source: MarketRef?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "copied" to copied?.toMap() as Any,
        "market" to market?.toMap() as Any,
        "readiness" to readiness?.toMap() as Any,
        "seeded" to seeded?.toMap() as Any,
        "source" to source?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCloneResult(
            copied = MarketCloneCopied.from(map = map["copied"] as Map<String, Any>),
            market = Market.from(map = map["market"] as Map<String, Any>),
            readiness = MarketReadiness.from(map = map["readiness"] as Map<String, Any>),
            seeded = MarketCloneSeeded.from(map = map["seeded"] as Map<String, Any>),
            source = MarketRef.from(map = map["source"] as Map<String, Any>),
        )
    }
}