package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Families(
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

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "image_attribute" to image_attribute as Any,
        "label_attribute" to label_attribute as Any,
        "labels" to labels as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Families(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            image_attribute = map["image_attribute"] as? String,
            label_attribute = map["label_attribute"] as? String,
            labels = map["labels"] as? Any,
            updated_at = map["updated_at"] as? String,
        )
    }
}