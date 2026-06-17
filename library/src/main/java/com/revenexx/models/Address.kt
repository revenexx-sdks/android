package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Address(
    /**
     * 
     */
    @SerializedName("city")
    var city: String?,

    /**
     * 
     */
    @SerializedName("company")
    var company: String?,

    /**
     * 
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * 
     */
    @SerializedName("country")
    var country: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

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
    @SerializedName("region")
    var region: String?,

    /**
     * 
     */
    @SerializedName("street")
    var street: String?,

    /**
     * 
     */
    @SerializedName("street2")
    var street2: String?,

    /**
     * 
     */
    @SerializedName("type")
    var type: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

    /**
     * 
     */
    @SerializedName("zip")
    var zip: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "city" to city as Any,
        "company" to company as Any,
        "contact_id" to contact_id as Any,
        "country" to country as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "is_default" to is_default as Any,
        "name" to name as Any,
        "organization_id" to organization_id as Any,
        "phone" to phone as Any,
        "region" to region as Any,
        "street" to street as Any,
        "street2" to street2 as Any,
        "type" to type as Any,
        "updated_at" to updated_at as Any,
        "zip" to zip as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Address(
            city = map["city"] as? String,
            company = map["company"] as? String,
            contact_id = map["contact_id"] as? String,
            country = map["country"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            is_default = map["is_default"] as? Boolean,
            name = map["name"] as? String,
            organization_id = map["organization_id"] as? String,
            phone = map["phone"] as? String,
            region = map["region"] as? String,
            street = map["street"] as? String,
            street2 = map["street2"] as? String,
            type = map["type"] as? String,
            updated_at = map["updated_at"] as? String,
            zip = map["zip"] as? String,
        )
    }
}