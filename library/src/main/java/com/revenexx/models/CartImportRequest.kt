package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Import into an existing cart ('target_cart_id') or a new cart (owner 'contact_id'/'session_key' required).
 */
data class CartImportRequest(
    /**
     * Owner of a newly created cart.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * Raw CSV content (alternative to payload for csv profiles).
     */
    @SerializedName("csv")
    var csv: String?,

    /**
     * Name for a newly created cart.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The import payload: '{cart, items}' object, or a raw JSON/CSV string in the profile's format.
     */
    @SerializedName("payload")
    var payload: Any?,

    /**
     * Import profile to run; ad-hoc import when omitted.
     */
    @SerializedName("profile_id")
    var profile_id: String?,

    /**
     * Guest owner of a newly created cart.
     */
    @SerializedName("session_key")
    var session_key: String?,

    /**
     * Existing active cart to import into.
     */
    @SerializedName("target_cart_id")
    var target_cart_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact_id" to contact_id as Any,
        "csv" to csv as Any,
        "name" to name as Any,
        "payload" to payload as Any,
        "profile_id" to profile_id as Any,
        "session_key" to session_key as Any,
        "target_cart_id" to target_cart_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartImportRequest(
            contact_id = map["contact_id"] as? String,
            csv = map["csv"] as? String,
            name = map["name"] as? String,
            payload = map["payload"] as? Any,
            profile_id = map["profile_id"] as? String,
            session_key = map["session_key"] as? String,
            target_cart_id = map["target_cart_id"] as? String,
        )
    }
}