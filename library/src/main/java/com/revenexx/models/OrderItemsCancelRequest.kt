package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderItemsCancelRequest(
    /**
     * Who cancelled, as the caller reported it — an operator, a desk, a system. Free text; this app does not resolve it against a user directory.
     */
    @SerializedName("cancelled_by")
    var cancelled_by: String?,

    /**
     * The quantities to take off the order. Required here, unlike on /ship and /return: cancelling everything by default is not a thing anybody should be able to do by omission — that is what /cancel is for.
     */
    @SerializedName("positions")
    val positions: List<OrderCancelPosition>,

    /**
     * Why it was cancelled, free text. Mandatory when the tenant sets cancel_requires_reason — for those merchants an unexplained cancellation is refused with a 400.
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cancelled_by" to cancelled_by as Any,
        "positions" to positions.map { it.toMap() } as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderItemsCancelRequest(
            cancelled_by = map["cancelled_by"] as? String,
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderCancelPosition.from(map = it) },
            reason = map["reason"] as? String,
        )
    }
}