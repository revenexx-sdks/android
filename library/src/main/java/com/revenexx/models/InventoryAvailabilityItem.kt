package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One item to check: 'product_id' or 'sku'. Checking is free of consequence — it books nothing and holds nothing.
 */
data class InventoryAvailabilityItem(
    /**
     * The product to move, as the products app knows it. Give this OR `sku` — an item that names neither is answered 400. Matching is exact: a stock row keyed by SKU is not found by product id.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How many are wanted. It only decides `orderable`; the on_hand / reserved / available figures come back whatever it is. Omit it (or send null) to ask "is this sellable at all?", which is a check against 1.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * The article number to move, when the item has no product id. Give this OR `product_id`.
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryAvailabilityItem(
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
        )
    }
}