package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AddressType

/**
 * Partial update — omitted fields keep their current value.
 */
data class AddressUpdateRequest(
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
     * Owning contact (personal address).
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 3166-1 alpha-2 code.
     */
    @SerializedName("country")
    var country: String?,

    /**
     * The default address of its owner and type.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Recipient name.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Owning organization (company address).
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
     * Default 'shipping'.
     */
    @SerializedName("type")
    var type: AddressType?,

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
        "is_default" to is_default as Any,
        "name" to name as Any,
        "organization_id" to organization_id as Any,
        "phone" to phone as Any,
        "region" to region as Any,
        "street" to street as Any,
        "street2" to street2 as Any,
        "type" to type?.value as Any,
        "zip" to zip as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AddressUpdateRequest(
            city = map["city"] as? String,
            company = map["company"] as? String,
            contact_id = map["contact_id"] as? String,
            country = map["country"] as? String,
            is_default = map["is_default"] as? Boolean,
            name = map["name"] as? String,
            organization_id = map["organization_id"] as? String,
            phone = map["phone"] as? String,
            region = map["region"] as? String,
            street = map["street"] as? String,
            street2 = map["street2"] as? String,
            type = AddressType.values().find { it.value == (map["type"] as? String) } ?: null,
            zip = map["zip"] as? String,
        )
    }
}