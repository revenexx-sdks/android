package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuditEntry(
    /**
     * 
     */
    @SerializedName("action")
    val action: String,

    /**
     * 
     */
    @SerializedName("changes")
    val changes: List<Any>,

    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("resource_id")
    val resource_id: String,

    /**
     * 
     */
    @SerializedName("resource_key")
    val resource_key: String,

    /**
     * 
     */
    @SerializedName("resource_type")
    val resource_type: String,

    /**
     * 
     */
    @SerializedName("subject")
    val subject: String,

    /**
     * 
     */
    @SerializedName("tenant_id")
    val tenant_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "action" to action as Any,
        "changes" to changes as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "resource_id" to resource_id as Any,
        "resource_key" to resource_key as Any,
        "resource_type" to resource_type as Any,
        "subject" to subject as Any,
        "tenant_id" to tenant_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuditEntry(
            action = map["action"] as String,
            changes = map["changes"] as List<Any>,
            created_at = map["created_at"] as String,
            id = map["id"] as String,
            resource_id = map["resource_id"] as String,
            resource_key = map["resource_key"] as String,
            resource_type = map["resource_type"] as String,
            subject = map["subject"] as String,
            tenant_id = map["tenant_id"] as String,
        )
    }
}