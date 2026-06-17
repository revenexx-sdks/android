package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Frameworks List
 */
data class FrameworkList(
    /**
     * List of frameworks.
     */
    @SerializedName("frameworks")
    val frameworks: List<Framework>,

    /**
     * Total number of frameworks that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "frameworks" to frameworks.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FrameworkList(
            frameworks = (map["frameworks"] as List<Map<String, Any>>).map { Framework.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}