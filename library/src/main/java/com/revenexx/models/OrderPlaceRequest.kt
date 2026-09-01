package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The snapshot payload: items plus frozen buyer/addresses/payment/shipping. The order number is drawn from the order range, totals are computed from the items.
 */
data class OrderPlaceRequest(
    /**
     * The invoice address, FROZEN at place-time. Changing the customer's address afterwards does not change what this order was billed to.
     */
    @SerializedName("billing_address")
    var billing_address: Any?,

    /**
     * The ordering party as it was at place-time, FROZEN: a copy, not a reference, so the order still reads correctly after the customer record is renamed, merged or deleted. The caller decides what goes in; this app stores it and reads nothing out of it.
     */
    @SerializedName("buyer")
    var buyer: Any?,

    /**
     * The cart this order was placed from, when a storefront handed one over. A reference across an app boundary (the carts app), not a foreign key — nothing here checks that it resolves. Null for an order an integration or an operator created. The carts.order hand-over sets it.
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * The sales channel the order arrived through — webshop, app, phone desk, EDI. Null when the caller named none.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * The PERSON who ordered — a contact in the customers app. Resolved from the acting principal whenever the caller carries one, and a body value that disagrees is refused rather than silently overridden. Null for a guest checkout. Ignored when the caller carries a principal — the RESOLVED contact wins, and a body value that disagrees is a 400 rather than a silent override.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code of EVERY amount on this order. Frozen at place-time from the market's default_currency unless the caller named one. Nothing on this order is ever converted, and the approval threshold is read in this currency — which is why the threshold is a per-market setting. Defaults to the market's default_currency setting.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The BUYER's own reference — their purchase-order number. Free text, not unique, never generated here: it exists so the paperwork can carry the number the buyer's accounts payable will look for. One of the few fields PUT /orders/{id} may still change.
     */
    @SerializedName("customer_order_number")
    var customer_order_number: String?,

    /**
     * Optional, and CHECKED rather than used: the order always computes its own total from the positions, the shipping cost and the tax. Send it as a checksum on that arithmetic — if it agrees the order is placed, and if it disagrees the call is refused with 400 naming both numbers, yours and the computed one. The comparison is at 2 decimal places (this app stores 4, ERPs work to 2, so a difference below a cent is agreement). It is never taken as the order value: the approval threshold and the revenue rollup read the computed number, which is why a total that disagrees is an error rather than an override.
     */
    @SerializedName("grand_total")
    var grand_total: Double?,

    /**
     * The order positions — at least one, and at most the tenant's max_items_per_order (500 out of the box; a longer list is a 400 naming the limit).
     */
    @SerializedName("items")
    val items: List<OrderItemCreateRequest>,

    /**
     * Free-form data belonging to the INTEGRATION side — an ERP's own bookkeeping about this order. Stored and returned untouched; nothing here reads it.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The COMPANY the order is booked on — an organization in the customers app, and the B2B half of who ordered. This is what orders.reports.customer-rollup aggregates by and what makes an order visible to a buyer's colleagues. Null on a private or guest order, which the rollup counts separately because it cannot attribute it. A principal's own organization wins over this when it has one.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * The payment arrangement as it was chosen, FROZEN. This app reads exactly two keys and stores the rest untouched: 'status' seeds payment_status at place-time when it names one of the permitted values (anything else is ignored and the order starts 'open'), and 'payment_id' is merged in by POST /orders/{id}/payment-status. The method itself, its provider fields and any redirect state belong to the payments app.
     */
    @SerializedName("payment")
    var payment: Any?,

    /**
     * The shipping arrangement as it was chosen, FROZEN. Two keys are READ at place-time and feed the totals: 'price' becomes shipping_total (the shipping_total field is only the fallback when this is absent) and 'tax_rate' is what shipping is taxed at, because shipping is a Nebenleistung and is taxed too. Everything else — the carrier product, the delivery window, the pickup point — is stored untouched and belongs to the shipping app.
     */
    @SerializedName("shipping")
    var shipping: Any?,

    /**
     * The delivery address, FROZEN at place-time — what goes on the label of every shipment of this order. Null on an order that is never delivered (a service, a digital item, a collection).
     */
    @SerializedName("shipping_address")
    var shipping_address: Any?,

    /**
     * NET shipping cost, taken from shipping.price or, when the snapshot carries no price, from the request's shipping_total. In `currency`. Only read when the shipping snapshot carries no 'price'.
     */
    @SerializedName("shipping_total")
    var shipping_total: Double?,

    /**
     * Free-form data belonging to the ORDERING side — carried through from the storefront or the cart and handed back untouched. One of the few fields PUT /orders/{id} may still change.
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