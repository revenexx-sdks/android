package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderPaymentStatus

/**
 * 
 */
data class OrderPaymentStatusUpdateRequest(
    /**
     * Reference into the payment system — merged into the order's payment snapshot.
     */
    @SerializedName("payment_id")
    var payment_id: String?,

    /**
     * The new payment dimension value.
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