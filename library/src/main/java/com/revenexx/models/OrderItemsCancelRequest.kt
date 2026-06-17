package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderItemsCancelRequest(
    /**
     * Acting user/system.
     */
    @SerializedName("cancelled_by")
    var cancelled_by: String?,

    /**
     * 
     */
    @SerializedName("positions")
    val positions: List<OrderCancelPosition>,

    /**
     * 
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cancelled_by" to cancelled_by as Any,
        "positions" to positions.map { it.toMap() } as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderItemsCancelRequest(
            cancelled_by = map["cancelled_by"] as? String,
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderCancelPosition.from(map = it) },
            reason = map["reason"] as? String,
        )
    }
}