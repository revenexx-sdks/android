package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderCancellationScope

/**
 * A record of what was taken off an order and why — either the whole order (while nothing had shipped) or named quantities off a partly shipped one.
 */
data class OrderCancellation(
    /**
     * Who cancelled, as the caller reported it — an operator, a desk, a system. Free text; this app does not resolve it against a user directory.
     */
    @SerializedName("cancelled_by")
    var cancelled_by: String?,

    /**
     * When the cancellation was recorded.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the cancellation record.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The order that was cancelled from.
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * What this record removed. A scope 'order' record carries every position in full; a scope 'items' record carries exactly the quantities that were named.
     */
    @SerializedName("positions")
    var positions: List<OrderCancellationPosition>?,

    /**
     * Why it was cancelled, free text. Mandatory when the tenant sets cancel_requires_reason — for those merchants an unexplained cancellation is refused with a 400.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * Which of the two cancellations this was: 'order' is the full cancel (only possible while nothing has shipped, and it cancels every position in full), 'items' is the quantity-based one that takes open quantities off a partly shipped order.
     */
    @SerializedName("scope")
    var scope: OrderCancellationScope?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cancelled_by" to cancelled_by as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "order_id" to order_id as Any,
        "positions" to positions?.map { it.toMap() } as Any,
        "reason" to reason as Any,
        "scope" to scope?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCancellation(
            cancelled_by = map["cancelled_by"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            order_id = map["order_id"] as? String,
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderCancellationPosition.from(map = it) },
            reason = map["reason"] as? String,
            scope = OrderCancellationScope.values().find { it.value == (map["scope"] as? String) } ?: null,
        )
    }
}