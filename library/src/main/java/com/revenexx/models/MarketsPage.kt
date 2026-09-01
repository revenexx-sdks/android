package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Where in the result set this answer sits. `limit` and `offset` are the values that were APPLIED, not the ones that were asked for — the data plane clamps rather than refuses, so an out-of-range or unparseable value comes back corrected here instead of as a 400.
 */
data class MarketsPage(
    /**
     * True when `offset + returned < total`, i.e. another page exists. Cheaper to branch on than comparing the three numbers yourself.
     */
    @SerializedName("hasMore")
    var hasMore: Boolean?,

    /**
     * Page size actually applied. A request over 200 is clamped to 200, one under 1 (or one that is not a number) to the 50-row default.
     */
    @SerializedName("limit")
    var limit: Long?,

    /**
     * Row offset actually applied. A negative offset is clamped to 0.
     */
    @SerializedName("offset")
    var offset: Long?,

    /**
     * Rows in `items` on this page. Lower than `limit` on the last page.
     */
    @SerializedName("returned")
    var returned: Long?,

    /**
     * Rows matching the filter across ALL pages, ignoring limit and offset — the number to paginate against.
     */
    @SerializedName("total")
    var total: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "hasMore" to hasMore as Any,
        "limit" to limit as Any,
        "offset" to offset as Any,
        "returned" to returned as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketsPage(
            hasMore = map["hasMore"] as? Boolean,
            limit = (map["limit"] as? Number)?.toLong(),
            offset = (map["offset"] as? Number)?.toLong(),
            returned = (map["returned"] as? Number)?.toLong(),
            total = (map["total"] as? Number)?.toLong(),
        )
    }
}