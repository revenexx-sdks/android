package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Which cart survived, and what it cost. `target` is the cart that SURVIVES, already recomputed — that is the one to render. The source cart still exists and still holds its own lines: a merge copies them into the target and closes the source, it does not move them.
 */
data class CartMergeResult(
    /**
     * The source cart, now status merged, with merged_into_cart_id pointing at the target. It still exists and still holds its own lines: the merge copies, it does not move.
     */
    @SerializedName("merged_cart_id")
    var merged_cart_id: String?,

    /**
     * Lines read out of the source. Identical product lines at the same price add up rather than duplicating, so the target may have gained fewer rows than this.
     */
    @SerializedName("merged_lines")
    var merged_lines: Long?,

    /**
     * 
     */
    @SerializedName("target")
    var target: Cart?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "merged_cart_id" to merged_cart_id as Any,
        "merged_lines" to merged_lines as Any,
        "target" to target?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartMergeResult(
            merged_cart_id = map["merged_cart_id"] as? String,
            merged_lines = (map["merged_lines"] as? Number)?.toLong(),
            target = Cart.from(map = map["target"] as Map<String, Any>),
        )
    }
}