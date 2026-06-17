package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.DatabaseType

/**
 * Database
 */
data class Database(
    /**
     * Database creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Database ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Database update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * If database is enabled. Can be 'enabled' or 'disabled'. When disabled, the database is inaccessible to users, but remains accessible to Server SDKs using API keys.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * Database name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Database type.
     */
    @SerializedName("type")
    val type: DatabaseType,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "enabled" to enabled as Any,
        "name" to name as Any,
        "type" to type.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Database(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            enabled = map["enabled"] as Boolean,
            name = map["name"] as String,
            type = DatabaseType.values().find { it.value == map["type"] as String }!!,
        )
    }
}