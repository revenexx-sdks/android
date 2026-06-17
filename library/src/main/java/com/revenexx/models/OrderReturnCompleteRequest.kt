package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderReturnCompleteRequest(
    /**
     * How the return was settled (refund, replacement, …).
     */
    @SerializedName("resolution")
    var resolution: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "resolution" to resolution as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturnCompleteRequest(
            resolution = map["resolution"] as? String,
        )
    }
}