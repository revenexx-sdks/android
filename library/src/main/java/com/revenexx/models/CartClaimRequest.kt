package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CartClaimRequest(
    /**
     * Contact taking ownership.
     */
    @SerializedName("contact_id")
    val contact_id: String,

    /**
     * Guest session whose active carts are handed over.
     */
    @SerializedName("session_key")
    val session_key: String,

    /**
     * Merge the session carts into this cart instead of adopting them.
     */
    @SerializedName("target_cart_id")
    var target_cart_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact_id" to contact_id as Any,
        "session_key" to session_key as Any,
        "target_cart_id" to target_cart_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CartClaimRequest(
            contact_id = map["contact_id"] as String,
            session_key = map["session_key"] as String,
            target_cart_id = map["target_cart_id"] as? String,
        )
    }
}