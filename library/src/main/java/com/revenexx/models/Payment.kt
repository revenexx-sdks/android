package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PaymentDunningStage
import com.revenexx.enums.PaymentFailureCode
import com.revenexx.enums.PaymentMethodKind
import com.revenexx.enums.PaymentStatus

/**
 * 
 */
data class Payment(
    /**
     * What the provider is asked to authorize, in `currency`. 0 is legal (a free order) and negative is refused by the handler and by the CHECK behind it. `fee_amount` is recorded beside this and is NOT added to it — a checkout that charges its payment surcharge sends a total that already includes it.
     */
    @SerializedName("amount")
    var amount: Double?,

    /**
     * When the money was reserved — or, for invoice and prepayment, when it became owed. The clock the capture window and the dunning stages are measured from.
     */
    @SerializedName("authorized_at")
    var authorized_at: String?,

    /**
     * When the money was actually taken. The refund window is measured from here.
     */
    @SerializedName("captured_at")
    var captured_at: String?,

    /**
     * The cart this payment pays for. Not a foreign key: the payment is a record of what happened and outlives the cart. Indexed, so it is the cheap way to find the payment behind a checkout.
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * The paying customer contact. Not a foreign key — a payment must survive a contact being merged or erased. Indexed.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * When the payment was created. The dunning clock for invoice and prepayment runs from here.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * ISO 4217 code the amount and the fee are in. The database bounds the length at three characters and nothing else, so lower case is stored as written.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * When the NEXT dunning stage falls due — the moment a reminder becomes due, then the moment it becomes overdue. null once nothing further is pending, which includes an already overdue payment and every paid, cancelled or refunded one.
     */
    @SerializedName("dunning_due_at")
    var dunning_due_at: String?,

    /**
     * How overdue an unpaid self-managed payment is: 'none', 'reminder' or 'overdue'. Written by the daily dunning scan from the merchant's two thresholds, and reset the moment the money arrives or the claim is dropped. It classifies and never sends: what a reminder looks like is the merchant's own workflow.
     */
    @SerializedName("dunning_stage")
    var dunning_stage: PaymentDunningStage?,

    /**
     * The class of failure, out of a fixed taxonomy — the value to branch on. null unless the payment failed. The five classes say what a caller can DO: 'provider_unavailable', 'provider_unreachable', 'provider_not_configured', 'provider_declined', 'provider_error' — a provider that is unreachable or unavailable is worth a retry, a declined payment needs a different method from the buyer, and a provider that is not configured needs an operator.
     */
    @SerializedName("error_code")
    var error_code: PaymentFailureCode?,

    /**
     * One operator-facing sentence, fixed per `error_code`. Never the provider's or the runtime's own wording: that is unbounded internal text and it stays in the app log.
     */
    @SerializedName("error_message")
    var error_message: String?,

    /**
     * When the payment failed. `error_code` says which class of failure.
     */
    @SerializedName("failed_at")
    var failed_at: String?,

    /**
     * The method surcharge as it was computed at creation, in `currency`. Kept so the fee that was quoted stays readable after the method's fee configuration changes.
     */
    @SerializedName("fee_amount")
    var fee_amount: Double?,

    /**
     * Id of the payment. Every lifecycle route addresses it, and it is what the drivers send the provider as their merchant transaction reference.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The caller's own key for this creation attempt. Sending it again answers the SAME payment with 200 instead of creating a second one — which is what makes a retried checkout safe. Unique per tenant, so a filter on it answers at most one row.
     */
    @SerializedName("idempotency_key")
    var idempotency_key: String?,

    /**
     * Copied from the method at creation. 'self_managed' payments move through the lifecycle without a PSP; 'psp' payments are driven by `provider`.
     */
    @SerializedName("kind")
    var kind: PaymentMethodKind?,

    /**
     * Whatever the creating call sent, plus the keys this app writes onto it. The app's own: `provider_method` (the method's provider-side id, copied at creation), `return_url` (where the PSP sends the buyer back), `cancel_reason` / `refund_reason` (the operator's words from the cancel and refund routes, also handed to the provider) and `provider_fallback_from` (the provider that was WANTED, written when the tenant's fallback_provider stood in — the only record of why the money went through a different acquirer). Free jsonb; a caller's own keys are kept untouched beside these.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The `code` of the payment method this payment was made with, copied at creation. Deliberately a code and not a foreign key: the ledger records what happened and has to outlive the configuration it happened under.
     */
    @SerializedName("method_code")
    var method_code: String?,

    /**
     * What the storefront must do before this payment can go any further, or null when there is nothing to do. It is set exactly when `status` is `requires_action`, and every transition clears it. One shape exists today: `{ "type": "redirect", "url": … }` — send the buyer to `url` (that is also where a 3-D Secure challenge is presented, because the connector hands it back as a redirect), and when they come back call POST /payments/{id}/confirm. `type` is what to branch on; a client that does not recognise it must not guess.
     */
    @SerializedName("next_action")
    var next_action: Any?,

    /**
     * The external order reference the checkout wrote onto the payment. It is what POST /payments/orders/{order_ref}/capture resolves and the fallback key a PSP webhook is matched on when it carries no transaction id — so an integration that leaves it null gives up both. Free text with no uniqueness: several payments may share one reference.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * The PSP the money really went through — resolved at creation and rewritten if the tenant's fallback provider stood in, in which case `metadata.provider_fallback_from` records what was meant. null for self-managed payments.
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * The provider's own transaction id, as it answered — the value to quote in a PSP support case, and the primary key a webhook is matched on. Shaped by the provider, so nothing here constrains it; null until a provider has answered, and always null for self-managed payments.
     */
    @SerializedName("psp_payment_id")
    var psp_payment_id: String?,

    /**
     * When the payment was refunded in full — this app has no partial refund to record.
     */
    @SerializedName("refunded_at")
    var refunded_at: String?,

    /**
     * Where the payment stands. 'created' → 'requires_action' → 'authorized' → 'captured' → 'refunded', with 'failed' and 'cancelled' ending it. GET /payments/vocabularies/statuses serves the same set with labels, badge tones and which of them are final.
     */
    @SerializedName("status")
    var status: PaymentStatus?,

    /**
     * The tenant the row belongs to — the same slug the request carried in `X-Revenexx-Tenant`. Added by the platform rather than by this app, and echoed so a caller that fans several tenants into one store can tell the rows apart.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * When the row last moved. For a PSP payment still waiting on a callback this is what the webhook-staleness check measures against, so an old payment that changed a minute ago counts as progressing.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "amount" to amount as Any,
        "authorized_at" to authorized_at as Any,
        "captured_at" to captured_at as Any,
        "cart_id" to cart_id as Any,
        "contact_id" to contact_id as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "dunning_due_at" to dunning_due_at as Any,
        "dunning_stage" to dunning_stage?.value as Any,
        "error_code" to error_code?.value as Any,
        "error_message" to error_message as Any,
        "failed_at" to failed_at as Any,
        "fee_amount" to fee_amount as Any,
        "id" to id as Any,
        "idempotency_key" to idempotency_key as Any,
        "kind" to kind?.value as Any,
        "metadata" to metadata as Any,
        "method_code" to method_code as Any,
        "next_action" to next_action as Any,
        "order_ref" to order_ref as Any,
        "provider" to provider as Any,
        "psp_payment_id" to psp_payment_id as Any,
        "refunded_at" to refunded_at as Any,
        "status" to status?.value as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Payment(
            amount = (map["amount"] as? Number)?.toDouble(),
            authorized_at = map["authorized_at"] as? String,
            captured_at = map["captured_at"] as? String,
            cart_id = map["cart_id"] as? String,
            contact_id = map["contact_id"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            dunning_due_at = map["dunning_due_at"] as? String,
            dunning_stage = PaymentDunningStage.values().find { it.value == (map["dunning_stage"] as? String) } ?: null,
            error_code = PaymentFailureCode.values().find { it.value == (map["error_code"] as? String) } ?: null,
            error_message = map["error_message"] as? String,
            failed_at = map["failed_at"] as? String,
            fee_amount = (map["fee_amount"] as? Number)?.toDouble(),
            id = map["id"] as? String,
            idempotency_key = map["idempotency_key"] as? String,
            kind = PaymentMethodKind.values().find { it.value == (map["kind"] as? String) } ?: null,
            metadata = map["metadata"] as? Any,
            method_code = map["method_code"] as? String,
            next_action = map["next_action"] as? Any,
            order_ref = map["order_ref"] as? String,
            provider = map["provider"] as? String,
            psp_payment_id = map["psp_payment_id"] as? String,
            refunded_at = map["refunded_at"] as? String,
            status = PaymentStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}