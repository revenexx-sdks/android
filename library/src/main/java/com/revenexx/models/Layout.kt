package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Layout(
    /**
     * 
     */
    @SerializedName("color_accent")
    val color_accent: String,

    /**
     * 
     */
    @SerializedName("color_bg")
    val color_bg: String,

    /**
     * 
     */
    @SerializedName("color_text")
    val color_text: String,

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
    @SerializedName("font_family")
    val font_family: String,

    /**
     * 
     */
    @SerializedName("footer_note")
    val footer_note: String,

    /**
     * 
     */
    @SerializedName("id")
    val id: String,

    /**
     * 
     */
    @SerializedName("is_default")
    val is_default: Boolean,

    /**
     * 
     */
    @SerializedName("legal_name")
    val legal_name: String,

    /**
     * 
     */
    @SerializedName("lifecycle_state")
    val lifecycle_state: String,

    /**
     * 
     */
    @SerializedName("logo_url")
    val logo_url: String,

    /**
     * 
     */
    @SerializedName("markets")
    val markets: List<Any>,

    /**
     * 
     */
    @SerializedName("menu_links")
    val menu_links: List<Any>,

    /**
     * 
     */
    @SerializedName("name")
    val name: String,

    /**
     * 
     */
    @SerializedName("postal_address")
    val postal_address: String,

    /**
     * 
     */
    @SerializedName("sender_name")
    val sender_name: String,

    /**
     * 
     */
    @SerializedName("social_links")
    val social_links: List<Any>,

    /**
     * 
     */
    @SerializedName("support_email")
    val support_email: String,

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
    @SerializedName("width")
    val width: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "color_accent" to color_accent as Any,
        "color_bg" to color_bg as Any,
        "color_text" to color_text as Any,
        "created_at" to created_at as Any,
        "enabled" to enabled as Any,
        "font_family" to font_family as Any,
        "footer_note" to footer_note as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "legal_name" to legal_name as Any,
        "lifecycle_state" to lifecycle_state as Any,
        "logo_url" to logo_url as Any,
        "markets" to markets as Any,
        "menu_links" to menu_links as Any,
        "name" to name as Any,
        "postal_address" to postal_address as Any,
        "sender_name" to sender_name as Any,
        "social_links" to social_links as Any,
        "support_email" to support_email as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
        "valid_from" to valid_from as Any,
        "valid_until" to valid_until as Any,
        "width" to width as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Layout(
            color_accent = map["color_accent"] as String,
            color_bg = map["color_bg"] as String,
            color_text = map["color_text"] as String,
            created_at = map["created_at"] as String,
            enabled = map["enabled"] as Boolean,
            font_family = map["font_family"] as String,
            footer_note = map["footer_note"] as String,
            id = map["id"] as String,
            is_default = map["is_default"] as Boolean,
            legal_name = map["legal_name"] as String,
            lifecycle_state = map["lifecycle_state"] as String,
            logo_url = map["logo_url"] as String,
            markets = map["markets"] as List<Any>,
            menu_links = map["menu_links"] as List<Any>,
            name = map["name"] as String,
            postal_address = map["postal_address"] as String,
            sender_name = map["sender_name"] as String,
            social_links = map["social_links"] as List<Any>,
            support_email = map["support_email"] as String,
            tenant_id = map["tenant_id"] as String,
            updated_at = map["updated_at"] as String,
            valid_from = map["valid_from"] as String,
            valid_until = map["valid_until"] as String,
            width = map["width"] as String,
        )
    }
}