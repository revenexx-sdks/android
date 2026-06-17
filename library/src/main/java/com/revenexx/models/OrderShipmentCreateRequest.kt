package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Create a shipment. Omitted positions = ship everything still open.
 */
data class OrderShipmentCreateRequest(
    /**
     * 
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Delivery note number — drawn from the 'delivery' range when omitted.
     */
    @SerializedName("number")
    var number: String?,

    /**
     * Omitted = every position with open quantity, in full.
     */
    @SerializedName("positions")
    var positions: List<OrderShipmentPosition>?,

    /**
     * Defaults to now.
     */
    @SerializedName("shipped_at")
    var shipped_at: String?,

    /**
     * 
     */
    @SerializedName("tracking_code")
    var tracking_code: String?,

    /**
     * 
     */
    @SerializedName("tracking_url")
    var tracking_url: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "carrier" to carrier as Any,
        "metadata" to metadata as Any,
        "number" to number as Any,
        "positions" to positions.map { it.toMap() } as Any,
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