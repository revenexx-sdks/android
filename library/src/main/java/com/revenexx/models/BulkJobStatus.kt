package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Lifecycle of a `baseline.bulk_jobs` row:
 * `pending → running → completed`, or `partial` (finished with
 * `counts.rejected > 0`), `failed`, or `canceled`.
 * 
 */
class BulkJobStatus(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = BulkJobStatus(
        )
    }
}