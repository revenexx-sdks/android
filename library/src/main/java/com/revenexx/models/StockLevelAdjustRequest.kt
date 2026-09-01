package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Correct ONE stock row. The row already knows its location and its item, so a caller owes only the signed delta and a reason — which is exactly what an operator can be asked for in a dialog.
 */
data class StockLevelAdjustRequest(
    /**
     * The SIGNED correction to this row's `on_hand`: −3 writes off three, +3 finds three. A delta, not the new balance. Zero is refused (400). A correction that would take `on_hand` below zero is a 422 the database insists on; one that would take it below this row's own `reserved` is a 422 the `allow_negative_stock` setting can permit.
     */
    @SerializedName("quantity")
    val quantity: Double,

    /**
     * Why this row is being corrected, written onto the ledger booking. Owed unless `movement_reason_required` is 'none'.
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "quantity" to quantity as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = StockLevelAdjustRequest(
            quantity = (map["quantity"] as Number).toDouble(),
            reason = map["reason"] as? String,
        )
    }
}