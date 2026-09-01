package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartOrderRequest(
    /**
     * The order number this cart becomes, in order management's own numbering. Stored on the cart — filtering on it is how anyone gets from an order back to the cart behind it — and it is also the reference the stock reservation is booked under. Omit it and the cart id is used for the reservation instead.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "order_ref" to order_ref as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartOrderRequest(
            order_ref = map["order_ref"] as? String,
        )
    }
}