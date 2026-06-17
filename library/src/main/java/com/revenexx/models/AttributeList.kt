package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Attributes List
 */
data class AttributeList(
    /**
     * List of attributes.
     */
    @SerializedName("attributes")
    val attributes: List<Any>,

    /**
     * Total number of attributes in the given collection.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attributes" to attributes as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AttributeList(
            attributes = map["attributes"] as List<Any>,
            total = (map["total"] as Number).toLong(),
        )
    }
}