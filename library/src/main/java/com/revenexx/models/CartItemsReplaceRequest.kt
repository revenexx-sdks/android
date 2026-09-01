package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartItemsReplaceRequest<T>(
    /**
     * The complete new item set (set semantics).
     */
    @SerializedName("items")
    val items: List<CartItemCreateRequest<T>>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "items" to items.map { it.toMap() } as Any,
    )

    companion object {
        operator fun invoke(
            items: List<CartItemCreateRequest<Map<String, Any>>>,
        ) = CartItemsReplaceRequest<Map<String, Any>>(
            items,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = CartItemsReplaceRequest<T>(
            items = (map["items"] as List<Map<String, Any>>).map { CartItemCreateRequest.from(map = it, nestedType) },
        )
    }
}