package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ItemAvailability(
    /**
     * 
     */
    @SerializedName("available")
    var available: Double?,

    /**
     * 
     */
    @SerializedName("locations")
    var locations: List<Any>?,

    /**
     * 
     */
    @SerializedName("on_hand")
    var on_hand: Double?,

    /**
     * 
     */
    @SerializedName("orderable")
    var orderable: Boolean?,

    /**
     * 
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * 
     */
    @SerializedName("requested")
    var requested: Double?,

    /**
     * 
     */
    @SerializedName("reserved")
    var reserved: Double?,

    /**
     * 
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * false = unknown to inventory; the storefront decides whether untracked items sell freely.
     */
    @SerializedName("tracked")
    var tracked: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "available" to available as Any,
        "locations" to locations as Any,
        "on_hand" to on_hand as Any,
        "orderable" to orderable as Any,
        "product_id" to product_id as Any,
        "requested" to requested as Any,
        "reserved" to reserved as Any,
        "sku" to sku as Any,
        "tracked" to tracked as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ItemAvailability(
            available = (map["available"] as? Number)?.toDouble(),
            locations = map["locations"] as? List<Any>,
            on_hand = (map["on_hand"] as? Number)?.toDouble(),
            orderable = map["orderable"] as? Boolean,
            product_id = map["product_id"] as? String,
            requested = (map["requested"] as? Number)?.toDouble(),
            reserved = (map["reserved"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            tracked = map["tracked"] as? Boolean,
        )
    }
}