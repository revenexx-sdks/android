package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A cart needs an owner: 'contact_id' (customer) or 'session_key' (guest).
 */
data class CartCreateRequest(
    /**
     * 
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Owning customer contact.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code (default EUR).
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Make this THE current cart of its owner.
     */
    @SerializedName("is_current")
    var is_current: Boolean?,

    /**
     * 
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Display name (default 'Cart').
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Owning guest session.
     */
    @SerializedName("session_key")
    var session_key: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "contact_id" to contact_id as Any,
        "currency" to currency as Any,
        "is_current" to is_current as Any,
        "market_id" to market_id as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "session_key" to session_key as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartCreateRequest(
            channel_id = map["channel_id"] as? String,
            contact_id = map["contact_id"] as? String,
            currency = map["currency"] as? String,
            is_current = map["is_current"] as? Boolean,
            market_id = map["market_id"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            session_key = map["session_key"] as? String,
        )
    }
}