package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartItemType

/**
 * 
 */
data class CartItem<T>(
    /**
     * The cart this line belongs to. A line never moves between carts — a merge copies it into the target and closes the source cart.
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * What was configured on this line, in the configurator's own vocabulary — this app stores it and reads nothing out of it. Its mere PRESENCE is behaviour: a line that carries a configuration never merges with another, because two differently configured units of the same article are not one line. Keys are the configurator's; the example is one shape, not the shape.
     */
    @SerializedName("configuration")
    var configuration: Any?,

    /**
     * When the line was added. A merge into an existing line keeps the original — the quantity moved, the line did not.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * ISO 4217 code this line is priced in. Defaults to the cart's currency when a line is added without one.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The line, as carts.items.get/update/delete address it.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * quantity × unit_price, net, always derived. A line_total in a payload is ignored: the cart may not disagree with its own arithmetic.
     */
    @SerializedName("line_total")
    var line_total: Double?,

    /**
     * Free-form data the storefront hangs on the line. Stored and returned verbatim; no key in here is read by this app.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * What the line reads as on the cart page. Falls back to the SKU when a caller sends none, so a line always has something to show.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order within the cart, ascending. Lines come back in this order unless `order` says otherwise, and a bulk replace numbers them by their place in the payload.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The catalogue product this line came from, when it came from one. Null on a custom line, and null on a product line the storefront identified by SKU alone.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How much of it. Fractional on purpose — 2.5 metres of cable is a line, not a rounding error — and always greater than zero: removing a line is a DELETE, not a quantity of 0.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * The article number the merchant sorts by in the ERP — the value every integration joins on. Free text here: this app does not resolve it against the catalogue, so it is exactly what the storefront wrote into the line. Together with product_id and unit_price it decides whether adding the same article again lands on this line or opens a new one.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The product as the buyer was shown it when this line was added — the cart's own copy, so it stays honest when the catalogue moves underneath it. Free-form apart from the price: conversion reads `unit_price` (or `price` as a fallback) and nothing else. A snapshot without a readable price leaves the line alone in both price modes, which is deliberate — a missing snapshot must never be read as "free".
     */
    @SerializedName("snapshot")
    var snapshot: CartItemSnapshot<T>?,

    /**
     * VAT percent for this line, as a number (19 means 19 %). Stored with the line for the order to use — no total in this app includes tax.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * The tenant this row belongs to, echoed by the data plane.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * What kind of line this is. 'product' is a catalogue line and the only type that ever merges with another. 'configuration' is a configured product — it carries its configuration and always stands alone, because two differently configured units of the same article are not the same line. 'custom' is a free line nobody has to find in a catalogue: a service, a surcharge, a hand-typed position.
     */
    @SerializedName("type")
    var type: CartItemType?,

    /**
     * The unit the quantity is counted in ('pcs', 'm', 'kg', 'h'). Display and ERP hand-over only; this app converts nothing.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * Net price of ONE unit, in the line's currency. This is the working price — a resync, a PUT on the line or a repricing job may have moved it since the buyer saw it. The price the buyer WAS shown lives in snapshot, and carts.order decides which of the two the order is booked on.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * When the line last changed — including a quantity another add merged into it.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cart_id" to cart_id as Any,
        "configuration" to configuration as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "id" to id as Any,
        "line_total" to line_total as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
        "snapshot" to snapshot?.toMap() as Any,
        "tax_rate" to tax_rate as Any,
        "tenant_id" to tenant_id as Any,
        "type" to type?.value as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {
        operator fun invoke(
            cart_id: String?,
            configuration: Any?,
            created_at: String?,
            currency: String?,
            id: String?,
            line_total: Double?,
            metadata: Any?,
            name: String?,
            position: Long?,
            product_id: String?,
            quantity: Double?,
            sku: String?,
            snapshot: CartItemSnapshot<Map<String, Any>>?,
            tax_rate: Double?,
            tenant_id: String?,
            type: CartItemType?,
            unit: String?,
            unit_price: Double?,
            updated_at: String?,
        ) = CartItem<Map<String, Any>>(
            cart_id,
            configuration,
            created_at,
            currency,
            id,
            line_total,
            metadata,
            name,
            position,
            product_id,
            quantity,
            sku,
            snapshot,
            tax_rate,
            tenant_id,
            type,
            unit,
            unit_price,
            updated_at,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = CartItem<T>(
            cart_id = map["cart_id"] as? String,
            configuration = map["configuration"] as? Any,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            id = map["id"] as? String,
            line_total = (map["line_total"] as? Number)?.toDouble(),
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            snapshot = CartItemSnapshot.from(map = map["snapshot"] as Map<String, Any>, nestedType),
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            tenant_id = map["tenant_id"] as? String,
            type = CartItemType.values().find { it.value == (map["type"] as? String) } ?: null,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
        )
    }
}