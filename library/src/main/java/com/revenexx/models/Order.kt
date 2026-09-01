package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderFulfillmentStatus
import com.revenexx.enums.OrderPaymentStatus
import com.revenexx.enums.OrderStatus

/**
 * An ORDER as it was placed: a snapshot. Buyer, addresses, payment and shipping are frozen copies, the totals were computed here, and three independent dimensions say where it stands — status (lifecycle), payment_status (fed from outside) and fulfillment_status (derived from the positions).
 */
data class Order(
    /**
     * When the fulfilling system took the order over. Written once. While it is null the order can still be modified here; afterwards modification goes through that system, unless the tenant sets allow_modification_after_acknowledge.
     */
    @SerializedName("acknowledged_at")
    var acknowledged_at: String?,

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
     * When the order was cancelled, whether by a full cancel or by the last open quantity being cancelled position by position. Null otherwise.
     */
    @SerializedName("cancelled_at")
    var cancelled_at: String?,

    /**
     * The cart this order was placed from, when a storefront handed one over. A reference across an app boundary (the carts app), not a foreign key — nothing here checks that it resolves. Null for an order an integration or an operator created.
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * The sales channel the order arrived through — webshop, app, phone desk, EDI. Null when the caller named none.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * When the order was closed — by a full shipment, by payment or by hand, depending on the tenant's auto_complete_on. Null until then.
     */
    @SerializedName("completed_at")
    var completed_at: String?,

    /**
     * The PERSON who ordered — a contact in the customers app. Resolved from the acting principal whenever the caller carries one, and a body value that disagrees is refused rather than silently overridden. Null for a guest checkout.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * When the order row was written. For a placed order this is placed_at; for a requested one it is when the request was submitted.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * ISO 4217 code of EVERY amount on this order. Frozen at place-time from the market's default_currency unless the caller named one. Nothing on this order is ever converted, and the approval threshold is read in this currency — which is why the threshold is a per-market setting.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The BUYER's own reference — their purchase-order number. Free text, not unique, never generated here: it exists so the paperwork can carry the number the buyer's accounts payable will look for. One of the few fields PUT /orders/{id} may still change.
     */
    @SerializedName("customer_order_number")
    var customer_order_number: String?,

    /**
     * The FULFILLING system's reference for this order, typically the ERP order number. Written once by POST /orders/{id}/acknowledge and null until an integration acknowledged it.
     */
    @SerializedName("external_ref")
    var external_ref: String?,

    /**
     * Whether the order has SHIPPED, and the one dimension nobody writes: it is DERIVED after every quantity change from the positions' own bookkeeping. 'fulfilled' means shipped >= ordered − cancelled across all positions, 'partial' means something went out. Sending it has no effect; ship, cancel or return something and it moves.
     */
    @SerializedName("fulfillment_status")
    var fulfillment_status: OrderFulfillmentStatus?,

    /**
     * What the buyer owes: subtotal + shipping_total + tax_total, COMPUTED by this app and NEVER taken from the caller — trusting a supplied total is how inconsistent orders happened. This is the number the approval threshold is compared against and the number the revenue rollup sums.
     */
    @SerializedName("grand_total")
    var grand_total: Double?,

    /**
     * Why the order is held, in the words the shipping guard quotes back. Null when it is not held — releasing a hold clears it.
     */
    @SerializedName("hold_reason")
    var hold_reason: String?,

    /**
     * Primary key of the order, and the id every other route takes. Not the order number.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The summed ORDERED quantity over all positions, rounded to a whole number — a headline figure for a list, computed once at place-time. It is deliberately not reduced when something is cancelled or returned; the positions carry that arithmetic.
     */
    @SerializedName("item_count")
    var item_count: Long?,

    /**
     * Free-form data belonging to the INTEGRATION side — an ERP's own bookkeeping about this order. Stored and returned untouched; nothing here reads it.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The order number a human quotes — drawn from the tenant's order range at place-time, unique per tenant and never reused. It is NOT the id: every route addresses an order by uuid, and GET /orders?number=… is how a number becomes one.
     */
    @SerializedName("number")
    var number: String?,

    /**
     * A business stop, ORTHOGONAL to status: a held order keeps its lifecycle state and is refused at the guards. How far the hold reaches is the tenant's call (on_hold_blocks: shipping only, shipping and cancellation, or nothing at all).
     */
    @SerializedName("on_hold")
    var on_hold: Boolean?,

    /**
     * The COMPANY the order is booked on — an organization in the customers app, and the B2B half of who ordered. This is what orders.reports.customer-rollup aggregates by and what makes an order visible to a buyer's colleagues. Null on a private or guest order, which the rollup counts separately because it cannot attribute it.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * The payment arrangement as it was chosen, FROZEN. This app reads exactly two keys and stores the rest untouched: 'status' seeds payment_status at place-time when it names one of the permitted values (anything else is ignored and the order starts 'open'), and 'payment_id' is merged in by POST /orders/{id}/payment-status. The method itself, its provider fields and any redirect state belong to the payments app.
     */
    @SerializedName("payment")
    var payment: Any?,

    /**
     * Whether the order is PAID, and the dimension this app does not decide: it is fed from outside through POST /orders/{id}/payment-status (the payments app or an ERP), and only seeded at place-time from payment.status. Orthogonal to the lifecycle — a completed order can still be open, and a paid one can still be pending.
     */
    @SerializedName("payment_status")
    var payment_status: OrderPaymentStatus?,

    /**
     * When the order was PLACED. Null while it is pending approval: an order awaiting sign-off exists but was never placed, and that is exactly the difference this field records.
     */
    @SerializedName("placed_at")
    var placed_at: String?,

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
     * NET shipping cost, taken from shipping.price or, when the snapshot carries no price, from the request's shipping_total. In `currency`.
     */
    @SerializedName("shipping_total")
    var shipping_total: Double?,

    /**
     * Where the order stands in its LIFECYCLE, and one of three independent status dimensions. 'pending' = created but not placed, an order waiting for approval; 'placed' = accepted, nothing shipped; 'in_fulfillment' = part of it has gone out, or all of it has and the tenant does not close on shipment; 'completed' and 'cancelled' end it. Moved by the action routes only — it is not writable through PUT /orders/{id}.
     */
    @SerializedName("status")
    var status: OrderStatus?,

    /**
     * NET total of the positions (the sum of their line_total), COMPUTED here at place-time. In `currency`, four decimal places. A caller cannot set it.
     */
    @SerializedName("subtotal")
    var subtotal: Double?,

    /**
     * All tax on this order: the positions' tax_amount plus the tax on shipping (shipping_total × shipping.tax_rate). COMPUTED here — a caller cannot set it.
     */
    @SerializedName("tax_total")
    var tax_total: Double?,

    /**
     * When any column of the order last changed — every status move, every re-derived fulfillment, every modification.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Free-form data belonging to the ORDERING side — carried through from the storefront or the cart and handed back untouched. One of the few fields PUT /orders/{id} may still change.
     */
    @SerializedName("user_data")
    var user_data: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "acknowledged_at" to acknowledged_at as Any,
        "billing_address" to billing_address as Any,
        "buyer" to buyer as Any,
        "cancelled_at" to cancelled_at as Any,
        "cart_id" to cart_id as Any,
        "channel_id" to channel_id as Any,
        "completed_at" to completed_at as Any,
        "contact_id" to contact_id as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "customer_order_number" to customer_order_number as Any,
        "external_ref" to external_ref as Any,
        "fulfillment_status" to fulfillment_status?.value as Any,
        "grand_total" to grand_total as Any,
        "hold_reason" to hold_reason as Any,
        "id" to id as Any,
        "item_count" to item_count as Any,
        "metadata" to metadata as Any,
        "number" to number as Any,
        "on_hold" to on_hold as Any,
        "organization_id" to organization_id as Any,
        "payment" to payment as Any,
        "payment_status" to payment_status?.value as Any,
        "placed_at" to placed_at as Any,
        "shipping" to shipping as Any,
        "shipping_address" to shipping_address as Any,
        "shipping_total" to shipping_total as Any,
        "status" to status?.value as Any,
        "subtotal" to subtotal as Any,
        "tax_total" to tax_total as Any,
        "updated_at" to updated_at as Any,
        "user_data" to user_data as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Order(
            acknowledged_at = map["acknowledged_at"] as? String,
            billing_address = map["billing_address"] as? Any,
            buyer = map["buyer"] as? Any,
            cancelled_at = map["cancelled_at"] as? String,
            cart_id = map["cart_id"] as? String,
            channel_id = map["channel_id"] as? String,
            completed_at = map["completed_at"] as? String,
            contact_id = map["contact_id"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            customer_order_number = map["customer_order_number"] as? String,
            external_ref = map["external_ref"] as? String,
            fulfillment_status = OrderFulfillmentStatus.values().find { it.value == (map["fulfillment_status"] as? String) } ?: null,
            grand_total = (map["grand_total"] as? Number)?.toDouble(),
            hold_reason = map["hold_reason"] as? String,
            id = map["id"] as? String,
            item_count = (map["item_count"] as? Number)?.toLong(),
            metadata = map["metadata"] as? Any,
            number = map["number"] as? String,
            on_hold = map["on_hold"] as? Boolean,
            organization_id = map["organization_id"] as? String,
            payment = map["payment"] as? Any,
            payment_status = OrderPaymentStatus.values().find { it.value == (map["payment_status"] as? String) } ?: null,
            placed_at = map["placed_at"] as? String,
            shipping = map["shipping"] as? Any,
            shipping_address = map["shipping_address"] as? Any,
            shipping_total = (map["shipping_total"] as? Number)?.toDouble(),
            status = OrderStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            subtotal = (map["subtotal"] as? Number)?.toDouble(),
            tax_total = (map["tax_total"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
            user_data = map["user_data"] as? Any,
        )
    }
}