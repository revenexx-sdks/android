package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One quantity to put back into stock, named the way the inventories app wants it: by product, by sku, and how much.
 */
data class OrderRestockPosition(
    /**
     * The catalog product to restock. Null on a custom line, which is why `sku` is carried alongside it.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How much came back on this position, in the position's own unit.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * The article number to restock — the key a warehouse actually books against.
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
        ) = OrderRestockPosition(
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
        )
    }
}