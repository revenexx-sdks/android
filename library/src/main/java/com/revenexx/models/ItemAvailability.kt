package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ItemAvailability(
    /**
     * on_hand − reserved across the locations in scope: available-to-promise, and the number a storefront shows. It can be NEGATIVE once backorders have been reserved beyond stock — nothing floors it, because "sold more than we hold" is a real state a merchant needs to see.
     */
    @SerializedName("available")
    var available: Double?,

    /**
     * The per-location breakdown behind the summed figures — which place could actually ship it.
     */
    @SerializedName("locations")
    var locations: List<LocationAvailability>?,

    /**
     * Physically in stock, summed across the locations in scope (every enabled location, or the one `location_code` named). Promised units are included, so this is NOT what may be sold.
     */
    @SerializedName("on_hand")
    var on_hand: Double?,

    /**
     * True when the item is tracked and `available >= requested` at this moment. A SNAPSHOT, not a hold: nothing is set aside until POST /inventories/reserve, and two checkouts can both read true for the last unit.
     */
    @SerializedName("orderable")
    var orderable: Boolean?,

    /**
     * The product id as it was asked for, echoed. Null when the item was named by SKU.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The quantity the check was made against — the item's own `quantity`, or 1 when none was sent. `orderable` answers "can I have this many?", so it is only as strict as this number.
     */
    @SerializedName("requested")
    var requested: Double?,

    /**
     * Already promised to orders, summed across the same locations — the part of `on_hand` that is spoken for.
     */
    @SerializedName("reserved")
    var reserved: Double?,

    /**
     * The SKU as it was asked for, echoed. Null when the item was named by product id.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * False when this app has never seen the item: no stock row anywhere in scope. It is not an error and not a zero — the storefront decides whether an untracked item sells freely (a service, a made-to-order piece) or not at all. `on_hand`, `reserved` and `available` are 0 in that case, and `orderable` is false.
     */
    @SerializedName("tracked")
    var tracked: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "available" to available as Any,
        "locations" to locations?.map { it.toMap() } as Any,
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
            locations = (map["locations"] as List<Map<String, Any>>).map { LocationAvailability.from(map = it) },
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