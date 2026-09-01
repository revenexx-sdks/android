package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderPaymentStatus

/**
 * 
 */
data class OrderPaymentStatusUpdateRequest(
    /**
     * The reference into the payment system. MERGED into the order's payment snapshot under 'payment_id' — the rest of the snapshot is left alone — and carried in the order.payment_status.changed event. Omitted leaves the snapshot untouched.
     */
    @SerializedName("payment_id")
    var payment_id: String?,

    /**
     * The new value of the payment dimension. Whether the order is PAID, and the dimension this app does not decide: it is fed from outside through POST /orders/{id}/payment-status (the payments app or an ERP), and only seeded at place-time from payment.status. Orthogonal to the lifecycle — a completed order can still be open, and a paid one can still be pending.
     */
    @SerializedName("status")
    val status: OrderPaymentStatus,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "payment_id" to payment_id as Any,
        "status" to status.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderPaymentStatusUpdateRequest(
            payment_id = map["payment_id"] as? String,
            status = OrderPaymentStatus.values().find { it.value == map["status"] as String }!!,
        )
    }
}