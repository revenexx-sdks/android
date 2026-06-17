package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Team
 */
data class Team<T>(
    /**
     * Team creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Team ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Team update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Team name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Team preferences as a key-value object
     */
    @SerializedName("prefs")
    val prefs: Preferences<T>,

    /**
     * Total number of team members.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "name" to name as Any,
        "prefs" to prefs.toMap() as Any,
        "total" to total as Any,
    )

    companion object {
        operator fun invoke(
            createdAt: String,
            id: String,
            updatedAt: String,
            name: String,
            prefs: Preferences<Map<String, Any>>,
            total: Long,
        ) = Team<Map<String, Any>>(
            createdAt,
            id,
            updatedAt,
            name,
            prefs,
            total,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = Team<T>(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            name = map["name"] as String,
            prefs = Preferences.from(map = map["prefs"] as Map<String, Any>, nestedType),
            total = (map["total"] as Number).toLong(),
        )
    }
}