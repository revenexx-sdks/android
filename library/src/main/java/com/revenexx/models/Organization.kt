package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrganizationStatus

/**
 * A buying COMPANY — the unit a contract, a credit limit and a price list belong to. Its people are `contacts`, and it is mirrored into platform auth as a team.
 */
data class Organization(
    /**
     * Industry / line of business, in the merchant's own words. Free text: no NACE code, no WZ number, no list to pick from — whatever somebody typed on the company. Segment rules read it, and both `?branche=` and an `eq` condition match it EXACTLY and case-sensitively, so 'Maschinenbau' and 'maschinenbau' are two different industries. Indexed, so it stays cheap to filter on.
     */
    @SerializedName("branche")
    var branche: String?,

    /**
     * When this company record was created in this app. Not when the customer relationship began — an ERP import creates decade-old customers today.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Ceiling on open receivables in the market's currency, and one of the inputs that decide whether an order is accepted at all. Null means NO limit — not a limit of zero.
     */
    @SerializedName("credit_limit")
    var credit_limit: Double?,

    /**
     * The number this company carries in the merchant's own ERP — the key an ERP integration joins on, and what a service desk asks for on the phone. Free text with NO enforced format (a letter prefix and a running number is the common shape, but plain digits are just as valid), unique per tenant while it is set, and one of the fields duplicate detection can be pointed at. The real values come out of the merchant's ERP; nothing published here can name one that exists.
     */
    @SerializedName("customer_number")
    var customer_number: String?,

    /**
     * True stops SHIPMENTS to this company while leaving login and ordering alone — the "they may order, we are just not sending anything until this is settled" state. Separate from `status` on purpose: blocking the login to stop a delivery locks out the people who could settle it.
     */
    @SerializedName("delivery_block")
    var delivery_block: Boolean?,

    /**
     * Id of the platform TEAM this organization is mirrored as — what makes its people a team for storefront auth (sessions, SSO, mobile SDKs). Written by the mirror and ignored on every write a caller sends; null while the mirror has not run yet.
     */
    @SerializedName("external_team_id")
    var external_team_id: String?,

    /**
     * Primary key of the company record. Stable for its whole life; contacts, addresses, segment memberships and the metrics projection all point at it.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Where the company stands in the SALES PIPELINE, and a deliberately separate axis from `status`: a prospect that may log in and a customer that may not are both ordinary states, and one column cannot say that. One of the tenant's own stages (GET /customers/lifecycle-stages) — a fresh install starts with lead, prospect, customer, churned, and the merchant may add their own. Nothing moves it automatically; a stage changes when a person or an integration says so.
     */
    @SerializedName("lifecycle_stage")
    var lifecycle_stage: String?,

    /**
     * Legal or trading name of the COMPANY — never a person. Mirrored to the platform team, so a rename here is a rename in storefront auth too.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * When this company has to pay — one of the tenant's own terms (GET /customers/payment-terms, seeded with prepayment, direct_debit, net_7/14/30/60/90). Null means nothing was agreed and the order flow falls back to the market's `default_payment_terms`. This is a commercial term, not a payment method: HOW they pay is the payments app's business.
     */
    @SerializedName("payment_terms")
    var payment_terms: String?,

    /**
     * Code of the price list this company buys on — plain text pointing into the prices app. ADR-0055 forbids the cross-app foreign key, so nothing here checks it: a code that names no list simply prices nothing. `standard` is the list the prices app seeds on install.
     */
    @SerializedName("price_list")
    var price_list: String?,

    /**
     * Free-form per-organization settings, keyed by whatever the merchant's own integrations agree on — this app never branches on a key in here. Segment rules can address a TOP-LEVEL key as `setting:<key>`, which is the whole reason the blob survives: a flag an ERP writes here selects a segment without a schema change. Commercial terms are typed columns now (payment_terms, credit_limit); writing them back in here leaves the checkout reading the column and finding nothing.
     */
    @SerializedName("settings")
    var settings: Any?,

    /**
     * ACCESS, not pipeline: 'blocked' stops this company's people from logging in and is where a rejected registration parks the company it founded. 'active' is the default. For how far along a company is, read `lifecycle_stage` — reading this one for that is how a won deal gets locked out.
     */
    @SerializedName("status")
    var status: OrganizationStatus?,

    /**
     * The tenant this row belongs to — the store slug, not an id. Set by the platform from the authenticated context, never by a caller; a write that carries it is ignored, and no request can read another tenant's rows by sending a different one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * When any column of this row last changed.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * VAT identification number (USt-IdNr. in Germany) — the closest thing a B2B buyer has to a legal identity. Validated against the EU VIES service when the tenant's `organization_vat_id_required` setting is on, and stored verbatim otherwise, including for buyers outside the EU.
     */
    @SerializedName("vat_id")
    var vat_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "branche" to branche as Any,
        "created_at" to created_at as Any,
        "credit_limit" to credit_limit as Any,
        "customer_number" to customer_number as Any,
        "delivery_block" to delivery_block as Any,
        "external_team_id" to external_team_id as Any,
        "id" to id as Any,
        "lifecycle_stage" to lifecycle_stage as Any,
        "name" to name as Any,
        "payment_terms" to payment_terms as Any,
        "price_list" to price_list as Any,
        "settings" to settings as Any,
        "status" to status?.value as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
        "vat_id" to vat_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Organization(
            branche = map["branche"] as? String,
            created_at = map["created_at"] as? String,
            credit_limit = (map["credit_limit"] as? Number)?.toDouble(),
            customer_number = map["customer_number"] as? String,
            delivery_block = map["delivery_block"] as? Boolean,
            external_team_id = map["external_team_id"] as? String,
            id = map["id"] as? String,
            lifecycle_stage = map["lifecycle_stage"] as? String,
            name = map["name"] as? String,
            payment_terms = map["payment_terms"] as? String,
            price_list = map["price_list"] as? String,
            settings = map["settings"] as? Any,
            status = OrganizationStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
            vat_id = map["vat_id"] as? String,
        )
    }
}