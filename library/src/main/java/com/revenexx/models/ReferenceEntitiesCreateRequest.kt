package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReferenceEntitiesCreateRequest(
    /**
     * 
     */
    @SerializedName("code")
    val code: String,

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

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "image" to image as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReferenceEntitiesCreateRequest(
            code = map["code"] as String,
            image = map["image"] as? String,
            labels = map["labels"] as? Any,
        )
    }
}