package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PaymentFeeType
import com.revenexx.enums.PaymentMethodKind

/**
 * One method as a checkout should render it: identity, wording, and what it costs this buyer.
 */
data class EligiblePaymentMethod(
    /**
     * The code to send back as `method_code` when the payment is created.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The currency `fee` is in — the one the request asked with, echoed.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The merchant's line about this method, to show beside it at checkout.
     */
    @SerializedName("description")
    var description: String?,

    /**
     * The surcharge this method costs THIS buyer, already computed against the requested amount — a fixed fee as it stands, a percentage resolved into an amount. Not a column: no CHECK bounds it, so none is declared.
     */
    @SerializedName("fee")
    var fee: Double?,

    /**
     * How `fee` was arrived at, for a checkout that wants to show "2 % surcharge" rather than the amount.
     */
    @SerializedName("fee_type")
    var fee_type: PaymentFeeType?,

    /**
     * Whether choosing this method starts a PSP flow ('psp') or authorizes immediately ('self_managed').
     */
    @SerializedName("kind")
    var kind: PaymentMethodKind?,

    /**
     * Buyer-facing names keyed by language tag, or null when the merchant configured none — then `name` is all there is.
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * The operator-facing name. Prefer `labels` for anything a buyer reads.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The merchant's sort order. The list is already sorted by it; it is carried so a client that re-sorts can put it back.
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * The PSP behind it, for a checkout that has to load a provider SDK before it can collect an instrument. null for self-managed methods.
     */
    @SerializedName("provider")
    var provider: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "currency" to currency as Any,
        "description" to description as Any,
        "fee" to fee as Any,
        "fee_type" to fee_type?.value as Any,
        "kind" to kind?.value as Any,
        "labels" to labels as Any,
        "name" to name as Any,
        "position" to position as Any,
        "provider" to provider as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = EligiblePaymentMethod(
            code = map["code"] as? String,
            currency = map["currency"] as? String,
            description = map["description"] as? String,
            fee = (map["fee"] as? Number)?.toDouble(),
            fee_type = PaymentFeeType.values().find { it.value == (map["fee_type"] as? String) } ?: null,
            kind = PaymentMethodKind.values().find { it.value == (map["kind"] as? String) } ?: null,
            labels = map["labels"] as? Any,
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            provider = map["provider"] as? String,
        )
    }
}