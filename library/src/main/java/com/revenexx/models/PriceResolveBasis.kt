package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceCurrencySource
import com.revenexx.enums.PriceListTiebreak
import com.revenexx.enums.PriceRoundingMode
import com.revenexx.enums.PriceTaxInclusiveDefault

/**
 * The policy this answer was computed under — the tenant settings in force plus where the currency came from.
 */
data class PriceResolveBasis(
    /**
     * false ⇒ a buyer with no contact/organization is answered on_request for everything.
     */
    @SerializedName("anonymous_resolve_allowed")
    var anonymous_resolve_allowed: Boolean?,

    /**
     * Where `currency` came from: the request, the buyer market's own currency, the tenant's default_currency setting, or the shipped fallback.
     */
    @SerializedName("currency_source")
    var currency_source: PriceCurrencySource?,

    /**
     * The instant validity windows were evaluated at.
     */
    @SerializedName("evaluated_at")
    var evaluated_at: String?,

    /**
     * Which list won where specificity and priority tied.
     */
    @SerializedName("price_list_priority_tiebreak")
    var price_list_priority_tiebreak: PriceListTiebreak?,

    /**
     * Decimals every DERIVED amount (net, gross, line totals) was rounded to.
     */
    @SerializedName("price_precision")
    var price_precision: Long?,

    /**
     * How those amounts landed on the last decimal.
     */
    @SerializedName("rounding_mode")
    var rounding_mode: PriceRoundingMode?,

    /**
     * Tenant setting: the basis a price list that states none is read on.
     */
    @SerializedName("tax_inclusive_default")
    var tax_inclusive_default: PriceTaxInclusiveDefault?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "anonymous_resolve_allowed" to anonymous_resolve_allowed as Any,
        "currency_source" to currency_source?.value as Any,
        "evaluated_at" to evaluated_at as Any,
        "price_list_priority_tiebreak" to price_list_priority_tiebreak?.value as Any,
        "price_precision" to price_precision as Any,
        "rounding_mode" to rounding_mode?.value as Any,
        "tax_inclusive_default" to tax_inclusive_default?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceResolveBasis(
            anonymous_resolve_allowed = map["anonymous_resolve_allowed"] as? Boolean,
            currency_source = PriceCurrencySource.values().find { it.value == (map["currency_source"] as? String) } ?: null,
            evaluated_at = map["evaluated_at"] as? String,
            price_list_priority_tiebreak = PriceListTiebreak.values().find { it.value == (map["price_list_priority_tiebreak"] as? String) } ?: null,
            price_precision = (map["price_precision"] as? Number)?.toLong(),
            rounding_mode = PriceRoundingMode.values().find { it.value == (map["rounding_mode"] as? String) } ?: null,
            tax_inclusive_default = PriceTaxInclusiveDefault.values().find { it.value == (map["tax_inclusive_default"] as? String) } ?: null,
        )
    }
}