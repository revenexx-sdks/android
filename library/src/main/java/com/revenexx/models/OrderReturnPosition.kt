package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A position quantity to return — guarded against the shipped (not yet returned) quantity.
 */
data class OrderReturnPosition(
    /**
     * The order item (position) to act on. Read the ids from GET /orders/{id} (items[].id) or GET /orders/{id}/shippable (positions[].order_item_id) — an id this order does not carry is a 400.
     */
    @SerializedName("order_item_id")
    val order_item_id: String,

    /**
     * Defaults to the full remaining quantity of the position.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Report this position for restocking when the return completes (the explicit inventories.restock call stays with the orchestrator).
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
        ) = OrderReturnPosition(
            order_item_id = map["order_item_id"] as String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            restock = map["restock"] as? Boolean,
        )
    }
}