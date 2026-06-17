package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ShippingRate(
    /**
     * 
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * 
     */
    @SerializedName("eta_days_max")
    var eta_days_max: Long?,

    /**
     * 
     */
    @SerializedName("eta_days_min")
    var eta_days_min: Long?,

    /**
     * 
     */
    @SerializedName("free_reason")
    var free_reason: String?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * 
     */
    @SerializedName("pricing_type")
    var pricing_type: String?,

    /**
     * Shipping method tax class (or market default).
     */
    @SerializedName("tax_class")
    var tax_class: String?,

    /**
     * Tax rate % from markets.tax_classes for this market + tax_class.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "carrier" to carrier as Any,
        "code" to code as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "eta_days_max" to eta_days_max as Any,
        "eta_days_min" to eta_days_min as Any,
        "free_reason" to free_reason as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "price" to price as Any,
        "pricing_type" to pricing_type as Any,
        "tax_class" to tax_class as Any,
        "tax_rate" to tax_rate as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRate(
            carrier = map["carrier"] as? String,
            code = map["code"] as? String,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            eta_days_max = (map["eta_days_max"] as? Number)?.toLong(),
            eta_days_min = (map["eta_days_min"] as? Number)?.toLong(),
            free_reason = map["free_reason"] as? String,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
            pricing_type = map["pricing_type"] as? String,
            tax_class = map["tax_class"] as? String,
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
        )
    }
}