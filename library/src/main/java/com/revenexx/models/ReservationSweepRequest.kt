package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No fields — send `{}`. The cut-off is always now, and what counts as expired follows each reservation's own `expires_at` plus the `reservation_ttl_minutes` setting of the market it belongs to.
 */
class ReservationSweepRequest(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReservationSweepRequest(
        )
    }
}