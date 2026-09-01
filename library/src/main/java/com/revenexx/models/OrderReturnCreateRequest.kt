package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Register a return against the shipped quantities — the return number is drawn from the return range. Omitted positions = every position that still has a returnable quantity, in full ('the customer sent it all back').
 */
data class OrderReturnCreateRequest(
    /**
     * Free-form data for the caller — the returns portal's own reference. Stored and returned untouched.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * What is coming back. Omitted = every position with a returnable (shipped, not yet returned) quantity, in full.
     */
    @SerializedName("positions")
    var positions: List<OrderReturnPosition>?,

    /**
     * Why the goods are coming back, free text as the customer or the desk stated it. Also what /reject stores when it is given no resolution out of the published set.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * The default restock flag for positions that carry none of their own — and the only way to say "put it all back into stock" when the positions are defaulted. It does not restock anything itself: it decides what the completion REPORTS for the orchestrator's inventories.restock call.
     */
    @SerializedName("restock")
    var restock: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "metadata" to metadata as Any,
        "positions" to positions?.map { it.toMap() } as Any,
        "reason" to reason as Any,
        "restock" to restock as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderReturnCreateRequest(
            metadata = map["metadata"] as? Any,
            positions = (map["positions"] as List<Map<String, Any>>).map { OrderReturnPosition.from(map = it) },
            reason = map["reason"] as? String,
            restock = map["restock"] as? Boolean,
        )
    }
}