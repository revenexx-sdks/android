package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Template Function
 */
data class TemplateFunction(
    /**
     * Function execution schedult in CRON format.
     */
    @SerializedName("cron")
    val cron: String,

    /**
     * Function trigger events.
     */
    @SerializedName("events")
    val events: List<String>,

    /**
     * Function Template Icon.
     */
    @SerializedName("icon")
    val icon: String,

    /**
     * Function Template ID.
     */
    @SerializedName("id")
    val id: String,

    /**
     * Function Template Instructions.
     */
    @SerializedName("instructions")
    val instructions: String,

    /**
     * Function Template Name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Execution permissions.
     */
    @SerializedName("permissions")
    val permissions: List<String>,

    /**
     * VCS (Version Control System) Owner.
     */
    @SerializedName("providerOwner")
    val providerOwner: String,

    /**
     * VCS (Version Control System) Repository ID
     */
    @SerializedName("providerRepositoryId")
    val providerRepositoryId: String,

    /**
     * VCS (Version Control System) branch version (tag).
     */
    @SerializedName("providerVersion")
    val providerVersion: String,

    /**
     * List of runtimes that can be used with this template.
     */
    @SerializedName("runtimes")
    val runtimes: List<TemplateRuntime>,

    /**
     * Function scopes.
     */
    @SerializedName("scopes")
    val scopes: List<String>,

    /**
     * Function Template Tagline.
     */
    @SerializedName("tagline")
    val tagline: String,

    /**
     * Function execution timeout in seconds.
     */
    @SerializedName("timeout")
    val timeout: Long,

    /**
     * Function use cases.
     */
    @SerializedName("useCases")
    val useCases: List<String>,

    /**
     * Function variables.
     */
    @SerializedName("variables")
    val variables: List<TemplateVariable>,

    /**
     * VCS (Version Control System) Provider.
     */
    @SerializedName("vcsProvider")
    val vcsProvider: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cron" to cron as Any,
        "events" to events as Any,
        "icon" to icon as Any,
        "id" to id as Any,
        "instructions" to instructions as Any,
        "name" to name as Any,
        "permissions" to permissions as Any,
        "providerOwner" to providerOwner as Any,
        "providerRepositoryId" to providerRepositoryId as Any,
        "providerVersion" to providerVersion as Any,
        "runtimes" to runtimes.map { it.toMap() } as Any,
        "scopes" to scopes as Any,
        "tagline" to tagline as Any,
        "timeout" to timeout as Any,
        "useCases" to useCases as Any,
        "variables" to variables.map { it.toMap() } as Any,
        "vcsProvider" to vcsProvider as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TemplateFunction(
            cron = map["cron"] as String,
            events = map["events"] as List<String>,
            icon = map["icon"] as String,
            id = map["id"] as String,
            instructions = map["instructions"] as String,
            name = map["name"] as String,
            permissions = map["permissions"] as List<String>,
            providerOwner = map["providerOwner"] as String,
            providerRepositoryId = map["providerRepositoryId"] as String,
            providerVersion = map["providerVersion"] as String,
            runtimes = (map["runtimes"] as List<Map<String, Any>>).map { TemplateRuntime.from(map = it) },
            scopes = map["scopes"] as List<String>,
            tagline = map["tagline"] as String,
            timeout = (map["timeout"] as Number).toLong(),
            useCases = map["useCases"] as List<String>,
            variables = (map["variables"] as List<Map<String, Any>>).map { TemplateVariable.from(map = it) },
            vcsProvider = map["vcsProvider"] as String,
        )
    }
}