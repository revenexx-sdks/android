package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Activates a catalog PSP for this tenant — providers are configuration, not code.
 */
data class PaymentProviderCreateRequest(
    /**
     * The PSP's own API credentials, under the key names its auth scheme expects — `GET /payments/providers/catalog` publishes them per provider as `credential_fields` (Stripe: `api_key`; PayPal: `client_id` + `client_secret`; Novalnet: `api_key` + `payment_access_key` + `tariff_id`). They come from the provider's own dashboard, are handed to the driver in-process, and are never read back by any route. Write-only: to rotate one, write the new value. Whatever a document shows here is a placeholder.
     */
    @SerializedName("credentials")
    var credentials: Any?,

    /**
     * Only an enabled provider takes NEW payments: a method pointing at a disabled one falls through to the tenant's `fallback_provider`, and to a 422 if there is none. Nothing else reads it — capture, cancel and refund on the payments this PSP already holds go on working — which is what makes disabling the safe retirement and deleting the refused one. Defaults to false — finish the credentials before switching it on.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Operator-facing name of the configuration. Defaults to the catalog label, and is worth changing when a tenant runs two accounts with one PSP. null, omitted or empty falls back to the catalog label.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Per-provider switches this app understands, plus anything the merchant keeps beside them. Three keys are the app's own: `logo_url` (the bundled logo, filled in when the provider is seeded), `capture_method` and `three_ds` (what the prism driver does today). Free jsonb — an unknown key is stored and ignored.
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * The catalog code of the PSP this row configures — one row per provider per tenant. GET /payments/providers/catalog lists every code that may appear here. It is what every payment and every method naming this PSP resolves it by, so changing it is refused with 409 for as long as one of them does. Required on create, and refused with 400 when the catalog does not carry it.
     */
    @SerializedName("provider")
    val provider: String,

    /**
     * Whether the driver talks to the PSP's sandbox. New configurations start in test mode: a provider nobody verified must not touch live money. Unstated takes the tenant's own `test_mode_default` setting.
     */
    @SerializedName("test_mode")
    var test_mode: Boolean?,

    /**
     * The signing secret the PSP issues when its webhook endpoint is created, in the provider's own dashboard. webhooks.revenexx.com verifies each callback against it before the dispatcher hands the envelope to this app. Write-only, like `credentials`: it is stored, used, and never read back by any route, so there is nothing to compare a value against — to rotate it, write the new one. Whatever a document shows here is a generated placeholder, not a usable secret — writing it verbatim leaves every callback failing verification.
     */
    @SerializedName("webhook_secret")
    var webhook_secret: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "credentials" to credentials as Any,
        "enabled" to enabled as Any,
        "name" to name as Any,
        "options" to options as Any,
        "provider" to provider as Any,
        "test_mode" to test_mode as Any,
        "webhook_secret" to webhook_secret as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentProviderCreateRequest(
            credentials = map["credentials"] as? Any,
            enabled = map["enabled"] as? Boolean,
            name = map["name"] as? String,
            options = map["options"] as? Any,
            provider = map["provider"] as String,
            test_mode = map["test_mode"] as? Boolean,
            webhook_secret = map["webhook_secret"] as? String,
        )
    }
}