package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderItemType

/**
 * One POSITION of an order, frozen at place-time: the article as it was, the price as it was, and three running quantities (shipped, cancelled, returned) that everything after placement books against. `quantity` itself never changes.
 */
data class OrderItem(
    /**
     * The chosen options of a configured line — what the configurator produced, in whatever shape it produces. Only meaningful for type 'configuration'; null everywhere else.
     */
    @SerializedName("configuration")
    var configuration: Any?,

    /**
     * The buyer's own cost centre for this line — a B2B field: the same order is split across several of them and the buyer's finance department needs the split per line, not per order.
     */
    @SerializedName("cost_center")
    var cost_center: String?,

    /**
     * When the position was written — the moment the order was placed.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the position. This is the id every positions[] payload names: /ship, /items/cancel and /return all take order_item_id.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * quantity × unit_price, NET, always COMPUTED here — a caller cannot set it. The order's subtotal is the sum of these.
     */
    @SerializedName("line_total")
    var line_total: Double?,

    /**
     * Free-form data belonging to the integration side, per position. Stored and returned untouched.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The article name as it stood at place-time, frozen. Falls back to the sku when the caller sent none — a position always reads as something.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The order this position belongs to. Deleting the order deletes its positions.
     */
    @SerializedName("order_id")
    var order_id: String?,

    /**
     * The line number a human reads, and what the order is sorted by. Numbered in steps of the range's position_step (10, 20, 30) unless the caller set it explicitly — the gap is what lets a line be inserted later without renumbering.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * A free note the buyer attached to this line — an engraving, a delivery instruction, the drawing number the line refers to. Printed on the paperwork, read by nothing.
     */
    @SerializedName("position_text")
    var position_text: String?,

    /**
     * The product as it was at place-time, FROZEN: the copy that makes the order still correct after the catalog changes its price, its name or its attributes. The caller decides how much of the product to freeze; this app stores it and reads nothing out of it.
     */
    @SerializedName("product")
    var product: Any?,

    /**
     * The catalog product this line was taken from (the products app). Null on a custom line, and it stays a reference — the position keeps working after the product is retired.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How much was ORDERED, in `unit`. Three decimal places, so 2.5 m of cable is a real order line. Never changed afterwards — cancelling or returning writes the quantity_* columns instead, which is what keeps the order a truthful record of what was asked for.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * How much of this position was cancelled and will never ship. Written by /cancel (all of it) and /items/cancel (a named quantity). Cancelling reduces the effective quantity, so an order whose every position is fully cancelled becomes cancelled itself.
     */
    @SerializedName("quantity_cancelled")
    var quantity_cancelled: Double?,

    /**
     * How much of this position came BACK, booked when a return is completed — not when it is registered or received. This is the goods accounting: it never reduces quantity_shipped, so a position can be shipped 3 and returned 3.
     */
    @SerializedName("quantity_returned")
    var quantity_returned: Double?,

    /**
     * How much of this position has GONE OUT, summed over the shipments. Written only by POST /orders/{id}/ship; it is what fulfillment_status is derived from, and what a return is guarded against.
     */
    @SerializedName("quantity_shipped")
    var quantity_shipped: Double?,

    /**
     * The article number as it stood at place-time, frozen with the rest of the line. The value an ERP and a warehouse both join on, and the one field a picker reads. Null only on a line that never had one.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Tax on this line in `currency`. Derived from line_total × tax_rate/100 when the caller sent none, which is the normal case — but a caller may send it, for a market whose rounding rules differ from ours.
     */
    @SerializedName("tax_amount")
    var tax_amount: Double?,

    /**
     * Tax percentage for this line, as a number (19 means 19 %). Frozen at place-time with everything else.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * What kind of line this is: 'product' is a catalog article, 'configuration' a configured one carrying its configuration, 'custom' a line typed by hand that no catalog knows.
     */
    @SerializedName("type")
    var type: OrderItemType?,

    /**
     * The unit the quantity is counted in — piece, metre, kilogram, package. Free text as the catalog carries it; this app does no conversion.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * NET price per unit, FROZEN at place-time. A later price change in the catalog does not reach this order.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * When the position last changed, which in practice means the last time a quantity was booked onto it.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Free-form data belonging to the ordering side, per position — carried through from the cart line and handed back untouched.
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
        "type" to type?.value as Any,
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
            type = OrderItemType.values().find { it.value == (map["type"] as? String) } ?: null,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
            user_data = map["user_data"] as? Any,
        )
    }
}