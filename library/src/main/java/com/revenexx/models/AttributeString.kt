package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AttributeStringStatus

/**
 * AttributeString
 */
data class AttributeString(
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
     * Defines whether this attribute is encrypted or not.
     */
    @SerializedName("encrypt")
    var encrypt: Boolean?,

    /**
     * Error message. Displays error generated on failure of creating or deleting an attribute.
     */
    @SerializedName("error")
    val error: String,

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
     * Attribute size.
     */
    @SerializedName("size")
    val size: Long,

    /**
     * Attribute status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerializedName("status")
    val status: AttributeStringStatus,

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
        "encrypt" to encrypt as Any,
        "error" to error as Any,
        "key" to key as Any,
        "required" to required as Any,
        "size" to size as Any,
        "status" to status.value as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeString(
            createdAt = map["\$createdAt"] as String,
            updatedAt = map["\$updatedAt"] as String,
            array = map["array"] as? Boolean,
            encrypt = map["encrypt"] as? Boolean,
            error = map["error"] as String,
            key = map["key"] as String,
            required = map["required"] as Boolean,
            size = (map["size"] as Number).toLong(),
            status = AttributeStringStatus.values().find { it.value == map["status"] as String }!!,
            type = map["type"] as String,
        )
    }
}