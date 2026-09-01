package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One line of a delivery note: how much of one order position went out in one shipment.
 */
data class OrderShipmentItem(
    /**
     * When the booking was written.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the booked position line.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Which order position went out. Always a position of the same order as the shipment.
     */
    @SerializedName("order_item_id")
    var order_item_id: String?,

    /**
     * How much of that position this shipment carried. The sum of these over all shipments is the position's quantity_shipped.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * The shipment this booking belongs to. Deleting the shipment deletes it.
     */
    @SerializedName("shipment_id")
    var shipment_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "order_item_id" to order_item_id as Any,
        "quantity" to quantity as Any,
        "shipment_id" to shipment_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderShipmentItem(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            order_item_id = map["order_item_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            shipment_id = map["shipment_id"] as? String,
        )
    }
}