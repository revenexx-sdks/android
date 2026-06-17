package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReferenceEntities(
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
    @SerializedName("image")
    var image: String?,

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
        "image" to image as Any,
        "labels" to labels as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReferenceEntities(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            image = map["image"] as? String,
            labels = map["labels"] as? Any,
            updated_at = map["updated_at"] as? String,
        )
    }
}