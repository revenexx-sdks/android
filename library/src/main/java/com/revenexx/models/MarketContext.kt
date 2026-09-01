package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The whole of one market: the row, its three collections, and the four resolved answers a client would otherwise have to work out for itself.
 */
data class MarketContext(
    /**
     * Every currency this market trades in, in position order. Capped at 200. The market's own base currency should be among them; readiness reports it as blocking when it is not.
     */
    @SerializedName("currencies")
    var currencies: List<MarketCurrency>?,

    /**
     * The locale a storefront should render this market in. `source` names where it came from: 'market' (a locale flagged is_default), 'market_first' (no flag — first by position) or 'tenant_fallback' (the market registers none; the tenant's fallback_locale setting answered).
     */
    @SerializedName("default_locale")
    var default_locale: MarketDefaultLocale?,

    /**
     * How this tenant keys its translations, resolved rather than named: the key a client WRITES and the order it READS, per locale. Emitting the resolved answer is the point — a client handed only the setting names re-implements the policy and gets it subtly different, which is how a label editor came to ask for de-DE while the row held de.
     */
    @SerializedName("locale_policy")
    var locale_policy: MarketLocalePolicy?,

    /**
     * Every locale this market registers, in position order. Capped at 200. Empty is a real answer — read `default_locale` before assuming a language.
     */
    @SerializedName("locales")
    var locales: List<MarketLocale>?,

    /**
     * A distinct business context within a tenant — a country, a region, or a storefront segment such as B2C vs B2B — with its own base currency, locales, traded currencies and tax classes. A market is also the platform's `market` SCOPE dimension: every other commerce app slices its data by one, keyed on this row's `code`. A market is never just this row: it needs at least one locale, one currency and one tax class before it can serve, which is what /readiness measures and what /clone and /backfill build.
     */
    @SerializedName("market")
    var market: Market?,

    /**
     * Whether a stored price in this market is NET or GROSS — the market layer of an answer the prices app also holds. A price list's own tax_basis wins over this; `tax_basis: null` with `source: 'unset'` means this market declares nothing and the reader must fall through to the tenant's own default.
     */
    @SerializedName("pricing")
    var pricing: MarketPricing?,

    /**
     * Can this market actually trade? `ready` is false only when a BLOCKING check failed — no currency to quote in, no tax class to tax with. Warnings are degraded-but-serviceable.
     */
    @SerializedName("readiness")
    var readiness: MarketReadiness?,

    /**
     * Every tax class of this market with its rate, in position order. Capped at 200. This is the rate table other apps resolve a line against, by code.
     */
    @SerializedName("tax_classes")
    var tax_classes: List<MarketTaxClass>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currencies" to currencies?.map { it.toMap() } as Any,
        "default_locale" to default_locale?.toMap() as Any,
        "locale_policy" to locale_policy?.toMap() as Any,
        "locales" to locales?.map { it.toMap() } as Any,
        "market" to market?.toMap() as Any,
        "pricing" to pricing?.toMap() as Any,
        "readiness" to readiness?.toMap() as Any,
        "tax_classes" to tax_classes?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketContext(
            currencies = (map["currencies"] as List<Map<String, Any>>).map { MarketCurrency.from(map = it) },
            default_locale = MarketDefaultLocale.from(map = map["default_locale"] as Map<String, Any>),
            locale_policy = MarketLocalePolicy.from(map = map["locale_policy"] as Map<String, Any>),
            locales = (map["locales"] as List<Map<String, Any>>).map { MarketLocale.from(map = it) },
            market = Market.from(map = map["market"] as Map<String, Any>),
            pricing = MarketPricing.from(map = map["pricing"] as Map<String, Any>),
            readiness = MarketReadiness.from(map = map["readiness"] as Map<String, Any>),
            tax_classes = (map["tax_classes"] as List<Map<String, Any>>).map { MarketTaxClass.from(map = it) },
        )
    }
}