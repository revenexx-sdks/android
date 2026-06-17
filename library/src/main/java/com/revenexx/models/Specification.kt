package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Specification
 */
data class Specification(
    /**
     * Number of CPUs.
     */
    @SerializedName("cpus")
    val cpus: Double,

    /**
     * Is size enabled.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * Memory size in MB.
     */
    @SerializedName("memory")
    val memory: Long,

    /**
     * Size slug.
     */
    @SerializedName("slug")
    val slug: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cpus" to cpus as Any,
        "enabled" to enabled as Any,
        "memory" to memory as Any,
        "slug" to slug as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Specification(
            cpus = (map["cpus"] as Number).toDouble(),
            enabled = map["enabled"] as Boolean,
            memory = (map["memory"] as Number).toLong(),
            slug = map["slug"] as String,
        )
    }
}