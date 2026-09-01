package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A new matrix tier (from_value → price) of the method in the path.
 */
data class ShippingRateTierCreateRequest(
    /**
     * Lower bound of this tier, in the method's matrix measure — kilograms (or whatever the market's `weight_unit` names, converted through its factor) for a weight matrix, items for quantity, money in the method's currency for order_value, and the raw attribute value for 'attribute'. INCLUSIVE: the tier applies from this value upward, and the tier that wins is the one with the highest from_value at or below the measured value, so a measure of exactly 10 is priced by the tier at 10 rather than the one below it. The last tier has no upper bound. Unique per method — a second tier at the same threshold is a 409, because which of the two won would be whatever the database returned first. Defaults to 0.
     */
    @SerializedName("from_value")
    var from_value: Double?,

    /**
     * Display order in the matrix editor (default 0; a bulk replace derives it from the array index). Pricing reads from_value, never this.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * What this tier costs, in the method's currency. Charged in full for the whole consignment — a matrix is a lookup table, not a rate per unit. Defaults to 0.
     */
    @SerializedName("price")
    var price: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "from_value" to from_value as Any,
        "position" to position as Any,
        "price" to price as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRateTierCreateRequest(
            from_value = (map["from_value"] as? Number)?.toDouble(),
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
        )
    }
}