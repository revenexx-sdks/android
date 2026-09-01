package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Where this page sits in the full result set. Rows beyond `limit` are not returned and are not lost — ask for the next page with `offset`.
 */
data class PricePage(
    /**
     * true when `offset + returned < total` — there is another page to fetch.
     */
    @SerializedName("hasMore")
    var hasMore: Boolean?,

    /**
     * Page size actually applied — the `limit` you sent, clamped to 1…200 (default 50).
     */
    @SerializedName("limit")
    var limit: Long?,

    /**
     * Row offset actually applied (default 0).
     */
    @SerializedName("offset")
    var offset: Long?,

    /**
     * Rows in `items` on this page.
     */
    @SerializedName("returned")
    var returned: Long?,

    /**
     * Rows matching the filter across all pages, not just this one.
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
        ) = PricePage(
            hasMore = map["hasMore"] as? Boolean,
            limit = (map["limit"] as? Number)?.toLong(),
            offset = (map["offset"] as? Number)?.toLong(),
            returned = (map["returned"] as? Number)?.toLong(),
            total = (map["total"] as? Number)?.toLong(),
        )
    }
}