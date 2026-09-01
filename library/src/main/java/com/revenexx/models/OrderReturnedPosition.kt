package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One position quantity registered for return.
 */
data class OrderReturnedPosition(
    /**
     * The order item this quantity was booked against — an id out of the same order, never another one.
     */
    @SerializedName("order_item_id")
    var order_item_id: String?,

    /**
     * The quantity booked on that position, in the position's own unit. Three decimal places, so 0.5 m of cable is a real booking.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Whether this quantity is reported for restocking when the return completes. Restocking itself stays an explicit inventories.restock call by the orchestrator — this app never writes another app's stock.
     */
    @SerializedName("restock")
    var restock: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "order_item_id" to order_item_id as Any,
        "quantity" to quantity as Any,
        "restock" to restock as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturnedPosition(
            order_item_id = map["order_item_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            restock = map["restock"] as? Boolean,
        )
    }
}