package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingCarrierSource
import com.revenexx.enums.ShippingRatePricingType
import com.revenexx.enums.ShippingTaxSource

/**
 * One offerable shipping method with its computed price for this buyer context.
 */
data class ShippingRate(
    /**
     * The carrier CODE — unchanged for every caller that already reads it. The method's carrier_id, else its `carrier` text, else the tenant's default_carrier.
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * The carrier row's display name, or null when the code names no maintained carrier.
     */
    @SerializedName("carrier_name")
    var carrier_name: String?,

    /**
     * The class of service this rate is, from the carrier row — a code into the tenant's service levels.
     */
    @SerializedName("carrier_service_level")
    var carrier_service_level: String?,

    /**
     * Which step of the chain answered: 'method' (carrier_id), 'method_code' (the method's text matched a carrier), 'method_text' (it matched none), 'tenant_default' / 'tenant_default_text' (the setting, matched or not).
     */
    @SerializedName("carrier_source")
    var carrier_source: ShippingCarrierSource?,

    /**
     * Stable method code, unique per tenant (e.g. standard, express). What a checkout and an order line store, so it is the value every integration joins on.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * ISO 4217 code (default EUR). Exactly three characters — the column says so. Echoed into a rate, never converted: this app prices in the currency the method carries.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The delivery window a checkout can print. Calendar days, cut-off evaluated in UTC (send `at` to control the instant).
     */
    @SerializedName("delivery")
    var delivery: ShippingDeliveryEstimate?,

    /**
     * The sentence under the name in the checkout — the delivery promise in words. Null when the name says enough.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Transit time upper bound in calendar days, as applied: the method's own, else the carrier's.
     */
    @SerializedName("eta_days_max")
    var eta_days_max: Long?,

    /**
     * Transit time lower bound in calendar days, as applied: the method's own, else the carrier's.
     */
    @SerializedName("eta_days_min")
    var eta_days_min: Long?,

    /**
     * Only when a free-above threshold applied. Names the compared value AND its basis (net or gross), and says whether the threshold was the method's own or shop-wide — the free-shipping promise is a common dispute and this is the sentence that settles it.
     */
    @SerializedName("free_reason")
    var free_reason: String?,

    /**
     * Localized display names. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Display name shown in the checkout.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order in the checkout (default 0) — a rate answer is returned in this order.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The shipping fee for this basket, in `currency`, rounded to two decimals — 0 when a free-above threshold or a 'free' method applied. NULL when `quote_required` is true: the price is unknown, not zero, and a checkout must not add 0.00 for it.
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * Pricing model (default 'fixed'): 'fixed' is one price for every basket, 'free' is no price at all, 'matrix' is a tiered price read off this method's rate tiers. Only 'matrix' looks at matrix_basis, quote_above and the tier table.
     */
    @SerializedName("pricing_type")
    var pricing_type: ShippingRatePricingType?,

    /**
     * Only when quote_required — the measure and the threshold it exceeded, so an operator pricing it by hand can see what triggered the referral.
     */
    @SerializedName("quote_reason")
    var quote_reason: String?,

    /**
     * True when the matrix measure is above the method's quote_above threshold: the method is still offered, carries no price, and the storefront shows 'shipping on request'. The order is placed without a computed shipping fee.
     */
    @SerializedName("quote_required")
    var quote_required: Boolean?,

    /**
     * The tax class this rate was taxed under, as a code in markets.tax_classes — the method's own, the tenant's shipping_tax_class, or the market's default, whichever answered. Null means unresolved, not untaxed.
     */
    @SerializedName("tax_class")
    var tax_class: String?,

    /**
     * The rate in percent from markets.tax_classes for this market and tax_class — 19 means 19 %. Null means UNKNOWN, never 0: read `tax.resolved` before treating a missing rate as tax-free.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * Which step of the chain supplied the rate: the method's own class, the tenant's shipping_tax_class, the market default, or the tenant's default_shipping_tax_rate. Null means unknown, NOT untaxed.
     */
    @SerializedName("tax_source")
    var tax_source: ShippingTaxSource?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "carrier" to carrier as Any,
        "carrier_name" to carrier_name as Any,
        "carrier_service_level" to carrier_service_level as Any,
        "carrier_source" to carrier_source?.value as Any,
        "code" to code as Any,
        "currency" to currency as Any,
        "delivery" to delivery?.toMap() as Any,
        "description" to description as Any,
        "eta_days_max" to eta_days_max as Any,
        "eta_days_min" to eta_days_min as Any,
        "free_reason" to free_reason as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "price" to price as Any,
        "pricing_type" to pricing_type?.value as Any,
        "quote_reason" to quote_reason as Any,
        "quote_required" to quote_required as Any,
        "tax_class" to tax_class as Any,
        "tax_rate" to tax_rate as Any,
        "tax_source" to tax_source?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRate(
            carrier = map["carrier"] as? String,
            carrier_name = map["carrier_name"] as? String,
            carrier_service_level = map["carrier_service_level"] as? String,
            carrier_source = ShippingCarrierSource.values().find { it.value == (map["carrier_source"] as? String) } ?: null,
            code = map["code"] as? String,
            currency = map["currency"] as? String,
            delivery = ShippingDeliveryEstimate.from(map = map["delivery"] as Map<String, Any>),
            description = map["description"] as? String,
            eta_days_max = (map["eta_days_max"] as? Number)?.toLong(),
            eta_days_min = (map["eta_days_min"] as? Number)?.toLong(),
            free_reason = map["free_reason"] as? String,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
            pricing_type = ShippingRatePricingType.values().find { it.value == (map["pricing_type"] as? String) } ?: null,
            quote_reason = map["quote_reason"] as? String,
            quote_required = map["quote_required"] as? Boolean,
            tax_class = map["tax_class"] as? String,
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            tax_source = ShippingTaxSource.values().find { it.value == (map["tax_source"] as? String) } ?: null,
        )
    }
}