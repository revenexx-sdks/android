package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Framework
 */
data class Framework(
    /**
     * List of supported adapters.
     */
    @SerializedName("adapters")
    val adapters: List<FrameworkAdapter>,

    /**
     * Default runtime version.
     */
    @SerializedName("buildRuntime")
    val buildRuntime: String,

    /**
     * Framework key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Framework Name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * List of supported runtime versions.
     */
    @SerializedName("runtimes")
    val runtimes: List<String>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "adapters" to adapters.map { it.toMap() } as Any,
        "buildRuntime" to buildRuntime as Any,
        "key" to key as Any,
        "name" to name as Any,
        "runtimes" to runtimes as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Framework(
            adapters = (map["adapters"] as List<Map<String, Any>>).map { FrameworkAdapter.from(map = it) },
            buildRuntime = map["buildRuntime"] as String,
            key = map["key"] as String,
            name = map["name"] as String,
            runtimes = map["runtimes"] as List<String>,
        )
    }
}