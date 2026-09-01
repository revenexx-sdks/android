package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Template(
    /**
     * 
     */
    @SerializedName("body_html")
    val body_html: String,

    /**
     * 
     */
    @SerializedName("body_text")
    val body_text: String,

    /**
     * 
     */
    @SerializedName("channel")
    val channel: String,

    /**
     * 
     */
    @SerializedName("content_sid")
    val content_sid: String,

    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("design")
    val design: List<Any>,

    /**
     * 
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * 
     */
    @SerializedName("has_unpublished_changes")
    val has_unpublished_changes: String,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("is_published")
    val is_published: String,

    /**
     * 
     */
    @SerializedName("key")
    val key: String,

    /**
     * 
     */
    @SerializedName("layout_id")
    val layout_id: String,

    /**
     * 
     */
    @SerializedName("lifecycle_state")
    val lifecycle_state: String,

    /**
     * 
     */
    @SerializedName("locale")
    val locale: String,

    /**
     * 
     */
    @SerializedName("markets")
    val markets: List<Any>,

    /**
     * 
     */
    @SerializedName("message_class")
    val message_class: String,

    /**
     * 
     */
    @SerializedName("published_version_id")
    val published_version_id: String,

    /**
     * 
     */
    @SerializedName("source_library_key")
    val source_library_key: String,

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

    /**
     * 
     */
    @SerializedName("test_mode")
    val test_mode: Boolean,

    /**
     * 
     */
    @SerializedName("title")
    val title: String,

    /**
     * 
     */
    @SerializedName("updated_at")
    val updated_at: String,

    /**
     * 
     */
    @SerializedName("uses_raw_html")
    val uses_raw_html: String,

    /**
     * 
     */
    @SerializedName("valid_from")
    val valid_from: String,

    /**
     * 
     */
    @SerializedName("valid_until")
    val valid_until: String,

    /**
     * 
     */
    @SerializedName("variable_defaults")
    val variable_defaults: List<Any>,

    /**
     * 
     */
    @SerializedName("variables")
    val variables: List<Any>,

    /**
     * 
     */
    @SerializedName("whatsapp_category")
    val whatsapp_category: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "body_html" to body_html as Any,
        "body_text" to body_text as Any,
        "channel" to channel as Any,
        "content_sid" to content_sid as Any,
        "created_at" to created_at as Any,
        "design" to design as Any,
        "enabled" to enabled as Any,
        "has_unpublished_changes" to has_unpublished_changes as Any,
        "id" to id as Any,
        "is_published" to is_published as Any,
        "key" to key as Any,
        "layout_id" to layout_id as Any,
        "lifecycle_state" to lifecycle_state as Any,
        "locale" to locale as Any,
        "markets" to markets as Any,
        "message_class" to message_class as Any,
        "published_version_id" to published_version_id as Any,
        "source_library_key" to source_library_key as Any,
        "subject" to subject as Any,
        "tenant_id" to tenant_id as Any,
        "test_mode" to test_mode as Any,
        "title" to title as Any,
        "updated_at" to updated_at as Any,
        "uses_raw_html" to uses_raw_html as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
        "variable_defaults" to variable_defaults as Any,
        "variables" to variables as Any,
        "whatsapp_category" to whatsapp_category as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Template(
            body_html = map["body_html"] as String,
            body_text = map["body_text"] as String,
            channel = map["channel"] as String,
            content_sid = map["content_sid"] as String,
            created_at = map["created_at"] as String,
            design = map["design"] as List<Any>,
            enabled = map["enabled"] as Boolean,
            has_unpublished_changes = map["has_unpublished_changes"] as String,
            id = map["id"] as String,
            is_published = map["is_published"] as String,
            key = map["key"] as String,
            layout_id = map["layout_id"] as String,
            lifecycle_state = map["lifecycle_state"] as String,
            locale = map["locale"] as String,
            markets = map["markets"] as List<Any>,
            message_class = map["message_class"] as String,
            published_version_id = map["published_version_id"] as String,
            source_library_key = map["source_library_key"] as String,
            subject = map["subject"] as String,
            tenant_id = map["tenant_id"] as String,
            test_mode = map["test_mode"] as Boolean,
            title = map["title"] as String,
            updated_at = map["updated_at"] as String,
            uses_raw_html = map["uses_raw_html"] as String,
            valid_from = map["valid_from"] as String,
            valid_until = map["valid_until"] as String,
            variable_defaults = map["variable_defaults"] as List<Any>,
            variables = map["variables"] as List<Any>,
            whatsapp_category = map["whatsapp_category"] as String,
        )
    }
}