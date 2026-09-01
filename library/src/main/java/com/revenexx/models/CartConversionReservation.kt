package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What this app ASKED inventories for, and what it answered. This app holds no stock: inventories picks the location, applies the backorder policy and owns the hold's expiry.
 */
data class CartConversionReservation(
    /**
     * Lines inventories accepted without stock behind them, under the tenant's backorder policy — its policy, not this app's.
     */
    @SerializedName("backordered")
    var backordered: Long?,

    /**
     * inventories' hold deadline — its TTL, not this app's.
     */
    @SerializedName("expires_at")
    var expires_at: String?,

    /**
     * A hold exists. False with `requested: true` means inventories was asked and refused — `reason` says why, and only convert_reserves_stock = require turns that into a 409.
     */
    @SerializedName("ok")
    var ok: Boolean?,

    /**
     * The reference the reservation was booked under: the `order_ref` of the request, or the cart id when the call carried none. This is the string to hand inventories when releasing the hold.
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * Why no hold exists — stated, never implied. Present whenever `ok` is false, and also on the never case.
     */
    @SerializedName("reason")
    var reason: String?,

    /**
     * False when convert_reserves_stock is 'never' — no call was made at all, which is reported rather than dressed up as a silent success.
     */
    @SerializedName("requested")
    var requested: Boolean?,

    /**
     * Lines inventories confirmed a hold for.
     */
    @SerializedName("reservations")
    var reservations: Long?,

    /**
     * The HTTP status inventories answered with, present only when it refused. 404 is its own case: the tenant has no inventories app at all, which is a different problem from not enough stock.
     */
    @SerializedName("status")
    var status: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "backordered" to backordered as Any,
        "expires_at" to expires_at as Any,
        "ok" to ok as Any,
        "order_ref" to order_ref as Any,
        "reason" to reason as Any,
        "requested" to requested as Any,
        "reservations" to reservations as Any,
        "status" to status as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartConversionReservation(
            backordered = (map["backordered"] as? Number)?.toLong(),
            expires_at = map["expires_at"] as? String,
            ok = map["ok"] as? Boolean,
            order_ref = map["order_ref"] as? String,
            reason = map["reason"] as? String,
            requested = map["requested"] as? Boolean,
            reservations = (map["reservations"] as? Number)?.toLong(),
            status = (map["status"] as? Number)?.toLong(),
        )
    }
}