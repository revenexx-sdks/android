package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketStatus

/**
 * The path id is the SOURCE market (a uuid or a market code). Everything the new market does not inherit is here. The copy flags default to true; `is_default` is never copied, and the new market always gets its own base currency registered and marked default.
 */
data class MarketCloneRequest(
    /**
     * Code of the NEW market (unique per tenant).
     */
    @SerializedName("code")
    val code: String,

    /**
     * Copy the source's traded currencies. Default true. The new market's own base currency is registered and marked default either way.
     */
    @SerializedName("copy_currencies")
    var copy_currencies: Boolean?,

    /**
     * Copy the source's locales. Default true. False leaves the new market with no language of its own, so the tenant fallback_locale is seeded instead — it is never left with none.
     */
    @SerializedName("copy_locales")
    var copy_locales: Boolean?,

    /**
     * Copy the source's tax classes, rates and all. Default true. False leaves the market unable to tax anything, which readiness reports as blocking.
     */
    @SerializedName("copy_tax_classes")
    var copy_tax_classes: Boolean?,

    /**
     * Base currency of the new market (ISO 4217). Defaults to the source market's, and is registered and marked default on the new one either way.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Display name of the new market. Defaults to its code.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Status of the new market. Defaults to 'active'; clone it 'inactive' to build it out before it serves anyone.
     */
    @SerializedName("status")
    var status: MarketStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "copy_currencies" to copy_currencies as Any,
        "copy_locales" to copy_locales as Any,
        "copy_tax_classes" to copy_tax_classes as Any,
        "currency" to currency as Any,
        "name" to name as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCloneRequest(
            code = map["code"] as String,
            copy_currencies = map["copy_currencies"] as? Boolean,
            copy_locales = map["copy_locales"] as? Boolean,
            copy_tax_classes = map["copy_tax_classes"] as? Boolean,
            currency = map["currency"] as? String,
            name = map["name"] as? String,
            status = MarketStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}