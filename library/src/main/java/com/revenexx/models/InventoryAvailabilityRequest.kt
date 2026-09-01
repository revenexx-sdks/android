package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryAvailabilityRequest(
    /**
     * The items to check, at most 200 in one call. A cart, a category page, a feed row — one call answers them all, which is why this route is the batch one.
     */
    @SerializedName("items")
    var items: List<InventoryAvailabilityItem>?,

    /**
     * Restrict the check to ONE location, by its code — the stock a click-and-collect store can promise today. Omitted, every ENABLED location is summed; a disabled one is never counted either way.
     */
    @SerializedName("location_code")
    var location_code: String?,

    /**
     * Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Inline single-item form: how many are wanted (default 1). It decides `orderable` and nothing else.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Inline single-item form: the article number to move (instead of `product_id`).
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items?.map { it.toMap() } as Any,
        "location_code" to location_code as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryAvailabilityRequest(
            items = (map["items"] as List<Map<String, Any>>).map { InventoryAvailabilityItem.from(map = it) },
            location_code = map["location_code"] as? String,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
        )
    }
}