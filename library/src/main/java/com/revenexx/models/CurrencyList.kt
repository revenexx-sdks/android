package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Currencies List
 */
data class CurrencyList(
    /**
     * List of currencies.
     */
    @SerializedName("currencies")
    val currencies: List<Currency>,

    /**
     * Total number of currencies that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "currencies" to currencies.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CurrencyList(
            currencies = (map["currencies"] as List<Map<String, Any>>).map { Currency.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}