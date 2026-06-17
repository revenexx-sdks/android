package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Buyer context + items. Unpriceable items come back as on_request — a missing price is a first-class state, never 0.
 */
data class PriceResolveRequest(
    /**
     * Point in time for validity windows (ISO 8601 timestamp, default now).
     */
    @SerializedName("at")
    var at: String?,

    /**
     * Buyer context: channel.
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * Buyer context: contact — most specific scope.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 4217 code (default EUR) — only lists in this currency resolve.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * Items to price (at most 200 per call).
     */
    @SerializedName("items")
    val items: List<PriceResolveItem>,

    /**
     * Buyer context: market.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * Buyer context: organization.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "at" to at as Any,
        "channel_id" to channel_id as Any,
        "contact_id" to contact_id as Any,
        "currency" to currency as Any,
        "items" to items.map { it.toMap() } as Any,
        "market_id" to market_id as Any,
        "organization_id" to organization_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceResolveRequest(
            at = map["at"] as? String,
            channel_id = map["channel_id"] as? String,
            contact_id = map["contact_id"] as? String,
            currency = map["currency"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { PriceResolveItem.from(map = it) },
            market_id = map["market_id"] as? String,
            organization_id = map["organization_id"] as? String,
        )
    }
}