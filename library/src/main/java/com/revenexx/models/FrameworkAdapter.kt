package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Framework Adapter
 */
data class FrameworkAdapter(
    /**
     * Default command to build site into output directory.
     */
    @SerializedName("buildCommand")
    val buildCommand: String,

    /**
     * Name of fallback file to use instead of 404 page. If null, Appwrite 404 page will be displayed.
     */
    @SerializedName("fallbackFile")
    val fallbackFile: String,

    /**
     * Default command to download dependencies.
     */
    @SerializedName("installCommand")
    val installCommand: String,

    /**
     * Adapter key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Default output directory of build.
     */
    @SerializedName("outputDirectory")
    val outputDirectory: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "buildCommand" to buildCommand as Any,
        "fallbackFile" to fallbackFile as Any,
        "installCommand" to installCommand as Any,
        "key" to key as Any,
        "outputDirectory" to outputDirectory as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FrameworkAdapter(
            buildCommand = map["buildCommand"] as String,
            fallbackFile = map["fallbackFile"] as String,
            installCommand = map["installCommand"] as String,
            key = map["key"] as String,
            outputDirectory = map["outputDirectory"] as String,
        )
    }
}