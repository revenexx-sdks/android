package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ContactRegistrationStatus
import com.revenexx.enums.ContactStatus

/**
 * A PERSON, and the unit that logs in: one platform user, one email, one role inside its organization. A contact without an organization is a standalone buyer, not an error.
 */
data class Contact(
    /**
     * When this person record was created in this app.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Login identity and the unique key of a person within the tenant. Changing it changes the platform login with it. Two people at the same company therefore need two addresses — a shared purchasing mailbox is one contact, not several.
     */
    @SerializedName("email")
    var email: String?,

    /**
     * Id of the platform USER this contact is mirrored as — the account that actually holds the password and the sessions. Written by the mirror and ignored on every write a caller sends.
     */
    @SerializedName("external_user_id")
    var external_user_id: String?,

    /**
     * Given name. Optional: an ERP import often has only a mailbox.
     */
    @SerializedName("first_name")
    var first_name: String?,

    /**
     * Primary key of the person record. What the timeline, the permission routes and the principal resolution all name.
     */
    @SerializedName("id")
    var id: String?,

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
     * The company this person belongs to. NULL is a legitimate state, not a defect: a standalone buyer with no company behind them. Deleting the organization sets this null and keeps the person.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * Direct number of this person, as somebody typed it — free text, no format is enforced or normalized. E.164 is what an integration should send.
     */
    @SerializedName("phone")
    var phone: String?,

    /**
     * When a merchant approved or rejected the application. Null while nobody has decided.
     */
    @SerializedName("registration_decided_at")
    var registration_decided_at: String?,

    /**
     * Who decided — free text as the deciding client supplied it (an operator id or an email address), not a resolvable user reference.
     */
    @SerializedName("registration_decided_by")
    var registration_decided_by: String?,

    /**
     * Why the application was declined. Always recorded here; whether the APPLICANT is ever told it is the tenant's `registration_reason_disclosed` setting, because that is a legal decision and not a template one.
     */
    @SerializedName("registration_reason")
    var registration_reason: String?,

    /**
     * Where this person's own application stands: 'approved' (the default, and what an open store creates), 'pending' while a merchant has yet to decide, 'rejected' once they declined. Only the approve/reject routes move it; it is ignored on an ordinary update.
     */
    @SerializedName("registration_status")
    var registration_status: ContactRegistrationStatus?,

    /**
     * The person's role INSIDE its organization, and the only thing permissions are derived from. One of the tenant's own roles (GET /customers/roles); a tenant that never edited the ledger has viewer, requester, buyer, approver, admin. Also the team role on the platform mirror. There is no global role — the same person in two companies is two contacts.
     */
    @SerializedName("role")
    var role: String?,

    /**
     * Whether this person may act: 'invited' has been created but has not accepted, 'active' works, 'blocked' cannot log in. A create through the API defaults to 'invited'; a self-registration in an open store lands 'active'.
     */
    @SerializedName("status")
    var status: ContactStatus?,

    /**
     * The tenant this row belongs to — the store slug, not an id. Set by the platform from the authenticated context, never by a caller; a write that carries it is ignored, and no request can read another tenant's rows by sending a different one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

    /**
     * When any column of this row last changed.
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
        "job_title" to job_title as Any,
        "last_name" to last_name as Any,
        "locale" to locale as Any,
        "order_approval_limit" to order_approval_limit as Any,
        "organization_id" to organization_id as Any,
        "phone" to phone as Any,
        "registration_decided_at" to registration_decided_at as Any,
        "registration_decided_by" to registration_decided_by as Any,
        "registration_reason" to registration_reason as Any,
        "registration_status" to registration_status?.value as Any,
        "role" to role as Any,
        "status" to status?.value as Any,
        "tenant_id" to tenant_id as Any,
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
            job_title = map["job_title"] as? String,
            last_name = map["last_name"] as? String,
            locale = map["locale"] as? String,
            order_approval_limit = (map["order_approval_limit"] as? Number)?.toDouble(),
            organization_id = map["organization_id"] as? String,
            phone = map["phone"] as? String,
            registration_decided_at = map["registration_decided_at"] as? String,
            registration_decided_by = map["registration_decided_by"] as? String,
            registration_reason = map["registration_reason"] as? String,
            registration_status = ContactRegistrationStatus.values().find { it.value == (map["registration_status"] as? String) } ?: null,
            role = map["role"] as? String,
            status = ContactStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            tenant_id = map["tenant_id"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}