package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * `cart` is the cart as it now stands, totals already recomputed — the newly created one, or the target with the imported lines folded in.
 */
data class CartImport(
    /**
     * 
     */
    @SerializedName("cart")
    var cart: Cart?,

    /**
     * Lines read out of the payload. Identical product lines merge, so the cart may have gained fewer rows than this.
     */
    @SerializedName("imported_lines")
    var imported_lines: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cart" to cart?.toMap() as Any,
        "imported_lines" to imported_lines as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartImport(
            cart = Cart.from(map = map["cart"] as Map<String, Any>),
            imported_lines = (map["imported_lines"] as? Number)?.toLong(),
        )
    }
}