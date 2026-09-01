package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartMergeRequest(
    /**
     * The cart being folded in. It must be active, and it does NOT survive as a workspace: its lines are copied into the target, it becomes status merged, and merged_into_cart_id points at the target. Its own lines stay on it as the record of what was moved.
     */
    @SerializedName("source_cart_id")
    val source_cart_id: String,

    /**
     * The cart that SURVIVES. Must be active; it gains the source's lines (identical product lines at the same price adding up) and its totals are recomputed.
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