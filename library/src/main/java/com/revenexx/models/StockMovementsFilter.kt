package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `stock_movements` — a typo, a filter another entity has, `?q=` — is DROPPED and cannot appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class StockMovementsFilter<T>(
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
     * The literal `?location_id=` value this call was understood to carry.
     */
    @SerializedName("location_id")
    var location_id: String?,

    /**
     * The literal `?metadata=` value this call was understood to carry.
     */
    @SerializedName("metadata")
    var metadata: String?,

    /**
     * The literal `?order_ref=` value this call was understood to carry.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

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
     * The literal `?reason=` value this call was understood to carry.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * The literal `?sku=` value this call was understood to carry.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The literal `?type=` value this call was understood to carry.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "location_id" to location_id as Any,
        "metadata" to metadata as Any,
        "order_ref" to order_ref as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "reason" to reason as Any,
        "sku" to sku as Any,
        "type" to type as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            created_at: String?,
            id: String?,
            location_id: String?,
            metadata: String?,
            order_ref: String?,
            product_id: String?,
            quantity: String?,
            reason: String?,
            sku: String?,
            type: String?,
            data: Map<String, Any>
        ) = StockMovementsFilter<Map<String, Any>>(
            created_at,
            id,
            location_id,
            metadata,
            order_ref,
            product_id,
            quantity,
            reason,
            sku,
            type,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = StockMovementsFilter<T>(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            location_id = map["location_id"] as? String,
            metadata = map["metadata"] as? String,
            order_ref = map["order_ref"] as? String,
            product_id = map["product_id"] as? String,
            quantity = map["quantity"] as? String,
            reason = map["reason"] as? String,
            sku = map["sku"] as? String,
            type = map["type"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}