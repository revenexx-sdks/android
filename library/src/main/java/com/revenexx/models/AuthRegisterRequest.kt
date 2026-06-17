package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthRegisterRequest(
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
     * Join an existing organization.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Found a new organization; the contact becomes its admin.
     */
    @SerializedName("organization_name")
    var organization_name: String?,

    /**
     * 
     */
    @SerializedName("password")
    val password: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
        "first_name" to first_name as Any,
        "last_name" to last_name as Any,
        "locale" to locale as Any,
        "organization_id" to organization_id as Any,
        "organization_name" to organization_name as Any,
        "password" to password as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AuthRegisterRequest(
            email = map["email"] as String,
            first_name = map["first_name"] as? String,
            last_name = map["last_name"] as? String,
            locale = map["locale"] as? String,
            organization_id = map["organization_id"] as? String,
            organization_name = map["organization_name"] as? String,
            password = map["password"] as String,
        )
    }
}