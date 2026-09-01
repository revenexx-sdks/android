package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PaymentFeeType
import com.revenexx.enums.PaymentMethodKind

/**
 * 
 */
data class PaymentMethod(
    /**
     * The machine name of the method, unique per tenant and lower case by convention ('invoice', 'prepayment', 'card', 'paypal'). It is the string the checkout asks for, the string every payment stores, and therefore the one value here that cannot be changed freely: renaming it would leave the ledger naming something that no longer exists, so it is refused with 409 for as long as any payment names it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Allowed ISO 3166-1 alpha-2 country codes, compared upper-cased against the buyer country. null or an empty list means unrestricted — the invoice method this app seeds is restricted to DE, which is why an eligibility call without a country sees it excluded.
     */
    @SerializedName("countries")
    var countries: List<String>?,

    /**
     * When this configuration was created.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * One line explaining the method where it is offered — payment terms, what happens after the order. Shown to the buyer, so it is the merchant's wording rather than the app's.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * A disabled method is never eligible and never reaches a checkout. This is the switch an operator wants: deleting a method the ledger still names — or renaming its `code` — is refused with 409.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * The surcharge this method costs the buyer, read as an amount or as a percentage depending on `fee_type`. Never negative — a discount for paying a certain way is not expressible here.
     */
    @SerializedName("fee_amount")
    var fee_amount: Double?,

    /**
     * ISO 4217 code a fixed fee is expressed in. The database bounds the length at three characters and nothing else, so lower case is stored as written.
     */
    @SerializedName("fee_currency")
    var fee_currency: String?,

    /**
     * How `fee_amount` applies: 'none' (no surcharge), 'fixed' (that many units of `fee_currency`) or 'percent' (that share of the order amount).
     */
    @SerializedName("fee_type")
    var fee_type: PaymentFeeType?,

    /**
     * Id of the configuration row. A payment names its method by `code`, never by this — so an id is only ever used to address the configuration itself.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Who moves the money. 'self_managed' — invoice, prepayment — means the merchant fulfils and reconciles it outside any PSP, and such a payment authorizes the moment it is created. 'psp' means a configured provider authorizes, captures and refunds it.
     */
    @SerializedName("kind")
    var kind: PaymentMethodKind?,

    /**
     * Buyer-facing names keyed by language tag — what a storefront shows instead of the operator-facing `name`. Free jsonb: the database constrains neither the tags nor the values, so a client reads the tag it wants and falls back to `en`.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Largest order amount this method may be used for — the usual credit-risk cap on invoice and prepayment. null means no upper bound.
     */
    @SerializedName("max_order_value")
    var max_order_value: Double?,

    /**
     * Free-form merchant data carried on the configuration. This app never reads it — it is storage for the integrations that do (an ERP key for the method, a ledger account, a display hint).
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Smallest order amount this method may be used for — the usual guard against paying a €5 order by invoice. null means no lower bound.
     */
    @SerializedName("min_order_value")
    var min_order_value: Double?,

    /**
     * Operator-facing name, in the language the merchant administers in. What a buyer sees comes from `labels`.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Sort order at checkout, ascending — the merchant's preferred payment method first.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The PSP code this method transacts through, from GET /payments/providers/catalog. Only meaningful for kind 'psp'; a PSP method that names none falls back to the tenant's `default_provider` setting.
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * The provider's own payment-method id ('card', 'paypal', 'sepa_debit') — what the driver is told to charge. Copied onto every payment created with this method as `metadata.provider_method`.
     */
    @SerializedName("provider_method")
    var provider_method: String?,

    /**
     * The tenant the row belongs to — the same slug the request carried in `X-Revenexx-Tenant`. Added by the platform rather than by this app, and echoed so a caller that fans several tenants into one store can tell the rows apart.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * When it was last changed. The eligibility answer is computed live, so this is the age of the configuration and not of any cached result.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "countries" to countries as Any,
        "created_at" to created_at as Any,
        "description" to description as Any,
        "enabled" to enabled as Any,
        "fee_amount" to fee_amount as Any,
        "fee_currency" to fee_currency as Any,
        "fee_type" to fee_type?.value as Any,
        "id" to id as Any,
        "kind" to kind?.value as Any,
        "labels" to labels as Any,
        "max_order_value" to max_order_value as Any,
        "metadata" to metadata as Any,
        "min_order_value" to min_order_value as Any,
        "name" to name as Any,
        "position" to position as Any,
        "provider" to provider as Any,
        "provider_method" to provider_method as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentMethod(
            code = map["code"] as? String,
            countries = map["countries"] as? List<String>,
            created_at = map["created_at"] as? String,
            description = map["description"] as? String,
            enabled = map["enabled"] as? Boolean,
            fee_amount = (map["fee_amount"] as? Number)?.toDouble(),
            fee_currency = map["fee_currency"] as? String,
            fee_type = PaymentFeeType.values().find { it.value == (map["fee_type"] as? String) } ?: null,
            id = map["id"] as? String,
            kind = PaymentMethodKind.values().find { it.value == (map["kind"] as? String) } ?: null,
            labels = map["labels"] as? Any,
            max_order_value = (map["max_order_value"] as? Number)?.toDouble(),
            metadata = map["metadata"] as? Any,
            min_order_value = (map["min_order_value"] as? Number)?.toDouble(),
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            provider = map["provider"] as? String,
            provider_method = map["provider_method"] as? String,
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}