package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketStatus

/**
 * A distinct business context within a tenant — a country, a region, or a storefront segment such as B2C vs B2B — with its own base currency, locales, traded currencies and tax classes. A market is also the platform's `market` SCOPE dimension: every other commerce app slices its data by one, keyed on this row's `code`. A market is never just this row: it needs at least one locale, one currency and one tax class before it can serve, which is what /readiness measures and what /clone and /backfill build.
 */
data class Market(
    /**
     * Market code, unique per tenant, and the single most load-bearing string in this app: it IS the market scope slug. The Entity Scoping Engine publishes it as the `market` dimension (`scope_context.market` in the JWT), and every other commerce app — products, prices, orders, customers — stores THIS value to say which market a row belongs to. Renaming it re-keys that scope for everyone, so treat it as permanent. Accepted in place of the uuid on /readiness, /clone, /backfill and /make-default — but not on the item routes or /context, which take a uuid only.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * When the market row was inserted. Set by the database; never writable.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Base currency this market quotes in — ISO 4217, and schema.json's own default is 'EUR'. This is the single currency prices are STATED in; the currencies collection under the market is the wider set it accepts. A base currency missing from that collection is a blocking readiness failure.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Primary key. Note that OTHER apps do not store this: the market scope dimension is keyed on `code` (manifest `provides_scopes.slug_source = markets.code`), so a row elsewhere that is "in this market" carries the code, not this uuid. It is the item routes and /context that want this value.
     */
    @SerializedName("id")
    var id: String?,

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

    /**
     * When the market row was last written. Set by the database on every update; never writable.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "status" to status?.value as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Market(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            status = MarketStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            updated_at = map["updated_at"] as? String,
        )
    }
}