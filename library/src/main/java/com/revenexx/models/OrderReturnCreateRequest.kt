package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Register a return against the shipped quantities — the return number is drawn from the 'return' range.
 */
data class OrderReturnCreateRequest(
    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("positions")
    val positions: List<OrderReturnPosition>,

    /**
     * 
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "metadata" to metadata as Any,
        "positions" to positions.map { it.toMap() } as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturnCreateRequest(
            metadata = map["metadata"] as? Any,
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderReturnPosition.from(map = it) },
            reason = map["reason"] as? String,
        )
    }
}