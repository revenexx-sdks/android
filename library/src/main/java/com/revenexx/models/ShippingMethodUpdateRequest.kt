package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingMethodMatrixBasis
import com.revenexx.enums.ShippingMethodPricingType

/**
 * Partial update — omitted fields keep their current value.
 */
data class ShippingMethodUpdateRequest(
    /**
     * Carrier anchor for the upcoming carrier connect (dynamic rates, tracking links).
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * Stable method code, unique per tenant (e.g. standard, express).
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Allowed ISO 3166-1 alpha-2 codes; null or empty = worldwide.
     */
    @SerializedName("countries")
    var countries: List<String>?,

    /**
     * ISO 4217 code (default EUR).
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Only enabled methods appear in rate responses (default false).
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Delivery-time estimate for the checkout (days, upper bound).
     */
    @SerializedName("eta_days_max")
    var eta_days_max: Long?,

    /**
     * Delivery-time estimate for the checkout (days, lower bound).
     */
    @SerializedName("eta_days_min")
    var eta_days_min: Long?,

    /**
     * Free shipping at or above this order value — wins over every pricing model.
     */
    @SerializedName("free_above")
    var free_above: Double?,

    /**
     * Localized display names keyed by locale (e.g. {de, en}).
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Attribute name for matrix_basis 'attribute'.
     */
    @SerializedName("matrix_attribute")
    var matrix_attribute: String?,

    /**
     * The measure a matrix method prices over; 'attribute' reads matrix_attribute from the rate request.
     */
    @SerializedName("matrix_basis")
    var matrix_basis: ShippingMethodMatrixBasis?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Display name.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order in the checkout (default 0).
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The fixed price (default 0) — ignored for 'free' and 'matrix'.
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * Pricing model (default 'fixed'): one price, no price, or tiered over a measure.
     */
    @SerializedName("pricing_type")
    var pricing_type: ShippingMethodPricingType?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "carrier" to carrier as Any,
        "code" to code as Any,
        "countries" to countries as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "enabled" to enabled as Any,
        "eta_days_max" to eta_days_max as Any,
        "eta_days_min" to eta_days_min as Any,
        "free_above" to free_above as Any,
        "labels" to labels as Any,
        "matrix_attribute" to matrix_attribute as Any,
        "matrix_basis" to matrix_basis?.value as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "price" to price as Any,
        "pricing_type" to pricing_type?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingMethodUpdateRequest(
            carrier = map["carrier"] as? String,
            code = map["code"] as? String,
            countries = map["countries"] as? List<String>,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            enabled = map["enabled"] as? Boolean,
            eta_days_max = (map["eta_days_max"] as? Number)?.toLong(),
            eta_days_min = (map["eta_days_min"] as? Number)?.toLong(),
            free_above = (map["free_above"] as? Number)?.toDouble(),
            labels = map["labels"] as? Any,
            matrix_attribute = map["matrix_attribute"] as? String,
            matrix_basis = ShippingMethodMatrixBasis.values().find { it.value == (map["matrix_basis"] as? String) } ?: null,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
            pricing_type = ShippingMethodPricingType.values().find { it.value == (map["pricing_type"] as? String) } ?: null,
        )
    }
}