package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryReserveRequest(
    /**
     * Optional reservation expiry.
     */
    @SerializedName("expires_at")
    var expires_at: String?,

    /**
     * The items to reserve — all-or-nothing (at most 200).
     */
    @SerializedName("items")
    val items: List<InventoryStockItem>,

    /**
     * The order this reservation belongs to.
     */
    @SerializedName("order_ref")
    val order_ref: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "expires_at" to expires_at as Any,
        "items" to items.map { it.toMap() } as Any,
        "order_ref" to order_ref as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryReserveRequest(
            expires_at = map["expires_at"] as? String,
            items = (map["items"] as List<Map<String, Any>>).map { InventoryStockItem.from(map = it) },
            order_ref = map["order_ref"] as String,
        )
    }
}