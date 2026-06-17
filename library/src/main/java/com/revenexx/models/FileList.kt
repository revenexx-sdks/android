package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Files List
 */
data class FileList(
    /**
     * List of files.
     */
    @SerializedName("files")
    val files: List<File>,

    /**
     * Total number of files that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "files" to files.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FileList(
            files = (map["files"] as List<Map<String, Any>>).map { File.from(map = it) },
            total = (map["total"] as Number).toLong(),
        )
    }
}