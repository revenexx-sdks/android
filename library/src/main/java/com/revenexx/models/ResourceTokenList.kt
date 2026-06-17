package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Resource Tokens List
 */
data class ResourceTokenList(
    /**
     * List of tokens.
     */
    @SerializedName("tokens")
    val tokens: List<ResourceToken>,

    /**
     * Total number of tokens that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "tokens" to tokens.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ResourceTokenList(
            tokens = (map["tokens"] as List<Map<String, Any>>).map { ResourceToken.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}