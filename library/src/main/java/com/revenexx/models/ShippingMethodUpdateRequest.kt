package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ShippingMethodMatrixBasis
import com.revenexx.enums.ShippingMethodPricingType

/**
 * Partial update — omitted fields keep their current value.
 */
data class ShippingMethodUpdateRequest(
    /**
     * Carrier CODE, kept from before shipping_carriers existed. Looked up in the carrier table when carrier_id is not set, so an existing value keeps working and gains a tracking template; a code nobody maintains is still reported as a plain name.
     */
    @SerializedName("carrier")
    var carrier: String?,

    /**
     * The carrier this method ships with. Wins over `carrier` and supplies the tracking template, pickup cut-off, handling time and transit days.
     */
    @SerializedName("carrier_id")
    var carrier_id: String?,

    /**
     * Stable method code, unique per tenant (e.g. standard, express). What a checkout and an order line store, so it is the value every integration joins on.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The countries this method may be offered into. ISO 3166-1 alpha-2 codes; null or an empty array means no restriction. Compared upper-cased, so a lower-case entry still matches. Declared as an array rather than the bare object a jsonb column derives to — this one is always a list. ANDed with the carrier's own reach.
     */
    @SerializedName("countries")
    var countries: List<String>?,

    /**
     * ISO 4217 code (default EUR). Exactly three characters — the column says so. Echoed into a rate, never converted: this app prices in the currency the method carries.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The sentence under the name in the checkout — the delivery promise in words. Null when the name says enough.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Only enabled methods are ever quoted (default false); a disabled one is reported in `excluded` rather than hidden.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Transit time upper bound in calendar days. Falls back to the carrier's when null.
     */
    @SerializedName("eta_days_max")
    var eta_days_max: Long?,

    /**
     * Transit time lower bound in calendar days, for the checkout. Falls back to the carrier's when null.
     */
    @SerializedName("eta_days_min")
    var eta_days_min: Long?,

    /**
     * Free shipping at or above this order value — wins over every pricing model, including a matrix. Compared net or gross as the market's free_above_compares setting declares. Null falls back to the tenant's shop-wide free_shipping_threshold.
     */
    @SerializedName("free_above")
    var free_above: Double?,

    /**
     * Localized display names. A flat map keyed by locale — the Cockpit falls back to `en`. Null means the row has no translations and every client shows the untranslated column instead.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Attribute name for matrix_basis 'attribute' — the key the rate request's `attributes` map is read at. Free text: the set of attributes is the catalogue's, not this app's.
     */
    @SerializedName("matrix_attribute")
    var matrix_attribute: String?,

    /**
     * The measure a matrix method prices its tiers over: total basket weight (in the market's weight unit), total item count, order value, or 'attribute' — any number the rate request carries under matrix_attribute. Null falls back to the tenant's matrix_basis_default. Ignored unless pricing_type is 'matrix'.
     */
    @SerializedName("matrix_basis")
    var matrix_basis: ShippingMethodMatrixBasis?,

    /**
     * Free-form jsonb the platform never reads or validates — whatever the merchant or their integration needs to keep beside the row (a customer number with the carrier, an ERP key, a label-printer id). The shape varies BY INTEGRATION, not by anything this app knows, so no key is declared and none is reserved; the example is one plausible instance rather than a schema. A flat map of scalars is the convention, and nothing enforces it.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Display name shown in the checkout.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order in the checkout (default 0) — a rate answer is returned in this order.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The fixed price (default 0), in `currency` — ignored for 'free' and 'matrix'.
     */
    @SerializedName("price")
    var price: Double?,

    /**
     * Pricing model (default 'fixed'): 'fixed' is one price for every basket, 'free' is no price at all, 'matrix' is a tiered price read off this method's rate tiers. Only 'matrix' looks at matrix_basis, quote_above and the tier table.
     */
    @SerializedName("pricing_type")
    var pricing_type: ShippingMethodPricingType?,

    /**
     * Above this MATRIX MEASURE the method carries no automatic price: it is still offered, flagged `quote_required` with a reason, and the storefront shows 'shipping on request'. For bulky or overweight freight priced by hand. Null = every measure is priced automatically.
     */
    @SerializedName("quote_above")
    var quote_above: Double?,

    /**
     * This method's own tax class, as a CODE into the buyer market's tax classes (markets.tax_classes) — never a rate. First step of the tax chain: unset falls back to the tenant's shipping_tax_class setting, then the market default. Not a foreign key and it could not be (ADR-0055); GET /shipping/tax-classes/{code}/usage is the integrity question markets asks in its place.
     */
    @SerializedName("tax_class")
    var tax_class: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "carrier" to carrier as Any,
        "carrier_id" to carrier_id as Any,
        "code" to code as Any,
        "countries" to countries as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "enabled" to enabled as Any,
        "eta_days_max" to eta_days_max as Any,
        "eta_days_min" to eta_days_min as Any,
        "free_above" to free_above as Any,
        "labels" to labels as Any,
        "matrix_attribute" to matrix_attribute as Any,
        "matrix_basis" to matrix_basis?.value as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "position" to position as Any,
        "price" to price as Any,
        "pricing_type" to pricing_type?.value as Any,
        "quote_above" to quote_above as Any,
        "tax_class" to tax_class as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingMethodUpdateRequest(
            carrier = map["carrier"] as? String,
            carrier_id = map["carrier_id"] as? String,
            code = map["code"] as? String,
            countries = map["countries"] as? List<String>,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            enabled = map["enabled"] as? Boolean,
            eta_days_max = (map["eta_days_max"] as? Number)?.toLong(),
            eta_days_min = (map["eta_days_min"] as? Number)?.toLong(),
            free_above = (map["free_above"] as? Number)?.toDouble(),
            labels = map["labels"] as? Any,
            matrix_attribute = map["matrix_attribute"] as? String,
            matrix_basis = ShippingMethodMatrixBasis.values().find { it.value == (map["matrix_basis"] as? String) } ?: null,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            price = (map["price"] as? Number)?.toDouble(),
            pricing_type = ShippingMethodPricingType.values().find { it.value == (map["pricing_type"] as? String) } ?: null,
            quote_above = (map["quote_above"] as? Number)?.toDouble(),
            tax_class = map["tax_class"] as? String,
        )
    }
}