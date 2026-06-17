package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Locale codes list
 */
data class LocaleCodeList(
    /**
     * List of localeCodes.
     */
    @SerializedName("localeCodes")
    val localeCodes: List<LocaleCode>,

    /**
     * Total number of localeCodes that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "localeCodes" to localeCodes.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LocaleCodeList(
            localeCodes = (map["localeCodes"] as List<Map<String, Any>>).map { LocaleCode.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}