package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FamilyVariantsCreateRequest(
    /**
     * 
     */
    @SerializedName("axes")
    var axes: Any?,

    /**
     * 
     */
    @SerializedName("code")
    val code: String,

    /**
     * 
     */
    @SerializedName("family_id")
    val family_id: String,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "axes" to axes as Any,
        "code" to code as Any,
        "family_id" to family_id as Any,
        "labels" to labels as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FamilyVariantsCreateRequest(
            axes = map["axes"] as? Any,
            code = map["code"] as String,
            family_id = map["family_id"] as String,
            labels = map["labels"] as? Any,
        )
    }
}