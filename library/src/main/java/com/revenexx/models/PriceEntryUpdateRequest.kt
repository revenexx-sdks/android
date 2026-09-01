package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEntryType

/**
 * Partial update — omitted fields keep their current value.
 */
data class PriceEntryUpdateRequest(
    /**
     * Free-form bag: whatever JSON object you write round-trips exactly, and this app never reads it. Its keys are yours.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Default 'standard'; 'on_request' is the explicit no-price marker — it STOPS resolution for this item on this list and answers "price on request" even where a cheaper list exists.
     */
    @SerializedName("price_type")
    var price_type: PriceEntryType?,

    /**
     * The product this rung prices. An entry needs product_id or sku — the row CHECK enforces it.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Tier threshold (Staffelpreis): this price applies from this quantity upwards (default 1). The rungs of one item are the entries sharing its identity; the highest threshold at or below the requested quantity wins.
     */
    @SerializedName("quantity_min")
    var quantity_min: Double?,

    /**
     * The article number this rung prices (alternative to product_id). Matched exactly on resolve — never normalised or case-folded.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Unit of measure the price is per — free text, neither validated nor converted here. A resolve call’s `quantity` is counted in it.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * Price for ONE unit of `unit`, in the LIST’s currency and on the LIST’s tax basis — a decimal amount in major units (19.90), never minor units/cents. Stored at 4 decimals and echoed back exactly as sent (default 0).
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * Start of this entry’s own validity (ISO 8601) — how a promo price is expressed: a second rung, live only for its window. null = open-ended.
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * End of this entry’s own validity; null = open-ended. Outside it the rung is skipped and the ladder resolves as if it were not there.
     */
    @SerializedName("valid_until")
    var valid_until: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "metadata" to metadata as Any,
        "price_type" to price_type?.value as Any,
        "product_id" to product_id as Any,
        "quantity_min" to quantity_min as Any,
        "sku" to sku as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntryUpdateRequest(
            metadata = map["metadata"] as? Any,
            price_type = PriceEntryType.values().find { it.value == (map["price_type"] as? String) } ?: null,
            product_id = map["product_id"] as? String,
            quantity_min = (map["quantity_min"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}