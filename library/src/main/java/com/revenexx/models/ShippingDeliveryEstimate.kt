package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The delivery window a checkout can print. Calendar days, cut-off evaluated in UTC (send `at` to control the instant).
 */
data class ShippingDeliveryEstimate(
    /**
     * Whether the cut-off had passed at evaluation time, costing a day.
     */
    @SerializedName("cutoff_passed")
    var cutoff_passed: Boolean?,

    /**
     * The cut-off applied (HH:MM, UTC), or null when none is configured — the carrier's own when it declares one, else the market's `cutoff_time` setting.
     */
    @SerializedName("cutoff_time")
    var cutoff_time: String?,

    /**
     * ship_date + eta_days_min.
     */
    @SerializedName("earliest")
    var earliest: String?,

    /**
     * The tenant's handling_days setting, as applied.
     */
    @SerializedName("handling_days")
    var handling_days: Long?,

    /**
     * ship_date + eta_days_max.
     */
    @SerializedName("latest")
    var latest: String?,

    /**
     * The day the parcel leaves — today plus handling days, plus one when the cut-off has passed.
     */
    @SerializedName("ship_date")
    var ship_date: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cutoff_passed" to cutoff_passed as Any,
        "cutoff_time" to cutoff_time as Any,
        "earliest" to earliest as Any,
        "handling_days" to handling_days as Any,
        "latest" to latest as Any,
        "ship_date" to ship_date as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingDeliveryEstimate(
            cutoff_passed = map["cutoff_passed"] as? Boolean,
            cutoff_time = map["cutoff_time"] as? String,
            earliest = map["earliest"] as? String,
            handling_days = (map["handling_days"] as? Number)?.toLong(),
            latest = map["latest"] as? String,
            ship_date = map["ship_date"] as? String,
        )
    }
}