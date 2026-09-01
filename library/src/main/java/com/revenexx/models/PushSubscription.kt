package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PushSubscription(
    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("endpoint")
    val endpoint: String,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("last_seen_at")
    val last_seen_at: String,

    /**
     * 
     */
    @SerializedName("subscriber_id")
    val subscriber_id: String,

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
     * 
     */
    @SerializedName("user_agent")
    val user_agent: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "endpoint" to endpoint as Any,
        "id" to id as Any,
        "last_seen_at" to last_seen_at as Any,
        "subscriber_id" to subscriber_id as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
        "user_agent" to user_agent as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PushSubscription(
            created_at = map["created_at"] as String,
            endpoint = map["endpoint"] as String,
            id = map["id"] as String,
            last_seen_at = map["last_seen_at"] as String,
            subscriber_id = map["subscriber_id"] as String,
            tenant_id = map["tenant_id"] as String,
            updated_at = map["updated_at"] as String,
            user_agent = map["user_agent"] as String,
        )
    }
}