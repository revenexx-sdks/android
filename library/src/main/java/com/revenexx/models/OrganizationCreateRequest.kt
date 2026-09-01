package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrganizationStatus

/**
 * 
 */
data class OrganizationCreateRequest(
    /**
     * Industry / line of business, in the merchant's own words. Free text: no NACE code, no WZ number, no list to pick from — whatever somebody typed on the company. Segment rules read it, and both `?branche=` and an `eq` condition match it EXACTLY and case-sensitively, so 'Maschinenbau' and 'maschinenbau' are two different industries. Indexed, so it stays cheap to filter on.
     */
    @SerializedName("branche")
    var branche: String?,

    /**
     * Ceiling on open receivables in the market's currency, and one of the inputs that decide whether an order is accepted at all. Null means NO limit — not a limit of zero. A create without it inherits the tenant's `default_credit_limit`.
     */
    @SerializedName("credit_limit")
    var credit_limit: Double?,

    /**
     * The number this company carries in the merchant's own ERP — the key an ERP integration joins on, and what a service desk asks for on the phone. Free text with NO enforced format (a letter prefix and a running number is the common shape, but plain digits are just as valid), unique per tenant while it is set, and one of the fields duplicate detection can be pointed at. The real values come out of the merchant's ERP; nothing published here can name one that exists. A second company with the same number is a 409.
     */
    @SerializedName("customer_number")
    var customer_number: String?,

    /**
     * True stops SHIPMENTS to this company while leaving login and ordering alone — the "they may order, we are just not sending anything until this is settled" state. Separate from `status` on purpose: blocking the login to stop a delivery locks out the people who could settle it. Default false.
     */
    @SerializedName("delivery_block")
    var delivery_block: Boolean?,

    /**
     * Where the company stands in the SALES PIPELINE, and a deliberately separate axis from `status`: a prospect that may log in and a customer that may not are both ordinary states, and one column cannot say that. One of the tenant's own stages (GET /customers/lifecycle-stages) — a fresh install starts with lead, prospect, customer, churned, and the merchant may add their own. Nothing moves it automatically; a stage changes when a person or an integration says so. A create without it gets the stage flagged as default; a value the tenant does not keep is a 400.
     */
    @SerializedName("lifecycle_stage")
    var lifecycle_stage: String?,

    /**
     * Legal or trading name of the COMPANY — never a person. Mirrored to the platform team, so a rename here is a rename in storefront auth too.
     */
    @SerializedName("name")
    val name: String,

    /**
     * When this company has to pay — one of the tenant's own terms (GET /customers/payment-terms, seeded with prepayment, direct_debit, net_7/14/30/60/90). Null means nothing was agreed and the order flow falls back to the market's `default_payment_terms`. This is a commercial term, not a payment method: HOW they pay is the payments app's business. A create without it inherits the market's `default_payment_terms`; a value the tenant does not keep is a 400.
     */
    @SerializedName("payment_terms")
    var payment_terms: String?,

    /**
     * Code of the price list this company buys on — plain text pointing into the prices app. ADR-0055 forbids the cross-app foreign key, so nothing here checks it: a code that names no list simply prices nothing. `standard` is the list the prices app seeds on install.
     */
    @SerializedName("price_list")
    var price_list: String?,

    /**
     * Free-form per-organization settings, keyed by whatever the merchant's own integrations agree on — this app never branches on a key in here. Segment rules can address a TOP-LEVEL key as `setting:<key>`, which is the whole reason the blob survives: a flag an ERP writes here selects a segment without a schema change. Commercial terms are typed columns now (payment_terms, credit_limit); writing them back in here leaves the checkout reading the column and finding nothing. Replaced wholesale on an update — send the whole object, not a patch of it.
     */
    @SerializedName("settings")
    var settings: Any?,

    /**
     * ACCESS, not pipeline: 'blocked' stops this company's people from logging in and is where a rejected registration parks the company it founded. 'active' is the default. For how far along a company is, read `lifecycle_stage` — reading this one for that is how a won deal gets locked out. Default 'active'.
     */
    @SerializedName("status")
    var status: OrganizationStatus?,

    /**
     * VAT identification number (USt-IdNr. in Germany) — the closest thing a B2B buyer has to a legal identity. Validated against the EU VIES service when the tenant's `organization_vat_id_required` setting is on, and stored verbatim otherwise, including for buyers outside the EU.
     */
    @SerializedName("vat_id")
    var vat_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "branche" to branche as Any,
        "credit_limit" to credit_limit as Any,
        "customer_number" to customer_number as Any,
        "delivery_block" to delivery_block as Any,
        "lifecycle_stage" to lifecycle_stage as Any,
        "name" to name as Any,
        "payment_terms" to payment_terms as Any,
        "price_list" to price_list as Any,
        "settings" to settings as Any,
        "status" to status?.value as Any,
        "vat_id" to vat_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrganizationCreateRequest(
            branche = map["branche"] as? String,
            credit_limit = (map["credit_limit"] as? Number)?.toDouble(),
            customer_number = map["customer_number"] as? String,
            delivery_block = map["delivery_block"] as? Boolean,
            lifecycle_stage = map["lifecycle_stage"] as? String,
            name = map["name"] as String,
            payment_terms = map["payment_terms"] as? String,
            price_list = map["price_list"] as? String,
            settings = map["settings"] as? Any,
            status = OrganizationStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            vat_id = map["vat_id"] as? String,
        )
    }
}