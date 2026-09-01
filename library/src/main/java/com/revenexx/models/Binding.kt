package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Binding(
    /**
     * 
     */
    @SerializedName("channel")
    val channel: String,

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
    @SerializedName("event_topic")
    val event_topic: String,

    /**
     * 
     */
    @SerializedName("fallback_order")
    val fallback_order: Long,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("locale")
    val locale: String,

    /**
     * 
     */
    @SerializedName("recipient")
    val recipient: String,

    /**
     * 
     */
    @SerializedName("template_key")
    val template_key: String,

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
        "channel" to channel as Any,
        "created_at" to created_at as Any,
        "enabled" to enabled as Any,
        "event_topic" to event_topic as Any,
        "fallback_order" to fallback_order as Any,
        "id" to id as Any,
        "locale" to locale as Any,
        "recipient" to recipient as Any,
        "template_key" to template_key as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Binding(
            channel = map["channel"] as String,
            created_at = map["created_at"] as String,
            enabled = map["enabled"] as Boolean,
            event_topic = map["event_topic"] as String,
            fallback_order = (map["fallback_order"] as Number).toLong(),
            id = map["id"] as String,
            locale = map["locale"] as String,
            recipient = map["recipient"] as String,
            template_key = map["template_key"] as String,
            tenant_id = map["tenant_id"] as String,
            updated_at = map["updated_at"] as String,
        )
    }
}