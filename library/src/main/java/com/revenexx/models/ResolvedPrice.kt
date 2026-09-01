package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceOnRequestReason
import com.revenexx.enums.PriceTaxBasis
import com.revenexx.enums.PriceTaxBasisSource

/**
 * What one item costs this buyer, and which list said so.
 */
data class ResolvedPrice(
    /**
     * ISO 4217 currency of every amount on this item. Always the winning list’s currency, which always equals the call’s top-level `currency` — resolution only considers lists that match it, so a list and its answer can never disagree. null on an on-request item.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Present ONLY on an item that named neither `product_id` nor `sku`, and always with this exact text. The call still answers 200 and the item comes back on_request, because one malformed line must not cost a whole cart its prices.
     */
    @SerializedName("error")
    var error: String?,

    /**
     * `unit_price × quantity`, on the SAME basis as `unit_price` (so net if the list is net) and rounded to `basis.price_precision`. Not a tax-adjusted total — a cart computes its own from the net/gross pair.
     */
    @SerializedName("line_total")
    var line_total: Double?,

    /**
     * true = no price for this buyer context — show "price on request", never 0.
     */
    @SerializedName("on_request")
    var on_request: Boolean?,

    /**
     * Why there is no price: nothing prices it, a list marks it on-request, the tenant hides prices from anonymous buyers, or the item named neither product_id nor sku.
     */
    @SerializedName("on_request_reason")
    var on_request_reason: PriceOnRequestReason?,

    /**
     * The list that priced this item — null when nothing did. On an `on_request_entry` answer it is the list that said "ask us".
     */
    @SerializedName("price_list")
    var price_list: Any?,

    /**
     * Echo of the requested `product_id` — null when the item was identified by SKU.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The quantity this answer was computed for: what you sent, or 1 where you sent nothing or a non-positive value. It selects the tier and multiplies into `line_total`.
     */
    @SerializedName("quantity")
    var quantity: Double?,

    /**
     * Echo of the requested `sku` — null when the item was identified by product id.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * Whether the stored amount is net or gross. THE fact a price cannot be without.
     */
    @SerializedName("tax_basis")
    var tax_basis: PriceTaxBasis?,

    /**
     * Who decided it: the list's own tax_basis, a legacy tax_included=true on the list, or the tenant's tax_inclusive_default setting.
     */
    @SerializedName("tax_basis_source")
    var tax_basis_source: PriceTaxBasisSource?,

    /**
     * The tax class code that produced `tax_rate`: the product’s own class where the products app knows one, otherwise the buyer market’s default class. The codes are the tenant’s, defined in `markets.tax_classes` — conventionally `standard` and `reduced`. null when tax could not be resolved.
     */
    @SerializedName("tax_class")
    var tax_class: String?,

    /**
     * Whether unit_price already contains tax. Never null on a priced item — it is `tax_basis` as a boolean, kept for existing callers.
     */
    @SerializedName("tax_included")
    var tax_included: Boolean?,

    /**
     * Tax rate as a PERCENTAGE (19 means 19 %, not 0.19), read from `markets.tax_classes` for this market and `tax_class`. null means UNKNOWN — a checkout must be able to tell that apart from a genuine 0 %.
     */
    @SerializedName("tax_rate")
    var tax_rate: Double?,

    /**
     * The FULL quantity ladder the winning list holds for this item, ascending by `quantity_min` — what a PDP renders as a tier table. Empty on an on-request item.
     */
    @SerializedName("tiers")
    var tiers: List<PriceTier>?,

    /**
     * Price for ONE unit, in `currency` and on the basis `tax_basis` names — a decimal amount in major units (19.90 EUR), never minor units/cents. It is the stored rung exactly as a merchant typed it, unrounded. Do not display it without reading `tax_basis`; prefer `unit_price_net`/`unit_price_gross`, which are unambiguous.
     */
    @SerializedName("unit_price")
    var unit_price: Double?,

    /**
     * Unit price INCLUDING tax, in `currency`, rounded to `basis.price_precision` under `basis.rounding_mode`. Derived from `unit_price` and `tax_rate` in whichever direction `tax_basis` requires. Present only when `tax.resolved` is true.
     */
    @SerializedName("unit_price_gross")
    var unit_price_gross: Double?,

    /**
     * Unit price EXCLUDING tax, in `currency`, rounded to `basis.price_precision` under `basis.rounding_mode`. Present only when `tax.resolved` is true — null means the rate is unknown, not that there is no tax.
     */
    @SerializedName("unit_price_net")
    var unit_price_net: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currency" to currency as Any,
        "error" to error as Any,
        "line_total" to line_total as Any,
        "on_request" to on_request as Any,
        "on_request_reason" to on_request_reason?.value as Any,
        "price_list" to price_list as Any,
        "product_id" to product_id as Any,
        "quantity" to quantity as Any,
        "sku" to sku as Any,
        "tax_basis" to tax_basis?.value as Any,
        "tax_basis_source" to tax_basis_source?.value as Any,
        "tax_class" to tax_class as Any,
        "tax_included" to tax_included as Any,
        "tax_rate" to tax_rate as Any,
        "tiers" to tiers?.map { it.toMap() } as Any,
        "unit_price" to unit_price as Any,
        "unit_price_gross" to unit_price_gross as Any,
        "unit_price_net" to unit_price_net as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ResolvedPrice(
            currency = map["currency"] as? String,
            error = map["error"] as? String,
            line_total = (map["line_total"] as? Number)?.toDouble(),
            on_request = map["on_request"] as? Boolean,
            on_request_reason = PriceOnRequestReason.values().find { it.value == (map["on_request_reason"] as? String) } ?: null,
            price_list = map["price_list"] as? Any,
            product_id = map["product_id"] as? String,
            quantity = (map["quantity"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            tax_basis = PriceTaxBasis.values().find { it.value == (map["tax_basis"] as? String) } ?: null,
            tax_basis_source = PriceTaxBasisSource.values().find { it.value == (map["tax_basis_source"] as? String) } ?: null,
            tax_class = map["tax_class"] as? String,
            tax_included = map["tax_included"] as? Boolean,
            tax_rate = (map["tax_rate"] as? Number)?.toDouble(),
            tiers = (map["tiers"] as List<Map<String, Any>>).map { PriceTier.from(map = it) },
            unit_price = (map["unit_price"] as? Number)?.toDouble(),
            unit_price_gross = (map["unit_price_gross"] as? Number)?.toDouble(),
            unit_price_net = (map["unit_price_net"] as? Number)?.toDouble(),
        )
    }
}