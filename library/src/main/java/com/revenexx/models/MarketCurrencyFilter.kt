package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The exact-column filters this call applied, echoed back. Every value is the raw query string, never the column's own type: `?is_default=true` comes back as `"true"`. A `?column=value` naming a column this entity does not have is DROPPED rather than refused — the call answers 200 with the unfiltered list, and the key missing from here is the only way to find out.
 */
data class MarketCurrencyFilter(
    /**
     * The `code` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * The `created_at` filter as it arrived, verbatim. Present only when the call sent it. Any form the database accepts as a timestamp, including a bare date.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The `id` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The `is_default` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("is_default")
    var is_default: String?,

    /**
     * The owning market, taken from the route path. ALWAYS present, and always the path's market — a `?market_id=` in the query is overwritten by it rather than honoured, so this is never the value a caller sent.
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * The `position` filter as it arrived, verbatim. Present only when the call sent it.
     */
    @SerializedName("position")
    var position: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "market_id" to market_id as Any,
        "position" to position as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCurrencyFilter(
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? String,
            market_id = map["market_id"] as? String,
            position = map["position"] as? String,
        )
    }
}