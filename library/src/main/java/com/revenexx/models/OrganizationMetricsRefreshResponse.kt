package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrganizationMetricsRefreshResponse(
    /**
     * The instant the rolling windows are measured from. Send it back on every continuation — that is what stops the 30/90/365-day windows sliding while a multi-call refresh runs.
     */
    @SerializedName("as_of")
    var as_of: String?,

    /**
     * False if an insert had to fall back to row-at-a-time. A performance fact, not an error.
     */
    @SerializedName("batched")
    var batched: Boolean?,

    /**
     * Rollup calls made to the orders app — the cross-app cost of this pass.
     */
    @SerializedName("batches")
    var batches: Long?,

    /**
     * Where to resume: the id of the last organization this call processed. Send it back verbatim; null when the pass finished. No example is published — the value names a row in THIS tenant, and `cursor: "sample cursor"` reaches PostgREST as a malformed uuid and comes back as a 400 nobody can read.
     */
    @SerializedName("cursor")
    var cursor: String?,

    /**
     * False means the budget ran out with work left — POST again with the returned `cursor` AND `as_of`.
     */
    @SerializedName("done")
    var done: Boolean?,

    /**
     * Metrics rows created — organizations that had none yet.
     */
    @SerializedName("inserted")
    var inserted: Long?,

    /**
     * Orders the orders app counted while answering this call.
     */
    @SerializedName("orders_scanned")
    var orders_scanned: Long?,

    /**
     * Orders the orders app could not attribute to a company (B2C/guest). They belong to no organization and land in no metrics row.
     */
    @SerializedName("orders_without_organization")
    var orders_without_organization: Long?,

    /**
     * Organizations processed by THIS call.
     */
    @SerializedName("organizations")
    var organizations: Long?,

    /**
     * Rows that already said the same thing — no write was issued. A routine refresh is almost all of these.
     */
    @SerializedName("unchanged")
    var unchanged: Long?,

    /**
     * Metrics rows whose numbers actually changed.
     */
    @SerializedName("updated")
    var updated: Long?,

    /**
     * Of those, how many have at least one counted order.
     */
    @SerializedName("with_orders")
    var with_orders: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "as_of" to as_of as Any,
        "batched" to batched as Any,
        "batches" to batches as Any,
        "cursor" to cursor as Any,
        "done" to done as Any,
        "inserted" to inserted as Any,
        "orders_scanned" to orders_scanned as Any,
        "orders_without_organization" to orders_without_organization as Any,
        "organizations" to organizations as Any,
        "unchanged" to unchanged as Any,
        "updated" to updated as Any,
        "with_orders" to with_orders as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrganizationMetricsRefreshResponse(
            as_of = map["as_of"] as? String,
            batched = map["batched"] as? Boolean,
            batches = (map["batches"] as? Number)?.toLong(),
            cursor = map["cursor"] as? String,
            done = map["done"] as? Boolean,
            inserted = (map["inserted"] as? Number)?.toLong(),
            orders_scanned = (map["orders_scanned"] as? Number)?.toLong(),
            orders_without_organization = (map["orders_without_organization"] as? Number)?.toLong(),
            organizations = (map["organizations"] as? Number)?.toLong(),
            unchanged = (map["unchanged"] as? Number)?.toLong(),
            updated = (map["updated"] as? Number)?.toLong(),
            with_orders = (map["with_orders"] as? Number)?.toLong(),
        )
    }
}