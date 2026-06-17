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
 * Short-lived file access tokens.
 */
class Tokens(client: Client) : Service(client) {

    /**
     * List all the tokens created for a specific file or bucket. You can use the query params to filter your results.
     *
     * @param bucketId Storage bucket unique ID. You can create a new storage bucket using the Storage service [server integration](https://appwrite.io/docs/server/storage#createBucket).
     * @param fileId File unique ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: expire
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.ResourceTokenList]
     */
    @JvmOverloads
    suspend fun tokensList(
        bucketId: String,
        fileId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.ResourceTokenList {
        val apiPath = "/v1/tokens/buckets/{bucketId}/files/{fileId}"
            .replace("{bucketId}", bucketId)
            .replace("{fileId}", fileId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ResourceTokenList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ResourceTokenList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ResourceTokenList::class.java,
            converter,
        )
    }


    /**
     * Create a new token. A token is linked to a file. Token can be passed as a request URL search parameter.
     *
     * @param bucketId Storage bucket unique ID. You can create a new storage bucket using the Storage service [server integration](https://appwrite.io/docs/server/storage#createBucket).
     * @param fileId File unique ID.
     * @param expire Token expiry date
     * @return [com.revenexx.models.ResourceToken]
     */
    @JvmOverloads
    suspend fun tokensCreateFileToken(
        bucketId: String,
        fileId: String,
        expire: String? = null,
    ): com.revenexx.models.ResourceToken {
        val apiPath = "/v1/tokens/buckets/{bucketId}/files/{fileId}"
            .replace("{bucketId}", bucketId)
            .replace("{fileId}", fileId)

        val apiParams = mutableMapOf<String, Any?>(
            "expire" to expire,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ResourceToken = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ResourceToken.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ResourceToken::class.java,
            converter,
        )
    }


    /**
     * Delete a token by its unique ID.
     *
     * @param tokenId Token ID.
     * @return [Any]
     */
    suspend fun tokensDelete(
        tokenId: String,
    ): Any {
        val apiPath = "/v1/tokens/{tokenId}"
            .replace("{tokenId}", tokenId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Get a token by its unique ID.
     *
     * @param tokenId Token ID.
     * @return [com.revenexx.models.ResourceToken]
     */
    suspend fun tokensGet(
        tokenId: String,
    ): com.revenexx.models.ResourceToken {
        val apiPath = "/v1/tokens/{tokenId}"
            .replace("{tokenId}", tokenId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ResourceToken = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ResourceToken.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ResourceToken::class.java,
            converter,
        )
    }


    /**
     * Update a token by its unique ID. Use this endpoint to update a token's expiry date.
     *
     * @param tokenId Token unique ID.
     * @param expire File token expiry date
     * @return [com.revenexx.models.ResourceToken]
     */
    @JvmOverloads
    suspend fun tokensUpdate(
        tokenId: String,
        expire: String? = null,
    ): com.revenexx.models.ResourceToken {
        val apiPath = "/v1/tokens/{tokenId}"
            .replace("{tokenId}", tokenId)

        val apiParams = mutableMapOf<String, Any?>(
            "expire" to expire,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ResourceToken = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ResourceToken.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ResourceToken::class.java,
            converter,
        )
    }


}