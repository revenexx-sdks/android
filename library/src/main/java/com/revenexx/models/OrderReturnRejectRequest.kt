package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderReturnRefusal

/**
 * 
 */
data class OrderReturnRejectRequest(
    /**
     * Free-text fallback for 'resolution' — a sentence about this one return, not a value out of the set.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * Why the return was refused.
     */
    @SerializedName("resolution")
    var resolution: OrderReturnRefusal?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "reason" to reason as Any,
        "resolution" to resolution?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturnRejectRequest(
            reason = map["reason"] as? String,
            resolution = OrderReturnRefusal.values().find { it.value == (map["resolution"] as? String) } ?: null,
        )
    }
}