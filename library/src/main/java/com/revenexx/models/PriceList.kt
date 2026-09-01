package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceListStatus
import com.revenexx.enums.PriceListTaxBasis

/**
 * A price list: one currency, one tax basis, one validity window, one buyer scope — and the entries that price items in it. Which list wins for a given buyer is decided by scope first, then priority, then the default flag; see prices.resolve.
 */
data class PriceList(
    /**
     * Buyer scope: this list prices for this sales channel. Beats the open lists, loses to contact and organization scope.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * The unique per-tenant handle of the list — what an import, an ERP export and every integration addresses it by, and what the `default_price_list_code` setting names. It is never quietly reassigned: a second list under a code that is taken answers 409.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Buyer scope: this list prices for this one contact. The most specific scope there is — it beats organization, channel and every open list, whatever their priority.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * When the list was created. Also the `newest` tie-break’s input when the tenant settles genuine ties that way.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * ISO 4217 currency of EVERY amount in this list — entries carry no currency of their own, so this is the one that governs them. Resolution only ever considers lists whose currency equals the currency of the call: a list in another currency is not converted, it simply does not price the item. This app never converts between currencies.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Free text for whoever maintains the list — why it exists and who it is for. Never shown to a buyer.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * The price list itself. Every sub-route addresses the list by this id, and a resolve answer names the list that priced an item under `price_list.id`.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The fallback list. Within its group it deliberately sorts LAST, so a default list wins only where nothing more specific priced the item. At most one list per tenant holds the flag — `prices.lists.make-default` moves it in one call.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localised names, keyed by language tag: {"de": "Standardpreise", "en": "Standard prices"}. Read the tag you need and fall back to `en`; `name` is the untranslated original.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Free-form bag, unvalidated and never read by this app: whatever JSON object you write round-trips exactly. Its keys are the integration’s own — ERP provenance is the usual content, e.g. {"source_system": "erp", "erp_price_group": "A1"}.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Operator-facing name, shown wherever a human picks a list. Not addressable — integrations join on `code`.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Buyer scope: this list prices for buyers of this organization. Beats channel-scoped and open lists, loses to a contact-scoped one.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Tie-break WITHIN one specificity group, higher first. It never beats specificity: an organization-scoped list at priority 0 still wins over an open list at priority 100. Default 0.
     */
    @SerializedName("priority")
    var priority: Long?,

    /**
     * Gate: when true the list resolves only for a buyer who has a contact or organization context. An anonymous resolve never matches it, so a tenant that prices only for logged-in customers flags its list and guests fall through to price-on-request rather than to some other list’s number.
     */
    @SerializedName("requires_auth")
    var requires_auth: Boolean?,

    /**
     * Whether the list takes part in resolution at all. Only `active` lists are candidates; `inactive` retires a list without deleting the prices it holds.
     */
    @SerializedName("status")
    var status: PriceListStatus?,

    /**
     * Whether the amounts stored in this list are `net` (tax excluded) or `gross` (tax included) — the one fact a price cannot be without. null inherits the tenant’s `tax_inclusive_default` setting, and the resolve answer names which of the two decided under `tax_basis_source`.
     */
    @SerializedName("tax_basis")
    var tax_basis: PriceListTaxBasis?,

    /**
     * LEGACY mirror of `tax_basis`. `false` is the column default, so it is NOT read as anybody having chosen net; only `true` is read as a statement (gross), and only where `tax_basis` is null. Prefer `tax_basis`.
     */
    @SerializedName("tax_included")
    var tax_included: Boolean?,

    /**
     * When the row last changed. Written by the database, not by the caller.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * Start of the validity window of the WHOLE list; null = open-ended. Outside the window the list is not a candidate at all. The instant compared against is the resolve call’s `at`, echoed as `basis.evaluated_at`.
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * End of the validity window of the whole list; null = open-ended. Use it to let a season expire on its own instead of deactivating a list by hand.
     */
    @SerializedName("valid_until")
    var valid_until: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "code" to code as Any,
        "contact_id" to contact_id as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "labels" to labels as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "organization_id" to organization_id as Any,
        "priority" to priority as Any,
        "requires_auth" to requires_auth as Any,
        "status" to status?.value as Any,
        "tax_basis" to tax_basis?.value as Any,
        "tax_included" to tax_included as Any,
        "updated_at" to updated_at as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceList(
            channel_id = map["channel_id"] as? String,
            code = map["code"] as? String,
            contact_id = map["contact_id"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            organization_id = map["organization_id"] as? String,
            priority = (map["priority"] as? Number)?.toLong(),
            requires_auth = map["requires_auth"] as? Boolean,
            status = PriceListStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tax_basis = PriceListTaxBasis.values().find { it.value == (map["tax_basis"] as? String) } ?: null,
            tax_included = map["tax_included"] as? Boolean,
            updated_at = map["updated_at"] as? String,
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}