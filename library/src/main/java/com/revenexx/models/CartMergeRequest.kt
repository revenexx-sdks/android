package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartMergeRequest(
    /**
     * Cart whose lines move into the target (becomes status merged).
     */
    @SerializedName("source_cart_id")
    val source_cart_id: String,

    /**
     * Receiving cart (must be active).
     */
    @SerializedName("target_cart_id")
    val target_cart_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "source_cart_id" to source_cart_id as Any,
        "target_cart_id" to target_cart_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartMergeRequest(
            source_cart_id = map["source_cart_id"] as String,
            target_cart_id = map["target_cart_id"] as String,
        )
    }
}