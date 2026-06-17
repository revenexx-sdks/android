package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Countries List
 */
data class CountryList(
    /**
     * List of countries.
     */
    @SerializedName("countries")
    val countries: List<Country>,

    /**
     * Total number of countries that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "countries" to countries.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CountryList(
            countries = (map["countries"] as List<Map<String, Any>>).map { Country.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}