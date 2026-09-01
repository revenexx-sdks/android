package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AttributeValueBucket

/**
 * Where the value lives. Absent on an app whose custom fields are plain columns — then the field name IS the column.
 */
data class AttributeFieldStorage(
    /**
     * Which scope bucket this attribute writes to, implied by localizable/scopable.
     */
    @SerializedName("bucket")
    var bucket: AttributeValueBucket?,

    /**
     * The jsonb column holding the values (`attribute_values`).
     */
    @SerializedName("column")
    var column: String?,

    /**
     * The exact key path for the requested context, or null when the request named no locale/channel and the bucket needs one. Null means: read-only until a context is chosen.
     */
    @SerializedName("xpath")
    var xpath: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bucket" to bucket?.value as Any,
        "column" to column as Any,
        "path" to xpath as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeFieldStorage(
            bucket = AttributeValueBucket.values().find { it.value == (map["bucket"] as? String) } ?: null,
            column = map["column"] as? String,
            xpath = map["path"] as? List<String>,
        )
    }
}