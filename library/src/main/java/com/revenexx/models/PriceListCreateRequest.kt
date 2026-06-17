package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceListStatus

/**
 * 
 */
data class PriceListCreateRequest(
    /**
     * Scope: only this channel.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Unique list code per tenant.
     */
    @SerializedName("code")
    val code: String,

    /**
     * Scope: only this contact — beats every other scope.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code (default EUR) — resolution only considers lists matching the requested currency.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Default lists resolve last within their group.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Localised names ({de, en, …}).
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Scope: only this market.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("name")
    val name: String,

    /**
     * Scope: only this organization.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Tie-breaker within a specificity group (higher wins, default 0).
     */
    @SerializedName("priority")
    var priority: Long?,

    /**
     * Default 'active' — only active lists resolve.
     */
    @SerializedName("status")
    var status: PriceListStatus?,

    /**
     * Gross (true) or net (false, default) prices.
     */
    @SerializedName("tax_included")
    var tax_included: Boolean?,

    /**
     * Validity window start.
     */
    @SerializedName("valid_from")
    var valid_from: String?,

    /**
     * Validity window end.
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
        "market_id" to market_id as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "organization_id" to organization_id as Any,
        "priority" to priority as Any,
        "status" to status?.value as Any,
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
            market_id = map["market_id"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as String,
            organization_id = map["organization_id"] as? String,
            priority = (map["priority"] as? Number)?.toLong(),
            status = PriceListStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tax_included = map["tax_included"] as? Boolean,
            valid_from = map["valid_from"] as? String,
            valid_until = map["valid_until"] as? String,
        )
    }
}