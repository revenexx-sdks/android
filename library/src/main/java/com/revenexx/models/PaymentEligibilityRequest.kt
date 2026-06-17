package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The buyer context — restriction dimensions are ANDed, entries within a dimension ORed, empty = unrestricted.
 */
data class PaymentEligibilityRequest(
    /**
     * Order amount the fees are computed against (default 0).
     */
    @SerializedName("amount")
    var amount: Double?,

    /**
     * Buyer ISO country code — methods with country restrictions need it.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * ISO 4217 code (default EUR).
     */
    @SerializedName("currency")
    var currency: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "amount" to amount as Any,
        "country" to country as Any,
        "currency" to currency as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentEligibilityRequest(
            amount = (map["amount"] as? Number)?.toDouble(),
            country = map["country"] as? String,
            currency = map["currency"] as? String,
        )
    }
}