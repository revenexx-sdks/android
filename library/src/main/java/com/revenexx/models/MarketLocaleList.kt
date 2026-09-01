package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One page of locales of a market, the page it sits on, and the filters that produced it.
 */
data class MarketLocaleList(
    /**
     * The exact-column filters this call applied, echoed back. Every value is the raw query string, never the column's own type: `?is_default=true` comes back as `"true"`. A `?column=value` naming a column this entity does not have is DROPPED rather than refused — the call answers 200 with the unfiltered list, and the key missing from here is the only way to find out.
     */
    @SerializedName("filter")
    var filter: MarketLocaleFilter?,

    /**
     * The locales of a market on this page, in `order` — by `position` ascending unless the call asked otherwise.
     */
    @SerializedName("items")
    var items: List<MarketLocale>?,

    /**
     * Where in the result set this answer sits. `limit` and `offset` are the values that were APPLIED, not the ones that were asked for — the data plane clamps rather than refuses, so an out-of-range or unparseable value comes back corrected here instead of as a 400.
     */
    @SerializedName("page")
    var page: MarketsPage?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "filter" to filter?.toMap() as Any,
        "items" to items?.map { it.toMap() } as Any,
        "page" to page?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketLocaleList(
            filter = MarketLocaleFilter.from(map = map["filter"] as Map<String, Any>),
            items = (map["items"] as List<Map<String, Any>>).map { MarketLocale.from(map = it) },
            page = MarketsPage.from(map = map["page"] as Map<String, Any>),
        )
    }
}