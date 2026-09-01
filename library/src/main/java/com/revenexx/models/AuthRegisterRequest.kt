package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class AuthRegisterRequest(
    /**
     * The buyer's address. It becomes the login AND the unique key of the contact, so a second registration with it is a 409 — including while the first one is still waiting for approval.
     */
    @SerializedName("email")
    val email: String,

    /**
     * Given name. Optional: an ERP import often has only a mailbox.
     */
    @SerializedName("first_name")
    var first_name: String?,

    /**
     * Family name. Optional for the same reason.
     */
    @SerializedName("last_name")
    var last_name: String?,

    /**
     * The language this person is written to in — BCP 47, and one of the store's configured locales. Null falls back to the store default. One of the store's own locales, or the call is a 400.
     */
    @SerializedName("locale")
    var locale: String?,

    /**
     * JOIN an existing company — the invite shape. Neither b2b_registration_enabled nor b2c_registration_enabled applies to it.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * FOUND a new company, with this contact as its admin. This is what makes the registration a B2B one; leaving it out registers a standalone buyer.
     */
    @SerializedName("organization_name")
    var organization_name: String?,

    /**
     * The password the buyer chooses. It is hashed by the identity service at this moment and never travels again: an approval later enables the account, it does not issue a new credential.
     */
    @SerializedName("password")
    val password: String,

    /**
     * Where the welcome mail's button points — the buyer's first stop in this shop. Absent, the mail still goes out and simply carries no button. Ignored when the registration is an APPLICATION: there is no account to send anybody to yet.
     */
    @SerializedName("url")
    var url: String?,

    /**
     * VAT identification number (USt-IdNr. in Germany) — the closest thing a B2B buyer has to a legal identity. Validated against the EU VIES service when the tenant's `organization_vat_id_required` setting is on, and stored verbatim otherwise, including for buyers outside the EU. Required when the tenant's `organization_vat_id_required` is on, and checked BEFORE the company is created so a bad one leaves no half-founded organization behind.
     */
    @SerializedName("vat_id")
    var vat_id: String?,

    /**
     * Where the address-confirmation link points, when the tenant's `email_verification` asks for one on registration. `userId`, `secret` and `expire` are appended, and `PUT /customers/auth/verification` takes the first two. Without it the registration still succeeds and `verification_sent` is false — this app cannot invent a storefront URL, and a link pointing nowhere is worse than none.
     */
    @SerializedName("verification_url")
    var verification_url: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
        "first_name" to first_name as Any,
        "last_name" to last_name as Any,
        "locale" to locale as Any,
        "organization_id" to organization_id as Any,
        "organization_name" to organization_name as Any,
        "password" to password as Any,
        "url" to url as Any,
        "vat_id" to vat_id as Any,
        "verification_url" to verification_url as Any,
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
            url = map["url"] as? String,
            vat_id = map["vat_id"] as? String,
            verification_url = map["verification_url"] as? String,
        )
    }
}