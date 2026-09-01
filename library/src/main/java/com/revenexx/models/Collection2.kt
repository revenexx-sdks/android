package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Collection
 */
data class Collection2(
    /**
     * Collection creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Collection ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Collection permissions. Each entry is a permission string: an action wrapping a role, e.g. `read("any")`, `update("user:abc")`, `delete("team:abc/owner")`. Actions are `read`, `create`, `update`, `delete` and the aggregate `write` (= create + update + delete); the role inside the quotes takes the form described under “Role strings” in this document's introduction.
     */
    @SerializedName("\$permissions")
    val permissions: List<String>,

    /**
     * Collection update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Collection attributes.
     */
    @SerializedName("attributes")
    val attributes: List<Any>,

    /**
     * Maximum document size in bytes. Returns 0 when no limit applies.
     */
    @SerializedName("bytesMax")
    val bytesMax: Long,

    /**
     * Currently used document size in bytes based on defined attributes.
     */
    @SerializedName("bytesUsed")
    val bytesUsed: Long,

    /**
     * Database ID.
     */
    @SerializedName("databaseId")
    val databaseId: String,

    /**
     * Whether document-level permissions are enabled. When it is, each record's own `$permissions` are enforced on top of the container's.
     */
    @SerializedName("documentSecurity")
    val documentSecurity: Boolean,

    /**
     * Collection enabled. Can be 'enabled' or 'disabled'. When disabled, the collection is inaccessible to users, but remains accessible to Server SDKs using API keys.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * Collection indexes.
     */
    @SerializedName("indexes")
    val indexes: List<Index>,

    /**
     * Collection name.
     */
    @SerializedName("name")
    val name: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$permissions" to permissions as Any,
        "\$updatedAt" to updatedAt as Any,
        "attributes" to attributes as Any,
        "bytesMax" to bytesMax as Any,
        "bytesUsed" to bytesUsed as Any,
        "databaseId" to databaseId as Any,
        "documentSecurity" to documentSecurity as Any,
        "enabled" to enabled as Any,
        "indexes" to indexes.map { it.toMap() } as Any,
        "name" to name as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Collection2(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            permissions = map["\$permissions"] as List<String>,
            updatedAt = map["\$updatedAt"] as String,
            attributes = map["attributes"] as List<Any>,
            bytesMax = (map["bytesMax"] as Number).toLong(),
            bytesUsed = (map["bytesUsed"] as Number).toLong(),
            databaseId = map["databaseId"] as String,
            documentSecurity = map["documentSecurity"] as Boolean,
            enabled = map["enabled"] as Boolean,
            indexes = (map["indexes"] as List<Map<String, Any>>).map { Index.from(map = it) },
            name = map["name"] as String,
        )
    }
}