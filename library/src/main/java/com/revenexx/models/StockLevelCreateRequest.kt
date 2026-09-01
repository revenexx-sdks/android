package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A stock row tracks an item: 'product_id' or 'sku'.
 */
data class StockLevelCreateRequest(
    /**
     * The location this balance is held at — a `locations` row of this tenant (GET /inventories/locations). There is ONE stock row per (location, item): the same SKU in three warehouses is three rows, and what a storefront shows is their sum (POST /inventories/availability). Deleting the location deletes its stock rows with it. It has to exist already (GET /inventories/locations); an id no location carries is answered 400 by the foreign key, not 404.
     */
    @SerializedName("location_id")
    val location_id: String,

    /**
     * Free-form data the tenant keeps on this stock row, and ONE key this app reads: `backorder`. A literal boolean `true` there opts this item into backorders while `backorder_policy` is 'allow_per_sku' — anything else, including the string "true", does not, and the reservation is refused with 422. That is how a merchant backorders the supplier-stocked half of a catalogue without promising the rest.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * The product this row tracks, as the products app knows it. A row tracks a `product_id` or a `sku` — the database insists on at least one (CHECK `product_id is not null or sku is not null`) — and matching is exact: a row keyed by SKU is not found by product id.
     */
    @SerializedName("product_id")
    var product_id: String?,

    /**
     * The available quantity at or below which this row belongs on the replenishment worklist (GET /inventories/reorder-alerts). Null falls back to the `reorder_point_default` setting, so replenishment works without a threshold per SKU; 0 never alerts, which is how one row opts out.
     */
    @SerializedName("reorder_point")
    var reorder_point: Double?,

    /**
     * The article number this row tracks when there is no product id, which is the normal case for an ERP-stocked catalogue. Exact match, and the identity every stock call may use instead of a uuid.
     */
    @SerializedName("sku")
    var sku: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "location_id" to location_id as Any,
        "metadata" to metadata as Any,
        "product_id" to product_id as Any,
        "reorder_point" to reorder_point as Any,
        "sku" to sku as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = StockLevelCreateRequest(
            location_id = map["location_id"] as String,
            metadata = map["metadata"] as? Any,
            product_id = map["product_id"] as? String,
            reorder_point = (map["reorder_point"] as? Number)?.toDouble(),
            sku = map["sku"] as? String,
        )
    }
}