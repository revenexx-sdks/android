package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Contact(
    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("email")
    var email: String?,

    /**
     * 
     */
    @SerializedName("external_user_id")
    var external_user_id: String?,

    /**
     * 
     */
    @SerializedName("first_name")
    var first_name: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("is_primary")
    var is_primary: Boolean?,

    /**
     * 
     */
    @SerializedName("last_name")
    var last_name: String?,

    /**
     * 
     */
    @SerializedName("locale")
    var locale: String?,

    /**
     * 
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * 
     */
    @SerializedName("phone")
    var phone: String?,

    /**
     * 
     */
    @SerializedName("role")
    var role: String?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "email" to email as Any,
        "external_user_id" to external_user_id as Any,
        "first_name" to first_name as Any,
        "id" to id as Any,
        "is_primary" to is_primary as Any,
        "last_name" to last_name as Any,
        "locale" to locale as Any,
        "organization_id" to organization_id as Any,
        "phone" to phone as Any,
        "role" to role as Any,
        "status" to status as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Contact(
            created_at = map["created_at"] as? String,
            email = map["email"] as? String,
            external_user_id = map["external_user_id"] as? String,
            first_name = map["first_name"] as? String,
            id = map["id"] as? String,
            is_primary = map["is_primary"] as? Boolean,
            last_name = map["last_name"] as? String,
            locale = map["locale"] as? String,
            organization_id = map["organization_id"] as? String,
            phone = map["phone"] as? String,
            role = map["role"] as? String,
            status = map["status"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}