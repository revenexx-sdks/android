package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Replace ALL positions of the list (set semantics).
 */
data class OrderListItemsReplaceRequest(
    /**
     * The new full set of positions, in the order they should carry. An empty array empties the list. Every existing position is deleted and rewritten, so ids are NOT preserved. The array order is the DEFAULT and not an override: an entry that names no `position` takes its index, one that names its own keeps it — so a replace does not by itself renumber the list from zero.
     */
    @SerializedName("items")
    val items: List<OrderListItemInput>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListItemsReplaceRequest(
            items = (map["items"] as List<Map<String, Any>>).map { OrderListItemInput.from(map = it) },
        )
    }
}