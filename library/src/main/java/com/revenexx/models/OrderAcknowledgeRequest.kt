package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The acknowledgement carries one field, and it is optional: sending {} still stamps acknowledged_at, which is the point of the call. acknowledged_at is the server's clock and is never taken from the body.
 */
data class OrderAcknowledgeRequest(
    /**
     * The FULFILLING system's reference for this order, typically the ERP order number. Written once by POST /orders/{id}/acknowledge and null until an integration acknowledged it. Keeps the existing value when omitted.
     */
    @SerializedName("external_ref")
    var external_ref: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "external_ref" to external_ref as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderAcknowledgeRequest(
            external_ref = map["external_ref"] as? String,
        )
    }
}