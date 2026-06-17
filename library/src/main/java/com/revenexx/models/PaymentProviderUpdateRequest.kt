package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Partial update — omitted fields keep their current value.
 */
data class PaymentProviderUpdateRequest(
    /**
     * PSP credentials — the catalog's credential_fields say which keys the auth scheme expects.
     */
    @SerializedName("credentials")
    var credentials: Any?,

    /**
     * Only enabled providers transact (default false).
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * Display name — defaults to the catalog label.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Free-form provider options.
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * Provider code — must exist in the catalog (GET /payments/providers/catalog).
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * Sandbox/test credentials (default true).
     */
    @SerializedName("test_mode")
    var test_mode: Boolean?,

    /**
     * Shared secret for PSP callback verification.
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
        ) = PaymentProviderUpdateRequest(
            credentials = map["credentials"] as? Any,
            enabled = map["enabled"] as? Boolean,
            name = map["name"] as? String,
            options = map["options"] as? Any,
            provider = map["provider"] as? String,
            test_mode = map["test_mode"] as? Boolean,
            webhook_secret = map["webhook_secret"] as? String,
        )
    }
}