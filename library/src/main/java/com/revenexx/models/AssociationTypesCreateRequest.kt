package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AssociationTypesCreateRequest(
    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("is_quantified")
    var is_quantified: Boolean?,

    /**
     * 
     */
    @SerializedName("is_two_way")
    var is_two_way: Boolean?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "is_quantified" to is_quantified as Any,
        "is_two_way" to is_two_way as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AssociationTypesCreateRequest(
            code = map["code"] as String,
            is_quantified = map["is_quantified"] as? Boolean,
            is_two_way = map["is_two_way"] as? Boolean,
            labels = map["labels"] as? Any,
        )
    }
}