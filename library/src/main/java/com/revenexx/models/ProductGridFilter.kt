package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ProductGridFilter(
    /**
     * The attribute code to filter on.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The attribute's i18n labels, for the filter's own caption.
     */
    @SerializedName("label")
    var label: Any?,

    /**
     * Which control the filter asks for — the same widget vocabulary the columns use.
     */
    @SerializedName("type")
    var type: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "label" to label as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductGridFilter(
            code = map["code"] as? String,
            label = map["label"] as? Any,
            type = map["type"] as? String,
        )
    }
}