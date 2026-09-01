package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Narrow modification — these six columns and no others. Anything else in the body is ignored, and a body with none of them at all is a 400 naming the allowed set. A whole key REPLACES the value it names; there is no merge into an existing snapshot. Nothing here moves the order: status, payment and fulfillment travel through the action routes.
 */
data class OrderUpdateRequest(
    /**
     * The invoice address, FROZEN at place-time. Changing the customer's address afterwards does not change what this order was billed to. Replaced wholesale — send the whole address, not a patch of it.
     */
    @SerializedName("billing_address")
    var billing_address: Any?,

    /**
     * The ordering party as it was at place-time, FROZEN: a copy, not a reference, so the order still reads correctly after the customer record is renamed, merged or deleted. The caller decides what goes in; this app stores it and reads nothing out of it. Replaced wholesale — send the whole snapshot, not a patch of it.
     */
    @SerializedName("buyer")
    var buyer: Any?,

    /**
     * The BUYER's own reference — their purchase-order number. Free text, not unique, never generated here: it exists so the paperwork can carry the number the buyer's accounts payable will look for. One of the few fields PUT /orders/{id} may still change.
     */
    @SerializedName("customer_order_number")
    var customer_order_number: String?,

    /**
     * Free-form data belonging to the INTEGRATION side — an ERP's own bookkeeping about this order. Stored and returned untouched; nothing here reads it. Replaced wholesale.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The delivery address, FROZEN at place-time — what goes on the label of every shipment of this order. Null on an order that is never delivered (a service, a digital item, a collection). Replaced wholesale. This is the one correction that actually matters after placement: the label of every shipment still to go out is printed from it.
     */
    @SerializedName("shipping_address")
    var shipping_address: Any?,

    /**
     * Free-form data belonging to the ORDERING side — carried through from the storefront or the cart and handed back untouched. One of the few fields PUT /orders/{id} may still change. Replaced wholesale.
     */
    @SerializedName("user_data")
    var user_data: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "billing_address" to billing_address as Any,
        "buyer" to buyer as Any,
        "customer_order_number" to customer_order_number as Any,
        "metadata" to metadata as Any,
        "shipping_address" to shipping_address as Any,
        "user_data" to user_data as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderUpdateRequest(
            billing_address = map["billing_address"] as? Any,
            buyer = map["buyer"] as? Any,
            customer_order_number = map["customer_order_number"] as? String,
            metadata = map["metadata"] as? Any,
            shipping_address = map["shipping_address"] as? Any,
            user_data = map["user_data"] as? Any,
        )
    }
}