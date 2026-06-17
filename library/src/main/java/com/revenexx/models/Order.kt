package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Order(
    /**
     * 
     */
    @SerializedName("acknowledged_at")
    var acknowledged_at: String?,

    /**
     * 
     */
    @SerializedName("billing_address")
    var billing_address: Any?,

    /**
     * 
     */
    @SerializedName("buyer")
    var buyer: Any?,

    /**
     * 
     */
    @SerializedName("cancelled_at")
    var cancelled_at: String?,

    /**
     * 
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * 
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * 
     */
    @SerializedName("completed_at")
    var completed_at: String?,

    /**
     * 
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("customer_order_number")
    var customer_order_number: String?,

    /**
     * 
     */
    @SerializedName("external_ref")
    var external_ref: String?,

    /**
     * 
     */
    @SerializedName("fulfillment_status")
    var fulfillment_status: String?,

    /**
     * 
     */
    @SerializedName("grand_total")
    var grand_total: Double?,

    /**
     * 
     */
    @SerializedName("hold_reason")
    var hold_reason: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("item_count")
    var item_count: Long?,

    /**
     * 
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("number")
    var number: String?,

    /**
     * 
     */
    @SerializedName("on_hold")
    var on_hold: Boolean?,

    /**
     * 
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * 
     */
    @SerializedName("payment")
    var payment: Any?,

    /**
     * 
     */
    @SerializedName("payment_status")
    var payment_status: String?,

    /**
     * 
     */
    @SerializedName("placed_at")
    var placed_at: String?,

    /**
     * 
     */
    @SerializedName("shipping")
    var shipping: Any?,

    /**
     * 
     */
    @SerializedName("shipping_address")
    var shipping_address: Any?,

    /**
     * 
     */
    @SerializedName("shipping_total")
    var shipping_total: Double?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
     */
    @SerializedName("subtotal")
    var subtotal: Double?,

    /**
     * 
     */
    @SerializedName("tax_total")
    var tax_total: Double?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
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
        "fulfillment_status" to fulfillment_status as Any,
        "grand_total" to grand_total as Any,
        "hold_reason" to hold_reason as Any,
        "id" to id as Any,
        "item_count" to item_count as Any,
        "market_id" to market_id as Any,
        "metadata" to metadata as Any,
        "number" to number as Any,
        "on_hold" to on_hold as Any,
        "organization_id" to organization_id as Any,
        "payment" to payment as Any,
        "payment_status" to payment_status as Any,
        "placed_at" to placed_at as Any,
        "shipping" to shipping as Any,
        "shipping_address" to shipping_address as Any,
        "shipping_total" to shipping_total as Any,
        "status" to status as Any,
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
            fulfillment_status = map["fulfillment_status"] as? String,
            grand_total = (map["grand_total"] as? Number)?.toDouble(),
            hold_reason = map["hold_reason"] as? String,
            id = map["id"] as? String,
            item_count = (map["item_count"] as? Number)?.toLong(),
            market_id = map["market_id"] as? String,
            metadata = map["metadata"] as? Any,
            number = map["number"] as? String,
            on_hold = map["on_hold"] as? Boolean,
            organization_id = map["organization_id"] as? String,
            payment = map["payment"] as? Any,
            payment_status = map["payment_status"] as? String,
            placed_at = map["placed_at"] as? String,
            shipping = map["shipping"] as? Any,
            shipping_address = map["shipping_address"] as? Any,
            shipping_total = (map["shipping_total"] as? Number)?.toDouble(),
            status = map["status"] as? String,
            subtotal = (map["subtotal"] as? Number)?.toDouble(),
            tax_total = (map["tax_total"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
            user_data = map["user_data"] as? Any,
        )
    }
}