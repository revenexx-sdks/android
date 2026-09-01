package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingFreeAboveBasis
import com.revenexx.enums.ShippingRatesBasisMatrixBasisDefault

/**
 * How this answer was measured — the tenant settings that shaped it, echoed so the numbers can be re-derived.
 */
data class ShippingRatesBasis(
    /**
     * The instant the delivery estimates were computed from.
     */
    @SerializedName("evaluated_at")
    var evaluated_at: String?,

    /**
     * Whether free-above thresholds were compared against the net or the gross order value.
     */
    @SerializedName("free_above_compares")
    var free_above_compares: ShippingFreeAboveBasis?,

    /**
     * The measure a matrix method without its own basis priced over.
     */
    @SerializedName("matrix_basis_default")
    var matrix_basis_default: ShippingRatesBasisMatrixBasisDefault?,

    /**
     * The unit the request expressed its weight in; converted to weight_unit before any tier was matched.
     */
    @SerializedName("request_weight_unit")
    var request_weight_unit: String?,

    /**
     * Kilograms per unit of `request_weight_unit`, as applied.
     */
    @SerializedName("request_weight_unit_factor")
    var request_weight_unit_factor: Double?,

    /**
     * The unit the rate tiers are keyed in — this market's `weight_unit` setting, else the unit the tenant flagged as default.
     */
    @SerializedName("weight_unit")
    var weight_unit: String?,

    /**
     * Kilograms per unit of `weight_unit`, as applied. Echoed because a unit is a code PLUS a number and the number is what priced the parcel — a quote has to be re-derivable from its own payload, not from a table the merchant may since have edited.
     */
    @SerializedName("weight_unit_factor")
    var weight_unit_factor: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "evaluated_at" to evaluated_at as Any,
        "free_above_compares" to free_above_compares?.value as Any,
        "matrix_basis_default" to matrix_basis_default?.value as Any,
        "request_weight_unit" to request_weight_unit as Any,
        "request_weight_unit_factor" to request_weight_unit_factor as Any,
        "weight_unit" to weight_unit as Any,
        "weight_unit_factor" to weight_unit_factor as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRatesBasis(
            evaluated_at = map["evaluated_at"] as? String,
            free_above_compares = ShippingFreeAboveBasis.values().find { it.value == (map["free_above_compares"] as? String) } ?: null,
            matrix_basis_default = ShippingRatesBasisMatrixBasisDefault.values().find { it.value == (map["matrix_basis_default"] as? String) } ?: null,
            request_weight_unit = map["request_weight_unit"] as? String,
            request_weight_unit_factor = (map["request_weight_unit_factor"] as? Number)?.toDouble(),
            weight_unit = map["weight_unit"] as? String,
            weight_unit_factor = (map["weight_unit_factor"] as? Number)?.toDouble(),
        )
    }
}