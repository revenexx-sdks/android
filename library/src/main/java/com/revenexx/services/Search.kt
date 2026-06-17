package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxAPIRevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * Read-only full-text search over the tenant's installed collections.
 */
class Search(client: Client) : Service(client) {

    /**
     * The collections the tenant's installed apps have provisioned.
     *
     * @return [Any]
     */
    suspend fun searchListCollections(
    ): Any {
        val apiPath = "/v1/search/collections"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Full-text search within one collection using Typesense query parameters as the query string.
     *
     * @param collection Collection key (one the tenant has installed).
     * @param q Query text. Use `*` to match all.
     * @param queryBy Comma-separated fields to search.
     * @param filterBy Filter expression.
     * @param sortBy Sort expression.
     * @param page 1-based page.
     * @param perPage Hits per page (max 250).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun searchSearchDocumentsGet(
        collection: com.revenexx.enums.Collection,
        q: String? = null,
        queryBy: String? = null,
        filterBy: String? = null,
        sortBy: String? = null,
        page: Long? = null,
        perPage: Long? = null,
    ): Any {
        val apiPath = "/v1/search/collections/{collection}/documents/search"
            .replace("{collection}", collection.value)

        val apiParams = mutableMapOf<String, Any?>(
            "q" to q,
            "query_by" to queryBy,
            "filter_by" to filterBy,
            "sort_by" to sortBy,
            "page" to page,
            "per_page" to perPage,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Full-text search within one collection. The body holds Typesense search parameters.
     *
     * @param collection Collection key (one the tenant has installed).
     * @param facetBy Comma-separated fields to facet on.
     * @param filterBy Filter expression, e.g. `in_stock:=true`.
     * @param page 
     * @param perPage 
     * @param q Query text. Use `*` to match all.
     * @param queryBy Comma-separated fields to search.
     * @param sortBy Sort expression, e.g. `price:desc`.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun searchSearchDocuments(
        collection: com.revenexx.enums.Collection,
        facetBy: String? = null,
        filterBy: String? = null,
        page: Long? = null,
        perPage: Long? = null,
        q: String? = null,
        queryBy: String? = null,
        sortBy: String? = null,
    ): Any {
        val apiPath = "/v1/search/collections/{collection}/documents/search"
            .replace("{collection}", collection.value)

        val apiParams = mutableMapOf<String, Any?>(
            "facet_by" to facetBy,
            "filter_by" to filterBy,
            "page" to page,
            "per_page" to perPage,
            "q" to q,
            "query_by" to queryBy,
            "sort_by" to sortBy,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Fetch a single document by id from a collection the tenant has installed.
     *
     * @param collection Collection key (one the tenant has installed).
     * @param documentId Document id within the collection.
     * @return [Any]
     */
    suspend fun searchGetDocument(
        collection: com.revenexx.enums.Collection,
        documentId: String,
    ): Any {
        val apiPath = "/v1/search/collections/{collection}/documents/{documentId}"
            .replace("{collection}", collection.value)
            .replace("{documentId}", documentId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Run several searches in one request (the InstantSearch adapter uses this). Each entry names its collection.
     *
     * @param searches 
     * @return [Any]
     */
    suspend fun searchMultiSearch(
        searches: List<Any>,
    ): Any {
        val apiPath = "/v1/search/multi_search"

        val apiParams = mutableMapOf<String, Any?>(
            "searches" to searches,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


}