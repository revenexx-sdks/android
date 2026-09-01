package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ProductGridColumnSource

/**
 * 
 */
data class ProductGridColumn(
    /**
     * The key to read out of a row: a column name for the fixed columns, an attribute code for the rest (then it is a key of the row's `attributes` object).
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The attribute's i18n labels, or a plain title for the fixed columns.
     */
    @SerializedName("label")
    var label: Any?,

    /**
     * Where the value comes from: 'column' is a plain products column, 'attribute' a key inside `attribute_values`, 'resolved' something this route computed (the display name).
     */
    @SerializedName("source")
    var source: ProductGridColumnSource?,

    /**
     * Which control renders the cell — the same widget vocabulary `GET /products/attribute-schema` uses, so one renderer serves both.
     */
    @SerializedName("type")
    var type: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "label" to label as Any,
        "source" to source?.value as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductGridColumn(
            code = map["code"] as? String,
            label = map["label"] as? Any,
            source = ProductGridColumnSource.values().find { it.value == (map["source"] as? String) } ?: null,
            type = map["type"] as? String,
        )
    }
}