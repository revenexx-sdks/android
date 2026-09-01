package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PaymentTransitionRequest(
    /**
     * The operator's own words for why. Kept on the payment (`metadata.cancel_reason` / `metadata.refund_reason`) AND handed to the provider's own cancellation or refund reason field, so it is readable in the PSP's dashboard too. Trimmed and cut at 500 characters.
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentTransitionRequest(
            reason = map["reason"] as? String,
        )
    }
}