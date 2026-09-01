package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryAdjustRequest(
    /**
     * The corrections, at most 200 in one call — a stocktake, breakage, shrinkage. Quantities are SIGNED deltas, not new balances.
     */
    @SerializedName("items")
    var items: List<InventoryAdjustItem>?,

    /**
     * Which location is being corrected. Omitted, the `default_location_code` setting decides. A correction is per location: the same SKU in two warehouses is two corrections.
     */
    @SerializedName("location_code")
    var location_code: String?,

    /**
     * Inline single-item form: the product to move, instead of a one-entry `items` array. The two forms are equivalent — nothing downstream knows which arrived.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Inline single-item form: the SIGNED correction (negative writes stock off, positive finds it). Non-zero.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Why the stock is being corrected — this is the audit trail a stocktake leaves behind. Owed unless `movement_reason_required` is 'none' (its default, 'adjustments', asks for one exactly here); missing where it is owed, the call is 400.
     */
    @SerializedName("reason")
    var reason: String?,

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
        "reason" to reason as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryAdjustRequest(
            items = (map["items"] as List<Map<String, Any>>).map { InventoryAdjustItem.from(map = it) },
            location_code = map["location_code"] as? String,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            reason = map["reason"] as? String,
            sku = map["sku"] as? String,
        )
    }
}