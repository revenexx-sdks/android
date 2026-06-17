package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PaymentProvider(
    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("credentials")
    var credentials: Any?,

    /**
     * 
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("options")
    var options: Any?,

    /**
     * 
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * 
     */
    @SerializedName("test_mode")
    var test_mode: Boolean?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
     */
    @SerializedName("webhook_secret")
    var webhook_secret: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "credentials" to credentials as Any,
        "enabled" to enabled as Any,
        "id" to id as Any,
        "name" to name as Any,
        "options" to options as Any,
        "provider" to provider as Any,
        "test_mode" to test_mode as Any,
        "updated_at" to updated_at as Any,
        "webhook_secret" to webhook_secret as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentProvider(
            created_at = map["created_at"] as? String,
            credentials = map["credentials"] as? Any,
            enabled = map["enabled"] as? Boolean,
            id = map["id"] as? String,
            name = map["name"] as? String,
            options = map["options"] as? Any,
            provider = map["provider"] as? String,
            test_mode = map["test_mode"] as? Boolean,
            updated_at = map["updated_at"] as? String,
            webhook_secret = map["webhook_secret"] as? String,
        )
    }
}