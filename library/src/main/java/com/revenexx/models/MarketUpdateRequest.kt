package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketStatus

/**
 * Partial update — omitted fields keep their current value.
 */
data class MarketUpdateRequest(
    /**
     * Market code, unique per tenant, and the single most load-bearing string in this app: it IS the market scope slug. The Entity Scoping Engine publishes it as the `market` dimension (`scope_context.market` in the JWT), and every other commerce app — products, prices, orders, customers — stores THIS value to say which market a row belongs to. Renaming it re-keys that scope for everyone, so treat it as permanent. Accepted in place of the uuid on /readiness, /clone, /backfill and /make-default — but not on the item routes or /context, which take a uuid only.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Base currency this market quotes in — ISO 4217, and schema.json's own default is 'EUR'. This is the single currency prices are STATED in; the currencies collection under the market is the wider set it accepts. A base currency missing from that collection is a blocking readiness failure.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The tenant default market — what a call naming no market falls back to. Exactly one market holds it; move it with POST /markets/{id}/make-default rather than by writing this flag, which does not demote the market that currently holds it.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized display names for storefronts, keyed by locale: a flat {locale: label} map, one level deep, string values. WHICH key to write is not free — GET /markets/{id}/context returns `locale_policy`, whose `write` is the key this tenant keys by (a full locale under regional granularity, a bare language under language granularity) and whose `read` is the order to try. Null means nothing is translated and `name` is all there is.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Display name, in the operator's own language. Cockpit copy only — nothing resolves a market by it.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort position among the tenant's markets, ascending, default 0. Presentation only — it decides the order the Cockpit and a market picker list them in, and nothing resolves a market by it.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Default 'active'. Only an active market serves a storefront; 'inactive' keeps the market and all its configuration but takes it out of service. Readiness reports an active market that cannot trade as `serving: true, ready: false` — live and broken.
     */
    @SerializedName("status")
    var status: MarketStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "currency" to currency as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketUpdateRequest(
            code = map["code"] as? String,
            currency = map["currency"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            status = MarketStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}