package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class OrganizationMetricsFreshness(
    /**
     * Companies with no metrics row yet. A rule reading revenue silently skips them, so this is the number to watch after an import.
     */
    @SerializedName("missing")
    var missing: Long?,

    /**
     * The OLDEST computed_at in the table — the floor, not an average. Null when there are no rows at all.
     */
    @SerializedName("oldest_computed_at")
    var oldest_computed_at: String?,

    /**
     * The anchor those oldest numbers were measured from.
     */
    @SerializedName("orders_as_of")
    var orders_as_of: String?,

    /**
     * Companies in this tenant.
     */
    @SerializedName("organizations")
    var organizations: Long?,

    /**
     * Metrics rows that exist — at most one per company.
     */
    @SerializedName("rows")
    var rows: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "missing" to missing as Any,
        "oldest_computed_at" to oldest_computed_at as Any,
        "orders_as_of" to orders_as_of as Any,
        "organizations" to organizations as Any,
        "rows" to rows as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrganizationMetricsFreshness(
            missing = (map["missing"] as? Number)?.toLong(),
            oldest_computed_at = map["oldest_computed_at"] as? String,
            orders_as_of = map["orders_as_of"] as? String,
            organizations = (map["organizations"] as? Number)?.toLong(),
            rows = (map["rows"] as? Number)?.toLong(),
        )
    }
}