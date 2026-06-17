package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Payment(
    /**
     * 
     */
    @SerializedName("amount")
    var amount: Double?,

    /**
     * 
     */
    @SerializedName("authorized_at")
    var authorized_at: String?,

    /**
     * 
     */
    @SerializedName("captured_at")
    var captured_at: String?,

    /**
     * 
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * 
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("error_message")
    var error_message: String?,

    /**
     * 
     */
    @SerializedName("failed_at")
    var failed_at: String?,

    /**
     * 
     */
    @SerializedName("fee_amount")
    var fee_amount: Double?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("idempotency_key")
    var idempotency_key: String?,

    /**
     * 
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("method_code")
    var method_code: String?,

    /**
     * 
     */
    @SerializedName("next_action")
    var next_action: Any?,

    /**
     * 
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * 
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * 
     */
    @SerializedName("psp_payment_id")
    var psp_payment_id: String?,

    /**
     * 
     */
    @SerializedName("refunded_at")
    var refunded_at: String?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
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
        "error_message" to error_message as Any,
        "failed_at" to failed_at as Any,
        "fee_amount" to fee_amount as Any,
        "id" to id as Any,
        "idempotency_key" to idempotency_key as Any,
        "kind" to kind as Any,
        "metadata" to metadata as Any,
        "method_code" to method_code as Any,
        "next_action" to next_action as Any,
        "order_ref" to order_ref as Any,
        "provider" to provider as Any,
        "psp_payment_id" to psp_payment_id as Any,
        "refunded_at" to refunded_at as Any,
        "status" to status as Any,
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
            error_message = map["error_message"] as? String,
            failed_at = map["failed_at"] as? String,
            fee_amount = (map["fee_amount"] as? Number)?.toDouble(),
            id = map["id"] as? String,
            idempotency_key = map["idempotency_key"] as? String,
            kind = map["kind"] as? String,
            metadata = map["metadata"] as? Any,
            method_code = map["method_code"] as? String,
            next_action = map["next_action"] as? Any,
            order_ref = map["order_ref"] as? String,
            provider = map["provider"] as? String,
            psp_payment_id = map["psp_payment_id"] as? String,
            refunded_at = map["refunded_at"] as? String,
            status = map["status"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}