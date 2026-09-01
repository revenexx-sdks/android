package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Cancels the WHOLE order, and only while nothing has shipped. Both fields are optional unless the tenant requires a reason.
 */
data class OrderCancelRequest(
    /**
     * Who cancelled, as the caller reported it — an operator, a desk, a system. Free text; this app does not resolve it against a user directory.
     */
    @SerializedName("cancelled_by")
    var cancelled_by: String?,

    /**
     * Why it was cancelled, free text. Mandatory when the tenant sets cancel_requires_reason — for those merchants an unexplained cancellation is refused with a 400.
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cancelled_by" to cancelled_by as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCancelRequest(
            cancelled_by = map["cancelled_by"] as? String,
            reason = map["reason"] as? String,
        )
    }
}