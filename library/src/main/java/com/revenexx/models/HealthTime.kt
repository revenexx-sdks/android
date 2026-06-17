package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Health Time
 */
data class HealthTime(
    /**
     * Difference of unix remote and local timestamps in milliseconds.
     */
    @SerializedName("diff")
    val diff: Long,

    /**
     * Current unix timestamp of local server where Appwrite runs.
     */
    @SerializedName("localTime")
    val localTime: Long,

    /**
     * Current unix timestamp on trustful remote server.
     */
    @SerializedName("remoteTime")
    val remoteTime: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "diff" to diff as Any,
        "localTime" to localTime as Any,
        "remoteTime" to remoteTime as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = HealthTime(
            diff = (map["diff"] as Number).toLong(),
            localTime = (map["localTime"] as Number).toLong(),
            remoteTime = (map["remoteTime"] as Number).toLong(),
        )
    }
}