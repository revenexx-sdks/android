package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The category has to exist already; this route files a product into one, it does not create one.
 */
data class ProductCategoryAssignRequest(
    /**
     * The category to file the product into.
     */
    @SerializedName("category_id")
    val category_id: String,

    /**
     * Sort order inside the category. Default 0.
     */
    @SerializedName("position")
    var position: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "category_id" to category_id as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductCategoryAssignRequest(
            category_id = map["category_id"] as String,
            position = (map["position"] as? Number)?.toLong(),
        )
    }
}