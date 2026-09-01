package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ProductCategoriesSource

/**
 * Partial update — omitted fields keep their current value.
 */
data class ProductCategoriesUpdateRequest(
    /**
     * The category it is filed into. One row per (product, category), whichever way it got there.
     */
    @SerializedName("category_id")
    var category_id: String?,

    /**
     * Sort order of this product inside the category.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The product filed into the category. Deleting the product deletes the membership with it.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How the membership came about: 'manual' is hand-picked, 'rule' was materialized by a category rule. The two never touch each other — a recompute only ever inserts and deletes `rule` rows, so a hand-picked membership survives every pass.
     */
    @SerializedName("source")
    var source: ProductCategoriesSource?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "category_id" to category_id as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "source" to source?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductCategoriesUpdateRequest(
            category_id = map["category_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as? String,
            source = ProductCategoriesSource.values().find { it.value == (map["source"] as? String) } ?: null,
        )
    }
}