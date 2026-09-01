package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceListStatus
import com.revenexx.enums.PriceListTaxBasis

/**
 * 
 */
data class PriceListCreateRequest(
    /**
     * Scope: only this sales channel. Beats the open lists, loses to contact and organization.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Unique list code per tenant — the handle every import and integration addresses this list by. A code already in use answers 409.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Scope: only this contact. The most specific scope there is — it beats organization, channel and every open list, whatever their priority.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code (default EUR) — the currency of EVERY amount in this list, since entries carry none of their own. Resolution only considers lists matching the currency of the call; nothing is ever converted.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Free text for whoever maintains the list — why it exists and who it is for. Never shown to a buyer.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * The fallback list. Within its group it sorts LAST, so it wins only where nothing more specific priced the item. Use prices.lists.make-default to move the flag rather than setting it here — two defaults leave a tie to row order.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localised names, keyed by language tag — {"de": "Händlerpreise", "en": "Dealer prices"}. Omit to show `name` everywhere.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Free-form bag: whatever JSON object you write round-trips exactly, and this app never reads it. Its keys are yours — ERP provenance is the usual content.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Operator-facing name, shown wherever a human picks a list.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Scope: only buyers of this organization. Beats channel-scoped and open lists.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Tie-break WITHIN a specificity group (higher wins, default 0). It never beats scope: an organization list at 0 still wins over an open list at 100.
     */
    @SerializedName("priority")
    var priority: Long?,

    /**
     * Gate: when true the list resolves only for an authenticated buyer (contact or organization context); anonymous resolve calls get on_request. Default false (open to everyone).
     */
    @SerializedName("requires_auth")
    var requires_auth: Boolean?,

    /**
     * Default 'active' — only active lists resolve. 'inactive' retires a list without deleting its prices.
     */
    @SerializedName("status")
    var status: PriceListStatus?,

    /**
     * Whether the amounts in this list are net (tax excluded) or gross (tax included) — the one fact a price cannot be without. Omit (null) to inherit the tenant's tax_inclusive_default setting; the resolve answer names which of the two decided under tax_basis_source.
     */
    @SerializedName("tax_basis")
    var tax_basis: PriceListTaxBasis?,

    /**
     * LEGACY mirror of tax_basis. false is the column default and is NOT read as a statement of intent; true is read as gross, and only where tax_basis is null. Prefer tax_basis.
     */
    @SerializedName("tax_included")
    var tax_included: Boolean?,

    /**
     * Start of the validity window of the WHOLE list (ISO 8601); null = open-ended. Outside it the list is not a candidate at all.
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * End of the validity window of the whole list; null = open-ended. Lets a season expire on its own instead of being deactivated by hand.
     */
    @SerializedName("valid_until")
    var valid_until: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "code" to code as Any,
        "contact_id" to contact_id as Any,
        "currency" to currency as Any,
        "description" to description as Any,
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
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceListCreateRequest(
            channel_id = map["channel_id"] as? String,
            code = map["code"] as String,
            contact_id = map["contact_id"] as? String,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            is_default = map["is_default"] as? Boolean,
            labels = map["labels"] as? Any,
            metadata = map["metadata"] as? Any,
            name = map["name"] as String,
            organization_id = map["organization_id"] as? String,
            priority = (map["priority"] as? Number)?.toLong(),
            requires_auth = map["requires_auth"] as? Boolean,
            status = PriceListStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tax_basis = PriceListTaxBasis.values().find { it.value == (map["tax_basis"] as? String) } ?: null,
            tax_included = map["tax_included"] as? Boolean,
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}