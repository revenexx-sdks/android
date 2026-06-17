package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AttributeOptionsCreateRequest(
    /**
     * 
     */
    @SerializedName("attribute_id")
    val attribute_id: String,

    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("swatch")
    var swatch: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_id" to attribute_id as Any,
        "code" to code as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "swatch" to swatch as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeOptionsCreateRequest(
            attribute_id = map["attribute_id"] as String,
            code = map["code"] as String,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            swatch = map["swatch"] as? Any,
        )
    }
}