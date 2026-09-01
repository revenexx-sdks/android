package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The list as it now stands, plus whoever lost the flag.
 */
data class PriceListMakeDefaultResponse(
    /**
     * Codes of the lists that lost the flag — empty when this list already held it, which is what makes a repeated call free.
     */
    @SerializedName("demoted")
    var demoted: List<String>?,

    /**
     * A price list: one currency, one tax basis, one validity window, one buyer scope — and the entries that price items in it. Which list wins for a given buyer is decided by scope first, then priority, then the default flag; see prices.resolve.
     */
    @SerializedName("price_list")
    var price_list: PriceList?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "demoted" to demoted as Any,
        "price_list" to price_list?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceListMakeDefaultResponse(
            demoted = map["demoted"] as? List<String>,
            price_list = PriceList.from(map = map["price_list"] as Map<String, Any>),
        )
    }
}