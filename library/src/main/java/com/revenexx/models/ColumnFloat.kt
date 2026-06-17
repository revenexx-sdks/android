package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ColumnFloatStatus

/**
 * ColumnFloat
 */
data class ColumnFloat(
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
     * Column Key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Maximum value to enforce for new documents.
     */
    @SerializedName("max")
    var max: Double?,

    /**
     * Minimum value to enforce for new documents.
     */
    @SerializedName("min")
    var min: Double?,

    /**
     * Is column required?
     */
    @SerializedName("required")
    val required: Boolean,

    /**
     * Column status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerializedName("status")
    val status: ColumnFloatStatus,

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
        "key" to key as Any,
        "max" to max as Any,
        "min" to min as Any,
        "required" to required as Any,
        "status" to status.value as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ColumnFloat(
            createdAt = map["\$createdAt"] as String,
            updatedAt = map["\$updatedAt"] as String,
            array = map["array"] as? Boolean,
            error = map["error"] as String,
            key = map["key"] as String,
            max = (map["max"] as? Number)?.toDouble(),
            min = (map["min"] as? Number)?.toDouble(),
            required = map["required"] as Boolean,
            status = ColumnFloatStatus.values().find { it.value == map["status"] as String }!!,
            type = map["type"] as String,
        )
    }
}