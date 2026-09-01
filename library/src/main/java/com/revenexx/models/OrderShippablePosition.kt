package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One order position with the quantity that may still be shipped, and the three numbers that quantity is made of. Every position of the order is here, including the ones with nothing left open — a dialog needs to show a fully shipped line as fully shipped, not omit it.
 */
data class OrderShippablePosition(
    /**
     * The article name as it stood at place-time, frozen. Falls back to the sku when the caller sent none — a position always reads as something.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The position, by the id a positions[] payload names it with. This is what POST /orders/{id}/ship expects — copy it, do not construct it.
     */
    @SerializedName("order_item_id")
    var order_item_id: String?,

    /**
     * The line number a human reads, and what the order is sorted by. Numbered in steps of the range's position_step (10, 20, 30) unless the caller set it explicitly — the gap is what lets a line be inserted later without renumbering.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The catalog product this line was taken from (the products app). Null on a custom line, and it stays a reference — the position keeps working after the product is retired.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How much was ORDERED on this position. Unchanged by anything that happens afterwards.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * How much was cancelled and will never go out.
     */
    @SerializedName("quantity_cancelled")
    var quantity_cancelled: Double?,

    /**
     * quantity − shipped − cancelled: the budget POST /orders/{id}/ship guards this position against, and the largest quantity it will accept. Zero means the line is done.
     */
    @SerializedName("quantity_open")
    var quantity_open: Double?,

    /**
     * How much has already gone out.
     */
    @SerializedName("quantity_shipped")
    var quantity_shipped: Double?,

    /**
     * The article number as it stood at place-time, frozen with the rest of the line. The value an ERP and a warehouse both join on, and the one field a picker reads. Null only on a line that never had one.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The unit the quantity is counted in — piece, metre, kilogram, package. Free text as the catalog carries it; this app does no conversion.
     */
    @SerializedName("unit")
    var unit: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name as Any,
        "order_item_id" to order_item_id as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "quantity_cancelled" to quantity_cancelled as Any,
        "quantity_open" to quantity_open as Any,
        "quantity_shipped" to quantity_shipped as Any,
        "sku" to sku as Any,
        "unit" to unit as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderShippablePosition(
            name = map["name"] as? String,
            order_item_id = map["order_item_id"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            quantity_cancelled = (map["quantity_cancelled"] as? Number)?.toDouble(),
            quantity_open = (map["quantity_open"] as? Number)?.toDouble(),
            quantity_shipped = (map["quantity_shipped"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            unit = map["unit"] as? String,
        )
    }
}