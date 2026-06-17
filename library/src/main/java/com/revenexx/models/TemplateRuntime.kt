package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Template Runtime
 */
data class TemplateRuntime(
    /**
     * The build command used to build the deployment.
     */
    @SerializedName("commands")
    val commands: String,

    /**
     * The entrypoint file used to execute the deployment.
     */
    @SerializedName("entrypoint")
    val entrypoint: String,

    /**
     * Runtime Name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Path to function in VCS (Version Control System) repository
     */
    @SerializedName("providerRootDirectory")
    val providerRootDirectory: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "commands" to commands as Any,
        "entrypoint" to entrypoint as Any,
        "name" to name as Any,
        "providerRootDirectory" to providerRootDirectory as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TemplateRuntime(
            commands = map["commands"] as String,
            entrypoint = map["entrypoint"] as String,
            name = map["name"] as String,
            providerRootDirectory = map["providerRootDirectory"] as String,
        )
    }
}