package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderShipment(
    /**
     * 
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

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
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * 
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
        "created_at" to created_at as Any,
        "id" to id as Any,
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
            metadata = map["metadata"] as? Any,
            number = map["number"] as? String,
            order_id = map["order_id"] as? String,
            shipped_at = map["shipped_at"] as? String,
            tracking_code = map["tracking_code"] as? String,
            tracking_url = map["tracking_url"] as? String,
        )
    }
}