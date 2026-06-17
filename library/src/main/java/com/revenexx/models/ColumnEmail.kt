package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ColumnEmailStatus

/**
 * ColumnEmail
 */
data class ColumnEmail(
    /**
     * Column creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Column update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Is column an array?
     */
    @SerializedName("array")
    var array: Boolean?,

    /**
     * Error message. Displays error generated on failure of creating or deleting an column.
     */
    @SerializedName("error")
    val error: String,

    /**
     * String format.
     */
    @SerializedName("format")
    val format: String,

    /**
     * Column Key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Is column required?
     */
    @SerializedName("required")
    val required: Boolean,

    /**
     * Column status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerializedName("status")
    val status: ColumnEmailStatus,

    /**
     * Column type.
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
        ) = ColumnEmail(
            createdAt = map["\$createdAt"] as String,
            updatedAt = map["\$updatedAt"] as String,
            array = map["array"] as? Boolean,
            error = map["error"] as String,
            format = map["format"] as String,
            key = map["key"] as String,
            required = map["required"] as Boolean,
            status = ColumnEmailStatus.values().find { it.value == map["status"] as String }!!,
            type = map["type"] as String,
        )
    }
}