package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ShippingRateTiersReplaceRequest(
    /**
     * The complete new tier set (set semantics) — positions are derived from the array order. An empty array clears the matrix, and a matrix method with no tiers quotes nothing.
     */
    @SerializedName("tiers")
    val tiers: List<ShippingRateTierReplaceItem>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "tiers" to tiers.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ShippingRateTiersReplaceRequest(
            tiers = (map["tiers"] as List<Map<String, Any>>).map { ShippingRateTierReplaceItem.from(map = it) },
        )
    }
}