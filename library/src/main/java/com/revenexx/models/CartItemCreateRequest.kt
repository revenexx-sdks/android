package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartItemType

/**
 * An item needs an identity: 'name' or 'sku'.
 */
data class CartItemCreateRequest(
    /**
     * Free-form configuration — configured lines never merge.
     */
    @SerializedName("configuration")
    var configuration: Any?,

    /**
     * Defaults to the cart's currency.
     */
    @SerializedName("currency")
    var currency: String?,

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
     * 
     */
    @SerializedName("position")
    var position: Long?,

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
     * Loose product snapshot at add-time (price, name, image, …).
     */
    @SerializedName("snapshot")
    var snapshot: Any?,

    /**
     * 
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * Line type (default 'product'). Plain product lines merge by product+price; configurations always stand alone.
     */
    @SerializedName("type")
    var type: CartItemType?,

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
        "snapshot" to snapshot as Any,
        "tax_rate" to tax_rate as Any,
        "type" to type?.value as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartItemCreateRequest(
            configuration = map["configuration"] as? Any,
            currency = map["currency"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            snapshot = map["snapshot"] as? Any,
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            type = CartItemType.values().find { it.value == (map["type"] as? String) } ?: null,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
        )
    }
}