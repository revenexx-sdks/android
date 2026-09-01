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
 * The Revenexx app runtime (Appwrite functions, extended) and marketplace.
 */
class Apps(client: Client) : Service(client) {

    /**
     * List all Apps in the active project. Pass `search` to filter by name.
     *
     * @param queries Result filters, paging and ordering. Repeat the parameter once per query — `?queries=…&queries=…` — and make each value a JSON object, e.g. `{"method":"limit","values":[25]}`. The bracketed spellings `queries[]=` and `queries[0]=` are accepted too; the `limit(25)` call syntax is not. See “Query parameters” in this document's introduction. Filterable attributes, besides `$id`, `$createdAt`, `$updatedAt` and `$sequence`: name, enabled, runtime, deploymentId, schedule, scheduleNext, schedulePrevious, timeout, entrypoint, commands, installationId
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.FunctionList]
     */
    @JvmOverloads
    suspend fun appsList(
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.FunctionList {
        val apiPath = "/v1/apps"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "search" to search,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.FunctionList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FunctionList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FunctionList::class.java,
            converter,
        )
    }


    /**
     * Create a new revenexx App. An App is the deployment surface for code that runs on the platform — backend jobs, APIs, integrations. The created App owns subsequent deployments and executions.
     * 
     * Phase 1 mirrors the underlying Functions runtime 1:1; future phases will add manifest validation, registry coupling and schema migrations.
     *
     * @param functionId Function ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param name Function name. Max length: 128 chars.
     * @param runtime Execution runtime.
     * @param commands Build Commands.
     * @param enabled Is function enabled? When set to 'disabled', users cannot access the function but Server SDKs with and API key can still access the function. No data is lost when this is toggled.
     * @param entrypoint Entrypoint File. This path is relative to the "providerRootDirectory".
     * @param events Events list. Maximum of 100 events are allowed.
     * @param execute An array of role strings with execution permissions. By default no user is granted with any execute permissions. Roles take the form `any`, `guests`, `users`, `user:<id>`, `team:<id>`, `member:<id>` or `label:<name>`, some of them with a `/<dimension>` suffix such as `users/verified` or `team:<id>/owner`. At most 100 entries. See “Role strings” in this document's introduction.
     * @param installationId Installation ID of the platform's VCS (Version Control System) integration to deploy from.
     * @param logging When disabled, executions will exclude logs and errors, and will be slightly faster.
     * @param providerBranch Production branch for the repo linked to the function.
     * @param providerRepositoryId Repository ID of the repo linked to the function.
     * @param providerRootDirectory Path to function code in the linked repo.
     * @param providerSilentMode Is the VCS (Version Control System) connection in silent mode for the repo linked to the function? In silent mode, comments will not be made on commits and pull requests.
     * @param schedule Schedule CRON syntax.
     * @param scopes List of scopes allowed for API key auto-generated for every execution. Maximum of 100 scopes are allowed.
     * @param specification Runtime specification for the function and builds.
     * @param timeout Function maximum execution time in seconds.
     * @return [com.revenexx.models.Function]
     */
    @JvmOverloads
    suspend fun appsCreate(
        functionId: String,
        name: String,
        runtime: com.revenexx.enums.Runtime,
        commands: String? = null,
        enabled: Boolean? = null,
        entrypoint: String? = null,
        events: List<String>? = null,
        execute: List<String>? = null,
        installationId: String? = null,
        logging: Boolean? = null,
        providerBranch: String? = null,
        providerRepositoryId: String? = null,
        providerRootDirectory: String? = null,
        providerSilentMode: Boolean? = null,
        schedule: String? = null,
        scopes: List<com.revenexx.enums.Scopes>? = null,
        specification: String? = null,
        timeout: Long? = null,
    ): com.revenexx.models.Function {
        val apiPath = "/v1/apps"

        val apiParams = mutableMapOf<String, Any?>(
            "commands" to commands,
            "enabled" to enabled,
            "entrypoint" to entrypoint,
            "events" to events,
            "execute" to execute,
            "functionId" to functionId,
            "installationId" to installationId,
            "logging" to logging,
            "name" to name,
            "providerBranch" to providerBranch,
            "providerRepositoryId" to providerRepositoryId,
            "providerRootDirectory" to providerRootDirectory,
            "providerSilentMode" to providerSilentMode,
            "runtime" to runtime,
            "schedule" to schedule,
            "scopes" to scopes,
            "specification" to specification,
            "timeout" to timeout,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Function = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Function.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Function::class.java,
            converter,
        )
    }


    /**
     * List apps published to the Marketplace. Proxies the App Registry on Console with `?published=true` filter.
     *
     * @param search Search by app name, title or vendor.
     * @param perPage Items per page.
     * @param page Page number.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun appsListMarketplace(
        search: String? = null,
        perPage: Long? = null,
        page: Long? = null,
    ): Any {
        val apiPath = "/v1/apps/marketplace"

        val apiParams = mutableMapOf<String, Any?>(
            "search" to search,
            "per_page" to perPage,
            "page" to page,
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
     * Install a Marketplace app on the calling project's tenant. Body: { owner, name }.
     *
     * @param name App name.
     * @param owner Owner tenant slug of the app being installed.
     * @return [Any]
     */
    suspend fun appsInstallFromMarketplace(
        name: String,
        owner: String,
    ): Any {
        val apiPath = "/v1/apps/marketplace/install"

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "owner" to owner,
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
     * Get a list of all runtimes available for an App. Identical content to `functions.listRuntimes()`.
     *
     * @return [com.revenexx.models.RuntimeList]
     */
    suspend fun appsListRuntimes(
    ): com.revenexx.models.RuntimeList {
        val apiPath = "/v1/apps/runtimes"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.RuntimeList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.RuntimeList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.RuntimeList::class.java,
            converter,
        )
    }


    /**
     * List the compute specifications (CPU + memory) available to Apps in this project.
     *
     * @return [com.revenexx.models.SpecificationList]
     */
    suspend fun appsListSpecifications(
    ): com.revenexx.models.SpecificationList {
        val apiPath = "/v1/apps/specifications"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.SpecificationList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.SpecificationList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.SpecificationList::class.java,
            converter,
        )
    }


    /**
     * List the curated catalogue of App templates that can be used as starting points.
     *
     * @param runtimes List of runtimes allowed for filtering function templates. Maximum of 100 runtimes are allowed.
     * @param useCases List of use cases allowed for filtering function templates. Maximum of 100 use cases are allowed.
     * @param limit Limit the number of templates returned in the response. Default limit is 25, and maximum limit is 5000.
     * @param offset Offset the list of returned templates. Maximum offset is 5000.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.TemplateFunctionList]
     */
    @JvmOverloads
    suspend fun appsListTemplates(
        runtimes: List<com.revenexx.enums.Runtimes>? = null,
        useCases: List<com.revenexx.enums.UseCases>? = null,
        limit: Long? = null,
        offset: Long? = null,
        total: Boolean? = null,
    ): com.revenexx.models.TemplateFunctionList {
        val apiPath = "/v1/apps/templates"

        val apiParams = mutableMapOf<String, Any?>(
            "runtimes" to runtimes,
            "useCases" to useCases,
            "limit" to limit,
            "offset" to offset,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.TemplateFunctionList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.TemplateFunctionList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.TemplateFunctionList::class.java,
            converter,
        )
    }


    /**
     * Get a single App template by its ID.
     *
     * @param templateId Template ID.
     * @return [com.revenexx.models.TemplateFunction]
     */
    suspend fun appsGetTemplate(
        templateId: String,
    ): com.revenexx.models.TemplateFunction {
        val apiPath = "/v1/apps/templates/{templateId}"
            .replace("{templateId}", templateId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.TemplateFunction = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.TemplateFunction.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.TemplateFunction::class.java,
            converter,
        )
    }


    /**
     * Get aggregated usage stats across all Apps in the project for the requested time range.
     *
     * @param range Date range.
     * @return [com.revenexx.models.UsageFunctions]
     */
    @JvmOverloads
    suspend fun appsListUsage(
        range: com.revenexx.enums.Range? = null,
    ): com.revenexx.models.UsageFunctions {
        val apiPath = "/v1/apps/usage"

        val apiParams = mutableMapOf<String, Any?>(
            "range" to range,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.UsageFunctions = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.UsageFunctions.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.UsageFunctions::class.java,
            converter,
        )
    }


    /**
     * Delete an App and all of its deployments. Cascades to the App Registry — Console removes the matching `RegisteredApp` row.
     *
     * @param functionId App ID.
     * @return [Any]
     */
    suspend fun appsDelete(
        functionId: String,
    ): Any {
        val apiPath = "/v1/apps/{functionId}"
            .replace("{functionId}", functionId)

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
     * Get an App by its unique ID.
     *
     * @param functionId Function ID.
     * @return [com.revenexx.models.Function]
     */
    suspend fun appsGet(
        functionId: String,
    ): com.revenexx.models.Function {
        val apiPath = "/v1/apps/{functionId}"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Function = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Function.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Function::class.java,
            converter,
        )
    }


    /**
     * Update an App. Use this endpoint to rename, change runtime, schedule, environment variables and other configuration.
     *
     * @param functionId Function ID.
     * @param name Function name. Max length: 128 chars.
     * @param commands Build Commands.
     * @param enabled Is function enabled? When set to 'disabled', users cannot access the function but Server SDKs with and API key can still access the function. No data is lost when this is toggled.
     * @param entrypoint Entrypoint File. This path is relative to the "providerRootDirectory".
     * @param events Events list. Maximum of 100 events are allowed.
     * @param execute An array of role strings with execution permissions. By default no user is granted with any execute permissions. Roles take the form `any`, `guests`, `users`, `user:<id>`, `team:<id>`, `member:<id>` or `label:<name>`, some of them with a `/<dimension>` suffix such as `users/verified` or `team:<id>/owner`. At most 100 entries. See “Role strings” in this document's introduction.
     * @param installationId Installation ID of the platform's VCS (Version Control System) integration to deploy from.
     * @param logging When disabled, executions will exclude logs and errors, and will be slightly faster.
     * @param providerBranch Production branch for the repo linked to the function
     * @param providerRepositoryId Repository ID of the repo linked to the function
     * @param providerRootDirectory Path to function code in the linked repo.
     * @param providerSilentMode Is the VCS (Version Control System) connection in silent mode for the repo linked to the function? In silent mode, comments will not be made on commits and pull requests.
     * @param runtime Execution runtime.
     * @param schedule Schedule CRON syntax.
     * @param scopes List of scopes allowed for API Key auto-generated for every execution. Maximum of 100 scopes are allowed.
     * @param specification Runtime specification for the function and builds.
     * @param timeout Maximum execution time in seconds.
     * @return [com.revenexx.models.Function]
     */
    @JvmOverloads
    suspend fun appsUpdate(
        functionId: String,
        name: String,
        commands: String? = null,
        enabled: Boolean? = null,
        entrypoint: String? = null,
        events: List<String>? = null,
        execute: List<String>? = null,
        installationId: String? = null,
        logging: Boolean? = null,
        providerBranch: String? = null,
        providerRepositoryId: String? = null,
        providerRootDirectory: String? = null,
        providerSilentMode: Boolean? = null,
        runtime: com.revenexx.enums.Runtime? = null,
        schedule: String? = null,
        scopes: List<com.revenexx.enums.Scopes>? = null,
        specification: String? = null,
        timeout: Long? = null,
    ): com.revenexx.models.Function {
        val apiPath = "/v1/apps/{functionId}"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "commands" to commands,
            "enabled" to enabled,
            "entrypoint" to entrypoint,
            "events" to events,
            "execute" to execute,
            "installationId" to installationId,
            "logging" to logging,
            "name" to name,
            "providerBranch" to providerBranch,
            "providerRepositoryId" to providerRepositoryId,
            "providerRootDirectory" to providerRootDirectory,
            "providerSilentMode" to providerSilentMode,
            "runtime" to runtime,
            "schedule" to schedule,
            "scopes" to scopes,
            "specification" to specification,
            "timeout" to timeout,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Function = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Function.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Function::class.java,
            converter,
        )
    }


    /**
     * Set the active deployment for an App. The chosen deployment must already be `ready`.
     *
     * @param functionId Function ID.
     * @param deploymentId Deployment ID.
     * @return [com.revenexx.models.Function]
     */
    suspend fun appsUpdateDeployment(
        functionId: String,
        deploymentId: String,
    ): com.revenexx.models.Function {
        val apiPath = "/v1/apps/{functionId}/deployment"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "deploymentId" to deploymentId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Function = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Function.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Function::class.java,
            converter,
        )
    }


    /**
     * List the deployment history of an App.
     *
     * @param functionId Function ID.
     * @param queries Result filters, paging and ordering. Repeat the parameter once per query — `?queries=…&queries=…` — and make each value a JSON object, e.g. `{"method":"limit","values":[25]}`. The bracketed spellings `queries[]=` and `queries[0]=` are accepted too; the `limit(25)` call syntax is not. See “Query parameters” in this document's introduction. Filterable attributes, besides `$id`, `$createdAt`, `$updatedAt` and `$sequence`: buildSize, sourceSize, totalSize, buildDuration, status, activate, type
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.DeploymentList]
     */
    @JvmOverloads
    suspend fun appsListDeployments(
        functionId: String,
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.DeploymentList {
        val apiPath = "/v1/apps/{functionId}/deployments"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "search" to search,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.DeploymentList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.DeploymentList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.DeploymentList::class.java,
            converter,
        )
    }


    /**
     * Upload a new code deployment for an App. Accepts a `.tar.gz`
     * archive containing the App source. Phase 2 will extract the
     * manifest from this archive and validate it against the App
     * Registry before kicking off the build.
     *
     * @param functionId Function ID.
     * @param activate Automatically activate the deployment when it is finished building.
     * @param code Your source directory packaged as a gzipped tar archive (`.tar.gz`), sent as the file part of the multipart request.
     * @param commands Build Commands.
     * @param entrypoint Entrypoint File.
     * @return [com.revenexx.models.Deployment]
     */
    @JvmOverloads
    suspend fun appsCreateDeployment(
        functionId: String,
        activate: Boolean,
        code: InputFile,
        commands: String? = null,
        entrypoint: String? = null,
        onProgress: ((UploadProgress) -> Unit)? = null
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/apps/{functionId}/deployments"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "activate" to activate,
            "code" to code,
            "commands" to commands,
            "entrypoint" to entrypoint,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "multipart/form-data",
        )
        val converter: (Any) -> com.revenexx.models.Deployment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Deployment.from(map = it as Map<String, Any>)
        }
        val idParamName: String? = null    
        val paramName = "code"
        return client.chunkedUpload(
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Deployment::class.java,
            converter = converter,
            paramName = paramName,
            idParamName = idParamName,
            onProgress = onProgress,
        )
    }


    /**
     * Re-deploy an existing build under a new deployment ID. Useful for promoting a known-good preview build to production without rebuilding.
     *
     * @param functionId Function ID.
     * @param deploymentId Deployment ID.
     * @param buildId Build unique ID.
     * @return [com.revenexx.models.Deployment]
     */
    @JvmOverloads
    suspend fun appsCreateDuplicateDeployment(
        functionId: String,
        deploymentId: String,
        buildId: String? = null,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/apps/{functionId}/deployments/duplicate"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "buildId" to buildId,
            "deploymentId" to deploymentId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Deployment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Deployment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Deployment::class.java,
            converter,
        )
    }


    /**
     * Create a new App deployment from a template in the App Templates catalogue.
     *
     * @param functionId Function ID.
     * @param owner The name of the owner of the template.
     * @param reference Reference value, can be a commit hash, branch name, or release tag
     * @param repository Repository name of the template.
     * @param rootDirectory Path to function code in the template repo.
     * @param type Type for the reference provided. Can be commit, branch, or tag
     * @param activate Automatically activate the deployment when it is finished building.
     * @return [com.revenexx.models.Deployment]
     */
    @JvmOverloads
    suspend fun appsCreateTemplateDeployment(
        functionId: String,
        owner: String,
        reference: String,
        repository: String,
        rootDirectory: String,
        type: com.revenexx.enums.Type,
        activate: Boolean? = null,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/apps/{functionId}/deployments/template"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "activate" to activate,
            "owner" to owner,
            "reference" to reference,
            "repository" to repository,
            "rootDirectory" to rootDirectory,
            "type" to type,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Deployment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Deployment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Deployment::class.java,
            converter,
        )
    }


    /**
     * Trigger a new deployment from the App's connected Git repository.
     *
     * @param functionId Function ID.
     * @param reference VCS reference to create deployment from. Depending on type this can be: branch name, commit hash
     * @param type Type of reference passed. Allowed values are: branch, commit
     * @param activate Automatically activate the deployment when it is finished building.
     * @return [com.revenexx.models.Deployment]
     */
    @JvmOverloads
    suspend fun appsCreateVcsDeployment(
        functionId: String,
        reference: String,
        type: com.revenexx.enums.AppsCreateVcsDeploymentType,
        activate: Boolean? = null,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/apps/{functionId}/deployments/vcs"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "activate" to activate,
            "reference" to reference,
            "type" to type,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Deployment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Deployment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Deployment::class.java,
            converter,
        )
    }


    /**
     * Delete a deployment. The active deployment cannot be deleted while it is active — switch first via the deployment-update endpoint.
     *
     * @param functionId Function ID.
     * @param deploymentId Deployment ID.
     * @return [Any]
     */
    suspend fun appsDeleteDeployment(
        functionId: String,
        deploymentId: String,
    ): Any {
        val apiPath = "/v1/apps/{functionId}/deployments/{deploymentId}"
            .replace("{functionId}", functionId)
            .replace("{deploymentId}", deploymentId)

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
     * Get a deployment by its unique ID.
     *
     * @param functionId Function ID.
     * @param deploymentId Deployment ID.
     * @return [com.revenexx.models.Deployment]
     */
    suspend fun appsGetDeployment(
        functionId: String,
        deploymentId: String,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/apps/{functionId}/deployments/{deploymentId}"
            .replace("{functionId}", functionId)
            .replace("{deploymentId}", deploymentId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Deployment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Deployment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Deployment::class.java,
            converter,
        )
    }


    /**
     * Get a redirect URL to download the source archive of an App deployment. Useful for re-running a build locally or auditing what was deployed.
     *
     * @param functionId Function ID.
     * @param deploymentId Deployment ID.
     * @param type Deployment file to download. Can be: "source", "output".
     * @return [Any]
     */
    @JvmOverloads
    suspend fun appsGetDeploymentDownload(
        functionId: String,
        deploymentId: String,
        type: com.revenexx.enums.AppsGetDeploymentDownloadType? = null,
    ): Any {
        val apiPath = "/v1/apps/{functionId}/deployments/{deploymentId}/download"
            .replace("{functionId}", functionId)
            .replace("{deploymentId}", deploymentId)

        val apiParams = mutableMapOf<String, Any?>(
            "type" to type,
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
     * Cancel an in-progress deployment build. Used by the Cockpit "Cancel build" affordance.
     *
     * @param functionId Function ID.
     * @param deploymentId Deployment ID.
     * @return [com.revenexx.models.Deployment]
     */
    suspend fun appsUpdateDeploymentStatus(
        functionId: String,
        deploymentId: String,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/apps/{functionId}/deployments/{deploymentId}/status"
            .replace("{functionId}", functionId)
            .replace("{deploymentId}", deploymentId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Deployment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Deployment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Deployment::class.java,
            converter,
        )
    }


    /**
     * List the execution history of an App.
     *
     * @param functionId Function ID.
     * @param queries Result filters, paging and ordering. Repeat the parameter once per query — `?queries=…&queries=…` — and make each value a JSON object, e.g. `{"method":"limit","values":[25]}`. The bracketed spellings `queries[]=` and `queries[0]=` are accepted too; the `limit(25)` call syntax is not. See “Query parameters” in this document's introduction. Filterable attributes, besides `$id`, `$createdAt`, `$updatedAt` and `$sequence`: trigger, status, responseStatusCode, duration, requestMethod, requestPath, deploymentId
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.ExecutionList]
     */
    @JvmOverloads
    suspend fun appsListExecutions(
        functionId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.ExecutionList {
        val apiPath = "/v1/apps/{functionId}/executions"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ExecutionList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ExecutionList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ExecutionList::class.java,
            converter,
        )
    }


    /**
     * Trigger an App execution. Use the optional `body`, `path`, `method` and `headers` parameters to invoke the App as if from an HTTP request.
     *
     * @param functionId Function ID.
     * @param async Execute code in the background. Default value is false.
     * @param body HTTP body of execution. Default value is empty string.
     * @param headers HTTP headers of execution. Defaults to empty.
     * @param method HTTP method of execution. Default value is POST.
     * @param path HTTP path of execution. Path can include query params. Default value is /
     * @param scheduledAt Scheduled execution time in [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format. DateTime value must be in future with precision in minutes.
     * @return [com.revenexx.models.Execution]
     */
    @JvmOverloads
    suspend fun appsCreateExecution(
        functionId: String,
        async: Boolean? = null,
        body: String? = null,
        headers: Any? = null,
        method: com.revenexx.enums.Method? = null,
        path: String? = null,
        scheduledAt: String? = null,
    ): com.revenexx.models.Execution {
        val apiPath = "/v1/apps/{functionId}/executions"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "async" to async,
            "body" to body,
            "headers" to headers,
            "method" to method,
            "path" to path,
            "scheduledAt" to scheduledAt,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Execution = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Execution.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Execution::class.java,
            converter,
        )
    }


    /**
     * Delete an App execution by its unique ID.
     *
     * @param functionId Function ID.
     * @param executionId Execution ID.
     * @return [Any]
     */
    suspend fun appsDeleteExecution(
        functionId: String,
        executionId: String,
    ): Any {
        val apiPath = "/v1/apps/{functionId}/executions/{executionId}"
            .replace("{functionId}", functionId)
            .replace("{executionId}", executionId)

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
     * Get an App execution by its unique ID.
     *
     * @param functionId Function ID.
     * @param executionId Execution ID.
     * @return [com.revenexx.models.Execution]
     */
    suspend fun appsGetExecution(
        functionId: String,
        executionId: String,
    ): com.revenexx.models.Execution {
        val apiPath = "/v1/apps/{functionId}/executions/{executionId}"
            .replace("{functionId}", functionId)
            .replace("{executionId}", executionId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Execution = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Execution.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Execution::class.java,
            converter,
        )
    }


    /**
     * Read-through view of the App's App Registry row — visibility + Marketplace publish flag. Used by Cockpit to render the Publish/Unpublish button correctly on cold load.
     *
     * @param functionId App ID.
     * @return [Any]
     */
    suspend fun appsGetMarketplaceStatus(
        functionId: String,
    ): Any {
        val apiPath = "/v1/apps/{functionId}/marketplace-status"
            .replace("{functionId}", functionId)

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
     * Remove this App from the Marketplace listing. Existing tenant installations are unaffected. Idempotent.
     *
     * @param functionId App ID.
     * @return [Any]
     */
    suspend fun appsUnpublish(
        functionId: String,
    ): Any {
        val apiPath = "/v1/apps/{functionId}/publish"
            .replace("{functionId}", functionId)

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
     * Publish this App to the Marketplace. The App must have at
     * least one `ready` deployment with a registered manifest,
     * and its visibility (derived from `billing.json`) must be
     * `public` or `included`. Idempotent.
     *
     * @param functionId App ID.
     * @return [Any]
     */
    suspend fun appsPublish(
        functionId: String,
    ): Any {
        val apiPath = "/v1/apps/{functionId}/publish"
            .replace("{functionId}", functionId)

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
     * Get usage stats for a single App over the requested time range.
     *
     * @param functionId Function ID.
     * @param range Date range.
     * @return [com.revenexx.models.UsageFunction]
     */
    @JvmOverloads
    suspend fun appsGetUsage(
        functionId: String,
        range: com.revenexx.enums.Range? = null,
    ): com.revenexx.models.UsageFunction {
        val apiPath = "/v1/apps/{functionId}/usage"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "range" to range,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.UsageFunction = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.UsageFunction.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.UsageFunction::class.java,
            converter,
        )
    }


    /**
     * List all environment variables defined for the App.
     *
     * @param functionId Function unique ID.
     * @return [com.revenexx.models.VariableList]
     */
    suspend fun appsListVariables(
        functionId: String,
    ): com.revenexx.models.VariableList {
        val apiPath = "/v1/apps/{functionId}/variables"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.VariableList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.VariableList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.VariableList::class.java,
            converter,
        )
    }


    /**
     * Create a new App environment variable. These are passed into the App at runtime as `process.env.*`.
     *
     * @param functionId Function unique ID.
     * @param key Variable key. Max length: 255 chars.
     * @param value Variable value. Max length: 8192 chars.
     * @param secret Secret variables can be updated or deleted, but only functions can read them during build and runtime.
     * @return [com.revenexx.models.Variable]
     */
    @JvmOverloads
    suspend fun appsCreateVariable(
        functionId: String,
        key: String,
        value: String,
        secret: Boolean? = null,
    ): com.revenexx.models.Variable {
        val apiPath = "/v1/apps/{functionId}/variables"
            .replace("{functionId}", functionId)

        val apiParams = mutableMapOf<String, Any?>(
            "key" to key,
            "secret" to secret,
            "value" to value,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Variable = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Variable.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Variable::class.java,
            converter,
        )
    }


    /**
     * Delete an App environment variable.
     *
     * @param functionId Function unique ID.
     * @param variableId Variable unique ID.
     * @return [Any]
     */
    suspend fun appsDeleteVariable(
        functionId: String,
        variableId: String,
    ): Any {
        val apiPath = "/v1/apps/{functionId}/variables/{variableId}"
            .replace("{functionId}", functionId)
            .replace("{variableId}", variableId)

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
     * Get an App variable by its unique ID.
     *
     * @param functionId Function unique ID.
     * @param variableId Variable unique ID.
     * @return [com.revenexx.models.Variable]
     */
    suspend fun appsGetVariable(
        functionId: String,
        variableId: String,
    ): com.revenexx.models.Variable {
        val apiPath = "/v1/apps/{functionId}/variables/{variableId}"
            .replace("{functionId}", functionId)
            .replace("{variableId}", variableId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Variable = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Variable.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Variable::class.java,
            converter,
        )
    }


    /**
     * Update an App environment variable.
     *
     * @param functionId Function unique ID.
     * @param variableId Variable unique ID.
     * @param key Variable key. Max length: 255 chars.
     * @param secret Secret variables can be updated or deleted, but only functions can read them during build and runtime.
     * @param value Variable value. Max length: 8192 chars.
     * @return [com.revenexx.models.Variable]
     */
    @JvmOverloads
    suspend fun appsUpdateVariable(
        functionId: String,
        variableId: String,
        key: String,
        secret: Boolean? = null,
        value: String? = null,
    ): com.revenexx.models.Variable {
        val apiPath = "/v1/apps/{functionId}/variables/{variableId}"
            .replace("{functionId}", functionId)
            .replace("{variableId}", variableId)

        val apiParams = mutableMapOf<String, Any?>(
            "key" to key,
            "secret" to secret,
            "value" to value,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Variable = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Variable.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Variable::class.java,
            converter,
        )
    }


}