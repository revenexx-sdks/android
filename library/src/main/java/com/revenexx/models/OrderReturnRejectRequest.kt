package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderReturnRejectRequest(
    /**
     * Fallback for 'resolution'.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * Why the return was rejected.
     */
    @SerializedName("resolution")
    var resolution: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "reason" to reason as Any,
        "resolution" to resolution as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturnRejectRequest(
            reason = map["reason"] as? String,
            resolution = map["resolution"] as? String,
        )
    }
}