package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class MeasurementFamiliesCreateRequest(
    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("standard_unit")
    val standard_unit: String,

    /**
     * 
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
        ) = MeasurementFamiliesCreateRequest(
            code = map["code"] as String,
            labels = map["labels"] as? Any,
            standard_unit = map["standard_unit"] as String,
            units = map["units"] as? Any,
        )
    }
}