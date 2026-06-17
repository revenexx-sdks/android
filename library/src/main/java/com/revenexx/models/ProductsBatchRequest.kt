package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ProductsBatchRequest(
    /**
     * 
     */
    @SerializedName("ids")
    var ids: List<String>?,

    /**
     * 
     */
    @SerializedName("skus")
    var skus: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "ids" to ids as Any,
        "skus" to skus as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductsBatchRequest(
            ids = map["ids"] as? List<String>,
            skus = map["skus"] as? List<String>,
        )
    }
}