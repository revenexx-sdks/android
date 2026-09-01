package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketDefaultLocaleSource

/**
 * The locale a storefront should render this market in. `source` names where it came from: 'market' (a locale flagged is_default), 'market_first' (no flag — first by position) or 'tenant_fallback' (the market registers none; the tenant's fallback_locale setting answered).
 */
data class MarketDefaultLocale(
    /**
     * Locale code, language-COUNTRY — the language a storefront renders this market in, and the key a translation is stored under. Unique per market. The app's own seeded value is the tenant's `fallback_locale` setting, whose declared default is de-DE.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * ISO 3166-1 alpha-2 country code — the region half of `code`. It is a spelling of the language, not a shipping destination: a market may register de-AT without trading in Austria.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * ISO 639-1 language code — the language half of `code`, stored separately so a client can group markets by language without parsing.
     */
    @SerializedName("language")
    var language: String?,

    /**
     * Which of the three rules answered. 'market' — a locale of this market carries is_default. 'market_first' — none does, so the first by position was taken. 'tenant_fallback' — the market registers no locale at all and the tenant's fallback_locale setting answered, which means this locale is NOT one of the market's own and nothing here was configured for it.
     */
    @SerializedName("source")
    var source: MarketDefaultLocaleSource?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "country" to country as Any,
        "language" to language as Any,
        "source" to source?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketDefaultLocale(
            code = map["code"] as? String,
            country = map["country"] as? String,
            language = map["language"] as? String,
            source = MarketDefaultLocaleSource.values().find { it.value == (map["source"] as? String) } ?: null,
        )
    }
}