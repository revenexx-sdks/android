package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Function
 */
data class Function(
    /**
     * Function creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Function ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Function update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * The build command used to build the deployment.
     */
    @SerializedName("commands")
    val commands: String,

    /**
     * Active deployment creation date in ISO 8601 format.
     */
    @SerializedName("deploymentCreatedAt")
    val deploymentCreatedAt: String,

    /**
     * Function's active deployment ID.
     */
    @SerializedName("deploymentId")
    val deploymentId: String,

    /**
     * Function enabled.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * The entrypoint file used to execute the deployment.
     */
    @SerializedName("entrypoint")
    val entrypoint: String,

    /**
     * Function trigger events.
     */
    @SerializedName("events")
    val events: List<String>,

    /**
     * Execution permissions.
     */
    @SerializedName("execute")
    val execute: List<String>,

    /**
     * Function VCS (Version Control System) installation id.
     */
    @SerializedName("installationId")
    val installationId: String,

    /**
     * Latest deployment creation date in ISO 8601 format.
     */
    @SerializedName("latestDeploymentCreatedAt")
    val latestDeploymentCreatedAt: String,

    /**
     * Function's latest deployment ID.
     */
    @SerializedName("latestDeploymentId")
    val latestDeploymentId: String,

    /**
     * Status of latest deployment. Possible values are "waiting", "processing", "building", "ready", and "failed".
     */
    @SerializedName("latestDeploymentStatus")
    val latestDeploymentStatus: String,

    /**
     * Is the function deployed with the latest configuration? This is set to false if you've changed an environment variables, entrypoint, commands, or other settings that needs redeploy to be applied. When the value is false, redeploy the function to update it with the latest configuration.
     */
    @SerializedName("live")
    val live: Boolean,

    /**
     * When disabled, executions will exclude logs and errors, and will be slightly faster.
     */
    @SerializedName("logging")
    val logging: Boolean,

    /**
     * Function name.
     */
    @SerializedName("name")
    val name: String,

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
     * Path to function in VCS (Version Control System) repository
     */
    @SerializedName("providerRootDirectory")
    val providerRootDirectory: String,

    /**
     * Is VCS (Version Control System) connection is in silent mode? When in silence mode, no comments will be posted on the repository pull or merge requests
     */
    @SerializedName("providerSilentMode")
    val providerSilentMode: Boolean,

    /**
     * Function execution and build runtime.
     */
    @SerializedName("runtime")
    val runtime: String,

    /**
     * Function execution schedule in CRON format.
     */
    @SerializedName("schedule")
    val schedule: String,

    /**
     * Allowed permission scopes.
     */
    @SerializedName("scopes")
    val scopes: List<String>,

    /**
     * Machine specification for builds and executions.
     */
    @SerializedName("specification")
    val specification: String,

    /**
     * Function execution timeout in seconds.
     */
    @SerializedName("timeout")
    val timeout: Long,

    /**
     * Function variables.
     */
    @SerializedName("vars")
    val vars: List<Variable>,

    /**
     * Version of Open Runtimes used for the function.
     */
    @SerializedName("version")
    val version: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "commands" to commands as Any,
        "deploymentCreatedAt" to deploymentCreatedAt as Any,
        "deploymentId" to deploymentId as Any,
        "enabled" to enabled as Any,
        "entrypoint" to entrypoint as Any,
        "events" to events as Any,
        "execute" to execute as Any,
        "installationId" to installationId as Any,
        "latestDeploymentCreatedAt" to latestDeploymentCreatedAt as Any,
        "latestDeploymentId" to latestDeploymentId as Any,
        "latestDeploymentStatus" to latestDeploymentStatus as Any,
        "live" to live as Any,
        "logging" to logging as Any,
        "name" to name as Any,
        "providerBranch" to providerBranch as Any,
        "providerRepositoryId" to providerRepositoryId as Any,
        "providerRootDirectory" to providerRootDirectory as Any,
        "providerSilentMode" to providerSilentMode as Any,
        "runtime" to runtime as Any,
        "schedule" to schedule as Any,
        "scopes" to scopes as Any,
        "specification" to specification as Any,
        "timeout" to timeout as Any,
        "vars" to vars.map { it.toMap() } as Any,
        "version" to version as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Function(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            commands = map["commands"] as String,
            deploymentCreatedAt = map["deploymentCreatedAt"] as String,
            deploymentId = map["deploymentId"] as String,
            enabled = map["enabled"] as Boolean,
            entrypoint = map["entrypoint"] as String,
            events = map["events"] as List<String>,
            execute = map["execute"] as List<String>,
            installationId = map["installationId"] as String,
            latestDeploymentCreatedAt = map["latestDeploymentCreatedAt"] as String,
            latestDeploymentId = map["latestDeploymentId"] as String,
            latestDeploymentStatus = map["latestDeploymentStatus"] as String,
            live = map["live"] as Boolean,
            logging = map["logging"] as Boolean,
            name = map["name"] as String,
            providerBranch = map["providerBranch"] as String,
            providerRepositoryId = map["providerRepositoryId"] as String,
            providerRootDirectory = map["providerRootDirectory"] as String,
            providerSilentMode = map["providerSilentMode"] as Boolean,
            runtime = map["runtime"] as String,
            schedule = map["schedule"] as String,
            scopes = map["scopes"] as List<String>,
            specification = map["specification"] as String,
            timeout = (map["timeout"] as Number).toLong(),
            vars = (map["vars"] as List<Map<String, Any>>).map { Variable.from(map = it) },
            version = map["version"] as String,
        )
    }
}