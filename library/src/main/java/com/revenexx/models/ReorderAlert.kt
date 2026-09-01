package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ReorderPointSource

/**
 * 
 */
data class ReorderAlert(
    /**
     * on_hand − reserved: the figure compared against the reorder point. Alerting on AVAILABLE rather than on_hand is the point of this list — a shelf that looks full but is entirely sold is exactly the row a buyer must see.
     */
    @SerializedName("available")
    var available: Double?,

    /**
     * That location's code, resolved for the reader so no second call is needed. Null if the location row could not be read.
     */
    @SerializedName("location_code")
    var location_code: String?,

    /**
     * Whether that location is enabled. A DISABLED location still alerts — its stock is invisible to availability, but the goods are real and somebody has to decide. Null if the location row could not be read.
     */
    @SerializedName("location_enabled")
    var location_enabled: Boolean?,

    /**
     * The location holding it.
     */
    @SerializedName("location_id")
    var location_id: String?,

    /**
     * What is physically there right now, promised units included.
     */
    @SerializedName("on_hand")
    var on_hand: Double?,

    /**
     * The product this row tracks, null when it is tracked by SKU.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The threshold that was applied to this row — its own, or the tenant default.
     */
    @SerializedName("reorder_point")
    var reorder_point: Double?,

    /**
     * 'row' — the stock row's own threshold. 'default' — the reorder_point_default setting.
     */
    @SerializedName("reorder_point_source")
    var reorder_point_source: ReorderPointSource?,

    /**
     * How much of it is already promised to orders.
     */
    @SerializedName("reserved")
    var reserved: Double?,

    /**
     * How far below the point this row has fallen. The list is sorted by it, worst first.
     */
    @SerializedName("shortfall")
    var shortfall: Double?,

    /**
     * The article number this row tracks, null when it is tracked by product id.
     */
    @SerializedName("sku")
    var sku: String?,

    /**
     * The stock row that is low — the id to correct or receive against (POST /inventories/stock/{id}/adjust).
     */
    @SerializedName("stock_level_id")
    var stock_level_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "available" to available as Any,
        "location_code" to location_code as Any,
        "location_enabled" to location_enabled as Any,
        "location_id" to location_id as Any,
        "on_hand" to on_hand as Any,
        "product_id" to product_id as Any,
        "reorder_point" to reorder_point as Any,
        "reorder_point_source" to reorder_point_source?.value as Any,
        "reserved" to reserved as Any,
        "shortfall" to shortfall as Any,
        "sku" to sku as Any,
        "stock_level_id" to stock_level_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ReorderAlert(
            available = (map["available"] as? Number)?.toDouble(),
            location_code = map["location_code"] as? String,
            location_enabled = map["location_enabled"] as? Boolean,
            location_id = map["location_id"] as? String,
            on_hand = (map["on_hand"] as? Number)?.toDouble(),
            product_id = map["product_id"] as? String,
            reorder_point = (map["reorder_point"] as? Number)?.toDouble(),
            reorder_point_source = ReorderPointSource.values().find { it.value == (map["reorder_point_source"] as? String) } ?: null,
            reserved = (map["reserved"] as? Number)?.toDouble(),
            shortfall = (map["shortfall"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
            stock_level_id = map["stock_level_id"] as? String,
        )
    }
}