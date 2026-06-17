package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ProductCategoriesCreateRequest(
    /**
     * 
     */
    @SerializedName("category_id")
    val category_id: String,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("product_id")
    val product_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "category_id" to category_id as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductCategoriesCreateRequest(
            category_id = map["category_id"] as String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as String,
        )
    }
}