package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `product_associations` — `?status=`, a typo, a filter another entity has — is DROPPED and does not appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class ProductAssociationsFilter<T>(
    /**
     * The literal `?association_type_id=` value this call was understood to carry.
     */
    @SerializedName("association_type_id")
    var association_type_id: String?,

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
     * The literal `?quantity=` value this call was understood to carry.
     */
    @SerializedName("quantity")
    var quantity: String?,

    /**
     * The literal `?target_product_id=` value this call was understood to carry.
     */
    @SerializedName("target_product_id")
    var target_product_id: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "association_type_id" to association_type_id as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "target_product_id" to target_product_id as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            association_type_id: String?,
            created_at: String?,
            id: String?,
            position: String?,
            product_id: String?,
            quantity: String?,
            target_product_id: String?,
            data: Map<String, Any>
        ) = ProductAssociationsFilter<Map<String, Any>>(
            association_type_id,
            created_at,
            id,
            position,
            product_id,
            quantity,
            target_product_id,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = ProductAssociationsFilter<T>(
            association_type_id = map["association_type_id"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            position = map["position"] as? String,
            product_id = map["product_id"] as? String,
            quantity = map["quantity"] as? String,
            target_product_id = map["target_product_id"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}