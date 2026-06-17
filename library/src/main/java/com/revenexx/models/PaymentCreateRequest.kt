package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Creates AND authorizes: self-managed methods authorize immediately, PSP methods may answer next_action (redirect). Eligibility is re-checked server-side.
 */
data class PaymentCreateRequest(
    /**
     * Order amount — 0 is legal (free orders), negative is not.
     */
    @SerializedName("amount")
    val amount: Double,

    /**
     * The cart this payment pays for.
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * Paying customer contact.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * Buyer ISO country code for the eligibility check.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * ISO 4217 code (default EUR).
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Same key answers the same payment instead of a duplicate.
     */
    @SerializedName("idempotency_key")
    var idempotency_key: String?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Code of a configured payment method.
     */
    @SerializedName("method_code")
    val method_code: String,

    /**
     * External order reference — also the webhook fallback key.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * Where the PSP redirect flow returns the buyer to.
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