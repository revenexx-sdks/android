package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Documents List
 */
data class DocumentList<T>(
    /**
     * List of documents.
     */
    @SerializedName("documents")
    val documents: List<Document<T>>,

    /**
     * Total number of documents that matched your query.
     */
    @SerializedName("total")
    val total: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "documents" to documents.map { it.toMap() } as Any,
        "total" to total as Any,
    )

    companion object {
        operator fun invoke(
            documents: List<Document<Map<String, Any>>>,
            total: Long,
        ) = DocumentList<Map<String, Any>>(
            documents,
            total,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = DocumentList<T>(
            documents = (map["documents"] as List<Map<String, Any>>).map { Document.from(map = it, nestedType) },
            total = (map["total"] as Number).toLong(),
        )
    }
}