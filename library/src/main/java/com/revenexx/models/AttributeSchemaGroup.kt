package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AttributeSchemaGroup(
    /**
     * The group code, which is what every field in the section carries as its `group`.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The section heading, resolved for the requested locale.
     */
    @SerializedName("label")
    var label: String?,

    /**
     * Where the section sits, ascending. The array is already in this order.
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "label" to label as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeSchemaGroup(
            code = map["code"] as? String,
            label = map["label"] as? String,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}