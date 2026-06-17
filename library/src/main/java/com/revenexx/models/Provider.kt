package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Provider
 */
data class Provider(
    /**
     * Provider creation time in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Provider ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Provider update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Provider credentials.
     */
    @SerializedName("credentials")
    val credentials: Any,

    /**
     * Is provider enabled?
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * The name for the provider instance.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Provider options.
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * The name of the provider service.
     */
    @SerializedName("provider")
    val provider: String,

    /**
     * Type of provider.
     */
    @SerializedName("type")
    val type: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "credentials" to credentials as Any,
        "enabled" to enabled as Any,
        "name" to name as Any,
        "options" to options as Any,
        "provider" to provider as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Provider(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            credentials = map["credentials"] as Any,
            enabled = map["enabled"] as Boolean,
            name = map["name"] as String,
            options = map["options"] as? Any,
            provider = map["provider"] as String,
            type = map["type"] as String,
        )
    }
}