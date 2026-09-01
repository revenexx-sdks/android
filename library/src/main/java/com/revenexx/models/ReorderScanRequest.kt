package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No fields — send `{}`. What counts as low follows each row's own `reorder_point` and the market's `reorder_point_default`, exactly as GET /inventories/reorder-alerts computes it.
 */
class ReorderScanRequest(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReorderScanRequest(
        )
    }
}