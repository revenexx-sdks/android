package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Metric
 */
data class Metric(
    /**
     * The date at which this metric was aggregated in ISO 8601 format.
     */
    @SerializedName("date")
    val date: String,

    /**
     * The value of this metric at the timestamp.
     */
    @SerializedName("value")
    val value: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "date" to date as Any,
        "value" to value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Metric(
            date = map["date"] as String,
            value = (map["value"] as Number).toLong(),
        )
    }
}