package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderReturnSettlement

/**
 * 
 */
data class OrderReturnCompleteRequest(
    /**
     * How the return was settled. Omitted = settled without recording how.
     */
    @SerializedName("resolution")
    var resolution: OrderReturnSettlement?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "resolution" to resolution?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturnCompleteRequest(
            resolution = OrderReturnSettlement.values().find { it.value == (map["resolution"] as? String) } ?: null,
        )
    }
}