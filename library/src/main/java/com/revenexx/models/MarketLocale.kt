package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One language a market is rendered in, and one key its translations are stored under. A market may register several; one of them is the default a storefront falls back to.
 */
data class MarketLocale(
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
     * When the locale was registered on this market. Set by the database; never writable.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of this locale registration. The locale is named by `code` everywhere else.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The locale a storefront renders this market in when the request asks for none. At most one per market; where none carries the flag the first by position is used, and `default_locale.source` on the context says which of the two happened.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * ISO 639-1 language code — the language half of `code`, stored separately so a client can group markets by language without parsing.
     */
    @SerializedName("language")
    var language: String?,

    /**
     * The market this locale belongs to. Filled from the route path on write and never read out of the body; ON DELETE CASCADE, so deleting the market deletes this row.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Sort position among this market's locales, ascending, default 0 — and the tie-break that picks a default when no locale is flagged.
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "country" to country as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "language" to language as Any,
        "market_id" to market_id as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketLocale(
            code = map["code"] as? String,
            country = map["country"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            language = map["language"] as? String,
            market_id = map["market_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}