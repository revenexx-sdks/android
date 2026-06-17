package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A position quantity to cancel — guarded against the open (unshipped, uncancelled) quantity.
 */
data class OrderCancelPosition(
    /**
     * The order item (position) to act on.
     */
    @SerializedName("order_item_id")
    val order_item_id: String,

    /**
     * Defaults to the full remaining quantity of the position.
     */
    @SerializedName("quantity")
    var quantity: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "order_item_id" to order_item_id as Any,
        "quantity" to quantity as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCancelPosition(
            order_item_id = map["order_item_id"] as String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
        )
    }
}