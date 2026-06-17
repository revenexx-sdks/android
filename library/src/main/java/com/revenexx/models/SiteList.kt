package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Sites List
 */
data class SiteList(
    /**
     * List of sites.
     */
    @SerializedName("sites")
    val sites: List<Site>,

    /**
     * Total number of sites that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "sites" to sites.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SiteList(
            sites = (map["sites"] as List<Map<String, Any>>).map { Site.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}