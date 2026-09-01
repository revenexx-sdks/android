package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartMaintenanceRequest(
    /**
     * Report what the sweep WOULD do and write nothing. Worth doing before a first retention run: cart_ttl_days deletes carts and their lines.
     */
    @SerializedName("dry_run")
    var dry_run: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "dry_run" to dry_run as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartMaintenanceRequest(
            dry_run = map["dry_run"] as? Boolean,
        )
    }
}