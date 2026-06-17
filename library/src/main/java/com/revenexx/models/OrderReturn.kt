package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderReturn(
    /**
     * 
     */
    @SerializedName("completed_at")
    var completed_at: String?,

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
    @SerializedName("positions")
    var positions: Any?,

    /**
     * 
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * 
     */
    @SerializedName("received_at")
    var received_at: String?,

    /**
     * 
     */
    @SerializedName("registered_at")
    var registered_at: String?,

    /**
     * 
     */
    @SerializedName("rejected_at")
    var rejected_at: String?,

    /**
     * 
     */
    @SerializedName("resolution")
    var resolution: String?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "completed_at" to completed_at as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "metadata" to metadata as Any,
        "number" to number as Any,
        "order_id" to order_id as Any,
        "positions" to positions as Any,
        "reason" to reason as Any,
        "received_at" to received_at as Any,
        "registered_at" to registered_at as Any,
        "rejected_at" to rejected_at as Any,
        "resolution" to resolution as Any,
        "status" to status as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturn(
            completed_at = map["completed_at"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            metadata = map["metadata"] as? Any,
            number = map["number"] as? String,
            order_id = map["order_id"] as? String,
            positions = map["positions"] as? Any,
            reason = map["reason"] as? String,
            received_at = map["received_at"] as? String,
            registered_at = map["registered_at"] as? String,
            rejected_at = map["rejected_at"] as? String,
            resolution = map["resolution"] as? String,
            status = map["status"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}