package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The buyer context the checkout resolves rates for — matrix methods need their measure (weight, quantity, order value or attribute) to apply.
 */
data class ShippingRatesRequest(
    /**
     * Measure values for attribute matrices, keyed by attribute name.
     */
    @SerializedName("attributes")
    var attributes: Any?,

    /**
     * Destination ISO 3166-1 alpha-2 code — checked against method country restrictions.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * Echoed into the rates (default 'EUR').
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Buyer market for tax resolution (else inferred from country, else first market).
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Order value (default 0) — drives free-above thresholds and order_value matrices.
     */
    @SerializedName("order_value")
    var order_value: Double?,

    /**
     * Total quantity — measure for quantity matrices.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Total weight — measure for weight matrices.
     */
    @SerializedName("weight")
    var weight: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attributes" to attributes as Any,
        "country" to country as Any,
        "currency" to currency as Any,
        "market_id" to market_id as Any,
        "order_value" to order_value as Any,
        "quantity" to quantity as Any,
        "weight" to weight as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRatesRequest(
            attributes = map["attributes"] as? Any,
            country = map["country"] as? String,
            currency = map["currency"] as? String,
            market_id = map["market_id"] as? String,
            order_value = (map["order_value"] as? Number)?.toDouble(),
            quantity = (map["quantity"] as? Number)?.toDouble(),
            weight = (map["weight"] as? Number)?.toDouble(),
        )
    }
}