package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderItemType

/**
 * A position of the placed order — needs an identity: 'name' or 'sku'. Items are SNAPSHOTS: carry the product copy, prices are frozen at place-time.
 */
data class OrderItemCreateRequest(
    /**
     * Free-form configuration of configured lines.
     */
    @SerializedName("configuration")
    var configuration: Any?,

    /**
     * 
     */
    @SerializedName("cost_center")
    var cost_center: String?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Falls back to 'sku' when omitted.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Explicit position number; otherwise numbered in steps of the order range's position_step.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("position_text")
    var position_text: String?,

    /**
     * Frozen product snapshot at place-time ('snapshot' is accepted as an alias).
     */
    @SerializedName("product")
    var product: Any?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Default 1.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Alias for 'product'.
     */
    @SerializedName("snapshot")
    var snapshot: Any?,

    /**
     * Derived from line_total and tax_rate when omitted.
     */
    @SerializedName("tax_amount")
    var tax_amount: Double?,

    /**
     * Percent (default 0).
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * Line type (default 'product').
     */
    @SerializedName("type")
    var type: OrderItemType?,

    /**
     * 
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * Per-unit net price — line_total is always derived.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * Free-form user data.
     */
    @SerializedName("user_data")
    var user_data: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "configuration" to configuration as Any,
        "cost_center" to cost_center as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "position_text" to position_text as Any,
        "product" to product as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
        "snapshot" to snapshot as Any,
        "tax_amount" to tax_amount as Any,
        "tax_rate" to tax_rate as Any,
        "type" to type?.value as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "user_data" to user_data as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderItemCreateRequest(
            configuration = map["configuration"] as? Any,
            cost_center = map["cost_center"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            position_text = map["position_text"] as? String,
            product = map["product"] as? Any,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            snapshot = map["snapshot"] as? Any,
            tax_amount = (map["tax_amount"] as? Number)?.toDouble(),
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            type = OrderItemType.values().find { it.value == (map["type"] as? String) } ?: null,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            user_data = map["user_data"] as? Any,
        )
    }
}