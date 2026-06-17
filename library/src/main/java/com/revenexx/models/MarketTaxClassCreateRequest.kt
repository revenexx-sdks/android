package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The owning market comes from the route path ('market_id').
 */
data class MarketTaxClassCreateRequest(
    /**
     * Tax class code (unique per market).
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localized display names ({locale: label}).
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("name")
    val name: String,

    /**
     * Sort position (default 0).
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Tax rate in percent, 0–100 (default 0).
     */
    @SerializedName("rate")
    var rate: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "rate" to rate as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketTaxClassCreateRequest(
            code = map["code"] as String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            name = map["name"] as String,
            position = (map["position"] as? Number)?.toLong(),
            rate = (map["rate"] as? Number)?.toDouble(),
        )
    }
}