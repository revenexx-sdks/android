package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One value per PE-102 block that moves data.
 */
class BulkJobType(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = BulkJobType(
        )
    }
}