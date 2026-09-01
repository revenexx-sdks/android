package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderListCartMode

/**
 * 
 */
data class OrderListToCartResult(
    /**
     * Positions written to the cart. Equal to the list's position count minus `skipped`.
     */
    @SerializedName("added")
    var added: Long?,

    /**
     * True when this call created the cart. A created cart is the owner's CURRENT cart, because a cart the buyer cannot see is not "added to cart".
     */
    @SerializedName("cart_created")
    var cart_created: Boolean?,

    /**
     * The cart the positions landed in: the one that was passed in, or the one this call created.
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * The list that was converted. Unchanged by the call — a conversion reads the list, it never empties it.
     */
    @SerializedName("list_id")
    var list_id: String?,

    /**
     * The mode that was actually applied — the one that was asked for, or the tenant's 'cart_merge_mode' default when the call named none.
     */
    @SerializedName("mode")
    var mode: OrderListCartMode?,

    /**
     * Positions left out because the catalogue no longer knows their article. Only ever non-empty when 'on_missing_article' is 'skip' — 'include' converts them anyway and 'fail' answers 400 instead.
     */
    @SerializedName("skipped")
    var skipped: List<OrderListSkippedPosition>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "added" to added as Any,
        "cart_created" to cart_created as Any,
        "cart_id" to cart_id as Any,
        "list_id" to list_id as Any,
        "mode" to mode?.value as Any,
        "skipped" to skipped?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListToCartResult(
            added = (map["added"] as? Number)?.toLong(),
            cart_created = map["cart_created"] as? Boolean,
            cart_id = map["cart_id"] as? String,
            list_id = map["list_id"] as? String,
            mode = OrderListCartMode.values().find { it.value == (map["mode"] as? String) } ?: null,
            skipped = (map["skipped"] as List<Map<String, Any>>).map { OrderListSkippedPosition.from(map = it) },
        )
    }
}