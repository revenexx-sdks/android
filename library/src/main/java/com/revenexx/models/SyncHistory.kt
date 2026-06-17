package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class SyncHistory(
    /**
     * 
     */
    @SerializedName("bytes_synced")
    val bytes_synced: Long,

    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("duration_ms")
    val duration_ms: Long,

    /**
     * 
     */
    @SerializedName("error")
    val error: String,

    /**
     * 
     */
    @SerializedName("id")
    val id: Long,

    /**
     * 
     */
    @SerializedName("rule_id")
    val rule_id: String,

    /**
     * 
     */
    @SerializedName("run_id")
    val run_id: String,

    /**
     * 
     */
    @SerializedName("source_path")
    val source_path: String,

    /**
     * 
     */
    @SerializedName("status")
    val status: String,

    /**
     * 
     */
    @SerializedName("target_asset_id")
    val target_asset_id: String,

    /**
     * 
     */
    @SerializedName("tenant_id")
    val tenant_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "bytes_synced" to bytes_synced as Any,
        "created_at" to created_at as Any,
        "duration_ms" to duration_ms as Any,
        "error" to error as Any,
        "id" to id as Any,
        "rule_id" to rule_id as Any,
        "run_id" to run_id as Any,
        "source_path" to source_path as Any,
        "status" to status as Any,
        "target_asset_id" to target_asset_id as Any,
        "tenant_id" to tenant_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SyncHistory(
            bytes_synced = (map["bytes_synced"] as Number).toLong(),
            created_at = map["created_at"] as String,
            duration_ms = (map["duration_ms"] as Number).toLong(),
            error = map["error"] as String,
            id = (map["id"] as Number).toLong(),
            rule_id = map["rule_id"] as String,
            run_id = map["run_id"] as String,
            source_path = map["source_path"] as String,
            status = map["status"] as String,
            target_asset_id = map["target_asset_id"] as String,
            tenant_id = map["tenant_id"] as String,
        )
    }
}