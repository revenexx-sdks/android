package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Transaction
 */
data class Transaction(
    /**
     * Transaction creation time in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Transaction ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Transaction update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Expiration time in ISO 8601 format.
     */
    @SerializedName("expiresAt")
    val expiresAt: String,

    /**
     * Number of operations in the transaction.
     */
    @SerializedName("operations")
    val operations: Long,

    /**
     * Current status of the transaction. One of: pending, committing, committed, rolled_back, failed.
     */
    @SerializedName("status")
    val status: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "expiresAt" to expiresAt as Any,
        "operations" to operations as Any,
        "status" to status as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Transaction(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            expiresAt = map["expiresAt"] as String,
            operations = (map["operations"] as Number).toLong(),
            status = map["status"] as String,
        )
    }
}