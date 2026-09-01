package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No payload — send {}. Which market is promoted comes from the path, and there is nothing else to say.
 */
class MarketMakeDefaultRequest(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketMakeDefaultRequest(
        )
    }
}