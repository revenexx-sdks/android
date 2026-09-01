package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One answer per requested item, in request order, plus the currency, the tax context and the policy the numbers were computed under.
 */
data class PriceResolveResponse(
    /**
     * The policy this answer was computed under — the tenant settings in force plus where the currency came from.
     */
    @SerializedName("basis")
    var basis: PriceResolveBasis?,

    /**
     * ISO 4217 currency the whole answer is quoted in, and the currency lists had to match to be candidates at all. `basis.currency_source` says where it came from: the request, the buyer market, the tenant setting, or the shipped fallback.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * One entry per requested item, in the order the items were sent. An item that could not be priced is present and `on_request`, never missing.
     */
    @SerializedName("prices")
    var prices: List<ResolvedPrice>?,

    /**
     * Tax resolution status of this answer. resolved=false ⇒ tax_class/tax_rate are unknown, NOT zero.
     */
    @SerializedName("tax")
    var tax: PriceTaxContext?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "basis" to basis?.toMap() as Any,
        "currency" to currency as Any,
        "prices" to prices?.map { it.toMap() } as Any,
        "tax" to tax?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceResolveResponse(
            basis = PriceResolveBasis.from(map = map["basis"] as Map<String, Any>),
            currency = map["currency"] as? String,
            prices = (map["prices"] as List<Map<String, Any>>).map { ResolvedPrice.from(map = it) },
            tax = PriceTaxContext.from(map = map["tax"] as Map<String, Any>),
        )
    }
}