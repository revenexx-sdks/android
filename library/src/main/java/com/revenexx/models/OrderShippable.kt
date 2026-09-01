package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What a shipment of this order may still contain, and whether one would be accepted at all — answered by the same code POST /orders/{id}/ship runs, so the two cannot drift.
 */
data class OrderShippable(
    /**
     * Why not, in the very words POST /orders/{id}/ship would refuse with — including the hold reason where there is one. Null when `shippable` is true.
     */
    @SerializedName("blocked_reason")
    var blocked_reason: String?,

    /**
     * How many positions still have an open quantity — the number of lines a shipment dialog would offer.
     */
    @SerializedName("open_positions")
    var open_positions: Long?,

    /**
     * The summed open quantity over those positions. Mixes units where the order does, so it is a headline figure, not a total to act on.
     */
    @SerializedName("open_quantity")
    var open_quantity: Double?,

    /**
     * Just enough of the order to render the answer — the full row is GET /orders/{id}.
     */
    @SerializedName("order")
    var order: OrderShippableOrder?,

    /**
     * Every position of the order, in position order, each with its open quantity.
     */
    @SerializedName("positions")
    var positions: List<OrderShippablePosition>?,

    /**
     * Whether a shipment would be accepted RIGHT NOW — the one question a "create shipment" button should be enabled on. False when the order is held, cancelled, completed, or has nothing open.
     */
    @SerializedName("shippable")
    var shippable: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "blocked_reason" to blocked_reason as Any,
        "open_positions" to open_positions as Any,
        "open_quantity" to open_quantity as Any,
        "order" to order?.toMap() as Any,
        "positions" to positions?.map { it.toMap() } as Any,
        "shippable" to shippable as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderShippable(
            blocked_reason = map["blocked_reason"] as? String,
            open_positions = (map["open_positions"] as? Number)?.toLong(),
            open_quantity = (map["open_quantity"] as? Number)?.toDouble(),
            order = OrderShippableOrder.from(map = map["order"] as Map<String, Any>),
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderShippablePosition.from(map = it) },
            shippable = map["shippable"] as? Boolean,
        )
    }
}