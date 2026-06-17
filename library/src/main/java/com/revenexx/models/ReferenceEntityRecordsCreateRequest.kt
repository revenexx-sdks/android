package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReferenceEntityRecordsCreateRequest(
    /**
     * 
     */
    @SerializedName("attribute_values")
    var attribute_values: Any?,

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
    @SerializedName("reference_entity_id")
    val reference_entity_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attribute_values" to attribute_values as Any,
        "code" to code as Any,
        "labels" to labels as Any,
        "reference_entity_id" to reference_entity_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReferenceEntityRecordsCreateRequest(
            attribute_values = map["attribute_values"] as? Any,
            code = map["code"] as String,
            labels = map["labels"] as? Any,
            reference_entity_id = map["reference_entity_id"] as String,
        )
    }
}