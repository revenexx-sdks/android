package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderCancelRequest(
    /**
     * Acting user/system.
     */
    @SerializedName("cancelled_by")
    var cancelled_by: String?,

    /**
     * 
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cancelled_by" to cancelled_by as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCancelRequest(
            cancelled_by = map["cancelled_by"] as? String,
            reason = map["reason"] as? String,
        )
    }
}