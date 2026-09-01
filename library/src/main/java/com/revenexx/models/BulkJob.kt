package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A bulk job as returned by `/bulk-jobs`. Note that the row counts are
 * nested under `counts` — they are not top-level fields — and that the
 * response carries no `tenant_id` (the listing envelope does) and no
 * `updated_at`.
 * 
 */
data class BulkJob(
    /**
     * 
     */
    @SerializedName("app")
    var app: String?,

    /**
     * 
     */
    @SerializedName("correlation_id")
    var correlation_id: String?,

    /**
     * 
     */
    @SerializedName("counts")
    var counts: Any?,

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
    @SerializedName("duration_ms")
    var duration_ms: Long?,

    /**
     * 
     */
    @SerializedName("entity")
    var entity: String?,

    /**
     * 
     */
    @SerializedName("error_message")
    var error_message: String?,

    /**
     * 
     */
    @SerializedName("finished_at")
    var finished_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("profile_id")
    var profile_id: String?,

    /**
     * Engine-reported progress. For an export this carries the
     * `object_key` and `format` the result is written to.
     * 
     */
    @SerializedName("progress")
    var progress: Any?,

    /**
     * 
     */
    @SerializedName("started_at")
    var started_at: String?,

    /**
     * 
     */
    @SerializedName("status")
    var status: BulkJobStatus?,

    /**
     * 
     */
    @SerializedName("type")
    var type: BulkJobType?,

    /**
     * 
     */
    @SerializedName("vendor")
    var vendor: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "app" to app as Any,
        "correlation_id" to correlation_id as Any,
        "counts" to counts as Any,
        "created_at" to created_at as Any,
        "created_by" to created_by as Any,
        "duration_ms" to duration_ms as Any,
        "entity" to entity as Any,
        "error_message" to error_message as Any,
        "finished_at" to finished_at as Any,
        "id" to id as Any,
        "profile_id" to profile_id as Any,
        "progress" to progress as Any,
        "started_at" to started_at as Any,
        "status" to status?.toMap() as Any,
        "type" to type?.toMap() as Any,
        "vendor" to vendor as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = BulkJob(
            app = map["app"] as? String,
            correlation_id = map["correlation_id"] as? String,
            counts = map["counts"] as? Any,
            created_at = map["created_at"] as? String,
            created_by = map["created_by"] as? String,
            duration_ms = (map["duration_ms"] as? Number)?.toLong(),
            entity = map["entity"] as? String,
            error_message = map["error_message"] as? String,
            finished_at = map["finished_at"] as? String,
            id = map["id"] as? String,
            profile_id = map["profile_id"] as? String,
            progress = map["progress"] as? Any,
            started_at = map["started_at"] as? String,
            status = BulkJobStatus.from(map = map["status"] as Map<String, Any>),
            type = BulkJobType.from(map = map["type"] as Map<String, Any>),
            vendor = map["vendor"] as? String,
        )
    }
}