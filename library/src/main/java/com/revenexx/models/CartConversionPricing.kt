package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartPriceSnapshotMode

/**
 * How price_snapshot_mode settled the two prices every line carries.
 */
data class CartConversionPricing(
    /**
     * Lines in the cart when it converted.
     */
    @SerializedName("lines")
    var lines: Long?,

    /**
     * Lines the mode had to rewrite because snapshot and unit_price disagreed — repriced in 'snapshot' mode, re-snapshotted in 'live' mode. A line whose snapshot carries no readable price is never touched in either mode.
     */
    @SerializedName("lines_changed")
    var lines_changed: Long?,

    /**
     * The tenant's price_snapshot_mode, as it ran. 'snapshot' books the order on the price the buyer was shown; 'live' books it on the line's current unit_price and rewrites the snapshot to agree, so the frozen line never claims a price nobody was charged.
     */
    @SerializedName("mode")
    var mode: CartPriceSnapshotMode?,

    /**
     * The cart's frozen subtotal, and what the order is booked on.
     */
    @SerializedName("subtotal_after")
    var subtotal_after: Double?,

    /**
     * The cart's subtotal as it stood before the mode was applied. Compare it with subtotal_after and 'why is the order €4 off the cart' is answered by the response instead of by an argument.
     */
    @SerializedName("subtotal_before")
    var subtotal_before: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "lines" to lines as Any,
        "lines_changed" to lines_changed as Any,
        "mode" to mode?.value as Any,
        "subtotal_after" to subtotal_after as Any,
        "subtotal_before" to subtotal_before as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartConversionPricing(
            lines = (map["lines"] as? Number)?.toLong(),
            lines_changed = (map["lines_changed"] as? Number)?.toLong(),
            mode = CartPriceSnapshotMode.values().find { it.value == (map["mode"] as? String) } ?: null,
            subtotal_after = (map["subtotal_after"] as? Number)?.toDouble(),
            subtotal_before = (map["subtotal_before"] as? Number)?.toDouble(),
        )
    }
}