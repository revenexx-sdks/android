package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What the booking produced: the new shipment with the quantities it took, and the order as it now stands.
 */
data class OrderShipmentCreated(
    /**
     * The order after the booking: fulfillment_status is re-derived from the positions, and status may have moved to in_fulfillment or (depending on the tenant's auto_complete_on) completed.
     */
    @SerializedName("order")
    var order: Order?,

    /**
     * The shipment that was created, WITH the position quantities it booked — the only place a caller learns which quantities actually went out when the positions were defaulted.
     */
    @SerializedName("shipment")
    var shipment: OrderShipment?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "order" to order?.toMap() as Any,
        "shipment" to shipment?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderShipmentCreated(
            order = Order.from(map = map["order"] as Map<String, Any>),
            shipment = OrderShipment.from(map = map["shipment"] as Map<String, Any>),
        )
    }
}