package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Provider list
 */
data class ProviderList(
    /**
     * List of providers.
     */
    @SerializedName("providers")
    val providers: List<Provider>,

    /**
     * Total number of providers that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "providers" to providers.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProviderList(
            providers = (map["providers"] as List<Map<String, Any>>).map { Provider.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}