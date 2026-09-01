package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * File
 */
data class File(
    /**
     * File creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * File ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * File permissions. Each entry is a permission string: an action wrapping a role, e.g. `read("any")`, `update("user:abc")`, `delete("team:abc/owner")`. Actions are `read`, `create`, `update`, `delete` and the aggregate `write` (= create + update + delete); the role inside the quotes takes the form described under “Role strings” in this document's introduction.
     */
    @SerializedName("\$permissions")
    val permissions: List<String>,

    /**
     * File update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Bucket ID.
     */
    @SerializedName("bucketId")
    val bucketId: String,

    /**
     * Total number of chunks available
     */
    @SerializedName("chunksTotal")
    val chunksTotal: Long,

    /**
     * Total number of chunks uploaded
     */
    @SerializedName("chunksUploaded")
    val chunksUploaded: Long,

    /**
     * Compression algorithm used for the file. Will be one of none, [gzip](https://en.wikipedia.org/wiki/Gzip), or [zstd](https://en.wikipedia.org/wiki/Zstd).
     */
    @SerializedName("compression")
    val compression: String,

    /**
     * Whether file contents are encrypted at rest.
     */
    @SerializedName("encryption")
    val encryption: Boolean,

    /**
     * File mime type.
     */
    @SerializedName("mimeType")
    val mimeType: String,

    /**
     * File name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * File MD5 signature.
     */
    @SerializedName("signature")
    val signature: String,

    /**
     * File original size in bytes.
     */
    @SerializedName("sizeOriginal")
    val sizeOriginal: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$permissions" to permissions as Any,
        "\$updatedAt" to updatedAt as Any,
        "bucketId" to bucketId as Any,
        "chunksTotal" to chunksTotal as Any,
        "chunksUploaded" to chunksUploaded as Any,
        "compression" to compression as Any,
        "encryption" to encryption as Any,
        "mimeType" to mimeType as Any,
        "name" to name as Any,
        "signature" to signature as Any,
        "sizeOriginal" to sizeOriginal as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = File(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            permissions = map["\$permissions"] as List<String>,
            updatedAt = map["\$updatedAt"] as String,
            bucketId = map["bucketId"] as String,
            chunksTotal = (map["chunksTotal"] as Number).toLong(),
            chunksUploaded = (map["chunksUploaded"] as Number).toLong(),
            compression = map["compression"] as String,
            encryption = map["encryption"] as Boolean,
            mimeType = map["mimeType"] as String,
            name = map["name"] as String,
            signature = map["signature"] as String,
            sizeOriginal = (map["sizeOriginal"] as Number).toLong(),
        )
    }
}