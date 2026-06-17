package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Document
 */
data class Document<T>(
    /**
     * Collection ID.
     */
    @SerializedName("\$collectionId")
    val collectionId: String,

    /**
     * Document creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Database ID.
     */
    @SerializedName("\$databaseId")
    val databaseId: String,

    /**
     * Document ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Document permissions. [Learn more about permissions](https://appwrite.io/docs/permissions).
     */
    @SerializedName("\$permissions")
    val permissions: List<String>,

    /**
     * Document automatically incrementing ID.
     */
    @SerializedName("\$sequence")
    val sequence: Long,

    /**
     * Document update date in ISO 8601 format.
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
        "\$collectionId" to collectionId as Any,
        "\$createdAt" to createdAt as Any,
        "\$databaseId" to databaseId as Any,
        "\$id" to id as Any,
        "\$permissions" to permissions as Any,
        "\$sequence" to sequence as Any,
        "\$updatedAt" to updatedAt as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            collectionId: String,
            createdAt: String,
            databaseId: String,
            id: String,
            permissions: List<String>,
            sequence: Long,
            updatedAt: String,
            data: Map<String, Any>
        ) = Document<Map<String, Any>>(
            collectionId,
            createdAt,
            databaseId,
            id,
            permissions,
            sequence,
            updatedAt,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = Document<T>(
            collectionId = map["\$collectionId"] as String,
            createdAt = map["\$createdAt"] as String,
            databaseId = map["\$databaseId"] as String,
            id = map["\$id"] as String,
            permissions = map["\$permissions"] as List<String>,
            sequence = (map["\$sequence"] as Number).toLong(),
            updatedAt = map["\$updatedAt"] as String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}