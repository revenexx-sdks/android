package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The snapshot payload: items plus frozen buyer/addresses/payment/shipping. The order number is drawn from the order range, totals are computed from the items.
 */
data class OrderPlaceRequest(
    /**
     * Frozen billing address.
     */
    @SerializedName("billing_address")
    var billing_address: Any?,

    /**
     * Frozen buyer snapshot (name, email, …).
     */
    @SerializedName("buyer")
    var buyer: Any?,

    /**
     * Source cart (the carts.order hand-over).
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * 
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Ordering customer contact.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code (default EUR).
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The buyer's own order/PO number.
     */
    @SerializedName("customer_order_number")
    var customer_order_number: String?,

    /**
     * Override — computed as subtotal + shipping + tax when omitted.
     */
    @SerializedName("grand_total")
    var grand_total: Double?,

    /**
     * The order positions (at most 500).
     */
    @SerializedName("items")
    val items: List<OrderItemCreateRequest>,

    /**
     * 
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * B2B organization.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Frozen payment snapshot — a known 'payment.status' seeds payment_status (otherwise 'open').
     */
    @SerializedName("payment")
    var payment: Any?,

    /**
     * Frozen shipping snapshot — 'shipping.price' seeds shipping_total.
     */
    @SerializedName("shipping")
    var shipping: Any?,

    /**
     * Frozen shipping address.
     */
    @SerializedName("shipping_address")
    var shipping_address: Any?,

    /**
     * Shipping total (fallback when 'shipping.price' is absent).
     */
    @SerializedName("shipping_total")
    var shipping_total: Double?,

    /**
     * Free-form user data.
     */
    @SerializedName("user_data")
    var user_data: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "billing_address" to billing_address as Any,
        "buyer" to buyer as Any,
        "cart_id" to cart_id as Any,
        "channel_id" to channel_id as Any,
        "contact_id" to contact_id as Any,
        "currency" to currency as Any,
        "customer_order_number" to customer_order_number as Any,
        "grand_total" to grand_total as Any,
        "items" to items.map { it.toMap() } as Any,
        "market_id" to market_id as Any,
        "metadata" to metadata as Any,
        "organization_id" to organization_id as Any,
        "payment" to payment as Any,
        "shipping" to shipping as Any,
        "shipping_address" to shipping_address as Any,
        "shipping_total" to shipping_total as Any,
        "user_data" to user_data as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderPlaceRequest(
            billing_address = map["billing_address"] as? Any,
            buyer = map["buyer"] as? Any,
            cart_id = map["cart_id"] as? String,
            channel_id = map["channel_id"] as? String,
            contact_id = map["contact_id"] as? String,
            currency = map["currency"] as? String,
            customer_order_number = map["customer_order_number"] as? String,
            grand_total = (map["grand_total"] as? Number)?.toDouble(),
            items = (map["items"] as List<Map<String, Any>>).map { OrderItemCreateRequest.from(map = it) },
            market_id = map["market_id"] as? String,
            metadata = map["metadata"] as? Any,
            organization_id = map["organization_id"] as? String,
            payment = map["payment"] as? Any,
            shipping = map["shipping"] as? Any,
            shipping_address = map["shipping_address"] as? Any,
            shipping_total = (map["shipping_total"] as? Number)?.toDouble(),
            user_data = map["user_data"] as? Any,
        )
    }
}