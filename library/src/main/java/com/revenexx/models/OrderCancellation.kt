package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderCancellation(
    /**
     * 
     */
    @SerializedName("cancelled_by")
    var cancelled_by: String?,

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
    @SerializedName("scope")
    var scope: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cancelled_by" to cancelled_by as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "order_id" to order_id as Any,
        "positions" to positions as Any,
        "reason" to reason as Any,
        "scope" to scope as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCancellation(
            cancelled_by = map["cancelled_by"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            order_id = map["order_id"] as? String,
            positions = map["positions"] as? Any,
            reason = map["reason"] as? String,
            scope = map["scope"] as? String,
        )
    }
}