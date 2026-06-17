package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Sessions List
 */
data class SessionList(
    /**
     * List of sessions.
     */
    @SerializedName("sessions")
    val sessions: List<Session>,

    /**
     * Total number of sessions that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "sessions" to sessions.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SessionList(
            sessions = (map["sessions"] as List<Map<String, Any>>).map { Session.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}