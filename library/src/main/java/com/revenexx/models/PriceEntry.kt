package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEntryType

/**
 * One rung of one item’s quantity ladder inside one price list. The ladder IS the set of entries sharing an identity (product_id or sku); the amount is in the LIST’s currency and on the LIST’s tax basis.
 */
data class PriceEntry(
    /**
     * When the entry was created.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The entry itself — one rung of one item’s quantity ladder.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Free-form bag, unvalidated and never read by this app: whatever JSON object you write round-trips exactly. Its keys are the integration’s own, e.g. {"source_system": "erp", "imported_batch": "2026-02-14"}.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The price list this entry belongs to, and therefore the currency and tax basis its amount is on. Set from the path on write.
     */
    @SerializedName("price_list_id")
    var price_list_id: String?,

    /**
     * `standard` is a number. `on_request` is the explicit no-price marker: it STOPS resolution for this item on this list and answers price-on-request, even where a cheaper list exists — the list is authoritative for this buyer and it says "ask us".
     */
    @SerializedName("price_type")
    var price_type: PriceEntryType?,

    /**
     * The product this rung prices. An entry needs `product_id` or `sku` (a row CHECK enforces it); an entry that carries both prices whichever of the two the resolve item names.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * Lowest quantity this price applies from (Staffelpreis). The ladder for one item is the set of entries sharing its identity: the rung with the HIGHEST quantity_min at or below the requested quantity wins, and below the first rung the first rung’s price applies — a minimum order quantity belongs to the catalog, not to the ladder.
     */
    @SerializedName("quantity_min")
    var quantity_min: Double?,

    /**
     * The article number this rung prices, for a price book keyed by SKU rather than by product id — matched exactly, never normalised or case-folded.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The unit of measure the price is per — ‘pcs’, ‘m’, ‘kg’, a packaging size. Free text: this app neither validates nor converts it, and the `quantity` of a resolve call is counted in it.
     */
    @SerializedName("unit")
    var unit: String?,

    /**
     * Price for ONE unit of `unit`, expressed in the list’s `currency` and on the list’s `tax_basis` — a decimal amount in major units (19.90 EUR), never minor units/cents. Stored at 4 decimals so a per-1000-piece price survives, and echoed back exactly as it was written; only DERIVED amounts (net, gross, line totals) are rounded to the tenant’s `price_precision`.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * When the entry last changed. A bulk adjust only writes the rows whose price actually moved, so this is a real "the price changed here" marker.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Start of this entry’s own validity; null = open-ended. This is how a promo price is expressed — a second rung for the same item and quantity, live only for its window.
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * End of this entry’s own validity; null = open-ended. Outside the window the rung is skipped and the ladder resolves as if it were not there.
     */
    @SerializedName("valid_until")
    var valid_until: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "metadata" to metadata as Any,
        "price_list_id" to price_list_id as Any,
        "price_type" to price_type?.value as Any,
        "product_id" to product_id as Any,
        "quantity_min" to quantity_min as Any,
        "sku" to sku as Any,
        "unit" to unit as Any,
        "unit_price" to unit_price as Any,
        "updated_at" to updated_at as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntry(
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            metadata = map["metadata"] as? Any,
            price_list_id = map["price_list_id"] as? String,
            price_type = PriceEntryType.values().find { it.value == (map["price_type"] as? String) } ?: null,
            product_id = map["product_id"] as? String,
            quantity_min = (map["quantity_min"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            unit = map["unit"] as? String,
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}