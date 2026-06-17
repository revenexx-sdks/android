package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Only safe columns are updatable — status moves through the lifecycle routes.
 */
data class CartUpdateRequest(
    /**
     * 
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * ISO 4217 code.
     */
    @SerializedName("currency")
    var currency: String?,

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
     * 
     */
    @SerializedName("name")
    var name: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "channel_id" to channel_id as Any,
        "currency" to currency as Any,
        "market_id" to market_id as Any,
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
            market_id = map["market_id"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
        )
    }
}