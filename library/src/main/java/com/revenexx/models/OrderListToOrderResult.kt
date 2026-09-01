package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderListToOrderResult(
    /**
     * The list that was ordered. Unchanged by the call — the list stays, so it can be ordered again next month.
     */
    @SerializedName("list_id")
    var list_id: String?,

    /**
     * The orders app's answer, verbatim and unreshaped — the whole created order, whose shape is the orders app's own `Order` schema (GET /v1/orders/{id}) and is deliberately not restated here, because a copy would be the thing that goes stale. `order_id`, `order_number` and `status` are lifted out of it for a client that needs nothing else.
     */
    @SerializedName("order")
    var order: Any?,

    /**
     * The order the orders app created. Null only when that app answered without one, which is a fault worth reporting rather than a normal outcome.
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * The order number a human quotes, drawn from the tenant's order range by the orders app. It is NOT the id: every orders route addresses an order by uuid.
     */
    @SerializedName("order_number")
    var order_number: String?,

    /**
     * Positions handed to the orders app — the list's count minus `skipped`.
     */
    @SerializedName("positions")
    var positions: Long?,

    /**
     * Positions left out because the catalogue no longer knows their article. Only ever non-empty when 'on_missing_article' is 'skip'.
     */
    @SerializedName("skipped")
    var skipped: List<OrderListSkippedPosition>?,

    /**
     * Where the new order stands, as the orders app decided: 'placed' when it was accepted outright, 'pending' when it awaits approval — a contact holding only orders.request, or an order above the tenant's approval threshold. This app does not choose it and cannot override it.
     */
    @SerializedName("status")
    var status: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "list_id" to list_id as Any,
        "order" to order as Any,
        "order_id" to order_id as Any,
        "order_number" to order_number as Any,
        "positions" to positions as Any,
        "skipped" to skipped?.map { it.toMap() } as Any,
        "status" to status as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListToOrderResult(
            list_id = map["list_id"] as? String,
            order = map["order"] as? Any,
            order_id = map["order_id"] as? String,
            order_number = map["order_number"] as? String,
            positions = (map["positions"] as? Number)?.toLong(),
            skipped = (map["skipped"] as List<Map<String, Any>>).map { OrderListSkippedPosition.from(map = it) },
            status = map["status"] as? String,
        )
    }
}