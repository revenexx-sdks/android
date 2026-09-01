package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `product_categories` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class ProductCategoriesFilter<T>(
    /**
     * The literal `?category_id=` value this call was understood to carry.
     */
    @SerializedName("category_id")
    var category_id: String?,

    /**
     * The literal `?created_at=` value this call was understood to carry.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The literal `?id=` value this call was understood to carry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The literal `?position=` value this call was understood to carry.
     */
    @SerializedName("position")
    var position: String?,

    /**
     * The literal `?product_id=` value this call was understood to carry.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The literal `?source=` value this call was understood to carry.
     */
    @SerializedName("source")
    var source: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "category_id" to category_id as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "source" to source as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            category_id: String?,
            created_at: String?,
            id: String?,
            position: String?,
            product_id: String?,
            source: String?,
            data: Map<String, Any>
        ) = ProductCategoriesFilter<Map<String, Any>>(
            category_id,
            created_at,
            id,
            position,
            product_id,
            source,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = ProductCategoriesFilter<T>(
            category_id = map["category_id"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            position = map["position"] as? String,
            product_id = map["product_id"] as? String,
            source = map["source"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}