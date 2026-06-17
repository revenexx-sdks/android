package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The dispatch envelope from webhooks.revenexx.com — request.body carries the raw, vendor-shaped PSP callback (stripe payment intents or the generic {event, psp_payment_id?, order_ref?, error?} shape). Intentionally unconstrained so no PSP notification is ever rejected at the gate.
 */
class PaymentWebhookIngestRequest(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentWebhookIngestRequest(
        )
    }
}