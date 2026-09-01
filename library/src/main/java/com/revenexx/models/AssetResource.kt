package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AssetResource(
    /**
     * 
     */
    @SerializedName("alt_text")
    val alt_text: String,

    /**
     * 
     */
    @SerializedName("content_hash")
    val content_hash: String,

    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("deleted_at")
    val deleted_at: String,

    /**
     * 
     */
    @SerializedName("description")
    val description: String,

    /**
     * 
     */
    @SerializedName("display_name")
    val display_name: String,

    /**
     * 
     */
    @SerializedName("dominant_color")
    val dominant_color: String,

    /**
     * 
     */
    @SerializedName("duration_ms")
    val duration_ms: Long,

    /**
     * 
     */
    @SerializedName("folder_id")
    val folder_id: String,

    /**
     * 
     */
    @SerializedName("height")
    val height: Long,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("kind")
    val kind: String,

    /**
     * 
     */
    @SerializedName("metadata")
    val metadata: List<Any>,

    /**
     * 
     */
    @SerializedName("mime_type")
    val mime_type: String,

    /**
     * 
     */
    @SerializedName("model_url")
    val model_url: String,

    /**
     * 
     */
    @SerializedName("original_name")
    val original_name: String,

    /**
     * 
     */
    @SerializedName("page_count")
    val page_count: Long,

    /**
     * 
     */
    @SerializedName("path_name")
    val path_name: String,

    /**
     * 3D derivatives (null unless rendered): preview image + .glb mesh.
     */
    @SerializedName("preview_url")
    val preview_url: String,

    /**
     * 
     */
    @SerializedName("processed_at")
    val processed_at: String,

    /**
     * 
     */
    @SerializedName("size_bytes")
    val size_bytes: Long,

    /**
     * 
     */
    @SerializedName("status")
    val status: String,

    /**
     * 
     */
    @SerializedName("tags")
    val tags: List<Any>,

    /**
     * 
     */
    @SerializedName("tenant_id")
    val tenant_id: String,

    /**
     * 
     */
    @SerializedName("updated_at")
    val updated_at: String,

    /**
     * Null for a private asset — it is only reachable through a signed
     * URL, so there is no path-addressed public URL to hand out.
     */
    @SerializedName("url")
    val url: String,

    /**
     * 
     */
    @SerializedName("usdz_url")
    val usdz_url: String,

    /**
     * 
     */
    @SerializedName("visibility")
    val visibility: String,

    /**
     * 
     */
    @SerializedName("width")
    val width: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "alt_text" to alt_text as Any,
        "content_hash" to content_hash as Any,
        "created_at" to created_at as Any,
        "deleted_at" to deleted_at as Any,
        "description" to description as Any,
        "display_name" to display_name as Any,
        "dominant_color" to dominant_color as Any,
        "duration_ms" to duration_ms as Any,
        "folder_id" to folder_id as Any,
        "height" to height as Any,
        "id" to id as Any,
        "kind" to kind as Any,
        "metadata" to metadata as Any,
        "mime_type" to mime_type as Any,
        "model_url" to model_url as Any,
        "original_name" to original_name as Any,
        "page_count" to page_count as Any,
        "path_name" to path_name as Any,
        "preview_url" to preview_url as Any,
        "processed_at" to processed_at as Any,
        "size_bytes" to size_bytes as Any,
        "status" to status as Any,
        "tags" to tags as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
        "url" to url as Any,
        "usdz_url" to usdz_url as Any,
        "visibility" to visibility as Any,
        "width" to width as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AssetResource(
            alt_text = map["alt_text"] as String,
            content_hash = map["content_hash"] as String,
            created_at = map["created_at"] as String,
            deleted_at = map["deleted_at"] as String,
            description = map["description"] as String,
            display_name = map["display_name"] as String,
            dominant_color = map["dominant_color"] as String,
            duration_ms = (map["duration_ms"] as Number).toLong(),
            folder_id = map["folder_id"] as String,
            height = (map["height"] as Number).toLong(),
            id = map["id"] as String,
            kind = map["kind"] as String,
            metadata = map["metadata"] as List<Any>,
            mime_type = map["mime_type"] as String,
            model_url = map["model_url"] as String,
            original_name = map["original_name"] as String,
            page_count = (map["page_count"] as Number).toLong(),
            path_name = map["path_name"] as String,
            preview_url = map["preview_url"] as String,
            processed_at = map["processed_at"] as String,
            size_bytes = (map["size_bytes"] as Number).toLong(),
            status = map["status"] as String,
            tags = map["tags"] as List<Any>,
            tenant_id = map["tenant_id"] as String,
            updated_at = map["updated_at"] as String,
            url = map["url"] as String,
            usdz_url = map["usdz_url"] as String,
            visibility = map["visibility"] as String,
            width = (map["width"] as Number).toLong(),
        )
    }
}