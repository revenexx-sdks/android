package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderFulfillmentStatus
import com.revenexx.enums.OrderStatus

/**
 * Just enough of the order to render the answer — the full row is GET /orders/{id}.
 */
data class OrderShippableOrder(
    /**
     * Whether the order has SHIPPED, and the one dimension nobody writes: it is DERIVED after every quantity change from the positions' own bookkeeping. 'fulfilled' means shipped >= ordered − cancelled across all positions, 'partial' means something went out. Sending it has no effect; ship, cancel or return something and it moves.
     */
    @SerializedName("fulfillment_status")
    var fulfillment_status: OrderFulfillmentStatus?,

    /**
     * Why the order is held, in the words the shipping guard quotes back. Null when it is not held — releasing a hold clears it.
     */
    @SerializedName("hold_reason")
    var hold_reason: String?,

    /**
     * The order this answer is about.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The order number a human quotes — drawn from the tenant's order range at place-time, unique per tenant and never reused. It is NOT the id: every route addresses an order by uuid, and GET /orders?number=… is how a number becomes one.
     */
    @SerializedName("number")
    var number: String?,

    /**
     * A business stop, ORTHOGONAL to status: a held order keeps its lifecycle state and is refused at the guards. How far the hold reaches is the tenant's call (on_hold_blocks: shipping only, shipping and cancellation, or nothing at all).
     */
    @SerializedName("on_hold")
    var on_hold: Boolean?,

    /**
     * Where the order stands in its LIFECYCLE, and one of three independent status dimensions. 'pending' = created but not placed, an order waiting for approval; 'placed' = accepted, nothing shipped; 'in_fulfillment' = part of it has gone out, or all of it has and the tenant does not close on shipment; 'completed' and 'cancelled' end it. Moved by the action routes only — it is not writable through PUT /orders/{id}.
     */
    @SerializedName("status")
    var status: OrderStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "fulfillment_status" to fulfillment_status?.value as Any,
        "hold_reason" to hold_reason as Any,
        "id" to id as Any,
        "number" to number as Any,
        "on_hold" to on_hold as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderShippableOrder(
            fulfillment_status = OrderFulfillmentStatus.values().find { it.value == (map["fulfillment_status"] as? String) } ?: null,
            hold_reason = map["hold_reason"] as? String,
            id = map["id"] as? String,
            number = map["number"] as? String,
            on_hold = map["on_hold"] as? Boolean,
            status = OrderStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}