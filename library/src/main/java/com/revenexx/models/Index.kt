package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.IndexStatus

/**
 * Index
 */
data class Index(
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
     * Index attributes.
     */
    @SerializedName("attributes")
    val attributes: List<String>,

    /**
     * Error message. Displays error generated on failure of creating or deleting an index.
     */
    @SerializedName("error")
    val error: String,

    /**
     * Index key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Index attributes length.
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
    val status: IndexStatus,

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
        "attributes" to attributes as Any,
        "error" to error as Any,
        "key" to key as Any,
        "lengths" to lengths as Any,
        "orders" to orders as Any,
        "status" to status.value as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Index(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            attributes = map["attributes"] as List<String>,
            error = map["error"] as String,
            key = map["key"] as String,
            lengths = map["lengths"] as List<Long>,
            orders = map["orders"] as? List<String>,
            status = IndexStatus.values().find { it.value == map["status"] as String }!!,
            type = map["type"] as String,
        )
    }
}