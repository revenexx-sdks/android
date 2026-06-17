package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Bucket
 */
data class Bucket(
    /**
     * Bucket creation time in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Bucket ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Bucket permissions. [Learn more about permissions](https://appwrite.io/docs/permissions).
     */
    @SerializedName("\$permissions")
    val permissions: List<String>,

    /**
     * Bucket update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Allowed file extensions.
     */
    @SerializedName("allowedFileExtensions")
    val allowedFileExtensions: List<String>,

    /**
     * Virus scanning is enabled.
     */
    @SerializedName("antivirus")
    val antivirus: Boolean,

    /**
     * Compression algorithm chosen for compression. Will be one of none, [gzip](https://en.wikipedia.org/wiki/Gzip), or [zstd](https://en.wikipedia.org/wiki/Zstd).
     */
    @SerializedName("compression")
    val compression: String,

    /**
     * Bucket enabled.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * Bucket is encrypted.
     */
    @SerializedName("encryption")
    val encryption: Boolean,

    /**
     * Whether file-level security is enabled. [Learn more about permissions](https://appwrite.io/docs/permissions).
     */
    @SerializedName("fileSecurity")
    val fileSecurity: Boolean,

    /**
     * Maximum file size supported.
     */
    @SerializedName("maximumFileSize")
    val maximumFileSize: Long,

    /**
     * Bucket name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Total size of this bucket in bytes.
     */
    @SerializedName("totalSize")
    val totalSize: Long,

    /**
     * Image transformations are enabled.
     */
    @SerializedName("transformations")
    val transformations: Boolean,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$permissions" to permissions as Any,
        "\$updatedAt" to updatedAt as Any,
        "allowedFileExtensions" to allowedFileExtensions as Any,
        "antivirus" to antivirus as Any,
        "compression" to compression as Any,
        "enabled" to enabled as Any,
        "encryption" to encryption as Any,
        "fileSecurity" to fileSecurity as Any,
        "maximumFileSize" to maximumFileSize as Any,
        "name" to name as Any,
        "totalSize" to totalSize as Any,
        "transformations" to transformations as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Bucket(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            permissions = map["\$permissions"] as List<String>,
            updatedAt = map["\$updatedAt"] as String,
            allowedFileExtensions = map["allowedFileExtensions"] as List<String>,
            antivirus = map["antivirus"] as Boolean,
            compression = map["compression"] as String,
            enabled = map["enabled"] as Boolean,
            encryption = map["encryption"] as Boolean,
            fileSecurity = map["fileSecurity"] as Boolean,
            maximumFileSize = (map["maximumFileSize"] as Number).toLong(),
            name = map["name"] as String,
            totalSize = (map["totalSize"] as Number).toLong(),
            transformations = map["transformations"] as Boolean,
        )
    }
}