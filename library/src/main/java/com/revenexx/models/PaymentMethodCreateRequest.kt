package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PaymentFeeType
import com.revenexx.enums.PaymentMethodKind

/**
 * A method needs its identity: code + name.
 */
data class PaymentMethodCreateRequest(
    /**
     * Stable method code (unique per tenant, e.g. 'invoice', 'card').
     */
    @SerializedName("code")
    val code: String,

    /**
     * Allowed ISO country codes — empty/omitted = unrestricted.
     */
    @SerializedName("countries")
    var countries: List<String>?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * Disabled methods are never eligible (default false).
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Fixed amount or percent value, per fee_type (default 0).
     */
    @SerializedName("fee_amount")
    var fee_amount: Double?,

    /**
     * ISO 4217 code (default EUR).
     */
    @SerializedName("fee_currency")
    var fee_currency: String?,

    /**
     * How 'fee_amount' applies (default 'none').
     */
    @SerializedName("fee_type")
    var fee_type: PaymentFeeType?,

    /**
     * Self-managed (merchant fulfils, default) or PSP-backed ('provider' required to transact).
     */
    @SerializedName("kind")
    var kind: PaymentMethodKind?,

    /**
     * Localized display names ({ de, en, … }).
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * Maximum order amount — omitted = no upper bound.
     */
    @SerializedName("max_order_value")
    var max_order_value: Double?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Minimum order amount — omitted = no lower bound.
     */
    @SerializedName("min_order_value")
    var min_order_value: Double?,

    /**
     * Display name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Sort position in the checkout (default 0).
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * PSP code from the catalog — only for kind 'psp'.
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * The provider's payment method id (e.g. 'card', 'paypal').
     */
    @SerializedName("provider_method")
    var provider_method: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "countries" to countries as Any,
        "description" to description as Any,
        "enabled" to enabled as Any,
        "fee_amount" to fee_amount as Any,
        "fee_currency" to fee_currency as Any,
        "fee_type" to fee_type?.value as Any,
        "kind" to kind?.value as Any,
        "labels" to labels as Any,
        "max_order_value" to max_order_value as Any,
        "metadata" to metadata as Any,
        "min_order_value" to min_order_value as Any,
        "name" to name as Any,
        "position" to position as Any,
        "provider" to provider as Any,
        "provider_method" to provider_method as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentMethodCreateRequest(
            code = map["code"] as String,
            countries = map["countries"] as? List<String>,
            description = map["description"] as? String,
            enabled = map["enabled"] as? Boolean,
            fee_amount = (map["fee_amount"] as? Number)?.toDouble(),
            fee_currency = map["fee_currency"] as? String,
            fee_type = PaymentFeeType.values().find { it.value == (map["fee_type"] as? String) } ?: null,
            kind = PaymentMethodKind.values().find { it.value == (map["kind"] as? String) } ?: null,
            labels = map["labels"] as? Any,
            max_order_value = (map["max_order_value"] as? Number)?.toDouble(),
            metadata = map["metadata"] as? Any,
            min_order_value = (map["min_order_value"] as? Number)?.toDouble(),
            name = map["name"] as String,
            position = (map["position"] as? Number)?.toLong(),
            provider = map["provider"] as? String,
            provider_method = map["provider_method"] as? String,
        )
    }
}