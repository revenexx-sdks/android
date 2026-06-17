package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AttributeEmailStatus

/**
 * AttributeEmail
 */
data class AttributeEmail(
    /**
     * Attribute creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Attribute update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Is attribute an array?
     */
    @SerializedName("array")
    var array: Boolean?,

    /**
     * Error message. Displays error generated on failure of creating or deleting an attribute.
     */
    @SerializedName("error")
    val error: String,

    /**
     * String format.
     */
    @SerializedName("format")
    val format: String,

    /**
     * Attribute Key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Is attribute required?
     */
    @SerializedName("required")
    val required: Boolean,

    /**
     * Attribute status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerializedName("status")
    val status: AttributeEmailStatus,

    /**
     * Attribute type.
     */
    @SerializedName("type")
    val type: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$updatedAt" to updatedAt as Any,
        "array" to array as Any,
        "error" to error as Any,
        "format" to format as Any,
        "key" to key as Any,
        "required" to required as Any,
        "status" to status.value as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeEmail(
            createdAt = map["\$createdAt"] as String,
            updatedAt = map["\$updatedAt"] as String,
            array = map["array"] as? Boolean,
            error = map["error"] as String,
            format = map["format"] as String,
            key = map["key"] as String,
            required = map["required"] as Boolean,
            status = AttributeEmailStatus.values().find { it.value == map["status"] as String }!!,
            type = map["type"] as String,
        )
    }
}