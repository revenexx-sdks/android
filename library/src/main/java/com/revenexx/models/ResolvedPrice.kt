package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ResolvedPrice(
    /**
     * 
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("line_total")
    var line_total: Double?,

    /**
     * true = no price for this buyer context — show "price on request", never 0.
     */
    @SerializedName("on_request")
    var on_request: Boolean?,

    /**
     * 
     */
    @SerializedName("price_list")
    var price_list: Any?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Resolved tax class code (from the product, or the market default).
     */
    @SerializedName("tax_class")
    var tax_class: String?,

    /**
     * 
     */
    @SerializedName("tax_included")
    var tax_included: Boolean?,

    /**
     * Tax rate % from markets.tax_classes for this market + tax_class.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * 
     */
    @SerializedName("tiers")
    var tiers: List<Any>?,

    /**
     * Stored price as-is (net or gross per tax_included). Prefer unit_price_net/unit_price_gross.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * Gross unit price (incl. tax).
     */
    @SerializedName("unit_price_gross")
    var unit_price_gross: Double?,

    /**
     * Net unit price (excl. tax).
     */
    @SerializedName("unit_price_net")
    var unit_price_net: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currency" to currency as Any,
        "line_total" to line_total as Any,
        "on_request" to on_request as Any,
        "price_list" to price_list as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
        "tax_class" to tax_class as Any,
        "tax_included" to tax_included as Any,
        "tax_rate" to tax_rate as Any,
        "tiers" to tiers as Any,
        "unit_price" to unit_price as Any,
        "unit_price_gross" to unit_price_gross as Any,
        "unit_price_net" to unit_price_net as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ResolvedPrice(
            currency = map["currency"] as? String,
            line_total = (map["line_total"] as? Number)?.toDouble(),
            on_request = map["on_request"] as? Boolean,
            price_list = map["price_list"] as? Any,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            tax_class = map["tax_class"] as? String,
            tax_included = map["tax_included"] as? Boolean,
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            tiers = map["tiers"] as? List<Any>,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            unit_price_gross = (map["unit_price_gross"] as? Number)?.toDouble(),
            unit_price_net = (map["unit_price_net"] as? Number)?.toDouble(),
        )
    }
}