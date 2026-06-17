package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Specifications List
 */
data class SpecificationList(
    /**
     * List of specifications.
     */
    @SerializedName("specifications")
    val specifications: List<Specification>,

    /**
     * Total number of specifications that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "specifications" to specifications.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SpecificationList(
            specifications = (map["specifications"] as List<Map<String, Any>>).map { Specification.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}