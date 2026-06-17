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
 * Static sites and their deployments.
 */
class Sites(client: Client) : Service(client) {

    /**
     * Get a list of all the project's sites. You can use the query params to filter your results.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: name, enabled, framework, deploymentId, buildCommand, installCommand, outputDirectory, installationId
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.SiteList]
     */
    @JvmOverloads
    suspend fun sitesList(
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.SiteList {
        val apiPath = "/v1/sites"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "search" to search,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.SiteList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.SiteList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.SiteList::class.java,
            converter,
        )
    }


    /**
     * Create a new site.
     *
     * @param buildRuntime Runtime to use during build step.
     * @param framework Sites framework.
     * @param name Site name. Max length: 128 chars.
     * @param siteId Site ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param adapter Framework adapter defining rendering strategy. Allowed values are: static, ssr
     * @param buildCommand Build Command.
     * @param enabled Is site enabled? When set to 'disabled', users cannot access the site but Server SDKs with and API key can still access the site. No data is lost when this is toggled.
     * @param fallbackFile Fallback file for single page application sites.
     * @param installCommand Install Command.
     * @param installationId Appwrite Installation ID for VCS (Version Control System) deployment.
     * @param logging When disabled, request logs will exclude logs and errors, and site responses will be slightly faster.
     * @param outputDirectory Output Directory for site.
     * @param providerBranch Production branch for the repo linked to the site.
     * @param providerRepositoryId Repository ID of the repo linked to the site.
     * @param providerRootDirectory Path to site code in the linked repo.
     * @param providerSilentMode Is the VCS (Version Control System) connection in silent mode for the repo linked to the site? In silent mode, comments will not be made on commits and pull requests.
     * @param specification Framework specification for the site and builds.
     * @param timeout Maximum request time in seconds.
     * @return [com.revenexx.models.Site]
     */
    @JvmOverloads
    suspend fun sitesCreate(
        buildRuntime: com.revenexx.enums.BuildRuntime,
        framework: com.revenexx.enums.Framework,
        name: String,
        siteId: String,
        adapter: com.revenexx.enums.Adapter? = null,
        buildCommand: String? = null,
        enabled: Boolean? = null,
        fallbackFile: String? = null,
        installCommand: String? = null,
        installationId: String? = null,
        logging: Boolean? = null,
        outputDirectory: String? = null,
        providerBranch: String? = null,
        providerRepositoryId: String? = null,
        providerRootDirectory: String? = null,
        providerSilentMode: Boolean? = null,
        specification: String? = null,
        timeout: Long? = null,
    ): com.revenexx.models.Site {
        val apiPath = "/v1/sites"

        val apiParams = mutableMapOf<String, Any?>(
            "adapter" to adapter,
            "buildCommand" to buildCommand,
            "buildRuntime" to buildRuntime,
            "enabled" to enabled,
            "fallbackFile" to fallbackFile,
            "framework" to framework,
            "installCommand" to installCommand,
            "installationId" to installationId,
            "logging" to logging,
            "name" to name,
            "outputDirectory" to outputDirectory,
            "providerBranch" to providerBranch,
            "providerRepositoryId" to providerRepositoryId,
            "providerRootDirectory" to providerRootDirectory,
            "providerSilentMode" to providerSilentMode,
            "siteId" to siteId,
            "specification" to specification,
            "timeout" to timeout,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Site = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Site.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Site::class.java,
            converter,
        )
    }


    /**
     * Get a list of all frameworks that are currently available on the server instance.
     *
     * @return [com.revenexx.models.FrameworkList]
     */
    suspend fun sitesListFrameworks(
    ): com.revenexx.models.FrameworkList {
        val apiPath = "/v1/sites/frameworks"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.FrameworkList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.FrameworkList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.FrameworkList::class.java,
            converter,
        )
    }


    /**
     * List allowed site specifications for this instance.
     *
     * @return [com.revenexx.models.SpecificationList]
     */
    suspend fun sitesListSpecifications(
    ): com.revenexx.models.SpecificationList {
        val apiPath = "/v1/sites/specifications"

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
     * Delete a site by its unique ID.
     *
     * @param siteId Site ID.
     * @return [Any]
     */
    suspend fun sitesDelete(
        siteId: String,
    ): Any {
        val apiPath = "/v1/sites/{siteId}"
            .replace("{siteId}", siteId)

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
     * Get a site by its unique ID.
     *
     * @param siteId Site ID.
     * @return [com.revenexx.models.Site]
     */
    suspend fun sitesGet(
        siteId: String,
    ): com.revenexx.models.Site {
        val apiPath = "/v1/sites/{siteId}"
            .replace("{siteId}", siteId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Site = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Site.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Site::class.java,
            converter,
        )
    }


    /**
     * Update site by its unique ID.
     *
     * @param siteId Site ID.
     * @param framework Sites framework.
     * @param name Site name. Max length: 128 chars.
     * @param adapter Framework adapter defining rendering strategy. Allowed values are: static, ssr
     * @param buildCommand Build Command.
     * @param buildRuntime Runtime to use during build step.
     * @param enabled Is site enabled? When set to 'disabled', users cannot access the site but Server SDKs with and API key can still access the site. No data is lost when this is toggled.
     * @param fallbackFile Fallback file for single page application sites.
     * @param installCommand Install Command.
     * @param installationId Appwrite Installation ID for VCS (Version Control System) deployment.
     * @param logging When disabled, request logs will exclude logs and errors, and site responses will be slightly faster.
     * @param outputDirectory Output Directory for site.
     * @param providerBranch Production branch for the repo linked to the site.
     * @param providerRepositoryId Repository ID of the repo linked to the site.
     * @param providerRootDirectory Path to site code in the linked repo.
     * @param providerSilentMode Is the VCS (Version Control System) connection in silent mode for the repo linked to the site? In silent mode, comments will not be made on commits and pull requests.
     * @param specification Framework specification for the site and builds.
     * @param timeout Maximum request time in seconds.
     * @return [com.revenexx.models.Site]
     */
    @JvmOverloads
    suspend fun sitesUpdate(
        siteId: String,
        framework: com.revenexx.enums.Framework,
        name: String,
        adapter: com.revenexx.enums.Adapter? = null,
        buildCommand: String? = null,
        buildRuntime: com.revenexx.enums.BuildRuntime? = null,
        enabled: Boolean? = null,
        fallbackFile: String? = null,
        installCommand: String? = null,
        installationId: String? = null,
        logging: Boolean? = null,
        outputDirectory: String? = null,
        providerBranch: String? = null,
        providerRepositoryId: String? = null,
        providerRootDirectory: String? = null,
        providerSilentMode: Boolean? = null,
        specification: String? = null,
        timeout: Long? = null,
    ): com.revenexx.models.Site {
        val apiPath = "/v1/sites/{siteId}"
            .replace("{siteId}", siteId)

        val apiParams = mutableMapOf<String, Any?>(
            "adapter" to adapter,
            "buildCommand" to buildCommand,
            "buildRuntime" to buildRuntime,
            "enabled" to enabled,
            "fallbackFile" to fallbackFile,
            "framework" to framework,
            "installCommand" to installCommand,
            "installationId" to installationId,
            "logging" to logging,
            "name" to name,
            "outputDirectory" to outputDirectory,
            "providerBranch" to providerBranch,
            "providerRepositoryId" to providerRepositoryId,
            "providerRootDirectory" to providerRootDirectory,
            "providerSilentMode" to providerSilentMode,
            "specification" to specification,
            "timeout" to timeout,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Site = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Site.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Site::class.java,
            converter,
        )
    }


    /**
     * Update the site active deployment. Use this endpoint to switch the code deployment that should be used when visitor opens your site.
     *
     * @param siteId Site ID.
     * @param deploymentId Deployment ID.
     * @return [com.revenexx.models.Site]
     */
    suspend fun sitesUpdateSiteDeployment(
        siteId: String,
        deploymentId: String,
    ): com.revenexx.models.Site {
        val apiPath = "/v1/sites/{siteId}/deployment"
            .replace("{siteId}", siteId)

        val apiParams = mutableMapOf<String, Any?>(
            "deploymentId" to deploymentId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Site = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Site.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Site::class.java,
            converter,
        )
    }


    /**
     * Get a list of all the site's code deployments. You can use the query params to filter your results.
     *
     * @param siteId Site ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: buildSize, sourceSize, totalSize, buildDuration, status, activate, type
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.DeploymentList]
     */
    @JvmOverloads
    suspend fun sitesListDeployments(
        siteId: String,
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.DeploymentList {
        val apiPath = "/v1/sites/{siteId}/deployments"
            .replace("{siteId}", siteId)

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
     * Create a new site code deployment. Use this endpoint to upload a new version of your site code. To activate your newly uploaded code, you'll need to update the site's deployment to use your new deployment ID.
     *
     * @param siteId Site ID.
     * @param activate Automatically activate the deployment when it is finished building.
     * @param code Gzip file with your code package. When used with the Appwrite CLI, pass the path to your code directory, and the CLI will automatically package your code. Use a path that is within the current directory.
     * @param buildCommand Build Commands.
     * @param installCommand Install Commands.
     * @param outputDirectory Output Directory.
     * @return [com.revenexx.models.Deployment]
     */
    @JvmOverloads
    suspend fun sitesCreateDeployment(
        siteId: String,
        activate: Boolean,
        code: String,
        buildCommand: String? = null,
        installCommand: String? = null,
        outputDirectory: String? = null,
        onProgress: ((UploadProgress) -> Unit)? = null
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/sites/{siteId}/deployments"
            .replace("{siteId}", siteId)

        val apiParams = mutableMapOf<String, Any?>(
            "activate" to activate,
            "buildCommand" to buildCommand,
            "code" to code,
            "installCommand" to installCommand,
            "outputDirectory" to outputDirectory,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "multipart/form-data",
        )
        val converter: (Any) -> com.revenexx.models.Deployment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Deployment.from(map = it as Map<String, Any>)
        }
        val idParamName: String? = null    
        return client.chunkedUpload(
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Deployment::class.java,
            converter,
            paramName,
            idParamName,
            onProgress,
        )
    }


    /**
     * Create a new build for an existing site deployment. This endpoint allows you to rebuild a deployment with the updated site configuration, including its commands and output directory if they have been modified. The build process will be queued and executed asynchronously. The original deployment's code will be preserved and used for the new build.
     *
     * @param siteId Site ID.
     * @param deploymentId Deployment ID.
     * @return [com.revenexx.models.Deployment]
     */
    suspend fun sitesCreateDuplicateDeployment(
        siteId: String,
        deploymentId: String,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/sites/{siteId}/deployments/duplicate"
            .replace("{siteId}", siteId)

        val apiParams = mutableMapOf<String, Any?>(
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
     * Create a deployment based on a template.
     * 
     * Use this endpoint with combination of [listTemplates](https://appwrite.io/docs/products/sites/templates) to find the template details.
     *
     * @param siteId Site ID.
     * @param owner The name of the owner of the template.
     * @param reference Reference value, can be a commit hash, branch name, or release tag
     * @param repository Repository name of the template.
     * @param rootDirectory Path to site code in the template repo.
     * @param type Type for the reference provided. Can be commit, branch, or tag
     * @param activate Automatically activate the deployment when it is finished building.
     * @return [com.revenexx.models.Deployment]
     */
    @JvmOverloads
    suspend fun sitesCreateTemplateDeployment(
        siteId: String,
        owner: String,
        reference: String,
        repository: String,
        rootDirectory: String,
        type: com.revenexx.enums.Type,
        activate: Boolean? = null,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/sites/{siteId}/deployments/template"
            .replace("{siteId}", siteId)

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
     * Create a deployment when a site is connected to VCS.
     * 
     * This endpoint lets you create deployment from a branch, commit, or a tag.
     *
     * @param siteId Site ID.
     * @param reference VCS reference to create deployment from. Depending on type this can be: branch name, commit hash
     * @param type Type of reference passed. Allowed values are: branch, commit
     * @param activate Automatically activate the deployment when it is finished building.
     * @return [com.revenexx.models.Deployment]
     */
    @JvmOverloads
    suspend fun sitesCreateVcsDeployment(
        siteId: String,
        reference: String,
        type: com.revenexx.enums.Type,
        activate: Boolean? = null,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/sites/{siteId}/deployments/vcs"
            .replace("{siteId}", siteId)

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
     * Delete a site deployment by its unique ID.
     *
     * @param siteId Site ID.
     * @param deploymentId Deployment ID.
     * @return [Any]
     */
    suspend fun sitesDeleteDeployment(
        siteId: String,
        deploymentId: String,
    ): Any {
        val apiPath = "/v1/sites/{siteId}/deployments/{deploymentId}"
            .replace("{siteId}", siteId)
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
     * Get a site deployment by its unique ID.
     *
     * @param siteId Site ID.
     * @param deploymentId Deployment ID.
     * @return [com.revenexx.models.Deployment]
     */
    suspend fun sitesGetDeployment(
        siteId: String,
        deploymentId: String,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/sites/{siteId}/deployments/{deploymentId}"
            .replace("{siteId}", siteId)
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
     * Get a site deployment content by its unique ID. The endpoint response return with a 'Content-Disposition: attachment' header that tells the browser to start downloading the file to user downloads directory.
     *
     * @param siteId Site ID.
     * @param deploymentId Deployment ID.
     * @param type Deployment file to download. Can be: "source", "output".
     * @return [Any]
     */
    @JvmOverloads
    suspend fun sitesGetDeploymentDownload(
        siteId: String,
        deploymentId: String,
        type: com.revenexx.enums.Type? = null,
    ): Any {
        val apiPath = "/v1/sites/{siteId}/deployments/{deploymentId}/download"
            .replace("{siteId}", siteId)
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
     * Cancel an ongoing site deployment build. If the build is already in progress, it will be stopped and marked as canceled. If the build hasn't started yet, it will be marked as canceled without executing. You cannot cancel builds that have already completed (status 'ready') or failed. The response includes the final build status and details.
     *
     * @param siteId Site ID.
     * @param deploymentId Deployment ID.
     * @return [com.revenexx.models.Deployment]
     */
    suspend fun sitesUpdateDeploymentStatus(
        siteId: String,
        deploymentId: String,
    ): com.revenexx.models.Deployment {
        val apiPath = "/v1/sites/{siteId}/deployments/{deploymentId}/status"
            .replace("{siteId}", siteId)
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
     * Get a list of all site logs. You can use the query params to filter your results.
     *
     * @param siteId Site ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: trigger, status, responseStatusCode, duration, requestMethod, requestPath, deploymentId
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.ExecutionList]
     */
    @JvmOverloads
    suspend fun sitesListLogs(
        siteId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.ExecutionList {
        val apiPath = "/v1/sites/{siteId}/logs"
            .replace("{siteId}", siteId)

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
     * Delete a site log by its unique ID.
     *
     * @param siteId Site ID.
     * @param logId Log ID.
     * @return [Any]
     */
    suspend fun sitesDeleteLog(
        siteId: String,
        logId: String,
    ): Any {
        val apiPath = "/v1/sites/{siteId}/logs/{logId}"
            .replace("{siteId}", siteId)
            .replace("{logId}", logId)

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
     * Get a site request log by its unique ID.
     *
     * @param siteId Site ID.
     * @param logId Log ID.
     * @return [com.revenexx.models.Execution]
     */
    suspend fun sitesGetLog(
        siteId: String,
        logId: String,
    ): com.revenexx.models.Execution {
        val apiPath = "/v1/sites/{siteId}/logs/{logId}"
            .replace("{siteId}", siteId)
            .replace("{logId}", logId)

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
     * Get a list of all variables of a specific site.
     *
     * @param siteId Site unique ID.
     * @return [com.revenexx.models.VariableList]
     */
    suspend fun sitesListVariables(
        siteId: String,
    ): com.revenexx.models.VariableList {
        val apiPath = "/v1/sites/{siteId}/variables"
            .replace("{siteId}", siteId)

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
     * Create a new site variable. These variables can be accessed during build and runtime (server-side rendering) as environment variables.
     *
     * @param siteId Site unique ID.
     * @param key Variable key. Max length: 255 chars.
     * @param value Variable value. Max length: 8192 chars.
     * @param secret Secret variables can be updated or deleted, but only sites can read them during build and runtime.
     * @return [com.revenexx.models.Variable]
     */
    @JvmOverloads
    suspend fun sitesCreateVariable(
        siteId: String,
        key: String,
        value: String,
        secret: Boolean? = null,
    ): com.revenexx.models.Variable {
        val apiPath = "/v1/sites/{siteId}/variables"
            .replace("{siteId}", siteId)

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
     * Delete a variable by its unique ID.
     *
     * @param siteId Site unique ID.
     * @param variableId Variable unique ID.
     * @return [Any]
     */
    suspend fun sitesDeleteVariable(
        siteId: String,
        variableId: String,
    ): Any {
        val apiPath = "/v1/sites/{siteId}/variables/{variableId}"
            .replace("{siteId}", siteId)
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
     * Get a variable by its unique ID.
     *
     * @param siteId Site unique ID.
     * @param variableId Variable unique ID.
     * @return [com.revenexx.models.Variable]
     */
    suspend fun sitesGetVariable(
        siteId: String,
        variableId: String,
    ): com.revenexx.models.Variable {
        val apiPath = "/v1/sites/{siteId}/variables/{variableId}"
            .replace("{siteId}", siteId)
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
     * Update variable by its unique ID.
     *
     * @param siteId Site unique ID.
     * @param variableId Variable unique ID.
     * @param key Variable key. Max length: 255 chars.
     * @param secret Secret variables can be updated or deleted, but only sites can read them during build and runtime.
     * @param value Variable value. Max length: 8192 chars.
     * @return [com.revenexx.models.Variable]
     */
    @JvmOverloads
    suspend fun sitesUpdateVariable(
        siteId: String,
        variableId: String,
        key: String,
        secret: Boolean? = null,
        value: String? = null,
    ): com.revenexx.models.Variable {
        val apiPath = "/v1/sites/{siteId}/variables/{variableId}"
            .replace("{siteId}", siteId)
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