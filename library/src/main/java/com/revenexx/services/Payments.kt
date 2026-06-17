package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxAPIRevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * 
 */
class Payments(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun paymentsList(
    ): Any {
        val apiPath = "/v1/payments"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param amount Order amount — 0 is legal (free orders), negative is not.
     * @param methodCode Code of a configured payment method.
     * @param cartId The cart this payment pays for.
     * @param contactId Paying customer contact.
     * @param country Buyer ISO country code for the eligibility check.
     * @param currency ISO 4217 code (default EUR).
     * @param idempotencyKey Same key answers the same payment instead of a duplicate.
     * @param metadata Free-form metadata.
     * @param orderRef External order reference — also the webhook fallback key.
     * @param returnUrl Where the PSP redirect flow returns the buyer to.
     * @return [com.revenexx.models.Payment]
     */
    @JvmOverloads
    suspend fun paymentsCreate(
        amount: Double,
        methodCode: String,
        cartId: String? = null,
        contactId: String? = null,
        country: String? = null,
        currency: String? = null,
        idempotencyKey: String? = null,
        metadata: Any? = null,
        orderRef: String? = null,
        returnUrl: String? = null,
    ): com.revenexx.models.Payment {
        val apiPath = "/v1/payments"

        val apiParams = mutableMapOf<String, Any?>(
            "amount" to amount,
            "cart_id" to cartId,
            "contact_id" to contactId,
            "country" to country,
            "currency" to currency,
            "idempotency_key" to idempotencyKey,
            "metadata" to metadata,
            "method_code" to methodCode,
            "order_ref" to orderRef,
            "return_url" to returnUrl,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Payment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Payment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Payment::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun paymentsMethodsList(
    ): Any {
        val apiPath = "/v1/payments/methods"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param code Stable method code (unique per tenant, e.g. 'invoice', 'card').
     * @param name Display name.
     * @param countries Allowed ISO country codes — empty/omitted = unrestricted.
     * @param description 
     * @param enabled Disabled methods are never eligible (default false).
     * @param feeAmount Fixed amount or percent value, per fee_type (default 0).
     * @param feeCurrency ISO 4217 code (default EUR).
     * @param feeType How 'fee_amount' applies (default 'none').
     * @param kind Self-managed (merchant fulfils, default) or PSP-backed ('provider' required to transact).
     * @param labels Localized display names ({ de, en, … }).
     * @param maxOrderValue Maximum order amount — omitted = no upper bound.
     * @param metadata Free-form metadata.
     * @param minOrderValue Minimum order amount — omitted = no lower bound.
     * @param position Sort position in the checkout (default 0).
     * @param provider PSP code from the catalog — only for kind 'psp'.
     * @param providerMethod The provider's payment method id (e.g. 'card', 'paypal').
     * @return [com.revenexx.models.PaymentMethod]
     */
    @JvmOverloads
    suspend fun paymentsMethodsCreate(
        code: String,
        name: String,
        countries: List<String>? = null,
        description: String? = null,
        enabled: Boolean? = null,
        feeAmount: Double? = null,
        feeCurrency: String? = null,
        feeType: com.revenexx.enums.PaymentFeeType? = null,
        kind: com.revenexx.enums.PaymentMethodKind? = null,
        labels: Any? = null,
        maxOrderValue: Double? = null,
        metadata: Any? = null,
        minOrderValue: Double? = null,
        position: Long? = null,
        provider: String? = null,
        providerMethod: String? = null,
    ): com.revenexx.models.PaymentMethod {
        val apiPath = "/v1/payments/methods"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "countries" to countries,
            "description" to description,
            "enabled" to enabled,
            "fee_amount" to feeAmount,
            "fee_currency" to feeCurrency,
            "fee_type" to feeType,
            "kind" to kind,
            "labels" to labels,
            "max_order_value" to maxOrderValue,
            "metadata" to metadata,
            "min_order_value" to minOrderValue,
            "name" to name,
            "position" to position,
            "provider" to provider,
            "provider_method" to providerMethod,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PaymentMethod = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PaymentMethod.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PaymentMethod::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun paymentsMethodsDefaults(
    ): Any {
        val apiPath = "/v1/payments/methods/defaults"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param amount Order amount the fees are computed against (default 0).
     * @param country Buyer ISO country code — methods with country restrictions need it.
     * @param currency ISO 4217 code (default EUR).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun paymentsMethodsEligible(
        amount: Double? = null,
        country: String? = null,
        currency: String? = null,
    ): Any {
        val apiPath = "/v1/payments/methods/eligible"

        val apiParams = mutableMapOf<String, Any?>(
            "amount" to amount,
            "country" to country,
            "currency" to currency,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun paymentsMethodsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/payments/methods/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.PaymentMethod]
     */
    suspend fun paymentsMethodsGet(
        id: String,
    ): com.revenexx.models.PaymentMethod {
        val apiPath = "/v1/payments/methods/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PaymentMethod = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PaymentMethod.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PaymentMethod::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code Stable method code (unique per tenant, e.g. 'invoice', 'card').
     * @param countries Allowed ISO country codes — empty/omitted = unrestricted.
     * @param description 
     * @param enabled Disabled methods are never eligible (default false).
     * @param feeAmount Fixed amount or percent value, per fee_type (default 0).
     * @param feeCurrency ISO 4217 code (default EUR).
     * @param feeType How 'fee_amount' applies (default 'none').
     * @param kind Self-managed (merchant fulfils, default) or PSP-backed ('provider' required to transact).
     * @param labels Localized display names ({ de, en, … }).
     * @param maxOrderValue Maximum order amount — omitted = no upper bound.
     * @param metadata Free-form metadata.
     * @param minOrderValue Minimum order amount — omitted = no lower bound.
     * @param name Display name.
     * @param position Sort position in the checkout (default 0).
     * @param provider PSP code from the catalog — only for kind 'psp'.
     * @param providerMethod The provider's payment method id (e.g. 'card', 'paypal').
     * @return [com.revenexx.models.PaymentMethod]
     */
    @JvmOverloads
    suspend fun paymentsMethodsUpdate(
        id: String,
        code: String? = null,
        countries: List<String>? = null,
        description: String? = null,
        enabled: Boolean? = null,
        feeAmount: Double? = null,
        feeCurrency: String? = null,
        feeType: com.revenexx.enums.PaymentFeeType? = null,
        kind: com.revenexx.enums.PaymentMethodKind? = null,
        labels: Any? = null,
        maxOrderValue: Double? = null,
        metadata: Any? = null,
        minOrderValue: Double? = null,
        name: String? = null,
        position: Long? = null,
        provider: String? = null,
        providerMethod: String? = null,
    ): com.revenexx.models.PaymentMethod {
        val apiPath = "/v1/payments/methods/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "countries" to countries,
            "description" to description,
            "enabled" to enabled,
            "fee_amount" to feeAmount,
            "fee_currency" to feeCurrency,
            "fee_type" to feeType,
            "kind" to kind,
            "labels" to labels,
            "max_order_value" to maxOrderValue,
            "metadata" to metadata,
            "min_order_value" to minOrderValue,
            "name" to name,
            "position" to position,
            "provider" to provider,
            "provider_method" to providerMethod,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PaymentMethod = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PaymentMethod.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PaymentMethod::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun paymentsProvidersList(
    ): Any {
        val apiPath = "/v1/payments/providers"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param provider Provider code — must exist in the catalog (GET /payments/providers/catalog).
     * @param credentials PSP credentials — the catalog's credential_fields say which keys the auth scheme expects.
     * @param enabled Only enabled providers transact (default false).
     * @param name Display name — defaults to the catalog label.
     * @param options Free-form provider options.
     * @param testMode Sandbox/test credentials (default true).
     * @param webhookSecret Shared secret for PSP callback verification.
     * @return [com.revenexx.models.PaymentProvider]
     */
    @JvmOverloads
    suspend fun paymentsProvidersCreate(
        provider: String,
        credentials: Any? = null,
        enabled: Boolean? = null,
        name: String? = null,
        options: Any? = null,
        testMode: Boolean? = null,
        webhookSecret: String? = null,
    ): com.revenexx.models.PaymentProvider {
        val apiPath = "/v1/payments/providers"

        val apiParams = mutableMapOf<String, Any?>(
            "credentials" to credentials,
            "enabled" to enabled,
            "name" to name,
            "options" to options,
            "provider" to provider,
            "test_mode" to testMode,
            "webhook_secret" to webhookSecret,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PaymentProvider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PaymentProvider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PaymentProvider::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun paymentsProvidersCatalog(
    ): Any {
        val apiPath = "/v1/payments/providers/catalog"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun paymentsProvidersDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/payments/providers/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.PaymentProvider]
     */
    suspend fun paymentsProvidersGet(
        id: String,
    ): com.revenexx.models.PaymentProvider {
        val apiPath = "/v1/payments/providers/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PaymentProvider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PaymentProvider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PaymentProvider::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param credentials PSP credentials — the catalog's credential_fields say which keys the auth scheme expects.
     * @param enabled Only enabled providers transact (default false).
     * @param name Display name — defaults to the catalog label.
     * @param options Free-form provider options.
     * @param provider Provider code — must exist in the catalog (GET /payments/providers/catalog).
     * @param testMode Sandbox/test credentials (default true).
     * @param webhookSecret Shared secret for PSP callback verification.
     * @return [com.revenexx.models.PaymentProvider]
     */
    @JvmOverloads
    suspend fun paymentsProvidersUpdate(
        id: String,
        credentials: Any? = null,
        enabled: Boolean? = null,
        name: String? = null,
        options: Any? = null,
        provider: String? = null,
        testMode: Boolean? = null,
        webhookSecret: String? = null,
    ): com.revenexx.models.PaymentProvider {
        val apiPath = "/v1/payments/providers/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "credentials" to credentials,
            "enabled" to enabled,
            "name" to name,
            "options" to options,
            "provider" to provider,
            "test_mode" to testMode,
            "webhook_secret" to webhookSecret,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PaymentProvider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PaymentProvider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PaymentProvider::class.java,
            converter,
        )
    }


    /**
     * Consumes the dispatch envelope from webhooks.revenexx.com: normalizes the provider callback (stripe payment intents + a generic shape), resolves the payment by psp_payment_id or order_ref and moves the ledger. Facts only move forward — provider retries and redeliveries are idempotent no-ops; unverified envelopes are refused.
     *
     * @param provider 
     * @param data Request body
     * @return [Any]
     */
    suspend fun paymentsWebhooksIngest(
        provider: String,
        data: Any,
    ): Any {
        val apiPath = "/v1/payments/webhooks/{provider}"
            .replace("{provider}", provider)

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Payment]
     */
    suspend fun paymentsGet(
        id: String,
    ): com.revenexx.models.Payment {
        val apiPath = "/v1/payments/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Payment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Payment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Payment::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Payment]
     */
    suspend fun paymentsCancel(
        id: String,
    ): com.revenexx.models.Payment {
        val apiPath = "/v1/payments/{id}/cancel"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Payment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Payment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Payment::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Payment]
     */
    suspend fun paymentsCapture(
        id: String,
    ): com.revenexx.models.Payment {
        val apiPath = "/v1/payments/{id}/capture"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Payment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Payment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Payment::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Payment]
     */
    suspend fun paymentsConfirm(
        id: String,
    ): com.revenexx.models.Payment {
        val apiPath = "/v1/payments/{id}/confirm"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Payment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Payment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Payment::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Payment]
     */
    suspend fun paymentsRefund(
        id: String,
    ): com.revenexx.models.Payment {
        val apiPath = "/v1/payments/{id}/refund"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Payment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Payment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Payment::class.java,
            converter,
        )
    }


}