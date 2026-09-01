package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderItemType

/**
 * A position of the placed order — needs an identity: 'name' or 'sku'. Items are SNAPSHOTS: carry the product copy, prices are frozen at place-time.
 */
data class OrderItemCreateRequest(
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
     * Free-form data belonging to the integration side, per position. Stored and returned untouched.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The article name as it stood at place-time, frozen. Falls back to the sku when the caller sent none — a position always reads as something. Falls back to 'sku' when omitted; one of the two is required.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The line number a human reads, and what the order is sorted by. Numbered in steps of the range's position_step (10, 20, 30) unless the caller set it explicitly — the gap is what lets a line be inserted later without renumbering. Omitted = numbered in steps of the order range's position_step.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * A free note the buyer attached to this line — an engraving, a delivery instruction, the drawing number the line refers to. Printed on the paperwork, read by nothing.
     */
    @SerializedName("position_text")
    var position_text: String?,

    /**
     * The product as it was at place-time, FROZEN: the copy that makes the order still correct after the catalog changes its price, its name or its attributes. The caller decides how much of the product to freeze; this app stores it and reads nothing out of it. 'snapshot' is accepted as an alias for this key.
     */
    @SerializedName("product")
    var product: Any?,

    /**
     * The catalog product this line was taken from (the products app). Null on a custom line, and it stays a reference — the position keeps working after the product is retired.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How much was ORDERED, in `unit`. Three decimal places, so 2.5 m of cable is a real order line. Never changed afterwards — cancelling or returning writes the quantity_* columns instead, which is what keeps the order a truthful record of what was asked for. Defaults to 1.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * The article number as it stood at place-time, frozen with the rest of the line. The value an ERP and a warehouse both join on, and the one field a picker reads. Null only on a line that never had one.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The product as it was at place-time, FROZEN: the copy that makes the order still correct after the catalog changes its price, its name or its attributes. The caller decides how much of the product to freeze; this app stores it and reads nothing out of it. Alias for 'product' — send one or the other, not both.
     */
    @SerializedName("snapshot")
    var snapshot: Any?,

    /**
     * Tax on this line in `currency`. Derived from line_total × tax_rate/100 when the caller sent none, which is the normal case — but a caller may send it, for a market whose rounding rules differ from ours. Send it only where your market rounds differently from line_total × tax_rate/100.
     */
    @SerializedName("tax_amount")
    var tax_amount: Double?,

    /**
     * Tax percentage for this line, as a number (19 means 19 %). Frozen at place-time with everything else. Defaults to 0.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * What kind of line this is: 'product' is a catalog article, 'configuration' a configured one carrying its configuration, 'custom' a line typed by hand that no catalog knows. Defaults to 'product'.
     */
    @SerializedName("type")
    var type: OrderItemType?,

    /**
     * The unit the quantity is counted in — piece, metre, kilogram, package. Free text as the catalog carries it; this app does no conversion.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * NET price per unit, FROZEN at place-time. A later price change in the catalog does not reach this order. Defaults to 0. line_total is always derived from it and never taken from the body.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * Free-form data belonging to the ordering side, per position — carried through from the cart line and handed back untouched.
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