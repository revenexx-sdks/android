package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.DeploymentStatus

/**
 * Deployment
 */
data class Deployment(
    /**
     * Deployment creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Deployment ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Deployment update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Whether the deployment should be automatically activated.
     */
    @SerializedName("activate")
    val activate: Boolean,

    /**
     * Raw billing.json bytes captured from the source archive at deploy time. Empty when no billing.json was shipped (private app).
     */
    @SerializedName("billingJson")
    val billingJson: String,

    /**
     * The current build time in seconds.
     */
    @SerializedName("buildDuration")
    val buildDuration: Long,

    /**
     * The current build ID.
     */
    @SerializedName("buildId")
    val buildId: String,

    /**
     * The build logs.
     */
    @SerializedName("buildLogs")
    val buildLogs: String,

    /**
     * The build output size in bytes.
     */
    @SerializedName("buildSize")
    val buildSize: Long,

    /**
     * The entrypoint file to use to execute the deployment code.
     */
    @SerializedName("entrypoint")
    val entrypoint: String,

    /**
     * Raw manifest.json bytes captured from the source archive at deploy time. Empty for legacy Function/Site deployments without a manifest.
     */
    @SerializedName("manifestJson")
    val manifestJson: String,

    /**
     * The branch of the vcs repository
     */
    @SerializedName("providerBranch")
    val providerBranch: String,

    /**
     * The branch of the vcs repository
     */
    @SerializedName("providerBranchUrl")
    val providerBranchUrl: String,

    /**
     * The name of vcs commit author
     */
    @SerializedName("providerCommitAuthor")
    val providerCommitAuthor: String,

    /**
     * The url of vcs commit author
     */
    @SerializedName("providerCommitAuthorUrl")
    val providerCommitAuthorUrl: String,

    /**
     * The commit hash of the vcs commit
     */
    @SerializedName("providerCommitHash")
    val providerCommitHash: String,

    /**
     * The commit message
     */
    @SerializedName("providerCommitMessage")
    val providerCommitMessage: String,

    /**
     * The url of the vcs commit
     */
    @SerializedName("providerCommitUrl")
    val providerCommitUrl: String,

    /**
     * The name of the vcs provider repository
     */
    @SerializedName("providerRepositoryName")
    val providerRepositoryName: String,

    /**
     * The name of the vcs provider repository owner
     */
    @SerializedName("providerRepositoryOwner")
    val providerRepositoryOwner: String,

    /**
     * The url of the vcs provider repository
     */
    @SerializedName("providerRepositoryUrl")
    val providerRepositoryUrl: String,

    /**
     * Resource ID.
     */
    @SerializedName("resourceId")
    val resourceId: String,

    /**
     * Resource type.
     */
    @SerializedName("resourceType")
    val resourceType: String,

    /**
     * Screenshot with dark theme preference file ID.
     */
    @SerializedName("screenshotDark")
    val screenshotDark: String,

    /**
     * Screenshot with light theme preference file ID.
     */
    @SerializedName("screenshotLight")
    val screenshotLight: String,

    /**
     * The code size in bytes.
     */
    @SerializedName("sourceSize")
    val sourceSize: Long,

    /**
     * The deployment status. Possible values are "waiting", "processing", "building", "ready", "canceled" and "failed".
     */
    @SerializedName("status")
    val status: DeploymentStatus,

    /**
     * The total size in bytes (source and build output).
     */
    @SerializedName("totalSize")
    val totalSize: Long,

    /**
     * Type of deployment.
     */
    @SerializedName("type")
    val type: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "activate" to activate as Any,
        "billingJson" to billingJson as Any,
        "buildDuration" to buildDuration as Any,
        "buildId" to buildId as Any,
        "buildLogs" to buildLogs as Any,
        "buildSize" to buildSize as Any,
        "entrypoint" to entrypoint as Any,
        "manifestJson" to manifestJson as Any,
        "providerBranch" to providerBranch as Any,
        "providerBranchUrl" to providerBranchUrl as Any,
        "providerCommitAuthor" to providerCommitAuthor as Any,
        "providerCommitAuthorUrl" to providerCommitAuthorUrl as Any,
        "providerCommitHash" to providerCommitHash as Any,
        "providerCommitMessage" to providerCommitMessage as Any,
        "providerCommitUrl" to providerCommitUrl as Any,
        "providerRepositoryName" to providerRepositoryName as Any,
        "providerRepositoryOwner" to providerRepositoryOwner as Any,
        "providerRepositoryUrl" to providerRepositoryUrl as Any,
        "resourceId" to resourceId as Any,
        "resourceType" to resourceType as Any,
        "screenshotDark" to screenshotDark as Any,
        "screenshotLight" to screenshotLight as Any,
        "sourceSize" to sourceSize as Any,
        "status" to status.value as Any,
        "totalSize" to totalSize as Any,
        "type" to type as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Deployment(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            activate = map["activate"] as Boolean,
            billingJson = map["billingJson"] as String,
            buildDuration = (map["buildDuration"] as Number).toLong(),
            buildId = map["buildId"] as String,
            buildLogs = map["buildLogs"] as String,
            buildSize = (map["buildSize"] as Number).toLong(),
            entrypoint = map["entrypoint"] as String,
            manifestJson = map["manifestJson"] as String,
            providerBranch = map["providerBranch"] as String,
            providerBranchUrl = map["providerBranchUrl"] as String,
            providerCommitAuthor = map["providerCommitAuthor"] as String,
            providerCommitAuthorUrl = map["providerCommitAuthorUrl"] as String,
            providerCommitHash = map["providerCommitHash"] as String,
            providerCommitMessage = map["providerCommitMessage"] as String,
            providerCommitUrl = map["providerCommitUrl"] as String,
            providerRepositoryName = map["providerRepositoryName"] as String,
            providerRepositoryOwner = map["providerRepositoryOwner"] as String,
            providerRepositoryUrl = map["providerRepositoryUrl"] as String,
            resourceId = map["resourceId"] as String,
            resourceType = map["resourceType"] as String,
            screenshotDark = map["screenshotDark"] as String,
            screenshotLight = map["screenshotLight"] as String,
            sourceSize = (map["sourceSize"] as Number).toLong(),
            status = DeploymentStatus.values().find { it.value == map["status"] as String }!!,
            totalSize = (map["totalSize"] as Number).toLong(),
            type = map["type"] as String,
        )
    }
}