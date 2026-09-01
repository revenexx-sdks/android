package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Facet values and their counts for one faceted field.
 */
data class FacetCount<T>(
    /**
     * 
     */
    @SerializedName("counts")
    var counts: List<Any>?,

    /**
     * 
     */
    @SerializedName("field_name")
    var field_name: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "counts" to counts as Any,
        "field_name" to field_name as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            counts: List<Any>?,
            field_name: String?,
            data: Map<String, Any>
        ) = FacetCount<Map<String, Any>>(
            counts,
            field_name,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FacetCount<T>(
            counts = map["counts"] as? List<Any>,
            field_name = map["field_name"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}