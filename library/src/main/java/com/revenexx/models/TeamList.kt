package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Teams List
 */
data class TeamList<T>(
    /**
     * List of teams.
     */
    @SerializedName("teams")
    val teams: List<Team<T>>,

    /**
     * Total number of teams that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "teams" to teams.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {
        operator fun invoke(
            teams: List<Team<Map<String, Any>>>,
            total: Long,
        ) = TeamList<Map<String, Any>>(
            teams,
            total,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = TeamList<T>(
            teams = (map["teams"] as List<Map<String, Any>>).map { Team.from(map = it, nestedType) },
            total = (map["total"] as Number).toLong(),
        )
    }
}