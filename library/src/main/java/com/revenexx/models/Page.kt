package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Page(
    /**
     * 
     */
    @SerializedName("analyze_ignored")
    var analyze_ignored: Any?,

    /**
     * 
     */
    @SerializedName("bundle")
    var bundle: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("created_by")
    var created_by: String?,

    /**
     * 
     */
    @SerializedName("deleted_at")
    var deleted_at: String?,

    /**
     * 
     */
    @SerializedName("host_options")
    var host_options: Any?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("meta")
    var meta: Any?,

    /**
     * 
     */
    @SerializedName("published_revision_id")
    var published_revision_id: String?,

    /**
     * 
     */
    @SerializedName("slug")
    var slug: String?,

    /**
     * 
     */
    @SerializedName("source_language")
    var source_language: String?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
     */
    @SerializedName("title")
    var title: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
     */
    @SerializedName("updated_by")
    var updated_by: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "analyze_ignored" to analyze_ignored as Any,
        "bundle" to bundle as Any,
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "deleted_at" to deleted_at as Any,
        "host_options" to host_options as Any,
        "id" to id as Any,
        "meta" to meta as Any,
        "published_revision_id" to published_revision_id as Any,
        "slug" to slug as Any,
        "source_language" to source_language as Any,
        "status" to status as Any,
        "title" to title as Any,
        "updated_at" to updated_at as Any,
        "updated_by" to updated_by as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Page(
            analyze_ignored = map["analyze_ignored"] as? Any,
            bundle = map["bundle"] as? String,
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            deleted_at = map["deleted_at"] as? String,
            host_options = map["host_options"] as? Any,
            id = map["id"] as? String,
            meta = map["meta"] as? Any,
            published_revision_id = map["published_revision_id"] as? String,
            slug = map["slug"] as? String,
            source_language = map["source_language"] as? String,
            status = map["status"] as? String,
            title = map["title"] as? String,
            updated_at = map["updated_at"] as? String,
            updated_by = map["updated_by"] as? String,
        )
    }
}