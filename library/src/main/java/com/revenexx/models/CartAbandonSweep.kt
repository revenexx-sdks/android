package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The first sweep: active carts nobody has touched since their market's window become abandoned. Nothing else in the platform ever stamps abandoned_at, so without this the abandonment funnel is empty by construction rather than empty because nobody abandons carts.
 */
data class CartAbandonSweep(
    /**
     * Carts actually marked. 0 on a dry run — see `found`.
     */
    @SerializedName("abandoned")
    var abandoned: Long?,

    /**
     * The abandon_after_minutes of the TENANT baseline — what a cart in no market ran on. 0 disables the sweep. Carts in a market were each held against their own market's window, which may differ from this.
     */
    @SerializedName("after_minutes")
    var after_minutes: Double?,

    /**
     * This pass looked at as many carts as one pass looks at, so there may be more behind them. The rest go on the next tick, oldest first — a backlog is visible here rather than merely slow.
     */
    @SerializedName("capped")
    var capped: Boolean?,

    /**
     * The carts this sweep touched, so a merchant can look at them before or after.
     */
    @SerializedName("cart_ids")
    var cart_ids: List<String>?,

    /**
     * Carts untouched since this instant were swept — the BASELINE cutoff. A run no longer has one cutoff, because each cart was held against its own market's clock; this is the one unassigned carts ran on.
     */
    @SerializedName("cutoff")
    var cutoff: String?,

    /**
     * At least one window in force (the baseline, or some market's). False means every applicable window was 0 and nothing was even considered.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Carts past their window. On a dry run this is the whole answer — `abandoned` stays 0.
     */
    @SerializedName("found")
    var found: Long?,

    /**
     * The market codes this pass came across, so an operator can see whose windows were actually in play. Empty when no examined cart belongs to a market.
     */
    @SerializedName("markets")
    var markets: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "abandoned" to abandoned as Any,
        "after_minutes" to after_minutes as Any,
        "capped" to capped as Any,
        "cart_ids" to cart_ids as Any,
        "cutoff" to cutoff as Any,
        "enabled" to enabled as Any,
        "found" to found as Any,
        "markets" to markets as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartAbandonSweep(
            abandoned = (map["abandoned"] as? Number)?.toLong(),
            after_minutes = (map["after_minutes"] as? Number)?.toDouble(),
            capped = map["capped"] as? Boolean,
            cart_ids = map["cart_ids"] as? List<String>,
            cutoff = map["cutoff"] as? String,
            enabled = map["enabled"] as? Boolean,
            found = (map["found"] as? Number)?.toLong(),
            markets = map["markets"] as? List<String>,
        )
    }
}