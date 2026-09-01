package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * Read-only full-text search over the tenant's installed collections.
 */
class Search(client: Client) : Service(client) {

    /**
     * The collections the tenant's installed apps have provisioned. Available on the API-gateway-trust path only — a `revx_` key authorises a single collection, so discovery is a gateway concern and a key-authenticated caller gets 403.
     *
     * @return [com.revenexx.models.Error]
     */
    suspend fun searchListCollections(
    ): com.revenexx.models.Error {
        val apiPath = "/v1/search/collections"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Returns the Typesense collection definition (fields, defaults, document count). Requires the `collections:read` action.
     *
     * @param collection A collection the tenant owns (see `GET /api/v1/collections`). Resolved to its namespaced Typesense name server-side; a collection the tenant does not own is a 404.
     * @return [com.revenexx.models.Error]
     */
    suspend fun searchGetCollection(
        collection: com.revenexx.enums.Collection,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/search/collections/{collection}"
            .replace("{collection}", collection.value)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Full-text search within one collection. Typesense search parameters are passed through verbatim as the query string, so parameters not listed here still reach Typesense. Requires the `documents:search` action.
     *
     * @param collection A collection the tenant owns (see `GET /api/v1/collections`). Resolved to its namespaced Typesense name server-side; a collection the tenant does not own is a 404.
     * @param q Query text. Use `*` to match everything.
     * @param queryBy Comma-separated fields to search, in weight order.
     * @param filterBy Filter expression, e.g. `in_stock:=true && price:<100`. ANDed with the tenant filter the proxy injects.
     * @param sortBy Sort expression, e.g. `price:desc`.
     * @param facetBy Comma-separated fields to facet on.
     * @param maxFacetValues Facet values to return per field.
     * @param groupBy Comma-separated fields to group results by.
     * @param includeFields Comma-separated document fields to return.
     * @param excludeFields Comma-separated document fields to omit.
     * @param highlightFullFields Comma-separated fields to highlight in full.
     * @param numTypos Typos tolerated per query token.
     * @param prefix Whether the last token is a prefix; per-field when comma-separated.
     * @param page 1-based page number.
     * @param perPage Hits per page.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun searchSearchDocumentsGet(
        collection: com.revenexx.enums.Collection,
        q: String? = null,
        queryBy: String? = null,
        filterBy: String? = null,
        sortBy: String? = null,
        facetBy: String? = null,
        maxFacetValues: Long? = null,
        groupBy: String? = null,
        includeFields: String? = null,
        excludeFields: String? = null,
        highlightFullFields: String? = null,
        numTypos: Long? = null,
        prefix: String? = null,
        page: Long? = null,
        perPage: Long? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/search/collections/{collection}/documents/search"
            .replace("{collection}", collection.value)

        val apiParams = mutableMapOf<String, Any?>(
            "q" to q,
            "query_by" to queryBy,
            "filter_by" to filterBy,
            "sort_by" to sortBy,
            "facet_by" to facetBy,
            "max_facet_values" to maxFacetValues,
            "group_by" to groupBy,
            "include_fields" to includeFields,
            "exclude_fields" to excludeFields,
            "highlight_full_fields" to highlightFullFields,
            "num_typos" to numTypos,
            "prefix" to prefix,
            "page" to page,
            "per_page" to perPage,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Full-text search within one collection, with the Typesense search parameters in the body. Requires the `documents:search` action.
     *
     * @param collection A collection the tenant owns (see `GET /api/v1/collections`). Resolved to its namespaced Typesense name server-side; a collection the tenant does not own is a 404.
     * @param excludeFields Comma-separated document fields to omit.
     * @param facetBy Comma-separated fields to facet on.
     * @param filterBy Filter expression, e.g. `in_stock:=true && price:<100`. ANDed with the tenant filter the proxy injects.
     * @param groupBy Comma-separated fields to group results by.
     * @param highlightFullFields Comma-separated fields to highlight in full.
     * @param includeFields Comma-separated document fields to return.
     * @param maxFacetValues Facet values to return per field.
     * @param numTypos Typos tolerated per query token.
     * @param page 1-based page number.
     * @param perPage Hits per page.
     * @param prefix Whether the last token is a prefix; per-field when comma-separated.
     * @param q Query text. Use `*` to match everything.
     * @param queryBy Comma-separated fields to search, in weight order.
     * @param sortBy Sort expression, e.g. `price:desc`.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun searchSearchDocuments(
        collection: com.revenexx.enums.Collection,
        excludeFields: String? = null,
        facetBy: String? = null,
        filterBy: String? = null,
        groupBy: String? = null,
        highlightFullFields: String? = null,
        includeFields: String? = null,
        maxFacetValues: Long? = null,
        numTypos: Long? = null,
        page: Long? = null,
        perPage: Long? = null,
        prefix: String? = null,
        q: String? = null,
        queryBy: String? = null,
        sortBy: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/search/collections/{collection}/documents/search"
            .replace("{collection}", collection.value)

        val apiParams = mutableMapOf<String, Any?>(
            "exclude_fields" to excludeFields,
            "facet_by" to facetBy,
            "filter_by" to filterBy,
            "group_by" to groupBy,
            "highlight_full_fields" to highlightFullFields,
            "include_fields" to includeFields,
            "max_facet_values" to maxFacetValues,
            "num_typos" to numTypos,
            "page" to page,
            "per_page" to perPage,
            "prefix" to prefix,
            "q" to q,
            "query_by" to queryBy,
            "sort_by" to sortBy,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Fetch a single document by id. The document shape is the collection's own schema, so it is described as a free-form object. Requires the `documents:get` action.
     *
     * @param collection A collection the tenant owns (see `GET /api/v1/collections`). Resolved to its namespaced Typesense name server-side; a collection the tenant does not own is a 404.
     * @param documentId The document's `id` within the collection.
     * @return [com.revenexx.models.Error]
     */
    suspend fun searchGetDocument(
        collection: com.revenexx.enums.Collection,
        documentId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/search/collections/{collection}/documents/{documentId}"
            .replace("{collection}", collection.value)
            .replace("{documentId}", documentId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Idempotent, and bounded by the tenant's own configuration: it can add
     * no field for an attribute the tenant has not marked `is_filterable`,
     * and drops only fields whose attribute it has itself un-marked. A run
     * that changes nothing makes zero calls to Typesense.
     * 
     * Body (optional) narrows the sweep to one app:
     * 
     *     {"vendor": "revenexx", "app": "products"}
     * 
     * Omitted, every app the tenant has installed is swept. Apps outside the
     * facet-sync allowlist are included in the response with
     * `skipped: app_not_enabled` rather than silently dropped — a caller
     * asking for an app that cannot have facets deserves to be told so.
     * 
     * The response shape below is DECLARED rather than inferred. Its entries
     * are built by spreading AttributeFacetSyncer::syncForCollection()'s
     * summary, and the generator cannot see through an array spread: left to
     * itself it emits an unnamed property and a null in `required`, which
     * Spectral rejects as `"1" property must be string`.
     * AppController::resyncFacets() carries the same declaration for the same
     * reason — keep both in step with syncForApp()'s return type.
     *
     * @param app 
     * @param vendor 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun gatewayFacetResync(
        app: String? = null,
        vendor: String? = null,
    ): Any {
        val apiPath = "/v1/search/facets/resync"

        val apiParams = mutableMapOf<String, Any?>(
            "app" to app,
            "vendor" to vendor,
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
     * Run several searches in one round trip — the endpoint the typesense-js `multiSearch` helper and the InstantSearch adapter use for every query. On the gateway-trust path each entry must name a collection the tenant owns. With a `revx_` key `collection_name` is optional and is forced to the key's own collection. Requires the `documents:search` action.
     *
     * @param searches The searches to run, in order. Must not be empty.
     * @return [com.revenexx.models.Error]
     */
    suspend fun searchMultiSearch(
        searches: List<com.revenexx.models.MultiSearchEntry<Any>>,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/search/multi_search"

        val apiParams = mutableMapOf<String, Any?>(
            "searches" to searches,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}