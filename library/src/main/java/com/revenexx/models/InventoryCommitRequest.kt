package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryCommitRequest(
    /**
     * The order whose active reservations are committed (shipment).
     */
    @SerializedName("order_ref")
    val order_ref: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "order_ref" to order_ref as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryCommitRequest(
            order_ref = map["order_ref"] as String,
        )
    }
}