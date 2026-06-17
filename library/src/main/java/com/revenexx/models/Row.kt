package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Row
 */
data class Row<T>(
    /**
     * Row creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Database ID.
     */
    @SerializedName("\$databaseId")
    val databaseId: String,

    /**
     * Row ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Row permissions. [Learn more about permissions](https://appwrite.io/docs/permissions).
     */
    @SerializedName("\$permissions")
    val permissions: List<String>,

    /**
     * Row automatically incrementing ID.
     */
    @SerializedName("\$sequence")
    val sequence: Long,

    /**
     * Table ID.
     */
    @SerializedName("\$tableId")
    val tableId: String,

    /**
     * Row update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$databaseId" to databaseId as Any,
        "\$id" to id as Any,
        "\$permissions" to permissions as Any,
        "\$sequence" to sequence as Any,
        "\$tableId" to tableId as Any,
        "\$updatedAt" to updatedAt as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            createdAt: String,
            databaseId: String,
            id: String,
            permissions: List<String>,
            sequence: Long,
            tableId: String,
            updatedAt: String,
            data: Map<String, Any>
        ) = Row<Map<String, Any>>(
            createdAt,
            databaseId,
            id,
            permissions,
            sequence,
            tableId,
            updatedAt,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = Row<T>(
            createdAt = map["\$createdAt"] as String,
            databaseId = map["\$databaseId"] as String,
            id = map["\$id"] as String,
            permissions = map["\$permissions"] as List<String>,
            sequence = (map["\$sequence"] as Number).toLong(),
            tableId = map["\$tableId"] as String,
            updatedAt = map["\$updatedAt"] as String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}