package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class InventoryReceiveRequest(
    /**
     * The inbound items (at most 200).
     */
    @SerializedName("items")
    val items: List<InventoryStockItem>,

    /**
     * Receiving location (default 'main').
     */
    @SerializedName("location_code")
    var location_code: String?,

    /**
     * Ledger note (e.g. delivery note number).
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items.map { it.toMap() } as Any,
        "location_code" to location_code as Any,
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = InventoryReceiveRequest(
            items = (map["items"] as List<Map<String, Any>>).map { InventoryStockItem.from(map = it) },
            location_code = map["location_code"] as? String,
            reason = map["reason"] as? String,
        )
    }
}