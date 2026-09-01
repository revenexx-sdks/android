package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartMaintenanceResult(
    /**
     * The first sweep: active carts nobody has touched since their market's window become abandoned. Nothing else in the platform ever stamps abandoned_at, so without this the abandonment funnel is empty by construction rather than empty because nobody abandons carts.
     */
    @SerializedName("abandon")
    var abandon: CartAbandonSweep?,

    /**
     * This pass wrote nothing. The counts and cart ids are the same ones the wet run would produce.
     */
    @SerializedName("dry_run")
    var dry_run: Boolean?,

    /**
     * The second sweep, and the only destructive thing this app does: carts past their retention window are deleted, their lines with them. An ordered cart is never touched at any setting — it is the source record of a sale.
     */
    @SerializedName("purge")
    var purge: CartPurgeSweep?,

    /**
     * The instant this pass measured every window against. One clock for both sweeps, so a cart cannot be judged idle by one and fresh by the other.
     */
    @SerializedName("swept_at")
    var swept_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "abandon" to abandon?.toMap() as Any,
        "dry_run" to dry_run as Any,
        "purge" to purge?.toMap() as Any,
        "swept_at" to swept_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartMaintenanceResult(
            abandon = CartAbandonSweep.from(map = map["abandon"] as Map<String, Any>),
            dry_run = map["dry_run"] as? Boolean,
            purge = CartPurgeSweep.from(map = map["purge"] as Map<String, Any>),
            swept_at = map["swept_at"] as? String,
        )
    }
}