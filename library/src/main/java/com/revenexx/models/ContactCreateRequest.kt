package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ContactRole
import com.revenexx.enums.ContactStatus

/**
 * Creates the contact (system of record) and mirrors it as a platform user (status defaults to invited).
 */
data class ContactCreateRequest(
    /**
     * 
     */
    @SerializedName("email")
    val email: String,

    /**
     * 
     */
    @SerializedName("first_name")
    var first_name: String?,

    /**
     * The primary contact of its organization.
     */
    @SerializedName("is_primary")
    var is_primary: Boolean?,

    /**
     * 
     */
    @SerializedName("last_name")
    var last_name: String?,

    /**
     * BCP 47, e.g. de-DE
     */
    @SerializedName("locale")
    var locale: String?,

    /**
     * Owning organization — membership is mirrored to the platform team.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * 
     */
    @SerializedName("phone")
    var phone: String?,

    /**
     * Default 'buyer' — also the team role on the platform mirror.
     */
    @SerializedName("role")
    var role: ContactRole?,

    /**
     * Default 'invited' on create.
     */
    @SerializedName("status")
    var status: ContactStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
        "first_name" to first_name as Any,
        "is_primary" to is_primary as Any,
        "last_name" to last_name as Any,
        "locale" to locale as Any,
        "organization_id" to organization_id as Any,
        "phone" to phone as Any,
        "role" to role?.value as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContactCreateRequest(
            email = map["email"] as String,
            first_name = map["first_name"] as? String,
            is_primary = map["is_primary"] as? Boolean,
            last_name = map["last_name"] as? String,
            locale = map["locale"] as? String,
            organization_id = map["organization_id"] as? String,
            phone = map["phone"] as? String,
            role = ContactRole.values().find { it.value == (map["role"] as? String) } ?: null,
            status = ContactStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}