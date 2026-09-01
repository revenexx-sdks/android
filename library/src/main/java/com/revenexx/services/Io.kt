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
 * Bulk data plane: import/export profiles, upload tickets, ad-hoc jobs and the job registry (Baseline).
 */
class Io(client: Client) : Service(client) {

    /**
     * The calling tenant's bulk jobs, newest first. Jobs are created by the
     * feature blocks (import / export / A/B swap / tenant copy / sample) —
     * never here; this surface is read-only.
     * 
     *
     * @param type 
     * @param status 
     * @param vendor 
     * @param app 
     * @param entity 
     * @param limit 
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    @JvmOverloads
    suspend fun listBulkJobs(
        type: Any? = null,
        status: Any? = null,
        vendor: String? = null,
        app: String? = null,
        entity: String? = null,
        limit: Long? = null,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/bulk-jobs"

        val apiParams = mutableMapOf<String, Any?>(
            "type" to type,
            "status" to status,
            "vendor" to vendor,
            "app" to app,
            "entity" to entity,
            "limit" to limit,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Status, row counts, and progress for one bulk job.
     * 
     * Tenant-scoped: an id belonging to another tenant is filtered out and
     * is therefore indistinguishable from a non-existent one — which is the
     * intent.
     * 
     *
     * @param id 
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    suspend fun getBulkJob(
        id: String,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/bulk-jobs/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Flat list of the entities the calling tenant's installed apps expose,
     * sorted by vendor, app, entity. Feeds the entity pickers of the
     * Integration Studio I/O nodes.
     * 
     * The app set comes from `baseline.tenant_app_versions`. Per app the
     * entity list is resolved from the tenant's pinned schema version; when
     * that pointer is stale (missing or not applied) it falls back to the
     * latest applied version of `(vendor, app)`. Apps with no applied
     * schema at all contribute no entities.
     * 
     *
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    suspend fun listIoEntities(
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/entities"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Creates a `bulk_job` and dispatches the engine to export the tenant's
     * rows for an entity. CSV/XML stream row-by-row into an S3 multipart
     * upload (flat RAM); JSON/XLSX are buffered. The response carries the
     * object key the result will be written to.
     * 
     *
     * @param app 
     * @param entity 
     * @param vendor 
     * @param format 
     * @param profileId 
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    @JvmOverloads
    suspend fun createExport(
        app: String,
        entity: String,
        vendor: String,
        format: com.revenexx.enums.Format? = null,
        profileId: String? = null,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/exports"

        val apiParams = mutableMapOf<String, Any?>(
            "app" to app,
            "entity" to entity,
            "format" to format,
            "profile_id" to profileId,
            "vendor" to vendor,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Mints a short-TTL signed S3 `GET` URL for the object a completed
     * export wrote. Tenant-scoped: an id belonging to another tenant — or
     * to a job that is not an export — is indistinguishable from a
     * non-existent one and answers `404`.
     * 
     * The job must have reached `completed` or `partial`; any earlier
     * state answers `409` and carries the current `job_status`.
     * 
     *
     * @param id The export job's id.
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    suspend fun getExportUrl(
        id: String,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/exports/{id}/url"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Creates a `bulk_job` and dispatches the engine to import a previously
     * uploaded object into the named entity. The engine streams CSV
     * row-by-row (flat RAM at 1M+ rows) and COPYs into the entity's staging
     * sibling before a merge / content-hash delta into the target.
     * 
     *
     * @param app 
     * @param entity 
     * @param objectKey 
     * @param vendor 
     * @param format 
     * @param keys Natural-key columns for upsert / delta.
     * @param maxRejects Rejected rows tolerated before the import fails. Omit for
unlimited (reject-and-continue); `0` = fail-fast.

     * @param mode 
     * @param profileId 
     * @param target `shadow` stages the dataset into the A/B `{table}__shadow`
sibling for diff + switch-over instead of writing live.

     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    @JvmOverloads
    suspend fun createImport(
        app: String,
        entity: String,
        objectKey: String,
        vendor: String,
        format: com.revenexx.enums.Format? = null,
        keys: List<String>? = null,
        maxRejects: Long? = null,
        mode: com.revenexx.enums.Mode? = null,
        profileId: String? = null,
        target: com.revenexx.enums.CreateImportTarget? = null,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/imports"

        val apiParams = mutableMapOf<String, Any?>(
            "app" to app,
            "entity" to entity,
            "format" to format,
            "keys" to keys,
            "max_rejects" to maxRejects,
            "mode" to mode,
            "object_key" to objectKey,
            "profile_id" to profileId,
            "target" to target,
            "vendor" to vendor,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * The calling tenant's saved profiles, ordered by name.
     * 
     * When `X-Revenexx-Market` is present the listing is filtered to the
     * profiles offered for that market — global profiles (`markets: null`)
     * plus those whose `markets` contain it. Omit the header to get every
     * profile, which is what the management view wants.
     * 
     *
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    suspend fun listProfiles(
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/profiles"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * A tenant-secured, reusable mapping (field rename + transforms + keys)
     * for a direction (`import`/`export`), format, and entity. Runnable
     * on-click via `/io/profiles/{id}/run`.
     * 
     *
     * @param app 
     * @param direction 
     * @param entity 
     * @param format 
     * @param name 
     * @param vendor 
     * @param applyMode 
     * @param mapping Field mapping. `fields[]` carry `target` (DB column),
`source` (external name) and ordered `transforms`; `keys[]`
are natural-key columns. Optional `max_rejects`/`target`
ride along for import runs.

     * @param markets Markets this profile applies to (n:m). Omitted, `null` or
empty means global — offered for every market.

     * @param options Free-form per-profile engine options.
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    @JvmOverloads
    suspend fun createProfile(
        app: String,
        direction: com.revenexx.enums.Direction,
        entity: String,
        format: String,
        name: String,
        vendor: String,
        applyMode: com.revenexx.enums.ApplyMode? = null,
        mapping: Any? = null,
        markets: List<String>? = null,
        options: Any? = null,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/profiles"

        val apiParams = mutableMapOf<String, Any?>(
            "app" to app,
            "apply_mode" to applyMode,
            "direction" to direction,
            "entity" to entity,
            "format" to format,
            "mapping" to mapping,
            "markets" to markets,
            "name" to name,
            "options" to options,
            "vendor" to vendor,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Permanently remove a saved profile owned by the calling tenant.
     * 
     * Idempotent, and deliberately not a `404` path: deleting an id that
     * does not belong to the tenant still answers `200`, with `deleted: 0`.
     * 
     *
     * @param id 
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    suspend fun deleteProfile(
        id: String,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/profiles/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * A single saved profile. Tenant-scoped: an id owned by another tenant
     * is indistinguishable from a non-existent one and answers `404`.
     * 
     *
     * @param id 
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    suspend fun showProfile(
        id: String,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/profiles/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Replace a saved profile's mapping, format, or apply mode (tenant-scoped).
     *
     * @param id 
     * @param app 
     * @param direction 
     * @param entity 
     * @param format 
     * @param name 
     * @param vendor 
     * @param applyMode 
     * @param mapping Field mapping. `fields[]` carry `target` (DB column),
`source` (external name) and ordered `transforms`; `keys[]`
are natural-key columns. Optional `max_rejects`/`target`
ride along for import runs.

     * @param markets Markets this profile applies to (n:m). Omitted, `null` or
empty means global — offered for every market.

     * @param options Free-form per-profile engine options.
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    @JvmOverloads
    suspend fun updateProfile(
        id: String,
        app: String,
        direction: com.revenexx.enums.Direction,
        entity: String,
        format: String,
        name: String,
        vendor: String,
        applyMode: com.revenexx.enums.ApplyMode? = null,
        mapping: Any? = null,
        markets: List<String>? = null,
        options: Any? = null,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/profiles/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "app" to app,
            "apply_mode" to applyMode,
            "direction" to direction,
            "entity" to entity,
            "format" to format,
            "mapping" to mapping,
            "markets" to markets,
            "name" to name,
            "options" to options,
            "vendor" to vendor,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Dispatches the engine using the saved profile. An import run requires
     * `object_key` (upload first); an export run writes a generated key.
     * 
     *
     * @param id 
     * @param markets Target market(s) the imported rows are assigned to (n:m).
Overrides the profile's own `markets` for this run; an
empty array means global (no assignment).

     * @param objectKey The uploaded object to import. Required for an import
run; ignored for an export run, which generates its own
key. Omitting it on an import answers `422` with
`RUN_NO_OBJECT`.

     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    @JvmOverloads
    suspend fun runProfile(
        id: String,
        markets: List<String>? = null,
        objectKey: String? = null,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/profiles/{id}/run"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "markets" to markets,
            "object_key" to objectKey,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


    /**
     * Returns a short-lived signed S3 `PUT` URL (+ required headers) and
     * the `object_key` to reference in a subsequent `/io/imports`. The
     * client uploads bytes directly to object storage — never through
     * Baseline.
     * 
     *
     * @param extension File extension for the generated key.
     * @return [com.revenexx.models.ValidationFailedResponse]
     */
    @JvmOverloads
    suspend fun createUpload(
        extension: String? = null,
    ): com.revenexx.models.ValidationFailedResponse {
        val apiPath = "/v1/io/uploads"

        val apiParams = mutableMapOf<String, Any?>(
            "extension" to extension,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ValidationFailedResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ValidationFailedResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ValidationFailedResponse::class.java,
            converter,
        )
    }


}