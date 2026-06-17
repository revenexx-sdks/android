package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class MarketCurrencyUpdateRequest(
    /**
     * ISO 4217 code, e.g. EUR (unique per market).
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Sort position (default 0).
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_default" to is_default as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCurrencyUpdateRequest(
            code = map["code"] as? String,
            is_default = map["is_default"] as? Boolean,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}