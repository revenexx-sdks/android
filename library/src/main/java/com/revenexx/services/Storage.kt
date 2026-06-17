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
 * Media storage: assets, folders, quotas (revenexx storage service).
 */
class Storage(client: Client) : Service(client) {

    /**
     * 
     *
     * @param search 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun assetIndex(
        search: String? = null,
    ): Any {
        val apiPath = "/v1/storage/assets"

        val apiParams = mutableMapOf<String, Any?>(
            "search" to search,
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
     * 
     *
     * @param file 
     * @param altText 
     * @param description 
     * @param displayName 
     * @param folderId 
     * @param keepArchive 
     * @param tags 
     * @param unpack Archives only: unpack the members after upload (see AssetController).
     * @param visibility 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun assetStore(
        file: String,
        altText: String? = null,
        description: String? = null,
        displayName: String? = null,
        folderId: String? = null,
        keepArchive: Boolean? = null,
        tags: List<String>? = null,
        unpack: Boolean? = null,
        visibility: com.revenexx.enums.Visibility? = null,
        onProgress: ((UploadProgress) -> Unit)? = null
    ): Any {
        val apiPath = "/v1/storage/assets"

        val apiParams = mutableMapOf<String, Any?>(
            "alt_text" to altText,
            "description" to description,
            "display_name" to displayName,
            "file" to file,
            "folder_id" to folderId,
            "keep_archive" to keepArchive,
            "tags" to tags,
            "unpack" to unpack,
            "visibility" to visibility,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "multipart/form-data",
        )
        val idParamName: String? = null    
        return client.chunkedUpload(
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
            paramName,
            idParamName,
            onProgress,
        )
    }


    /**
     * 
     *
     * @param folderId 
     * @param visibility 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun assetBulk(
        folderId: String? = null,
        visibility: String? = null,
    ): Any {
        val apiPath = "/v1/storage/assets/bulk"

        val apiParams = mutableMapOf<String, Any?>(
            "folder_id" to folderId,
            "visibility" to visibility,
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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun assetDestroy(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}"
            .replace("{id}", id)

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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun assetShow(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}"
            .replace("{id}", id)

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
     * 
     *
     * @param id 
     * @param altText 
     * @param description 
     * @param displayName 
     * @param folderId 
     * @param name 
     * @param tags 
     * @param visibility 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun assetUpdate(
        id: String,
        altText: String? = null,
        description: String? = null,
        displayName: String? = null,
        folderId: String? = null,
        name: String? = null,
        tags: List<String>? = null,
        visibility: com.revenexx.enums.Visibility? = null,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "alt_text" to altText,
            "description" to description,
            "display_name" to displayName,
            "folder_id" to folderId,
            "name" to name,
            "tags" to tags,
            "visibility" to visibility,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun assetDownload(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}/download"
            .replace("{id}", id)

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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun assetPermanent(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}/permanent"
            .replace("{id}", id)

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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun assetReprocess(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}/reprocess"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun assetRestore(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}/restore"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * 
     *
     * @param id 
     * @param ttlSeconds 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun assetSign(
        id: String,
        ttlSeconds: Long? = null,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}/sign"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "ttl_seconds" to ttlSeconds,
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
     * 
     *
     * @param id 
     * @param keepArchive 
     * @param targetFolderId 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun assetUnpack(
        id: String,
        keepArchive: Boolean? = null,
        targetFolderId: String? = null,
    ): Any {
        val apiPath = "/v1/storage/assets/{id}/unpack"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "keep_archive" to keepArchive,
            "target_folder_id" to targetFolderId,
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
     * 
     *
     * @return [Any]
     */
    suspend fun folderIndex(
    ): Any {
        val apiPath = "/v1/storage/folders"

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
     * 
     *
     * @param name 
     * @param parentId 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun folderStore(
        name: String,
        parentId: String? = null,
    ): Any {
        val apiPath = "/v1/storage/folders"

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "parent_id" to parentId,
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
     * 
     *
     * @param id 
     * @param recursive 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun folderDestroy(
        id: String,
        recursive: Boolean? = null,
    ): Any {
        val apiPath = "/v1/storage/folders/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "recursive" to recursive,
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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun folderShow(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/folders/{id}"
            .replace("{id}", id)

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
     * 
     *
     * @param id 
     * @param name 
     * @param parentId 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun folderUpdate(
        id: String,
        name: String? = null,
        parentId: String? = null,
    ): Any {
        val apiPath = "/v1/storage/folders/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "parent_id" to parentId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun syncRuleIndex(
    ): Any {
        val apiPath = "/v1/storage/sftp/rules"

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
     * 
     *
     * @return [Any]
     */
    suspend fun syncRuleStore(
    ): Any {
        val apiPath = "/v1/storage/sftp/rules"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun syncRuleDestroy(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/sftp/rules/{id}"
            .replace("{id}", id)

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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun syncRuleShow(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/sftp/rules/{id}"
            .replace("{id}", id)

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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun syncRuleUpdate(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/sftp/rules/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun syncRuleRun(
        id: String,
    ): Any {
        val apiPath = "/v1/storage/sftp/rules/{id}/run"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * 
     *
     * @param id 
     * @param runId 
     * @return [Any]
     */
    suspend fun syncRuleRunProtocol(
        id: String,
        runId: String,
    ): Any {
        val apiPath = "/v1/storage/sftp/rules/{id}/runs/{runId}"
            .replace("{id}", id)
            .replace("{runId}", runId)

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
     * 
     *
     * @param ruleId 
     * @param from 
     * @param to 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun syncRuleHistory(
        ruleId: String? = null,
        from: String? = null,
        to: String? = null,
    ): Any {
        val apiPath = "/v1/storage/sftp/sync-history"

        val apiParams = mutableMapOf<String, Any?>(
            "rule_id" to ruleId,
            "from" to from,
            "to" to to,
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
     * 
     *
     * @return [Any]
     */
    suspend fun tenantStats(
    ): Any {
        val apiPath = "/v1/storage/tenant/stats"

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
     * 
     *
     * @return [Any]
     */
    suspend fun tenantUsage(
    ): Any {
        val apiPath = "/v1/storage/tenant/usage"

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


}