package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Variable
 */
data class Variable(
    /**
     * Variable creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Variable ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Variable creation date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Variable key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * ID of resource to which the variable belongs. If resourceType is "project", it is empty. If resourceType is "function", it is ID of the function.
     */
    @SerializedName("resourceId")
    val resourceId: String,

    /**
     * Service to which the variable belongs. Possible values are "project", "function"
     */
    @SerializedName("resourceType")
    val resourceType: String,

    /**
     * Variable secret flag. Secret variables can only be updated or deleted, but never read.
     */
    @SerializedName("secret")
    val secret: Boolean,

    /**
     * Variable value.
     */
    @SerializedName("value")
    val value: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "key" to key as Any,
        "resourceId" to resourceId as Any,
        "resourceType" to resourceType as Any,
        "secret" to secret as Any,
        "value" to value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Variable(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            key = map["key"] as String,
            resourceId = map["resourceId"] as String,
            resourceType = map["resourceType"] as String,
            secret = map["secret"] as Boolean,
            value = map["value"] as String,
        )
    }
}