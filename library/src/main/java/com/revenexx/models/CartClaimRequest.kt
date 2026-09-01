package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.CartMergeStrategy

/**
 * 
 */
data class CartClaimRequest(
    /**
     * The contact taking ownership. Every active cart of that session ends up with this contact — adopted as it stands, or folded into `target_cart_id`.
     */
    @SerializedName("contact_id")
    val contact_id: String,

    /**
     * The guest session whose active carts are handed over — the key the storefront keeps in its own session or cookie and has been sending on every anonymous call. This app neither issues nor parses it, so the example shows the shape of an opaque token and not a format anything enforces.
     */
    @SerializedName("session_key")
    val session_key: String,

    /**
     * Override the tenant's cart_merge_strategy for this call: 'merge' keeps the target cart's own lines, 'replace' clears them first. Omit to use the setting.
     */
    @SerializedName("strategy")
    var strategy: CartMergeStrategy?,

    /**
     * Merge the session carts into this cart instead of adopting them.
     */
    @SerializedName("target_cart_id")
    var target_cart_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact_id" to contact_id as Any,
        "session_key" to session_key as Any,
        "strategy" to strategy?.value as Any,
        "target_cart_id" to target_cart_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartClaimRequest(
            contact_id = map["contact_id"] as String,
            session_key = map["session_key"] as String,
            strategy = CartMergeStrategy.values().find { it.value == (map["strategy"] as? String) } ?: null,
            target_cart_id = map["target_cart_id"] as? String,
        )
    }
}