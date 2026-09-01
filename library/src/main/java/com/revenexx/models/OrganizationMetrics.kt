package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What an organization has BOUGHT, materialized from the orders app. One row per organization — including all-zero rows for companies that never ordered, so a 'never bought anything' rule has something to match.
 */
data class OrganizationMetrics(
    /**
     * revenue_total / order_count, computed here from the sums rather than averaged upstream. Zero when there are no orders.
     */
    @SerializedName("avg_order_value")
    var avg_order_value: Double?,

    /**
     * revenue_365d / order_count_365d. Zero when there were none in the window.
     */
    @SerializedName("avg_order_value_365d")
    var avg_order_value_365d: Double?,

    /**
     * When this row was last written. The projection is materialized, so this is how stale the numbers are.
     */
    @SerializedName("computed_at")
    var computed_at: String?,

    /**
     * When the projection row first appeared.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The single ISO 4217 currency all counted orders were in. NULL when there were none, and also when there were several — read `currency_mixed` to tell those two apart.
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * True when this company ordered in more than one currency. The sums are still stored (dropping money is worse), but they are not comparable against a threshold, and a rule reading revenue should say so.
     */
    @SerializedName("currency_mixed")
    var currency_mixed: Boolean?,

    /**
     * When this company first ordered. Null if it never has — that is what makes it usable as "is this a customer at all?".
     */
    @SerializedName("first_order_at")
    var first_order_at: String?,

    /**
     * Primary key of the projection row.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * When this company last ordered. Null if it never has, which is why the virtual `days_since_last_order` rule field never matches those companies: use `last_order_at is_empty` for them.
     */
    @SerializedName("last_order_at")
    var last_order_at: String?,

    /**
     * Orders ever counted for this company.
     */
    @SerializedName("order_count")
    var order_count: Long?,

    /**
     * Orders in the 30 days before `orders_as_of`. A rolling window, not a calendar month.
     */
    @SerializedName("order_count_30d")
    var order_count_30d: Long?,

    /**
     * Orders in the 365 days before `orders_as_of`.
     */
    @SerializedName("order_count_365d")
    var order_count_365d: Long?,

    /**
     * Orders in the 90 days before `orders_as_of`.
     */
    @SerializedName("order_count_90d")
    var order_count_90d: Long?,

    /**
     * The instant the rolling windows were measured from. Pinned across a chunked refresh, so a multi-call pass cannot let the windows slide underneath it.
     */
    @SerializedName("orders_as_of")
    var orders_as_of: String?,

    /**
     * The company these numbers describe. One row per organization, and rows exist for companies that never ordered — all zeros rather than missing, so a "never bought" rule matches something.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Revenue in the 30 days before `orders_as_of`.
     */
    @SerializedName("revenue_30d")
    var revenue_30d: Double?,

    /**
     * Revenue in the 365 days before `orders_as_of`. The usual "how big is this customer" number, and the one a key-account rule should read.
     */
    @SerializedName("revenue_365d")
    var revenue_365d: Double?,

    /**
     * Revenue in the 90 days before `orders_as_of`.
     */
    @SerializedName("revenue_90d")
    var revenue_90d: Double?,

    /**
     * Revenue ever counted, in `currency`. Which orders count is the orders app's decision, not this app's.
     */
    @SerializedName("revenue_total")
    var revenue_total: Double?,

    /**
     * The tenant this row belongs to — the store slug, not an id. Set by the platform from the authenticated context, never by a caller; a write that carries it is ignored, and no request can read another tenant's rows by sending a different one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * When the row last changed. Unchanged numbers are not rewritten, so this can lag `computed_at`.
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "avg_order_value" to avg_order_value as Any,
        "avg_order_value_365d" to avg_order_value_365d as Any,
        "computed_at" to computed_at as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "currency_mixed" to currency_mixed as Any,
        "first_order_at" to first_order_at as Any,
        "id" to id as Any,
        "last_order_at" to last_order_at as Any,
        "order_count" to order_count as Any,
        "order_count_30d" to order_count_30d as Any,
        "order_count_365d" to order_count_365d as Any,
        "order_count_90d" to order_count_90d as Any,
        "orders_as_of" to orders_as_of as Any,
        "organization_id" to organization_id as Any,
        "revenue_30d" to revenue_30d as Any,
        "revenue_365d" to revenue_365d as Any,
        "revenue_90d" to revenue_90d as Any,
        "revenue_total" to revenue_total as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrganizationMetrics(
            avg_order_value = (map["avg_order_value"] as? Number)?.toDouble(),
            avg_order_value_365d = (map["avg_order_value_365d"] as? Number)?.toDouble(),
            computed_at = map["computed_at"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            currency_mixed = map["currency_mixed"] as? Boolean,
            first_order_at = map["first_order_at"] as? String,
            id = map["id"] as? String,
            last_order_at = map["last_order_at"] as? String,
            order_count = (map["order_count"] as? Number)?.toLong(),
            order_count_30d = (map["order_count_30d"] as? Number)?.toLong(),
            order_count_365d = (map["order_count_365d"] as? Number)?.toLong(),
            order_count_90d = (map["order_count_90d"] as? Number)?.toLong(),
            orders_as_of = map["orders_as_of"] as? String,
            organization_id = map["organization_id"] as? String,
            revenue_30d = (map["revenue_30d"] as? Number)?.toDouble(),
            revenue_365d = (map["revenue_365d"] as? Number)?.toDouble(),
            revenue_90d = (map["revenue_90d"] as? Number)?.toDouble(),
            revenue_total = (map["revenue_total"] as? Number)?.toDouble(),
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}