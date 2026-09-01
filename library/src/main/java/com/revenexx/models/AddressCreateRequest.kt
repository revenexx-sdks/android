package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * An address needs an owner: 'organization_id' or 'contact_id'.
 */
data class AddressCreateRequest(
    /**
     * City or town.
     */
    @SerializedName("city")
    val city: String,

    /**
     * Company line on the label. Often the owning organization's name, but not always — a delivery to a construction site carries the site.
     */
    @SerializedName("company")
    var company: String?,

    /**
     * Owning person — a personal address only that contact uses. Exactly one of organization_id / contact_id is set.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * ISO 3166-1 alpha-2 country code, exactly two letters. Uppercase by convention; it is what shipping and tax both key off.
     */
    @SerializedName("country")
    val country: String,

    /**
     * The default address of its owner AND type: one default billing and one default shipping address per owner. Setting it moves the flag off the previous holder. Default false.
     */
    @SerializedName("is_default")
    var is_default: Boolean?,

    /**
     * Recipient line on the label — the person or department the parcel is addressed to.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * Owning company — a company address, shared by everyone in it. Exactly one of organization_id / contact_id is set.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Phone number for the carrier to reach at this address — often a different one from the contact's own.
     */
    @SerializedName("phone")
    var phone: String?,

    /**
     * State, province or Bundesland. Required by some destinations (US, CA), unused by most European ones.
     */
    @SerializedName("region")
    var region: String?,

    /**
     * Street and house number, on one line, as the local post expects it.
     */
    @SerializedName("street")
    val street: String,

    /**
     * The second address line: building, floor, gate, c/o. Null when there is none.
     */
    @SerializedName("street2")
    var street2: String?,

    /**
     * What the address is FOR — one of the tenant's own address types (GET /customers/address-types), seeded with billing and shipping. A merchant may add their own (a works entrance, a central accounts office) without a release of this app. A create without it gets the type flagged as default; a type the tenant does not keep is a 400.
     */
    @SerializedName("type")
    var type: String?,

    /**
     * Postal code, as text — leading zeros are real in most countries.
     */
    @SerializedName("zip")
    val zip: String,

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
        "type" to type as Any,
        "zip" to zip as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = AddressCreateRequest(
            city = map["city"] as String,
            company = map["company"] as? String,
            contact_id = map["contact_id"] as? String,
            country = map["country"] as String,
            is_default = map["is_default"] as? Boolean,
            name = map["name"] as? String,
            organization_id = map["organization_id"] as? String,
            phone = map["phone"] as? String,
            region = map["region"] as? String,
            street = map["street"] as String,
            street2 = map["street2"] as? String,
            type = map["type"] as? String,
            zip = map["zip"] as String,
        )
    }
}