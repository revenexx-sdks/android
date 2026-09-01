package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One entry of the audit trail, which is also the domain event feed: every action writes a row, the manifest emits order_event.created on insert, and the row name IS the event name on the bus.
 */
data class OrderEvent(
    /**
     * Who caused it: the resolved contact id of the acting principal. Only order.placed and order.requested carry one today — every other row is null — so filtering on it filters to those two names. The database constrains nothing here (the column is text); the uuid shape is what this app WRITES, which is also why no example is published: no id an app invents names a row a tenant holds.
     */
    @SerializedName("actor")
    var actor: String?,

    /**
     * When it happened. The trail comes back oldest first, which is the order a human reads a history in.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the event row.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * WHAT happened, and this is the domain event: the manifest emits order_event.created on insert and this value is the event name on the bus. The names this app writes are order.placed, order.requested, order.updated, order.acknowledged, order.cancelled, order.item.cancelled, order.shipment.created, order.completed, order.held, order.unheld, order.payment_status.changed, order.comment.added, order.return.registered, order.return.received, order.return.completed and order.return.rejected.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The order this happened to.
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * The machine-readable body, and its shape follows `name`. order.placed / order.requested carry number, grand_total, currency, item_count, cart_id — plus approval_reason (permission | value_threshold) and threshold when the order is waiting for sign-off. order.shipment.created carries shipment_id, number, carrier, tracking_code and the booked positions. order.item.cancelled and order.return.* carry positions and the reason or resolution. order.completed carries via (shipment | payment | manual). order.payment_status.changed carries from, to and payment_id. Nothing validates it: it is what the route that wrote the row put there.
     */
    @SerializedName("payload")
    var payload: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "actor" to actor as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "name" to name as Any,
        "order_id" to order_id as Any,
        "payload" to payload as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderEvent(
            actor = map["actor"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            name = map["name"] as? String,
            order_id = map["order_id"] as? String,
            payload = map["payload"] as? Any,
        )
    }
}