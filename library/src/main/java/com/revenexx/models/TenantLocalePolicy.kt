package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketLocaleFallback
import com.revenexx.enums.MarketLocaleGranularity

/**
 * How this tenant keys its translations, resolved rather than named: the key a client WRITES and the order it READS, per locale. Emitting the resolved answer is the point — a client handed only the setting names re-implements the policy and gets it subtly different, which is how a label editor came to ask for de-DE while the row held de.
 */
data class TenantLocalePolicy(
    /**
     * settings#locale_fallback — what a read tries after the exact key holds nothing.
     */
    @SerializedName("fallback")
    var fallback: MarketLocaleFallback?,

    /**
     * settings#locale_granularity — whether a value is keyed by the full locale ('regional') or by its language alone.
     */
    @SerializedName("granularity")
    var granularity: MarketLocaleGranularity?,

    /**
     * The UNION of every market's locales, each one appearing once — the full set of inputs a tenant-baseline editor has to offer. Empty when no market registers a locale at all.
     */
    @SerializedName("locales")
    var locales: List<TenantLocaleKeys>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "fallback" to fallback?.value as Any,
        "granularity" to granularity?.value as Any,
        "locales" to locales?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TenantLocalePolicy(
            fallback = MarketLocaleFallback.values().find { it.value == (map["fallback"] as? String) } ?: null,
            granularity = MarketLocaleGranularity.values().find { it.value == (map["granularity"] as? String) } ?: null,
            locales = (map["locales"] as List<Map<String, Any>>).map { TenantLocaleKeys.from(map = it) },
        )
    }
}