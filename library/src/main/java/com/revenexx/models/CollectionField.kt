package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One field in a collection schema.
 */
data class CollectionField<T>(
    /**
     * Whether the field can be faceted on.
     */
    @SerializedName("facet")
    var facet: Boolean?,

    /**
     * 
     */
    @SerializedName("index")
    var index: Boolean?,

    /**
     * 
     */
    @SerializedName("name")
    val name: String,

    /**
     * 
     */
    @SerializedName("optional")
    var optional: Boolean?,

    /**
     * 
     */
    @SerializedName("sort")
    var sort: Boolean?,

    /**
     * Typesense field type, e.g. `string`, `int64`, `string[]`, `object`.
     */
    @SerializedName("type")
    val type: String,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "facet" to facet as Any,
        "index" to index as Any,
        "name" to name as Any,
        "optional" to optional as Any,
        "sort" to sort as Any,
        "type" to type as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            facet: Boolean?,
            index: Boolean?,
            name: String,
            optional: Boolean?,
            sort: Boolean?,
            type: String,
            data: Map<String, Any>
        ) = CollectionField<Map<String, Any>>(
            facet,
            index,
            name,
            optional,
            sort,
            type,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = CollectionField<T>(
            facet = map["facet"] as? Boolean,
            index = map["index"] as? Boolean,
            name = map["name"] as String,
            optional = map["optional"] as? Boolean,
            sort = map["sort"] as? Boolean,
            type = map["type"] as String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}