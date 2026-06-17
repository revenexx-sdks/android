package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.MarketStatus

/**
 * Partial update — omitted fields keep their current value.
 */
data class MarketUpdateRequest(
    /**
     * Market code (unique per tenant).
     */
    @SerializedName("code")
    var code: String?,

    /**
     * ISO 4217 code (default 'EUR').
     */
    @SerializedName("currency")
    var currency: String?,

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
    var name: String?,

    /**
     * Sort position (default 0).
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * Default 'active'.
     */
    @SerializedName("status")
    var status: MarketStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "currency" to currency as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketUpdateRequest(
            code = map["code"] as? String,
            currency = map["currency"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            status = MarketStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}