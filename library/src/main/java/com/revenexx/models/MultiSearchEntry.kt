package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One search inside a federated request. `collection_name` is required on the gateway-trust path; with a `revx_` key it is optional and is forced to the key's own collection.
 */
data class MultiSearchEntry<T>(
    /**
     * A collection the tenant owns.
     */
    @SerializedName("collection_name")
    var collection_name: String?,

    /**
     * Comma-separated document fields to omit.
     */
    @SerializedName("exclude_fields")
    var exclude_fields: String?,

    /**
     * Comma-separated fields to facet on.
     */
    @SerializedName("facet_by")
    var facet_by: String?,

    /**
     * Filter expression, e.g. `in_stock:=true && price:<100`. ANDed with the tenant filter the proxy injects.
     */
    @SerializedName("filter_by")
    var filter_by: String?,

    /**
     * Comma-separated fields to group results by.
     */
    @SerializedName("group_by")
    var group_by: String?,

    /**
     * Comma-separated fields to highlight in full.
     */
    @SerializedName("highlight_full_fields")
    var highlight_full_fields: String?,

    /**
     * Comma-separated document fields to return.
     */
    @SerializedName("include_fields")
    var include_fields: String?,

    /**
     * Facet values to return per field.
     */
    @SerializedName("max_facet_values")
    var max_facet_values: Long?,

    /**
     * Typos tolerated per query token.
     */
    @SerializedName("num_typos")
    var num_typos: Long?,

    /**
     * 1-based page number.
     */
    @SerializedName("page")
    var page: Long?,

    /**
     * Hits per page.
     */
    @SerializedName("per_page")
    var per_page: Long?,

    /**
     * Whether the last token is a prefix; per-field when comma-separated.
     */
    @SerializedName("prefix")
    var prefix: String?,

    /**
     * Query text. Use `*` to match everything.
     */
    @SerializedName("q")
    var q: String?,

    /**
     * Comma-separated fields to search, in weight order.
     */
    @SerializedName("query_by")
    var query_by: String?,

    /**
     * Sort expression, e.g. `price:desc`.
     */
    @SerializedName("sort_by")
    var sort_by: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "collection_name" to collection_name as Any,
        "exclude_fields" to exclude_fields as Any,
        "facet_by" to facet_by as Any,
        "filter_by" to filter_by as Any,
        "group_by" to group_by as Any,
        "highlight_full_fields" to highlight_full_fields as Any,
        "include_fields" to include_fields as Any,
        "max_facet_values" to max_facet_values as Any,
        "num_typos" to num_typos as Any,
        "page" to page as Any,
        "per_page" to per_page as Any,
        "prefix" to prefix as Any,
        "q" to q as Any,
        "query_by" to query_by as Any,
        "sort_by" to sort_by as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            collection_name: String?,
            exclude_fields: String?,
            facet_by: String?,
            filter_by: String?,
            group_by: String?,
            highlight_full_fields: String?,
            include_fields: String?,
            max_facet_values: Long?,
            num_typos: Long?,
            page: Long?,
            per_page: Long?,
            prefix: String?,
            q: String?,
            query_by: String?,
            sort_by: String?,
            data: Map<String, Any>
        ) = MultiSearchEntry<Map<String, Any>>(
            collection_name,
            exclude_fields,
            facet_by,
            filter_by,
            group_by,
            highlight_full_fields,
            include_fields,
            max_facet_values,
            num_typos,
            page,
            per_page,
            prefix,
            q,
            query_by,
            sort_by,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = MultiSearchEntry<T>(
            collection_name = map["collection_name"] as? String,
            exclude_fields = map["exclude_fields"] as? String,
            facet_by = map["facet_by"] as? String,
            filter_by = map["filter_by"] as? String,
            group_by = map["group_by"] as? String,
            highlight_full_fields = map["highlight_full_fields"] as? String,
            include_fields = map["include_fields"] as? String,
            max_facet_values = (map["max_facet_values"] as? Number)?.toLong(),
            num_typos = (map["num_typos"] as? Number)?.toLong(),
            page = (map["page"] as? Number)?.toLong(),
            per_page = (map["per_page"] as? Number)?.toLong(),
            prefix = map["prefix"] as? String,
            q = map["q"] as? String,
            query_by = map["query_by"] as? String,
            sort_by = map["sort_by"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}