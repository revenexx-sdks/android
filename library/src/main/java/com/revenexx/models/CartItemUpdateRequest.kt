package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartItemType

/**
 * Partial update — omitted fields keep their current value.
 */
data class CartItemUpdateRequest<T>(
    /**
     * What was configured on this line, in the configurator's own vocabulary — this app stores it and reads nothing out of it. Its mere PRESENCE is behaviour: a line that carries a configuration never merges with another, because two differently configured units of the same article are not one line. Keys are the configurator's; the example is one shape, not the shape.
     */
    @SerializedName("configuration")
    var configuration: Any?,

    /**
     * ISO 4217 code. Defaults to the cart's currency.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Free-form data the storefront hangs on the line. Stored and returned verbatim; no key in here is read by this app.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * What the line reads as on the cart page. Falls back to 'sku' when omitted, so a line always has something to show.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order within the cart, ascending. Default 0 when adding a line; in a bulk replace the payload order fills it in.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The catalogue product, when the line comes from one. Part of the merge identity: same product, same price, one line.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * How much of it — default 1. Fractional is legal (2.5 m of cable); zero and negative are not. On a plain product line that merges into an existing one, this is ADDED to what is already there, and max_quantity_per_line is checked on the result.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * The article number, exactly as the merchant knows it. Free text — this app does not resolve it against the catalogue — and part of the merge identity together with product_id and unit_price. The example only shows the shape of a real article number; nothing here enforces one.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The product as the buyer was shown it when this line was added — the cart's own copy, so it stays honest when the catalogue moves underneath it. Free-form apart from the price: conversion reads `unit_price` (or `price` as a fallback) and nothing else. A snapshot without a readable price leaves the line alone in both price modes, which is deliberate — a missing snapshot must never be read as "free".
     */
    @SerializedName("snapshot")
    var snapshot: CartItemSnapshot<T>?,

    /**
     * VAT percent for this line, as a number (19 means 19 %). Stored for the order to use — no total in this app includes tax.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * Line type (default 'product'). Plain product lines merge by product+price; configurations always stand alone.
     */
    @SerializedName("type")
    var type: CartItemType?,

    /**
     * The unit the quantity is counted in. Display and ERP hand-over only — this app converts nothing.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * Net price of one unit — line_total is always derived from it, never sent. Part of the merge identity: the same article at a different price opens a new line rather than averaging into the old one.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "configuration" to configuration as Any,
        "currency" to currency as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
        "snapshot" to snapshot?.toMap() as Any,
        "tax_rate" to tax_rate as Any,
        "type" to type?.value as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
    )

    companion object {
        operator fun invoke(
            configuration: Any?,
            currency: String?,
            metadata: Any?,
            name: String?,
            position: Long?,
            product_id: String?,
            quantity: Double?,
            sku: String?,
            snapshot: CartItemSnapshot<Map<String, Any>>?,
            tax_rate: Double?,
            type: CartItemType?,
            unit: String?,
            unit_price: Double?,
        ) = CartItemUpdateRequest<Map<String, Any>>(
            configuration,
            currency,
            metadata,
            name,
            position,
            product_id,
            quantity,
            sku,
            snapshot,
            tax_rate,
            type,
            unit,
            unit_price,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = CartItemUpdateRequest<T>(
            configuration = map["configuration"] as? Any,
            currency = map["currency"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            snapshot = CartItemSnapshot.from(map = map["snapshot"] as Map<String, Any>, nestedType),
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            type = CartItemType.values().find { it.value == (map["type"] as? String) } ?: null,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
        )
    }
}