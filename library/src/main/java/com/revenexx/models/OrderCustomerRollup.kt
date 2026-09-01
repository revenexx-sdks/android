package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Additive order facts for one organization. Average order value is revenue_total / order_count.
 */
data class OrderCustomerRollup(
    /**
     * Every currency seen on the counted orders, sorted. MORE THAN ONE MEANS THE SUMS MIX CURRENCIES — nothing here converts, so a two-currency row's revenue_total is a sum of unlike numbers and should be shown per currency or not at all.
     */
    @SerializedName("currencies")
    var currencies: List<String>?,

    /**
     * When this company first ordered — placed_at where there is one, otherwise created_at. Null cannot happen on a row that exists, but the field is nullable because the columns behind it are.
     */
    @SerializedName("first_order_at")
    var first_order_at: String?,

    /**
     * When they last ordered. Together with as_of this is the recency a churn rule reads.
     */
    @SerializedName("last_order_at")
    var last_order_at: String?,

    /**
     * How many orders of this company were counted — orders in one of the counted statuses, over all time.
     */
    @SerializedName("order_count")
    var order_count: Long?,

    /**
     * Orders in the 30 days before as_of.
     */
    @SerializedName("order_count_30d")
    var order_count_30d: Long?,

    /**
     * Orders in the 365 days before as_of — the rolling year a "still active" rule usually asks about.
     */
    @SerializedName("order_count_365d")
    var order_count_365d: Long?,

    /**
     * Orders in the 90 days before as_of.
     */
    @SerializedName("order_count_90d")
    var order_count_90d: Long?,

    /**
     * The company these facts belong to — the id the customers app knows it by. Every row of the answer carries one; orders without an organization are counted in orders_without_organization instead.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Revenue in the 30 days before as_of.
     */
    @SerializedName("revenue_30d")
    var revenue_30d: Double?,

    /**
     * Revenue in the 365 days before as_of.
     */
    @SerializedName("revenue_365d")
    var revenue_365d: Double?,

    /**
     * Revenue in the 90 days before as_of.
     */
    @SerializedName("revenue_90d")
    var revenue_90d: Double?,

    /**
     * Sum of grand_total over the counted orders. Gross: it includes tax and shipping, because grand_total does.
     */
    @SerializedName("revenue_total")
    var revenue_total: Double?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currencies" to currencies as Any,
        "first_order_at" to first_order_at as Any,
        "last_order_at" to last_order_at as Any,
        "order_count" to order_count as Any,
        "order_count_30d" to order_count_30d as Any,
        "order_count_365d" to order_count_365d as Any,
        "order_count_90d" to order_count_90d as Any,
        "organization_id" to organization_id as Any,
        "revenue_30d" to revenue_30d as Any,
        "revenue_365d" to revenue_365d as Any,
        "revenue_90d" to revenue_90d as Any,
        "revenue_total" to revenue_total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCustomerRollup(
            currencies = map["currencies"] as? List<String>,
            first_order_at = map["first_order_at"] as? String,
            last_order_at = map["last_order_at"] as? String,
            order_count = (map["order_count"] as? Number)?.toLong(),
            order_count_30d = (map["order_count_30d"] as? Number)?.toLong(),
            order_count_365d = (map["order_count_365d"] as? Number)?.toLong(),
            order_count_90d = (map["order_count_90d"] as? Number)?.toLong(),
            organization_id = map["organization_id"] as? String,
            revenue_30d = (map["revenue_30d"] as? Number)?.toDouble(),
            revenue_365d = (map["revenue_365d"] as? Number)?.toDouble(),
            revenue_90d = (map["revenue_90d"] as? Number)?.toDouble(),
            revenue_total = (map["revenue_total"] as? Number)?.toDouble(),
        )
    }
}