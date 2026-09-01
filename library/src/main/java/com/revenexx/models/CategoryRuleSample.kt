package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CategoryRuleSample(
    /**
     * A matching product.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Its SKU, so the sample is readable. Null only for a row whose SKU is unset, which the database does not allow.
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CategoryRuleSample(
            id = map["id"] as? String,
            sku = map["sku"] as? String,
        )
    }
}