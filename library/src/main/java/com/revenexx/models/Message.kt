package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Message(
    /**
     * 
     */
    @SerializedName("attachments")
    val attachments: List<Any>,

    /**
     * 
     */
    @SerializedName("attempts")
    val attempts: Long,

    /**
     * 
     */
    @SerializedName("binding_id")
    val binding_id: String,

    /**
     * 
     */
    @SerializedName("channel")
    val channel: String,

    /**
     * 
     */
    @SerializedName("click_count")
    val click_count: Long,

    /**
     * 
     */
    @SerializedName("clicked_at")
    val clicked_at: String,

    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("data")
    val data: List<Any>,

    /**
     * 
     */
    @SerializedName("delivered_at")
    val delivered_at: String,

    /**
     * 
     */
    @SerializedName("error")
    val error: String,

    /**
     * 
     */
    @SerializedName("from_draft")
    val from_draft: Boolean,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("idempotency_fingerprint")
    val idempotency_fingerprint: String,

    /**
     * 
     */
    @SerializedName("idempotency_key")
    val idempotency_key: String,

    /**
     * 
     */
    @SerializedName("locale")
    val locale: String,

    /**
     * 
     */
    @SerializedName("market")
    val market: String,

    /**
     * 
     */
    @SerializedName("message_class")
    val message_class: String,

    /**
     * 
     */
    @SerializedName("open_count")
    val open_count: Long,

    /**
     * 
     */
    @SerializedName("opened_at")
    val opened_at: String,

    /**
     * 
     */
    @SerializedName("provider_message_id")
    val provider_message_id: String,

    /**
     * 
     */
    @SerializedName("scheduled_for")
    val scheduled_for: String,

    /**
     * 
     */
    @SerializedName("sent_at")
    val sent_at: String,

    /**
     * 
     */
    @SerializedName("source_event_id")
    val source_event_id: String,

    /**
     * 
     */
    @SerializedName("status")
    val status: String,

    /**
     * 
     */
    @SerializedName("subject")
    val subject: String,

    /**
     * 
     */
    @SerializedName("suppression_reason")
    val suppression_reason: String,

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
    @SerializedName("to")
    val to: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "attachments" to attachments as Any,
        "attempts" to attempts as Any,
        "binding_id" to binding_id as Any,
        "channel" to channel as Any,
        "click_count" to click_count as Any,
        "clicked_at" to clicked_at as Any,
        "created_at" to created_at as Any,
        "data" to data as Any,
        "delivered_at" to delivered_at as Any,
        "error" to error as Any,
        "from_draft" to from_draft as Any,
        "id" to id as Any,
        "idempotency_fingerprint" to idempotency_fingerprint as Any,
        "idempotency_key" to idempotency_key as Any,
        "locale" to locale as Any,
        "market" to market as Any,
        "message_class" to message_class as Any,
        "open_count" to open_count as Any,
        "opened_at" to opened_at as Any,
        "provider_message_id" to provider_message_id as Any,
        "scheduled_for" to scheduled_for as Any,
        "sent_at" to sent_at as Any,
        "source_event_id" to source_event_id as Any,
        "status" to status as Any,
        "subject" to subject as Any,
        "suppression_reason" to suppression_reason as Any,
        "template_key" to template_key as Any,
        "tenant_id" to tenant_id as Any,
        "to" to to as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Message(
            attachments = map["attachments"] as List<Any>,
            attempts = (map["attempts"] as Number).toLong(),
            binding_id = map["binding_id"] as String,
            channel = map["channel"] as String,
            click_count = (map["click_count"] as Number).toLong(),
            clicked_at = map["clicked_at"] as String,
            created_at = map["created_at"] as String,
            data = map["data"] as List<Any>,
            delivered_at = map["delivered_at"] as String,
            error = map["error"] as String,
            from_draft = map["from_draft"] as Boolean,
            id = map["id"] as String,
            idempotency_fingerprint = map["idempotency_fingerprint"] as String,
            idempotency_key = map["idempotency_key"] as String,
            locale = map["locale"] as String,
            market = map["market"] as String,
            message_class = map["message_class"] as String,
            open_count = (map["open_count"] as Number).toLong(),
            opened_at = map["opened_at"] as String,
            provider_message_id = map["provider_message_id"] as String,
            scheduled_for = map["scheduled_for"] as String,
            sent_at = map["sent_at"] as String,
            source_event_id = map["source_event_id"] as String,
            status = map["status"] as String,
            subject = map["subject"] as String,
            suppression_reason = map["suppression_reason"] as String,
            template_key = map["template_key"] as String,
            tenant_id = map["tenant_id"] as String,
            to = map["to"] as String,
        )
    }
}