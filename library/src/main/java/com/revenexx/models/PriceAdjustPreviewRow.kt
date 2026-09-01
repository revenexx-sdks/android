package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One entry, before and after — the row a confirmation dialog shows.
 */
data class PriceAdjustPreviewRow(
    /**
     * The price entry this row is about.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * After rounding and ending snapping, in the same currency and on the same basis. Never negative: below the lowest candidate ending it clamps to it.
     */
    @SerializedName("new_unit_price")
    var new_unit_price: Double?,

    /**
     * The product it prices — null when the entry is identified by SKU.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Which rung of the ladder this is.
     */
    @SerializedName("quantity_min")
    var quantity_min: Double?,

    /**
     * The SKU it prices — null when the entry is identified by product id.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Before the change, in the list’s currency and on its tax basis.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id as Any,
        "new_unit_price" to new_unit_price as Any,
        "product_id" to product_id as Any,
        "quantity_min" to quantity_min as Any,
        "sku" to sku as Any,
        "unit_price" to unit_price as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceAdjustPreviewRow(
            id = map["id"] as? String,
            new_unit_price = (map["new_unit_price"] as? Number)?.toDouble(),
            product_id = map["product_id"] as? String,
            quantity_min = (map["quantity_min"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
        )
    }
}