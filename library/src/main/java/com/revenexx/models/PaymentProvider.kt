package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PaymentProvider(
    /**
     * When this PSP was configured for the tenant.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Only an enabled provider takes NEW payments: a method pointing at a disabled one falls through to the tenant's `fallback_provider`, and to a 422 if there is none. Nothing else reads it — capture, cancel and refund on the payments this PSP already holds go on working — which is what makes disabling the safe retirement and deleting the refused one.
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Id of the PSP configuration row — what the provider routes address. The provider itself is named by `provider`.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * Operator-facing name of the configuration. Defaults to the catalog label, and is worth changing when a tenant runs two accounts with one PSP.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Per-provider switches this app understands, plus anything the merchant keeps beside them. Three keys are the app's own: `logo_url` (the bundled logo, filled in when the provider is seeded), `capture_method` and `three_ds` (what the prism driver does today). Free jsonb — an unknown key is stored and ignored.
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * The catalog code of the PSP this row configures — one row per provider per tenant. GET /payments/providers/catalog lists every code that may appear here. It is what every payment and every method naming this PSP resolves it by, so changing it is refused with 409 for as long as one of them does.
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * Whether the driver talks to the PSP's sandbox. New configurations start in test mode: a provider nobody verified must not touch live money.
     */
    @SerializedName("test_mode")
    var test_mode: Boolean?,

    /**
     * When its configuration last changed — including a credential rotation, which is otherwise invisible from the outside.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "enabled" to enabled as Any,
        "id" to id as Any,
        "name" to name as Any,
        "options" to options as Any,
        "provider" to provider as Any,
        "test_mode" to test_mode as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentProvider(
            created_at = map["created_at"] as? String,
            enabled = map["enabled"] as? Boolean,
            id = map["id"] as? String,
            name = map["name"] as? String,
            options = map["options"] as? Any,
            provider = map["provider"] as? String,
            test_mode = map["test_mode"] as? Boolean,
            updated_at = map["updated_at"] as? String,
        )
    }
}