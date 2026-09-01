package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Creates AND authorizes: self-managed methods authorize immediately, PSP methods may answer next_action (redirect). Eligibility is re-checked server-side.
 */
data class PaymentCreateRequest(
    /**
     * What the provider is asked to authorize, in `currency`. 0 is legal (a free order) and negative is refused by the handler and by the CHECK behind it. `fee_amount` is recorded beside this and is NOT added to it — a checkout that charges its payment surcharge sends a total that already includes it.
     */
    @SerializedName("amount")
    val amount: Double,

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
     * The buyer's ISO 3166-1 alpha-2 country code, for the eligibility check. A method restricted to countries is refused with 422 without it.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * ISO 4217 code the amount and the fee are in. The database bounds the length at three characters and nothing else, so lower case is stored as written. Defaults to EUR.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * The caller's own key for this creation attempt. Sending it again answers the SAME payment with 200 instead of creating a second one — which is what makes a retried checkout safe. Unique per tenant, so a filter on it answers at most one row. The replay answers 200, not 201.
     */
    @SerializedName("idempotency_key")
    var idempotency_key: String?,

    /**
     * Free-form data to keep on the payment. Merged with the keys this app writes itself (`provider_method`, `return_url`, later the cancel/refund reasons), which win on a collision.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The `code` of the payment method this payment was made with, copied at creation. Deliberately a code and not a foreign key: the ledger records what happened and has to outlive the configuration it happened under. It must name a method this tenant has configured; eligibility for the buyer context below is re-checked here, whatever the checkout showed.
     */
    @SerializedName("method_code")
    val method_code: String,

    /**
     * The external order reference the checkout wrote onto the payment. It is what POST /payments/orders/{order_ref}/capture resolves and the fallback key a PSP webhook is matched on when it carries no transaction id — so an integration that leaves it null gives up both. Free text with no uniqueness: several payments may share one reference.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * Where the PSP sends the buyer back after a redirect or a 3-D Secure challenge. Kept in `metadata.return_url` and handed to the driver — a PSP method that needs a redirect and has none leaves the buyer stranded at the provider.
     */
    @SerializedName("return_url")
    var return_url: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "amount" to amount as Any,
        "cart_id" to cart_id as Any,
        "contact_id" to contact_id as Any,
        "country" to country as Any,
        "currency" to currency as Any,
        "idempotency_key" to idempotency_key as Any,
        "metadata" to metadata as Any,
        "method_code" to method_code as Any,
        "order_ref" to order_ref as Any,
        "return_url" to return_url as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentCreateRequest(
            amount = (map["amount"] as Number).toDouble(),
            cart_id = map["cart_id"] as? String,
            contact_id = map["contact_id"] as? String,
            country = map["country"] as? String,
            currency = map["currency"] as? String,
            idempotency_key = map["idempotency_key"] as? String,
            metadata = map["metadata"] as? Any,
            method_code = map["method_code"] as String,
            order_ref = map["order_ref"] as? String,
            return_url = map["return_url"] as? String,
        )
    }
}