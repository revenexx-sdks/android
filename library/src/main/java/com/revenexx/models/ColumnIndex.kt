package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Index
 */
data class ColumnIndex(
    /**
     * Index creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Index ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Index update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Index columns.
     */
    @SerializedName("columns")
    val columns: List<String>,

    /**
     * Error message. Displays error generated on failure of creating or deleting an index.
     */
    @SerializedName("error")
    val error: String,

    /**
     * Index Key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Index columns length.
     */
    @SerializedName("lengths")
    val lengths: List<Long>,

    /**
     * Index orders.
     */
    @SerializedName("orders")
    var orders: List<String>?,

    /**
     * Index status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerializedName("status")
    val status: String,

    /**
     * Index type.
     */
    @SerializedName("type")
    val type: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "columns" to columns as Any,
        "error" to error as Any,
        "key" to key as Any,
        "lengths" to lengths as Any,
        "orders" to orders as Any,
        "status" to status as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ColumnIndex(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            columns = map["columns"] as List<String>,
            error = map["error"] as String,
            key = map["key"] as String,
            lengths = map["lengths"] as List<Long>,
            orders = map["orders"] as? List<String>,
            status = map["status"] as String,
            type = map["type"] as String,
        )
    }
}