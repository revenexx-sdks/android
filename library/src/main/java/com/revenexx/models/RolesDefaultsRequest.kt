package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No fields — send {}.
 */
class RolesDefaultsRequest(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RolesDefaultsRequest(
        )
    }
}