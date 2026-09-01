package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class LibraryTemplate(
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
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("description")
    val description: String,

    /**
     * 
     */
    @SerializedName("design")
    val design: List<Any>,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("key")
    val key: String,

    /**
     * 
     */
    @SerializedName("locale")
    val locale: String,

    /**
     * 
     */
    @SerializedName("subject")
    val subject: String,

    /**
     * 
     */
    @SerializedName("suggested_event")
    val suggested_event: String,

    /**
     * 
     */
    @SerializedName("suggested_recipient")
    val suggested_recipient: String,

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
    @SerializedName("variables")
    val variables: List<Any>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "body_html" to body_html as Any,
        "body_text" to body_text as Any,
        "channel" to channel as Any,
        "created_at" to created_at as Any,
        "description" to description as Any,
        "design" to design as Any,
        "id" to id as Any,
        "key" to key as Any,
        "locale" to locale as Any,
        "subject" to subject as Any,
        "suggested_event" to suggested_event as Any,
        "suggested_recipient" to suggested_recipient as Any,
        "title" to title as Any,
        "updated_at" to updated_at as Any,
        "variables" to variables as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = LibraryTemplate(
            body_html = map["body_html"] as String,
            body_text = map["body_text"] as String,
            channel = map["channel"] as String,
            created_at = map["created_at"] as String,
            description = map["description"] as String,
            design = map["design"] as List<Any>,
            id = map["id"] as String,
            key = map["key"] as String,
            locale = map["locale"] as String,
            subject = map["subject"] as String,
            suggested_event = map["suggested_event"] as String,
            suggested_recipient = map["suggested_recipient"] as String,
            title = map["title"] as String,
            updated_at = map["updated_at"] as String,
            variables = map["variables"] as List<Any>,
        )
    }
}