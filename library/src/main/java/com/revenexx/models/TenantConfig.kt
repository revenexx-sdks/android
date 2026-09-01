package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class TenantConfig(
    /**
     * 
     */
    @SerializedName("created_at")
    val created_at: String,

    /**
     * 
     */
    @SerializedName("default_locale")
    val default_locale: String,

    /**
     * 
     */
    @SerializedName("defaults")
    val defaults: List<Any>,

    /**
     * 
     */
    @SerializedName("delivery_reporting")
    val delivery_reporting: List<Any>,

    /**
     * 
     */
    @SerializedName("locales")
    val locales: List<Any>,

    /**
     * 
     */
    @SerializedName("product")
    val product: String,

    /**
     * 
     */
    @SerializedName("provisioned_at")
    val provisioned_at: String,

    /**
     * 
     */
    @SerializedName("quiet_hours")
    val quiet_hours: List<Any>,

    /**
     * 
     */
    @SerializedName("quotas")
    val quotas: List<Any>,

    /**
     * 
     */
    @SerializedName("retention_days")
    val retention_days: Long,

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

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "default_locale" to default_locale as Any,
        "defaults" to defaults as Any,
        "delivery_reporting" to delivery_reporting as Any,
        "locales" to locales as Any,
        "product" to product as Any,
        "provisioned_at" to provisioned_at as Any,
        "quiet_hours" to quiet_hours as Any,
        "quotas" to quotas as Any,
        "retention_days" to retention_days as Any,
        "support_email" to support_email as Any,
        "tenant_id" to tenant_id as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = TenantConfig(
            created_at = map["created_at"] as String,
            default_locale = map["default_locale"] as String,
            defaults = map["defaults"] as List<Any>,
            delivery_reporting = map["delivery_reporting"] as List<Any>,
            locales = map["locales"] as List<Any>,
            product = map["product"] as String,
            provisioned_at = map["provisioned_at"] as String,
            quiet_hours = map["quiet_hours"] as List<Any>,
            quotas = map["quotas"] as List<Any>,
            retention_days = (map["retention_days"] as Number).toLong(),
            support_email = map["support_email"] as String,
            tenant_id = map["tenant_id"] as String,
            updated_at = map["updated_at"] as String,
        )
    }
}