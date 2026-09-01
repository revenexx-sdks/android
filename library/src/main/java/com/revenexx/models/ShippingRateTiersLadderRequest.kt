package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * An evenly-stepped tier table. Tiers are generated at from_value, from_value+step, … up to to_value; each costs step_price more than the one before.
 */
data class ShippingRateTiersLadderRequest(
    /**
     * Price of the first tier.
     */
    @SerializedName("base_price")
    val base_price: Double,

    /**
     * First tier threshold (default 0), in the method's matrix measure.
     */
    @SerializedName("from_value")
    var from_value: Double?,

    /**
     * Replace the whole table (default true) or append to it.
     */
    @SerializedName("replace")
    var replace: Boolean?,

    /**
     * Distance between two tiers. Must be > 0.
     */
    @SerializedName("step")
    val step: Double,

    /**
     * Added to each subsequent tier (default 0). A negative value is allowed as long as no tier ends up below 0.
     */
    @SerializedName("step_price")
    var step_price: Double?,

    /**
     * Last tier threshold. The final tier keeps applying above it — a matrix has no upper bound. Must be >= from_value.
     */
    @SerializedName("to_value")
    val to_value: Double,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "base_price" to base_price as Any,
        "from_value" to from_value as Any,
        "replace" to replace as Any,
        "step" to step as Any,
        "step_price" to step_price as Any,
        "to_value" to to_value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRateTiersLadderRequest(
            base_price = (map["base_price"] as Number).toDouble(),
            from_value = (map["from_value"] as? Number)?.toDouble(),
            replace = map["replace"] as? Boolean,
            step = (map["step"] as Number).toDouble(),
            step_price = (map["step_price"] as? Number)?.toDouble(),
            to_value = (map["to_value"] as Number).toDouble(),
        )
    }
}