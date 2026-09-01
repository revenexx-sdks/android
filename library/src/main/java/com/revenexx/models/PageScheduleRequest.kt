package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * When this working copy should go live.
 */
data class PageScheduleRequest(
    /**
     * The moment to publish at. Stored on the edit state and echoed back normalized to UTC.
     */
    @SerializedName("scheduledAt")
    val scheduledAt: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "scheduledAt" to scheduledAt as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageScheduleRequest(
            scheduledAt = map["scheduledAt"] as String,
        )
    }
}