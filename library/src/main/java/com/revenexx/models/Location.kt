package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Location(
    /**
     * 
     */
    @SerializedName("address")
    var address: Any?,

    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("priority")
    var priority: Long?,

    /**
     * 
     */
    @SerializedName("type")
    var type: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "address" to address as Any,
        "code" to code as Any,
        "created_at" to created_at as Any,
        "enabled" to enabled as Any,
        "id" to id as Any,
        "labels" to labels as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "priority" to priority as Any,
        "type" to type as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Location(
            address = map["address"] as? Any,
            code = map["code"] as? String,
            created_at = map["created_at"] as? String,
            enabled = map["enabled"] as? Boolean,
            id = map["id"] as? String,
            labels = map["labels"] as? Any,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            priority = (map["priority"] as? Number)?.toLong(),
            type = map["type"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}