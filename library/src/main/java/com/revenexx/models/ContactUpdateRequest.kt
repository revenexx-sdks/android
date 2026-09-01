package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ContactUpdateRequestRegistrationStatus
import com.revenexx.enums.ContactStatus

/**
 * Partial update — omitted fields keep their current value. external_user_id is mirror-managed and ignored, and so are the registration_* columns: registration state is only ever changed by the approve/reject routes.
 */
data class ContactUpdateRequest(
    /**
     * Login identity and the unique key of a person within the tenant. Changing it changes the platform login with it. Two people at the same company therefore need two addresses — a shared purchasing mailbox is one contact, not several.
     */
    @SerializedName("email")
    var email: String?,

    /**
     * Given name. Optional: an ERP import often has only a mailbox.
     */
    @SerializedName("first_name")
    var first_name: String?,

    /**
     * The main contact of its organization — who a merchant calls first. At most one per company is the intent; the tenant's `primary_contact_required` setting decides whether the last one may be demoted or deleted.
     */
    @SerializedName("is_primary")
    var is_primary: Boolean?,

    /**
     * What this person does at the company — free text on purpose, because it is a title and not a grant. The permission ladder is `role`; overloading a job title with authority silently un-grants everyone the day the ledger is enforced.
     */
    @SerializedName("job_title")
    var job_title: String?,

    /**
     * Family name. Optional for the same reason.
     */
    @SerializedName("last_name")
    var last_name: String?,

    /**
     * The language this person is written to in — BCP 47, and one of the store's configured locales. Null falls back to the store default.
     */
    @SerializedName("locale")
    var locale: String?,

    /**
     * Amount ceiling for this person, in the market's currency: with the `orders.approve` permission it is the most they may sign off. Null means no ceiling. An amount, never a grant — the grant comes from the role.
     */
    @SerializedName("order_approval_limit")
    var order_approval_limit: Double?,

    /**
     * The company this person belongs to. NULL is a legitimate state, not a defect: a standalone buyer with no company behind them. Deleting the organization sets this null and keeps the person. Membership is mirrored to the platform team.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Direct number of this person, as somebody typed it — free text, no format is enforced or normalized. E.164 is what an integration should send.
     */
    @SerializedName("phone")
    var phone: String?,

    /**
     * Where this person's own application stands: 'approved' (the default, and what an open store creates), 'pending' while a merchant has yet to decide, 'rejected' once they declined. Only the approve/reject routes move it; it is ignored on an ordinary update. On CREATE only, and only to file the contact as an application: 'pending' creates the platform user disabled and routes the contact through approve/reject. Ignored on update.
     */
    @SerializedName("registration_status")
    var registration_status: ContactUpdateRequestRegistrationStatus?,

    /**
     * The person's role INSIDE its organization, and the only thing permissions are derived from. One of the tenant's own roles (GET /customers/roles); a tenant that never edited the ledger has viewer, requester, buyer, approver, admin. Also the team role on the platform mirror. There is no global role — the same person in two companies is two contacts. A tenant that never edited the ledger has viewer, requester, buyer, approver, admin; a create without a role gets the one flagged as default, and a role the tenant does not keep is a 400.
     */
    @SerializedName("role")
    var role: String?,

    /**
     * Whether this person may act: 'invited' has been created but has not accepted, 'active' works, 'blocked' cannot log in. A create through the API defaults to 'invited'; a self-registration in an open store lands 'active'. Default 'invited' on create.
     */
    @SerializedName("status")
    var status: ContactStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "email" to email as Any,
        "first_name" to first_name as Any,
        "is_primary" to is_primary as Any,
        "job_title" to job_title as Any,
        "last_name" to last_name as Any,
        "locale" to locale as Any,
        "order_approval_limit" to order_approval_limit as Any,
        "organization_id" to organization_id as Any,
        "phone" to phone as Any,
        "registration_status" to registration_status?.value as Any,
        "role" to role as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContactUpdateRequest(
            email = map["email"] as? String,
            first_name = map["first_name"] as? String,
            is_primary = map["is_primary"] as? Boolean,
            job_title = map["job_title"] as? String,
            last_name = map["last_name"] as? String,
            locale = map["locale"] as? String,
            order_approval_limit = (map["order_approval_limit"] as? Number)?.toDouble(),
            organization_id = map["organization_id"] as? String,
            phone = map["phone"] as? String,
            registration_status = ContactUpdateRequestRegistrationStatus.values().find { it.value == (map["registration_status"] as? String) } ?: null,
            role = map["role"] as? String,
            status = ContactStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}