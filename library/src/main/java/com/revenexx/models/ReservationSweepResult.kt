package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ReservationSweepResult(
    /**
     * How many active reservations were found past their hold: the ones with an `expires_at` in the past, plus the undated ones older than their market's TTL.
     */
    @SerializedName("expired")
    var expired: Long?,

    /**
     * The market codes this run had to resolve a window for — every market that had an undated active reservation. Empty when nothing is market-assigned, which is the usual case.
     */
    @SerializedName("markets")
    var markets: List<String>?,

    /**
     * How many were actually given back — `reserved` lowered on the stock row and a `release` booking written for each. It equals `expired` unless a row vanished mid-run. Idempotent: a second run immediately after finds nothing and answers 0.
     */
    @SerializedName("released")
    var released: Long?,

    /**
     * The cut-off this run used — everything whose hold had run out by this moment was released. It is the run's own clock, not a stored value.
     */
    @SerializedName("swept_at")
    var swept_at: String?,

    /**
     * The `reservation_ttl_minutes` that applied to reservations belonging to NO market — the tenant baseline. A reservation assigned to a market is judged against that market's own window instead, which is why this is reported rather than assumed to be the only one.
     */
    @SerializedName("ttl_minutes")
    var ttl_minutes: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "expired" to expired as Any,
        "markets" to markets as Any,
        "released" to released as Any,
        "swept_at" to swept_at as Any,
        "ttl_minutes" to ttl_minutes as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReservationSweepResult(
            expired = (map["expired"] as? Number)?.toLong(),
            markets = map["markets"] as? List<String>,
            released = (map["released"] as? Number)?.toLong(),
            swept_at = map["swept_at"] as? String,
            ttl_minutes = (map["ttl_minutes"] as? Number)?.toDouble(),
        )
    }
}