package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Where this answer sits in the whole result set.
 */
data class OrderPage(
    /**
     * Whether another page exists after this one (offset + returned < total). The one field a "load more" button should read.
     */
    @SerializedName("hasMore")
    var hasMore: Boolean?,

    /**
     * The page size that was applied. A requested limit above 200 is CLAMPED to 200 rather than refused, so this is the number to believe, not the one you sent.
     */
    @SerializedName("limit")
    var limit: Long?,

    /**
     * The row offset that was applied.
     */
    @SerializedName("offset")
    var offset: Long?,

    /**
     * How many rows are in `items` right here — less than `limit` on the last page.
     */
    @SerializedName("returned")
    var returned: Long?,

    /**
     * How many rows match the filter in total, ignoring limit and offset. This is what a page count is computed from.
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
        ) = OrderPage(
            hasMore = map["hasMore"] as? Boolean,
            limit = (map["limit"] as? Number)?.toLong(),
            offset = (map["offset"] as? Number)?.toLong(),
            returned = (map["returned"] as? Number)?.toLong(),
            total = (map["total"] as? Number)?.toLong(),
        )
    }
}