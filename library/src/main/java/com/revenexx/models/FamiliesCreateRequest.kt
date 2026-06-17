package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FamiliesCreateRequest(
    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("image_attribute")
    var image_attribute: String?,

    /**
     * 
     */
    @SerializedName("label_attribute")
    var label_attribute: String?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "image_attribute" to image_attribute as Any,
        "label_attribute" to label_attribute as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FamiliesCreateRequest(
            code = map["code"] as String,
            image_attribute = map["image_attribute"] as? String,
            label_attribute = map["label_attribute"] as? String,
            labels = map["labels"] as? Any,
        )
    }
}