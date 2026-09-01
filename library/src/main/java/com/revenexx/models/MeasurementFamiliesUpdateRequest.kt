package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class MeasurementFamiliesUpdateRequest(
    /**
     * The measurement family's stable identifier. A `measure` attribute names one and then offers that family's units.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * What the measurement family is called, per language tag.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * The unit every value of this family is converted to before it is compared or sorted — the unit each `convert_factor` is relative to.
     */
    @SerializedName("standard_unit")
    var standard_unit: String?,

    /**
     * The units this family offers. `convert_factor` multiplies a value into `standard_unit`, so a gram is 0.001 kilograms; `symbol` is what a form prints next to the number.
     */
    @SerializedName("units")
    var units: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "labels" to labels as Any,
        "standard_unit" to standard_unit as Any,
        "units" to units as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MeasurementFamiliesUpdateRequest(
            code = map["code"] as? String,
            labels = map["labels"] as? Any,
            standard_unit = map["standard_unit"] as? String,
            units = map["units"] as? Any,
        )
    }
}