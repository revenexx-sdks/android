package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Table
 */
data class Table(
    /**
     * Table creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Table ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Table permissions. Each entry is a permission string: an action wrapping a role, e.g. `read("any")`, `update("user:abc")`, `delete("team:abc/owner")`. Actions are `read`, `create`, `update`, `delete` and the aggregate `write` (= create + update + delete); the role inside the quotes takes the form described under “Role strings” in this document's introduction.
     */
    @SerializedName("\$permissions")
    val permissions: List<String>,

    /**
     * Table update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Maximum row size in bytes. Returns 0 when no limit applies.
     */
    @SerializedName("bytesMax")
    val bytesMax: Long,

    /**
     * Currently used row size in bytes based on defined columns.
     */
    @SerializedName("bytesUsed")
    val bytesUsed: Long,

    /**
     * Table columns.
     */
    @SerializedName("columns")
    val columns: List<Any>,

    /**
     * Database ID.
     */
    @SerializedName("databaseId")
    val databaseId: String,

    /**
     * Table enabled. Can be 'enabled' or 'disabled'. When disabled, the table is inaccessible to users, but remains accessible to Server SDKs using API keys.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * Table indexes.
     */
    @SerializedName("indexes")
    val indexes: List<ColumnIndex>,

    /**
     * Table name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Whether row-level permissions are enabled. When it is, each record's own `$permissions` are enforced on top of the container's.
     */
    @SerializedName("rowSecurity")
    val rowSecurity: Boolean,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$permissions" to permissions as Any,
        "\$updatedAt" to updatedAt as Any,
        "bytesMax" to bytesMax as Any,
        "bytesUsed" to bytesUsed as Any,
        "columns" to columns as Any,
        "databaseId" to databaseId as Any,
        "enabled" to enabled as Any,
        "indexes" to indexes.map { it.toMap() } as Any,
        "name" to name as Any,
        "rowSecurity" to rowSecurity as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Table(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            permissions = map["\$permissions"] as List<String>,
            updatedAt = map["\$updatedAt"] as String,
            bytesMax = (map["bytesMax"] as Number).toLong(),
            bytesUsed = (map["bytesUsed"] as Number).toLong(),
            columns = map["columns"] as List<Any>,
            databaseId = map["databaseId"] as String,
            enabled = map["enabled"] as Boolean,
            indexes = (map["indexes"] as List<Map<String, Any>>).map { ColumnIndex.from(map = it) },
            name = map["name"] as String,
            rowSecurity = map["rowSecurity"] as Boolean,
        )
    }
}