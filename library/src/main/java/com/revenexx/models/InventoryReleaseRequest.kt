package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryReleaseRequest(
    /**
     * The order this hold belongs to. The caller supplies it — this app mints nothing — and it is the handle POST /inventories/release and POST /inventories/commit act on, so it has to be the same string the order carries elsewhere. At least one character (CHECK `length(order_ref) > 0`). Not unique: an order holds one reservation per item, and they are released or committed together. Every ACTIVE hold under this reference is given back; ones already committed or released are left alone. A reference no reservation carries releases nothing and answers `released: 0` — not an error, which is what makes a retried cancellation safe.
     */
    @SerializedName("order_ref")
    val order_ref: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "order_ref" to order_ref as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryReleaseRequest(
            order_ref = map["order_ref"] as String,
        )
    }
}