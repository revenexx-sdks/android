package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One handover to a carrier — a delivery note. An order has as many of these as it took to get the goods out; each carries the position quantities it booked.
 */
data class OrderShipment(
    /**
     * Who is carrying it, in the merchant's own words. Free text — this app neither validates it nor knows the carrier's API.
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * When the shipment was booked here, which is not necessarily when it left — that is shipped_at.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the shipment.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The booked position quantities of this shipment.
     */
    @SerializedName("items")
    var items: List<OrderShipmentItem>?,

    /**
     * Free-form data for the caller — the warehouse system's own reference for this handover. Stored and returned untouched.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The DELIVERY NOTE number — drawn from the tenant's delivery range, unique per tenant, and a different series from the order number. A caller may supply its own when the number is issued by the warehouse system instead.
     */
    @SerializedName("number")
    var number: String?,

    /**
     * The order this shipment belongs to. Deleting the order deletes its shipments.
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * When the goods actually left. Defaults to now, and a caller may backdate it — a shipment booked on Monday for a Friday handover says Friday.
     */
    @SerializedName("shipped_at")
    var shipped_at: String?,

    /**
     * The consignment number the carrier issued. Free text: every carrier formats it differently and this app stores whatever it is given.
     */
    @SerializedName("tracking_code")
    var tracking_code: String?,

    /**
     * Where a human can follow the parcel. Supplied by the caller — this app does not build it, because only the caller knows the carrier's tracking address.
     */
    @SerializedName("tracking_url")
    var tracking_url: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "carrier" to carrier as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "items" to items?.map { it.toMap() } as Any,
        "metadata" to metadata as Any,
        "number" to number as Any,
        "order_id" to order_id as Any,
        "shipped_at" to shipped_at as Any,
        "tracking_code" to tracking_code as Any,
        "tracking_url" to tracking_url as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderShipment(
            carrier = map["carrier"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { OrderShipmentItem.from(map = it) },
            metadata = map["metadata"] as? Any,
            number = map["number"] as? String,
            order_id = map["order_id"] as? String,
            shipped_at = map["shipped_at"] as? String,
            tracking_code = map["tracking_code"] as? String,
            tracking_url = map["tracking_url"] as? String,
        )
    }
}