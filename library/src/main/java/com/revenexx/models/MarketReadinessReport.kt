package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Can this market actually trade? `ready` is false only when a BLOCKING check failed — no currency to quote in, no tax class to tax with. Warnings are degraded-but-serviceable. The market it is about, what it is made of, and the verdict — the readiness block is inlined here rather than nested, so this is MarketReadiness plus two keys.
 */
data class MarketReadinessReport(
    /**
     * Ids of the checks that failed BLOCKING — the market cannot do the job at all until each is fixed. Empty exactly when `ready` is true.
     */
    @SerializedName("blocking")
    var blocking: List<String>?,

    /**
     * Every check that ran, passed or failed, in a fixed order: locales, currencies, tax_classes, tax_basis. `blocking` and `warnings` are the failures from this list by id; this is where the reason lives.
     */
    @SerializedName("checks")
    var checks: List<MarketReadinessCheck>?,

    /**
     * How much of a market this market actually is. All three at zero is a market that is a row and nothing else — the state two of the three live markets on the platform were left in, and the reason /clone and /backfill exist.
     */
    @SerializedName("counts")
    var counts: MarketReadinessCounts?,

    /**
     * The market the verdict is about, identified rather than returned in full — the five columns a reader needs to know which market answered. Read GET /markets/{id} for the rest.
     */
    @SerializedName("market")
    var market: MarketReadinessSubject?,

    /**
     * `blocking` is empty. Deliberately not "every check passed": a market with one locale and no default flag on it is serviceable, and a verdict that cried wolf about that would be ignored on the day it mattered.
     */
    @SerializedName("ready")
    var ready: Boolean?,

    /**
     * true when the market's status is 'active'. An active market that is not ready is live and broken — that combination is the one worth an alert.
     */
    @SerializedName("serving")
    var serving: Boolean?,

    /**
     * Ids of the checks that failed as WARNINGS — degraded but serviceable, because something else covers for them. A missing locale is only a warning while the tenant declares a fallback_locale.
     */
    @SerializedName("warnings")
    var warnings: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "blocking" to blocking as Any,
        "checks" to checks?.map { it.toMap() } as Any,
        "counts" to counts?.toMap() as Any,
        "market" to market?.toMap() as Any,
        "ready" to ready as Any,
        "serving" to serving as Any,
        "warnings" to warnings as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketReadinessReport(
            blocking = map["blocking"] as? List<String>,
            checks = (map["checks"] as List<Map<String, Any>>).map { MarketReadinessCheck.from(map = it) },
            counts = MarketReadinessCounts.from(map = map["counts"] as Map<String, Any>),
            market = MarketReadinessSubject.from(map = map["market"] as Map<String, Any>),
            ready = map["ready"] as? Boolean,
            serving = map["serving"] as? Boolean,
            warnings = map["warnings"] as? List<String>,
        )
    }
}