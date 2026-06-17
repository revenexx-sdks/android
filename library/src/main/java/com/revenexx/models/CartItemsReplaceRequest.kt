package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartItemsReplaceRequest(
    /**
     * The complete new item set (set semantics).
     */
    @SerializedName("items")
    val items: List<CartItemCreateRequest>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartItemsReplaceRequest(
            items = (map["items"] as List<Map<String, Any>>).map { CartItemCreateRequest.from(map = it) },
        )
    }
}