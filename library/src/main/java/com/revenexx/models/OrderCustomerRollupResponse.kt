package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrderCustomerRollupResponse(
    /**
     * The anchor the windows were measured from — echoed so a paging caller can pin it.
     */
    @SerializedName("as_of")
    var as_of: String?,

    /**
     * Where to resume, when `done` is false — the id of the last order this call read. Null once the scan finished. Send it back unchanged, together with the same as_of.
     */
    @SerializedName("cursor")
    var cursor: String?,

    /**
     * True = the whole set was scanned and this answer is complete. False = the scan hit its time budget: send `cursor` back to continue, and MERGE the parts (every number is additive, min for first_order_at, max for last_order_at, union for currencies).
     */
    @SerializedName("done")
    var done: Boolean?,

    /**
     * One row per organization that appeared on a counted order, sorted by id. A company with no counted order is absent — this answer does not carry zero rows.
     */
    @SerializedName("items")
    var items: List<OrderCustomerRollup>?,

    /**
     * How many order rows this call read, attributed or not. It is the cost of the call, and on a partial answer the size of the part.
     */
    @SerializedName("orders_scanned")
    var orders_scanned: Long?,

    /**
     * Orders read that carry no organization_id — private and guest orders. They are real revenue and are deliberately not attributed to anybody, so they appear here and in no row of items.
     */
    @SerializedName("orders_without_organization")
    var orders_without_organization: Long?,

    /**
     * How many rows `items` carries. On a partial answer this counts what THIS part saw, not the whole tenant.
     */
    @SerializedName("organizations")
    var organizations: Long?,

    /**
     * The statuses that were counted, echoed — the default set unless the request named its own.
     */
    @SerializedName("statuses")
    var statuses: List<String>?,

    /**
     * The rolling windows the *_30d / *_90d / *_365d numbers were measured over, in days. Echoed so a caller reads the numbers with the right labels instead of hard-coding three of them.
     */
    @SerializedName("windows")
    var windows: List<Long>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "as_of" to as_of as Any,
        "cursor" to cursor as Any,
        "done" to done as Any,
        "items" to items?.map { it.toMap() } as Any,
        "orders_scanned" to orders_scanned as Any,
        "orders_without_organization" to orders_without_organization as Any,
        "organizations" to organizations as Any,
        "statuses" to statuses as Any,
        "windows" to windows as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCustomerRollupResponse(
            as_of = map["as_of"] as? String,
            cursor = map["cursor"] as? String,
            done = map["done"] as? Boolean,
            items = (map["items"] as List<Map<String, Any>>).map { OrderCustomerRollup.from(map = it) },
            orders_scanned = (map["orders_scanned"] as? Number)?.toLong(),
            orders_without_organization = (map["orders_without_organization"] as? Number)?.toLong(),
            organizations = (map["organizations"] as? Number)?.toLong(),
            statuses = map["statuses"] as? List<String>,
            windows = map["windows"] as? List<Long>,
        )
    }
}