package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Runtime
 */
data class Runtime(
    /**
     * Runtime ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Base Docker image used to build the runtime.
     */
    @SerializedName("base")
    val base: String,

    /**
     * Image name of Docker Hub.
     */
    @SerializedName("image")
    val image: String,

    /**
     * Parent runtime key.
     */
    @SerializedName("key")
    val key: String,

    /**
     * Name of the logo image.
     */
    @SerializedName("logo")
    val logo: String,

    /**
     * Runtime Name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * List of supported architectures.
     */
    @SerializedName("supports")
    val supports: List<String>,

    /**
     * Runtime version.
     */
    @SerializedName("version")
    val version: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$id" to id as Any,
        "base" to base as Any,
        "image" to image as Any,
        "key" to key as Any,
        "logo" to logo as Any,
        "name" to name as Any,
        "supports" to supports as Any,
        "version" to version as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Runtime(
            id = map["\$id"] as String,
            base = map["base"] as String,
            image = map["image"] as String,
            key = map["key"] as String,
            logo = map["logo"] as String,
            name = map["name"] as String,
            supports = map["supports"] as List<String>,
            version = map["version"] as String,
        )
    }
}