package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AttributeOptions(
    /**
     * 
     */
    @SerializedName("attribute_id")
    var attribute_id: String?,

    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

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
        "created_at" to created_at as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "position" to position as Any,
        "swatch" to swatch as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeOptions(
            attribute_id = map["attribute_id"] as? String,
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            labels = map["labels"] as? Any,
            position = (map["position"] as? Number)?.toLong(),
            swatch = map["swatch"] as? Any,
        )
    }
}