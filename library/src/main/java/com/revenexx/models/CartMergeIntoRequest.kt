package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartMergeIntoRequest(
    /**
     * Receiving cart (must be active). The cart in the path is the source and becomes status merged.
     */
    @SerializedName("target_cart_id")
    val target_cart_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "target_cart_id" to target_cart_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartMergeIntoRequest(
            target_cart_id = map["target_cart_id"] as String,
        )
    }
}