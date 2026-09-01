package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A cart needs an owner: 'contact_id' (customer) or 'session_key' (guest).
 */
data class CartCreateRequest(
    /**
     * The sales channel this cart is being opened in, as a channel of the channels app. Stored for attribution; nothing in this app reads it.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * The customer who owns this cart, as a contact of the customers app. Send this OR session_key — a cart with neither owner is refused.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code the cart is priced in (default EUR). Lines added without a currency inherit it.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Make this THE current cart of its owner as it is created — the same thing carts.activate does later, and it clears the flag on every sibling cart of the same owner.
     */
    @SerializedName("is_current")
    var is_current: Boolean?,

    /**
     * Free-form data the storefront hangs on the cart. Stored and returned verbatim; no key in here is read by this app, and none is indexed.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * What the buyer calls this cart (default 'Cart'). An empty string is legal and lands on the default.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * The guest session that owns this cart — the key the storefront already keeps in its own session or cookie. Any non-empty string is accepted; this app issues none and parses none, so the example shows a shape and not a format. Send this OR contact_id.
     */
    @SerializedName("session_key")
    var session_key: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "contact_id" to contact_id as Any,
        "currency" to currency as Any,
        "is_current" to is_current as Any,
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
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            session_key = map["session_key"] as? String,
        )
    }
}