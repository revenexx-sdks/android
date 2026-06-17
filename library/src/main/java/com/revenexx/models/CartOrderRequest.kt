package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartOrderRequest(
    /**
     * External order reference from order management.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "order_ref" to order_ref as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartOrderRequest(
            order_ref = map["order_ref"] as? String,
        )
    }
}