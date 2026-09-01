package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The buyer context the checkout resolves rates for — matrix methods need their measure (weight, quantity, order value or attribute) to apply.
 */
data class ShippingRatesRequest(
    /**
     * The instant to evaluate the delivery estimate at (ISO 8601). Omitted: now. Lets a storefront compute the cut-off in its own timezone.
     */
    @SerializedName("at")
    var at: String?,

    /**
     * Measure values for attribute matrices, keyed by attribute NAME — the key a matrix method names in its matrix_attribute, and the value the number its tiers are matched against. Summed over the basket by the caller, not by this app. Only the key a method asks for is read; anything else in the map is carried along and ignored, and a value that is not a finite number excludes that method with a reason rather than failing the quote.
     */
    @SerializedName("attributes")
    var attributes: Any?,

    /**
     * Destination ISO 3166-1 alpha-2 code — compared upper-cased against method and carrier country restrictions. Omitted or null: every method that restricts by country is excluded, with a reason.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * ISO 4217 code, echoed into the rates (default 'EUR'). Echoed, not converted: this app prices in the currency the method carries.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Buyer market for tax resolution. Omitted: the market matching `country`, else the tenant's sole market — never an arbitrary one.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Order value (default 0) — drives order_value matrices, and free-above thresholds when no sided value is sent. Read on the basis the tenant's free_above_compares setting declares.
     */
    @SerializedName("order_value")
    var order_value: Double?,

    /**
     * Order value including tax. Compared against free-above thresholds when free_above_compares is 'gross'.
     */
    @SerializedName("order_value_gross")
    var order_value_gross: Double?,

    /**
     * Order value excluding tax. Compared against free-above thresholds when free_above_compares is 'net'.
     */
    @SerializedName("order_value_net")
    var order_value_net: Double?,

    /**
     * Total quantity — measure for quantity matrices.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Total weight — measure for weight matrices. Read in weight_unit and converted to the unit the tiers are keyed in.
     */
    @SerializedName("weight")
    var weight: Double?,

    /**
     * The unit `weight` is expressed in, as a CODE into the tenant's own weight units (GET /shipping/weight-units). Omitted, it is the unit this market quotes in. A unit the tenant does not keep is a 400 — a mis-read weight prices the wrong bracket silently, and guessing is worse than refusing.
     */
    @SerializedName("weight_unit")
    var weight_unit: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "at" to at as Any,
        "attributes" to attributes as Any,
        "country" to country as Any,
        "currency" to currency as Any,
        "market_id" to market_id as Any,
        "order_value" to order_value as Any,
        "order_value_gross" to order_value_gross as Any,
        "order_value_net" to order_value_net as Any,
        "quantity" to quantity as Any,
        "weight" to weight as Any,
        "weight_unit" to weight_unit as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRatesRequest(
            at = map["at"] as? String,
            attributes = map["attributes"] as? Any,
            country = map["country"] as? String,
            currency = map["currency"] as? String,
            market_id = map["market_id"] as? String,
            order_value = (map["order_value"] as? Number)?.toDouble(),
            order_value_gross = (map["order_value_gross"] as? Number)?.toDouble(),
            order_value_net = (map["order_value_net"] as? Number)?.toDouble(),
            quantity = (map["quantity"] as? Number)?.toDouble(),
            weight = (map["weight"] as? Number)?.toDouble(),
            weight_unit = map["weight_unit"] as? String,
        )
    }
}