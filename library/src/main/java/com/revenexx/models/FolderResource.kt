package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FolderResource(
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
    @SerializedName("is_system")
    val is_system: Boolean,

    /**
     * 
     */
    @SerializedName("name")
    val name: String,

    /**
     * 
     */
    @SerializedName("parent_id")
    val parent_id: String,

    /**
     * 
     */
    @SerializedName("xpath")
    val xpath: String,

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

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "id" to id as Any,
        "is_system" to is_system as Any,
        "name" to name as Any,
        "parent_id" to parent_id as Any,
        "path" to xpath as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FolderResource(
            created_at = map["created_at"] as String,
            id = map["id"] as String,
            is_system = map["is_system"] as Boolean,
            name = map["name"] as String,
            parent_id = map["parent_id"] as String,
            xpath = map["path"] as String,
            tenant_id = map["tenant_id"] as String,
            updated_at = map["updated_at"] as String,
        )
    }
}