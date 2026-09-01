package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A Typesense collection definition, passed through from Typesense. `name` is rewritten back to the tenant's public collection name.
 */
data class Collection<T>(
    /**
     * 
     */
    @SerializedName("default_sorting_field")
    var default_sorting_field: String?,

    /**
     * 
     */
    @SerializedName("enable_nested_fields")
    var enable_nested_fields: Boolean?,

    /**
     * 
     */
    @SerializedName("fields")
    var fields: List<CollectionField<T>>?,

    /**
     * The public collection name.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Documents currently indexed.
     */
    @SerializedName("num_documents")
    var num_documents: Long?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "default_sorting_field" to default_sorting_field as Any,
        "enable_nested_fields" to enable_nested_fields as Any,
        "fields" to fields?.map { it.toMap() } as Any,
        "name" to name as Any,
        "num_documents" to num_documents as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            default_sorting_field: String?,
            enable_nested_fields: Boolean?,
            fields: List<CollectionField<Map<String, Any>>>?,
            name: String?,
            num_documents: Long?,
            data: Map<String, Any>
        ) = Collection<Map<String, Any>>(
            default_sorting_field,
            enable_nested_fields,
            fields,
            name,
            num_documents,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = Collection<T>(
            default_sorting_field = map["default_sorting_field"] as? String,
            enable_nested_fields = map["enable_nested_fields"] as? Boolean,
            fields = (map["fields"] as List<Map<String, Any>>).map { CollectionField.from(map = it, nestedType) },
            name = map["name"] as? String,
            num_documents = (map["num_documents"] as? Number)?.toLong(),
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}