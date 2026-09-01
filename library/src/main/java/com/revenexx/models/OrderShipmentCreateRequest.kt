package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Book what went out. Every field is optional: an empty body ships every position that still has an open quantity, in full, on a delivery note number drawn from the tenant's delivery range — which is the whole payload for the common case.
 */
data class OrderShipmentCreateRequest(
    /**
     * Who is carrying it, in the merchant's own words. Free text — this app neither validates it nor knows the carrier's API.
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * Free-form data for the caller — the warehouse system's own reference for this handover. Stored and returned untouched.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The DELIVERY NOTE number — drawn from the tenant's delivery range, unique per tenant, and a different series from the order number. A caller may supply its own when the number is issued by the warehouse system instead. Drawn from the 'delivery' range when omitted; supply one only when the number is issued elsewhere.
     */
    @SerializedName("number")
    var number: String?,

    /**
     * What this shipment carries. Omitted = every position with an open quantity, in full. GET /orders/{id}/shippable answers exactly the budget each one is guarded against.
     */
    @SerializedName("positions")
    var positions: List<OrderShipmentPosition>?,

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
        "metadata" to metadata as Any,
        "number" to number as Any,
        "positions" to positions?.map { it.toMap() } as Any,
        "shipped_at" to shipped_at as Any,
        "tracking_code" to tracking_code as Any,
        "tracking_url" to tracking_url as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderShipmentCreateRequest(
            carrier = map["carrier"] as? String,
            metadata = map["metadata"] as? Any,
            number = map["number"] as? String,
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderShipmentPosition.from(map = it) },
            shipped_at = map["shipped_at"] as? String,
            tracking_code = map["tracking_code"] as? String,
            tracking_url = map["tracking_url"] as? String,
        )
    }
}