package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No payload — send {}. The kind is named by the path, and there is nothing else to decide.
 */
class OrderListKindMakeDefaultRequest(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListKindMakeDefaultRequest(
        )
    }
}