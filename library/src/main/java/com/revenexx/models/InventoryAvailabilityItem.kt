package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * An item to check: 'product_id' or 'sku'.
 */
data class InventoryAvailabilityItem(
    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Requested quantity for the orderable check (default 1).
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * 
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