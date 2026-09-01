package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Site
 */
data class Site(
    /**
     * Site creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Site ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Site update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Site framework adapter.
     */
    @SerializedName("adapter")
    val adapter: String,

    /**
     * The build command used to build the site.
     */
    @SerializedName("buildCommand")
    val buildCommand: String,

    /**
     * Site build runtime.
     */
    @SerializedName("buildRuntime")
    val buildRuntime: String,

    /**
     * Active deployment creation date in ISO 8601 format.
     */
    @SerializedName("deploymentCreatedAt")
    val deploymentCreatedAt: String,

    /**
     * Site's active deployment ID.
     */
    @SerializedName("deploymentId")
    val deploymentId: String,

    /**
     * Screenshot of active deployment with dark theme preference file ID.
     */
    @SerializedName("deploymentScreenshotDark")
    val deploymentScreenshotDark: String,

    /**
     * Screenshot of active deployment with light theme preference file ID.
     */
    @SerializedName("deploymentScreenshotLight")
    val deploymentScreenshotLight: String,

    /**
     * Site enabled.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * Name of the fallback file to serve instead of a 404 page. If null, the site runtime's built-in 404 page is served.
     */
    @SerializedName("fallbackFile")
    val fallbackFile: String,

    /**
     * Site framework.
     */
    @SerializedName("framework")
    val framework: String,

    /**
     * The install command used to install the site dependencies.
     */
    @SerializedName("installCommand")
    val installCommand: String,

    /**
     * Site VCS (Version Control System) installation id.
     */
    @SerializedName("installationId")
    val installationId: String,

    /**
     * Latest deployment creation date in ISO 8601 format.
     */
    @SerializedName("latestDeploymentCreatedAt")
    val latestDeploymentCreatedAt: String,

    /**
     * Site's latest deployment ID.
     */
    @SerializedName("latestDeploymentId")
    val latestDeploymentId: String,

    /**
     * Status of latest deployment. Possible values are "waiting", "processing", "building", "ready", and "failed".
     */
    @SerializedName("latestDeploymentStatus")
    val latestDeploymentStatus: String,

    /**
     * Is the site deployed with the latest configuration? This is set to false if you've changed an environment variables, entrypoint, commands, or other settings that needs redeploy to be applied. When the value is false, redeploy the site to update it with the latest configuration.
     */
    @SerializedName("live")
    val live: Boolean,

    /**
     * When disabled, request logs will exclude logs and errors, and site responses will be slightly faster.
     */
    @SerializedName("logging")
    val logging: Boolean,

    /**
     * Site name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * The directory where the site build output is located.
     */
    @SerializedName("outputDirectory")
    val outputDirectory: String,

    /**
     * VCS (Version Control System) branch name
     */
    @SerializedName("providerBranch")
    val providerBranch: String,

    /**
     * VCS (Version Control System) Repository ID
     */
    @SerializedName("providerRepositoryId")
    val providerRepositoryId: String,

    /**
     * Path to site in VCS (Version Control System) repository
     */
    @SerializedName("providerRootDirectory")
    val providerRootDirectory: String,

    /**
     * Is VCS (Version Control System) connection is in silent mode? When in silence mode, no comments will be posted on the repository pull or merge requests
     */
    @SerializedName("providerSilentMode")
    val providerSilentMode: Boolean,

    /**
     * Machine specification for builds and executions.
     */
    @SerializedName("specification")
    val specification: String,

    /**
     * Site request timeout in seconds.
     */
    @SerializedName("timeout")
    val timeout: Long,

    /**
     * Site variables.
     */
    @SerializedName("vars")
    val vars: List<Variable>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "adapter" to adapter as Any,
        "buildCommand" to buildCommand as Any,
        "buildRuntime" to buildRuntime as Any,
        "deploymentCreatedAt" to deploymentCreatedAt as Any,
        "deploymentId" to deploymentId as Any,
        "deploymentScreenshotDark" to deploymentScreenshotDark as Any,
        "deploymentScreenshotLight" to deploymentScreenshotLight as Any,
        "enabled" to enabled as Any,
        "fallbackFile" to fallbackFile as Any,
        "framework" to framework as Any,
        "installCommand" to installCommand as Any,
        "installationId" to installationId as Any,
        "latestDeploymentCreatedAt" to latestDeploymentCreatedAt as Any,
        "latestDeploymentId" to latestDeploymentId as Any,
        "latestDeploymentStatus" to latestDeploymentStatus as Any,
        "live" to live as Any,
        "logging" to logging as Any,
        "name" to name as Any,
        "outputDirectory" to outputDirectory as Any,
        "providerBranch" to providerBranch as Any,
        "providerRepositoryId" to providerRepositoryId as Any,
        "providerRootDirectory" to providerRootDirectory as Any,
        "providerSilentMode" to providerSilentMode as Any,
        "specification" to specification as Any,
        "timeout" to timeout as Any,
        "vars" to vars.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Site(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            adapter = map["adapter"] as String,
            buildCommand = map["buildCommand"] as String,
            buildRuntime = map["buildRuntime"] as String,
            deploymentCreatedAt = map["deploymentCreatedAt"] as String,
            deploymentId = map["deploymentId"] as String,
            deploymentScreenshotDark = map["deploymentScreenshotDark"] as String,
            deploymentScreenshotLight = map["deploymentScreenshotLight"] as String,
            enabled = map["enabled"] as Boolean,
            fallbackFile = map["fallbackFile"] as String,
            framework = map["framework"] as String,
            installCommand = map["installCommand"] as String,
            installationId = map["installationId"] as String,
            latestDeploymentCreatedAt = map["latestDeploymentCreatedAt"] as String,
            latestDeploymentId = map["latestDeploymentId"] as String,
            latestDeploymentStatus = map["latestDeploymentStatus"] as String,
            live = map["live"] as Boolean,
            logging = map["logging"] as Boolean,
            name = map["name"] as String,
            outputDirectory = map["outputDirectory"] as String,
            providerBranch = map["providerBranch"] as String,
            providerRepositoryId = map["providerRepositoryId"] as String,
            providerRootDirectory = map["providerRootDirectory"] as String,
            providerSilentMode = map["providerSilentMode"] as Boolean,
            specification = map["specification"] as String,
            timeout = (map["timeout"] as Number).toLong(),
            vars = (map["vars"] as List<Map<String, Any>>).map { Variable.from(map = it) },
        )
    }
}