package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class SyncRuleResource(
    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("last_run_at")
    val last_run_at: String,

    /**
     * 
     */
    @SerializedName("options")
    val options: List<Any>,

    /**
     * 
     */
    @SerializedName("schedule")
    val schedule: String,

    /**
     * 
     */
    @SerializedName("sftp_account_id")
    val sftp_account_id: String,

    /**
     * 
     */
    @SerializedName("source_path")
    val source_path: String,

    /**
     * 
     */
    @SerializedName("target_folder_id")
    val target_folder_id: String,

    /**
     * 
     */
    @SerializedName("tenant_id")
    val tenant_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "enabled" to enabled as Any,
        "id" to id as Any,
        "last_run_at" to last_run_at as Any,
        "options" to options as Any,
        "schedule" to schedule as Any,
        "sftp_account_id" to sftp_account_id as Any,
        "source_path" to source_path as Any,
        "target_folder_id" to target_folder_id as Any,
        "tenant_id" to tenant_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SyncRuleResource(
            created_at = map["created_at"] as String,
            enabled = map["enabled"] as Boolean,
            id = map["id"] as String,
            last_run_at = map["last_run_at"] as String,
            options = map["options"] as List<Any>,
            schedule = map["schedule"] as String,
            sftp_account_id = map["sftp_account_id"] as String,
            source_path = map["source_path"] as String,
            target_folder_id = map["target_folder_id"] as String,
            tenant_id = map["tenant_id"] as String,
        )
    }
}