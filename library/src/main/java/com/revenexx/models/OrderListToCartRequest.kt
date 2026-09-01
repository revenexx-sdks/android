package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.OrderListCartMode

/**
 * Every field is optional: with an empty body the list goes into a NEW cart for its owner, on the tenant defaults.
 */
data class OrderListToCartRequest(
    /**
     * Add to this existing cart. Omit to create one for the list owner and make it their current cart.
     */
    @SerializedName("cart_id")
    var cart_id: String?,

    /**
     * ISO 4217 code for the cart and its lines. Omit to let the carts app decide.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 'append' adds the positions (the carts app merges a line by product and price, so quantities accumulate); 'replace' makes the list the cart's entire contents. Defaults to the tenant's 'cart_merge_mode' setting.
     */
    @SerializedName("mode")
    var mode: OrderListCartMode?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cart_id" to cart_id as Any,
        "currency" to currency as Any,
        "mode" to mode?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderListToCartRequest(
            cart_id = map["cart_id"] as? String,
            currency = map["currency"] as? String,
            mode = OrderListCartMode.values().find { it.value == (map["mode"] as? String) } ?: null,
        )
    }
}