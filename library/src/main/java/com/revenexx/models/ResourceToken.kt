package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * ResourceToken
 */
data class ResourceToken(
    /**
     * Token creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Token ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Most recent access date in ISO 8601 format. This attribute is only updated again after 24 hours.
     */
    @SerializedName("accessedAt")
    val accessedAt: String,

    /**
     * Token expiration date in ISO 8601 format.
     */
    @SerializedName("expire")
    val expire: String,

    /**
     * Resource ID.
     */
    @SerializedName("resourceId")
    val resourceId: String,

    /**
     * Resource type.
     */
    @SerializedName("resourceType")
    val resourceType: String,

    /**
     * JWT encoded string.
     */
    @SerializedName("secret")
    val secret: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "accessedAt" to accessedAt as Any,
        "expire" to expire as Any,
        "resourceId" to resourceId as Any,
        "resourceType" to resourceType as Any,
        "secret" to secret as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ResourceToken(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            accessedAt = map["accessedAt"] as String,
            expire = map["expire"] as String,
            resourceId = map["resourceId"] as String,
            resourceType = map["resourceType"] as String,
            secret = map["secret"] as String,
        )
    }
}