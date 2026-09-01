package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Every field is optional — the buyer, the organization and the positions all come from the list.
 */
data class OrderListToOrderRequest(
    /**
     * ISO 4217 code. Omit to let the orders app apply the market default.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The BUYER's own order or purchase-order number, forwarded to the orders app verbatim. Free text and never generated here: it exists so the paperwork can carry the number the buyer's accounts payable will look for.
     */
    @SerializedName("customer_order_number")
    var customer_order_number: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currency" to currency as Any,
        "customer_order_number" to customer_order_number as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListToOrderRequest(
            currency = map["currency"] as? String,
            customer_order_number = map["customer_order_number"] as? String,
        )
    }
}