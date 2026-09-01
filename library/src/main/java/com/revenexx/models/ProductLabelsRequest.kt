package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ProductLabelsRequest(
    /**
     * Product ids to name. At most 500.
     */
    @SerializedName("ids")
    var ids: List<String>?,

    /**
     * Product SKUs to name. At most 500.
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
        ) = ProductLabelsRequest(
            ids = map["ids"] as? List<String>,
            skus = map["skus"] as? List<String>,
        )
    }
}