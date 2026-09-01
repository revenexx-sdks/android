package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Where this page sits in the result set. Everything needed to fetch the next one is here, so a client never has to guess whether it has seen everything.
 */
data class FormsPage(
    /**
     * True while `offset + returned < total`: another page follows, at `offset + returned`.
     */
    @SerializedName("hasMore")
    var hasMore: Boolean?,

    /**
     * The page size that was applied — the `limit` parameter after clamping to 1…200, or 50 when none was given.
     */
    @SerializedName("limit")
    var limit: Long?,

    /**
     * How many matching rows were skipped before this page.
     */
    @SerializedName("offset")
    var offset: Long?,

    /**
     * How many rows are in `items` — below `limit` exactly on the last page.
     */
    @SerializedName("returned")
    var returned: Long?,

    /**
     * How many rows match the filter in total, ignoring the page. This is the number to show a merchant; `returned` is only what fitted.
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
        ) = FormsPage(
            hasMore = map["hasMore"] as? Boolean,
            limit = (map["limit"] as? Number)?.toLong(),
            offset = (map["offset"] as? Number)?.toLong(),
            returned = (map["returned"] as? Number)?.toLong(),
            total = (map["total"] as? Number)?.toLong(),
        )
    }
}