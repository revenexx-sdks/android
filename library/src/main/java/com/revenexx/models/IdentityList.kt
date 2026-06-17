package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Identities List
 */
data class IdentityList(
    /**
     * List of identities.
     */
    @SerializedName("identities")
    val identities: List<Identity>,

    /**
     * Total number of identities that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "identities" to identities.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = IdentityList(
            identities = (map["identities"] as List<Map<String, Any>>).map { Identity.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}