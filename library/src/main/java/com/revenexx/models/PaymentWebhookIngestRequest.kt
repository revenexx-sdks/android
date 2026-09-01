package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The dispatch envelope from webhooks.revenexx.com. Nothing is required and nothing is constrained — three keys are read, and the rest is carried along.
 */
data class PaymentWebhookIngestRequest(
    /**
     * The dispatcher's delivery id. Echoed back as `delivery_id` so a delivery and what the ledger did can be correlated.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The captured HTTP request as the PSP sent it.
     */
    @SerializedName("request")
    var request: String?,

    /**
     * Whether the ingress verified the callback signature against the provider's `webhook_secret`. An explicit false is refused with 422: an endpoint may run in annotate mode, and the ledger stays sovereign over one that does.
     */
    @SerializedName("verified")
    var verified: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "request" to request as Any,
        "verified" to verified as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentWebhookIngestRequest(
            id = map["id"] as? String,
            request = map["request"] as? String,
            verified = map["verified"] as? String,
        )
    }
}