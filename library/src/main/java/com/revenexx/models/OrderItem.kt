package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderItem(
    /**
     * 
     */
    @SerializedName("configuration")
    var configuration: Any?,

    /**
     * 
     */
    @SerializedName("cost_center")
    var cost_center: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("line_total")
    var line_total: Double?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("position_text")
    var position_text: String?,

    /**
     * 
     */
    @SerializedName("product")
    var product: Any?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * 
     */
    @SerializedName("quantity_cancelled")
    var quantity_cancelled: Double?,

    /**
     * 
     */
    @SerializedName("quantity_returned")
    var quantity_returned: Double?,

    /**
     * 
     */
    @SerializedName("quantity_shipped")
    var quantity_shipped: Double?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * 
     */
    @SerializedName("tax_amount")
    var tax_amount: Double?,

    /**
     * 
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * 
     */
    @SerializedName("type")
    var type: String?,

    /**
     * 
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * 
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
     */
    @SerializedName("user_data")
    var user_data: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "configuration" to configuration as Any,
        "cost_center" to cost_center as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "line_total" to line_total as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "order_id" to order_id as Any,
        "position" to position as Any,
        "position_text" to position_text as Any,
        "product" to product as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "quantity_cancelled" to quantity_cancelled as Any,
        "quantity_returned" to quantity_returned as Any,
        "quantity_shipped" to quantity_shipped as Any,
        "sku" to sku as Any,
        "tax_amount" to tax_amount as Any,
        "tax_rate" to tax_rate as Any,
        "type" to type as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "updated_at" to updated_at as Any,
        "user_data" to user_data as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderItem(
            configuration = map["configuration"] as? Any,
            cost_center = map["cost_center"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            line_total = (map["line_total"] as? Number)?.toDouble(),
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            order_id = map["order_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            position_text = map["position_text"] as? String,
            product = map["product"] as? Any,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            quantity_cancelled = (map["quantity_cancelled"] as? Number)?.toDouble(),
            quantity_returned = (map["quantity_returned"] as? Number)?.toDouble(),
            quantity_shipped = (map["quantity_shipped"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            tax_amount = (map["tax_amount"] as? Number)?.toDouble(),
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            type = map["type"] as? String,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
            user_data = map["user_data"] as? Any,
        )
    }
}