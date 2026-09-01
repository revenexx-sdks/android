package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Only safe columns are updatable — status moves through the lifecycle routes.
 */
data class CartUpdateRequest(
    /**
     * Move the cart to another sales channel.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * ISO 4217 code. Changes what NEW lines inherit; lines already in the cart keep the currency they were added with.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Free-form data the storefront hangs on the cart. Stored and returned verbatim; no key in here is read by this app, and none is indexed.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * Rename the cart. Unlike on create, this is written verbatim — `null` and `''` are refused by the database.
     */
    @SerializedName("name")
    var name: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "currency" to currency as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartUpdateRequest(
            channel_id = map["channel_id"] as? String,
            currency = map["currency"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
        )
    }
}