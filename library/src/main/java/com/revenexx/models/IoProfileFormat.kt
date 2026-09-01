package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Profile source/sink format. `bmecat` is profile-only — the ad-hoc
 * `/io/imports` and `/io/exports` endpoints do not accept it.
 * 
 */
class IoProfileFormat(
) {
    fun toMap(): Map<String, Any> = mapOf(
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = IoProfileFormat(
        )
    }
}