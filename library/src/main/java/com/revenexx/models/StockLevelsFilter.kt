package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call was understood to carry, verbatim as they arrived. A query parameter that is not a column of `stock_levels` — a typo, a filter another entity has, `?q=` — is DROPPED and cannot appear here, and the list comes back unfiltered. This object is the only way to tell that apart from "nothing matched".
 */
data class StockLevelsFilter<T>(
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
     * The literal `?on_hand=` value this call was understood to carry.
     */
    @SerializedName("on_hand")
    var on_hand: String?,

    /**
     * The literal `?product_id=` value this call was understood to carry.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The literal `?reorder_point=` value this call was understood to carry.
     */
    @SerializedName("reorder_point")
    var reorder_point: String?,

    /**
     * The literal `?reserved=` value this call was understood to carry.
     */
    @SerializedName("reserved")
    var reserved: String?,

    /**
     * The literal `?sku=` value this call was understood to carry.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The literal `?updated_at=` value this call was understood to carry.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

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
        "on_hand" to on_hand as Any,
        "product_id" to product_id as Any,
        "reorder_point" to reorder_point as Any,
        "reserved" to reserved as Any,
        "sku" to sku as Any,
        "updated_at" to updated_at as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            created_at: String?,
            id: String?,
            location_id: String?,
            metadata: String?,
            on_hand: String?,
            product_id: String?,
            reorder_point: String?,
            reserved: String?,
            sku: String?,
            updated_at: String?,
            data: Map<String, Any>
        ) = StockLevelsFilter<Map<String, Any>>(
            created_at,
            id,
            location_id,
            metadata,
            on_hand,
            product_id,
            reorder_point,
            reserved,
            sku,
            updated_at,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = StockLevelsFilter<T>(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            location_id = map["location_id"] as? String,
            metadata = map["metadata"] as? String,
            on_hand = map["on_hand"] as? String,
            product_id = map["product_id"] as? String,
            reorder_point = map["reorder_point"] as? String,
            reserved = map["reserved"] as? String,
            sku = map["sku"] as? String,
            updated_at = map["updated_at"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}