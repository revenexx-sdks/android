package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * The PEOPLE inside the buying companies, and everything that happens to one: the contact rows, the activity timeline (`contact_events` — a call, a visit, a note, plus this app's own registration decisions), the approve/reject calls that settle a pending registration, and the effective permissions a contact ends up holding. A contact is the unit that logs in — one platform user, one email, one role inside its organization — and a contact without an organization is a standalone buyer, not an error. Both routes that write a timeline entry are here, including the one addressed by an organization id, because every row is keyed by a contact.
 */
class CustomersContacts(client: Client) : Service(client) {

    /**
     * A contact event is one entry on a customer's timeline: an activity somebody logged (a call, a visit, a meeting, a note) or a registration decision this app recorded itself. Every entry is keyed by a CONTACT and stamped with the organization derived from that contact, so a company's history is one indexed read rather than a join. Append-only — there is no update and no delete, which is what makes it usable as evidence. The activity feed, filtered by whichever column the question needs: `contact_id` for one person, `organization_id` for a whole company, `kind` for one type of activity. `kind: "system"` is this app's own registration decision trail (`registration.submitted` / `.approved` / `.rejected`), and no caller may file one of those. Paged with `limit`/`offset`/`order`; newest first is `order=occurred_at.desc`.
     *
     * @param id Filter to rows whose `id` is exactly this value. Primary key of the timeline entry.
     * @param contactId Filter to one person's timeline.
     * @param organizationId Filter to one company timeline — the whole history, without fanning out over its people.
     * @param kind Filter by entry kind. One of the tenant's own activity types (GET /customers/contact-event-kinds); 'system' is the registration decision trail and is the one a caller may not file.
     * @param name Filter by event name — registration.submitted | registration.approved | registration.rejected | activity.<kind>. This one IS this app's own vocabulary, not the tenant's.
     * @param subject Filter to rows whose `subject` is exactly this value. One line a person can scan in a timeline. Required for an activity; a decision row carries the app's own wording.
     * @param actor Filter to rows whose `actor` is exactly this value. Who logged the entry — free text as the client supplied it (operator id or email). Null for a row the app wrote itself.
     * @param occurredAt Exact timestamp equality on when it happened — there is no range filter on this API. Use `order=occurred_at.desc` with limit/offset to walk a timeline.
     * @param createdAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When the row was written. Together with `occurred_at` this is what tells a late entry from a live one.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun customersContactEventsList(
        id: String? = null,
        contactId: String? = null,
        organizationId: String? = null,
        kind: String? = null,
        name: String? = null,
        subject: String? = null,
        actor: String? = null,
        occurredAt: String? = null,
        createdAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/customers/contact_events"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "contact_id" to contactId,
            "organization_id" to organizationId,
            "kind" to kind,
            "name" to name,
            "subject" to subject,
            "actor" to actor,
            "occurred_at" to occurredAt,
            "created_at" to createdAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * A contact event is one entry on a customer's timeline: an activity somebody logged (a call, a visit, a meeting, a note) or a registration decision this app recorded itself. Every entry is keyed by a CONTACT and stamped with the organization derived from that contact, so a company's history is one indexed read rather than a join. Append-only — there is no update and no delete, which is what makes it usable as evidence. One timeline entry by id, as it was written. Entries are never edited, so what this answers is what was recorded at the time.
     *
     * @param id The contact event to read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersContactEventsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contact_events/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A contact is a PERSON, and the unit that logs in: one platform user, one email address, one role held inside its organization. A contact without an organization is a standalone buyer rather than an error, and two people at the same company are two contacts sharing an `organization_id`. The people list, and the read behind an approval queue: `registration_status=pending` is every application waiting for a decision. Every column is a filter — `external_user_id` in particular is how a storefront turns a platform auth id back into a customer — and the page is `limit`/`offset`/`order`.
     *
     * @param id Filter to exactly one person.
     * @param organizationId Filter to one company's people. The company address book.
     * @param email Filter by exact email — the one lookup that is guaranteed to return at most one person, because the address is unique per tenant.
     * @param firstName Filter to rows whose `first_name` is exactly this value. Given name. Optional: an ERP import often has only a mailbox.
     * @param lastName Filter to rows whose `last_name` is exactly this value. Family name. Optional for the same reason.
     * @param phone Filter to rows whose `phone` is exactly this value. Direct number of this person, as somebody typed it — free text, no format is enforced or normalized. E.164 is what an integration should send.
     * @param jobTitle Filter to rows whose `job_title` is exactly this value. What this person does at the company — free text on purpose, because it is a title and not a grant. The permission ladder is `role`; overloading a job title with authority silently un-grants everyone the day the ledger is enforced.
     * @param role Filter by role. One of the tenant's own roles (GET /customers/roles) — a tenant that never edited the ledger has viewer, requester, buyer, approver, admin.
     * @param status Filter by status.
     * @param orderApprovalLimit Filter to rows whose `order_approval_limit` is exactly this value. Amount ceiling for this person, in the market's currency: with the `orders.approve` permission it is the most they may sign off. Null means no ceiling. An amount, never a grant — the grant comes from the role.
     * @param registrationStatus Filter by registration state. `pending` IS the approval inbox — there is no second entity for it.
     * @param registrationDecidedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When a merchant approved or rejected the application. Null while nobody has decided.
     * @param registrationDecidedBy Filter to rows whose `registration_decided_by` is exactly this value. Who decided — free text as the deciding client supplied it (an operator id or an email address), not a resolvable user reference.
     * @param registrationReason Filter to rows whose `registration_reason` is exactly this value. Why the application was declined. Always recorded here; whether the APPLICANT is ever told it is the tenant's `registration_reason_disclosed` setting, because that is a legal decision and not a template one.
     * @param locale Filter to rows whose `locale` is exactly this value. The language this person is written to in — BCP 47, and one of the store's configured locales. Null falls back to the store default.
     * @param isPrimary Filter to the primary contacts — with `organization_id`, the one person a merchant calls first at that company.
     * @param externalUserId Find the contact behind a platform user id. What a storefront session resolves with when it has an auth id and needs the customer record.
     * @param createdAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When this person record was created in this app.
     * @param updatedAt Exact timestamp equality — this API has no range filter. To bound a period, sort with `order` and page. When any column of this row last changed.
     * @param limit Page size (default 50, max 200).
     * @param offset Row offset for pagination (default 0).
     * @param order Sort by one column: 'column' | 'column.asc' | 'column.desc'. A bare column sorts ascending. Anything else is refused with 400.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun customersContactsList(
        id: String? = null,
        organizationId: String? = null,
        email: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        phone: String? = null,
        jobTitle: String? = null,
        role: String? = null,
        status: com.revenexx.enums.Status? = null,
        orderApprovalLimit: Double? = null,
        registrationStatus: com.revenexx.enums.RegistrationStatus? = null,
        registrationDecidedAt: String? = null,
        registrationDecidedBy: String? = null,
        registrationReason: String? = null,
        locale: String? = null,
        isPrimary: Boolean? = null,
        externalUserId: String? = null,
        createdAt: String? = null,
        updatedAt: String? = null,
        limit: Long? = null,
        offset: Long? = null,
        order: String? = null,
    ): Any {
        val apiPath = "/v1/customers/contacts"

        val apiParams = mutableMapOf<String, Any?>(
            "id" to id,
            "organization_id" to organizationId,
            "email" to email,
            "first_name" to firstName,
            "last_name" to lastName,
            "phone" to phone,
            "job_title" to jobTitle,
            "role" to role,
            "status" to status,
            "order_approval_limit" to orderApprovalLimit,
            "registration_status" to registrationStatus,
            "registration_decided_at" to registrationDecidedAt,
            "registration_decided_by" to registrationDecidedBy,
            "registration_reason" to registrationReason,
            "locale" to locale,
            "is_primary" to isPrimary,
            "external_user_id" to externalUserId,
            "created_at" to createdAt,
            "updated_at" to updatedAt,
            "limit" to limit,
            "offset" to offset,
            "order" to order,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * A contact is a PERSON, and the unit that logs in: one platform user, one email address, one role held inside its organization. A contact without an organization is a standalone buyer rather than an error, and two people at the same company are two contacts sharing an `organization_id`. Creates the person and their platform login together, so a contact that exists can always sign in. `role` names one of this tenant's own roles and decides what they may do; `registration_status` may only be set to `pending` or `approved` here, because a rejection has to carry a reason and that is the reject route's job. `email` is the only field a create cannot omit; everything else is optional or defaulted by the database. Two rows of this tenant may not share `email` or `external_user_id` (while external_user_id IS NOT NULL).
     *
     * @param email Login identity and the unique key of a person within the tenant. Changing it changes the platform login with it. Two people at the same company therefore need two addresses — a shared purchasing mailbox is one contact, not several.
     * @param firstName Given name. Optional: an ERP import often has only a mailbox.
     * @param isPrimary The main contact of its organization — who a merchant calls first. At most one per company is the intent; the tenant's `primary_contact_required` setting decides whether the last one may be demoted or deleted.
     * @param jobTitle What this person does at the company — free text on purpose, because it is a title and not a grant. The permission ladder is `role`; overloading a job title with authority silently un-grants everyone the day the ledger is enforced.
     * @param lastName Family name. Optional for the same reason.
     * @param locale The language this person is written to in — BCP 47, and one of the store's configured locales. Null falls back to the store default.
     * @param orderApprovalLimit Amount ceiling for this person, in the market's currency: with the `orders.approve` permission it is the most they may sign off. Null means no ceiling. An amount, never a grant — the grant comes from the role.
     * @param organizationId The company this person belongs to. NULL is a legitimate state, not a defect: a standalone buyer with no company behind them. Deleting the organization sets this null and keeps the person. Membership is mirrored to the platform team.
     * @param phone Direct number of this person, as somebody typed it — free text, no format is enforced or normalized. E.164 is what an integration should send.
     * @param registrationStatus Where this person's own application stands: 'approved' (the default, and what an open store creates), 'pending' while a merchant has yet to decide, 'rejected' once they declined. Only the approve/reject routes move it; it is ignored on an ordinary update. On CREATE only, and only to file the contact as an application: 'pending' creates the platform user disabled and routes the contact through approve/reject. Ignored on update.
     * @param role The person's role INSIDE its organization, and the only thing permissions are derived from. One of the tenant's own roles (GET /customers/roles); a tenant that never edited the ledger has viewer, requester, buyer, approver, admin. Also the team role on the platform mirror. There is no global role — the same person in two companies is two contacts. A tenant that never edited the ledger has viewer, requester, buyer, approver, admin; a create without a role gets the one flagged as default, and a role the tenant does not keep is a 400.
     * @param status Whether this person may act: 'invited' has been created but has not accepted, 'active' works, 'blocked' cannot log in. A create through the API defaults to 'invited'; a self-registration in an open store lands 'active'. Default 'invited' on create.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersContactsCreate(
        email: String,
        firstName: String? = null,
        isPrimary: Boolean? = null,
        jobTitle: String? = null,
        lastName: String? = null,
        locale: String? = null,
        orderApprovalLimit: Double? = null,
        organizationId: String? = null,
        phone: String? = null,
        registrationStatus: com.revenexx.enums.CustomersContactsCreateRegistrationStatus? = null,
        role: String? = null,
        status: com.revenexx.enums.ContactStatus? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "first_name" to firstName,
            "is_primary" to isPrimary,
            "job_title" to jobTitle,
            "last_name" to lastName,
            "locale" to locale,
            "order_approval_limit" to orderApprovalLimit,
            "organization_id" to organizationId,
            "phone" to phone,
            "registration_status" to registrationStatus,
            "role" to role,
            "status" to status,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * This is how a call, a visit, a meeting, an email or a plain note reaches one person's timeline. It writes a contact_events row with kind != 'system' and emits contact_event.created, so an activity travels on the same bus as a registration decision and a timeline is one query rather than a union. organization_id is DERIVED from the contact, never taken from the body — an activity cannot be filed under a company the person does not belong to.
     *
     * @param contactId The person the entry is about. The organization is derived from them.
     * @param subject One line a person can scan in a timeline. Required — an entry nobody can read at a glance is not worth the row.
     * @param actor Who logged it (operator id or email). Free text; this app does not resolve it.
     * @param kind What happened. 'system' is deliberately NOT accepted — those rows are the registration decision trail and are written by the approve/reject routes. Default 'note'.
     * @param note The long form. Stored inside the event payload as `note`, not as a column of its own.
     * @param occurredAt When it actually happened. Defaults to now — a call logged on Monday about Friday should say Friday.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersContactsEventsCreate(
        contactId: String,
        subject: String,
        actor: String? = null,
        kind: com.revenexx.enums.ContactActivityKind? = null,
        note: String? = null,
        occurredAt: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{contact_id}/events"
            .replace("{contactId}", contactId)

        val apiParams = mutableMapOf<String, Any?>(
            "actor" to actor,
            "kind" to kind,
            "note" to note,
            "occurred_at" to occurredAt,
            "subject" to subject,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Tell somebody they were added to a company. A deliberate act rather than a side effect of creating the contact: a merchant entering a colleague from a business card is not always ready to mail them, and "added" and "told" are different decisions. No secret travels — the platform team membership is confirmed as it is created, so there is nothing to accept; the message says "you are in, here is the way in". Unlike the auth mails, a failure here IS a failure: the identity service sends nothing for this occasion, so this is the only message the person gets.
     *
     * @param contactId The person being told. They are already a member — this only sends the message.
     * @param url Where the invitation points — the storefront sign-in, normally. There is no token in it: the person is already a member and only has to sign in.
     * @param invitedBy Who did the inviting, as the recipient should read it. Absent, the company name is used — "Beispiel GmbH invited you" reads better than the name of somebody they have never heard of.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersContactsInvite(
        contactId: String,
        url: String,
        invitedBy: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{contact_id}/invite"
            .replace("{contactId}", contactId)

        val apiParams = mutableMapOf<String, Any?>(
            "invited_by" to invitedBy,
            "url" to url,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Computed from contacts.role on every call — the grants are never persisted, so this always reflects the role the contact holds right now.
     *
     * @param contactId The person whose grants are being read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersContactsPermissions(
        contactId: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{contact_id}/permissions"
            .replace("{contactId}", contactId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Only reachable for a contact whose registration_status is 'pending' or 'rejected' (approving a rejection reinstates it). Enables the platform user FIRST — the password the applicant chose at submit time works immediately, no new credential is issued — then sets registration_status='approved' and status='active', and un-blocks the organization this registration itself founded. Approving an already-approved registration is a no-op that emits nothing, so a retry is safe. Writes a contact_events row named 'registration.approved'.
     *
     * @param contactId The applicant. It is the CONTACT that is approved — the organization it founded is unblocked with it.
     * @param decidedBy Who approved it — recorded on the contact and carried in the event. Free text (operator id or email); this app does not resolve it.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersRegistrationsApprove(
        contactId: String,
        decidedBy: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{contact_id}/registration/approve"
            .replace("{contactId}", contactId)

        val apiParams = mutableMapOf<String, Any?>(
            "decided_by" to decidedBy,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Only reachable from 'pending'. Sets registration_status='rejected' and status='blocked', keeps the platform user in place but disabled — the email must not fall free for a silent second identity, and the merchant keeps the record. Delete the contact to remove both. 'reason' is mandatory and is stored on the contact plus carried in the event payload, so the applicant can be told why. Rejecting an already-rejected registration is a no-op. Writes a contact_events row named 'registration.rejected'.
     *
     * @param contactId The applicant being declined.
     * @param reason Why the application was declined. Always stored on the contact. It only reaches the APPLICANT when the tenant's registration_reason_disclosed setting is on — the event payload then carries it, and so does the 403 the login answers.
     * @param decidedBy Who rejected it — recorded on the contact and carried in the event.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersRegistrationsReject(
        contactId: String,
        reason: String,
        decidedBy: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{contact_id}/registration/reject"
            .replace("{contactId}", contactId)

        val apiParams = mutableMapOf<String, Any?>(
            "decided_by" to decidedBy,
            "reason" to reason,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A contact is a PERSON, and the unit that logs in: one platform user, one email address, one role held inside its organization. A contact without an organization is a standalone buyer rather than an error, and two people at the same company are two contacts sharing an `organization_id`. Removes the person and their platform login, so they can no longer sign in anywhere. Their company keeps trading; use `status: "blocked"` instead when the intent is to stop one person without erasing what they did. Deleting one takes every `contact_events` and `addresses` row that points at it with it — the foreign keys decide, not this route.
     *
     * @param id The contact to delete.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersContactsDelete(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A contact is a PERSON, and the unit that logs in: one platform user, one email address, one role held inside its organization. A contact without an organization is a standalone buyer rather than an error, and two people at the same company are two contacts sharing an `organization_id`. One person by id. What they are ALLOWED to do is not in here: permissions are derived from `role` at read time and answered by `GET /customers/contacts/{contact_id}/permissions`.
     *
     * @param id The contact to read.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersContactsGet(
        id: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A contact is a PERSON, and the unit that logs in: one platform user, one email address, one role held inside its organization. A contact without an organization is a standalone buyer rather than an error, and two people at the same company are two contacts sharing an `organization_id`. A partial update — send only what changes. `external_user_id` and every `registration_*` column are ignored: the link to platform auth is mirror-managed, and registration state is only ever moved by the approve and reject routes, which record why. Two rows of this tenant may not share `email` or `external_user_id` (while external_user_id IS NOT NULL).
     *
     * @param id The contact to update.
     * @param email Login identity and the unique key of a person within the tenant. Changing it changes the platform login with it. Two people at the same company therefore need two addresses — a shared purchasing mailbox is one contact, not several.
     * @param firstName Given name. Optional: an ERP import often has only a mailbox.
     * @param isPrimary The main contact of its organization — who a merchant calls first. At most one per company is the intent; the tenant's `primary_contact_required` setting decides whether the last one may be demoted or deleted.
     * @param jobTitle What this person does at the company — free text on purpose, because it is a title and not a grant. The permission ladder is `role`; overloading a job title with authority silently un-grants everyone the day the ledger is enforced.
     * @param lastName Family name. Optional for the same reason.
     * @param locale The language this person is written to in — BCP 47, and one of the store's configured locales. Null falls back to the store default.
     * @param orderApprovalLimit Amount ceiling for this person, in the market's currency: with the `orders.approve` permission it is the most they may sign off. Null means no ceiling. An amount, never a grant — the grant comes from the role.
     * @param organizationId The company this person belongs to. NULL is a legitimate state, not a defect: a standalone buyer with no company behind them. Deleting the organization sets this null and keeps the person. Membership is mirrored to the platform team.
     * @param phone Direct number of this person, as somebody typed it — free text, no format is enforced or normalized. E.164 is what an integration should send.
     * @param registrationStatus Where this person's own application stands: 'approved' (the default, and what an open store creates), 'pending' while a merchant has yet to decide, 'rejected' once they declined. Only the approve/reject routes move it; it is ignored on an ordinary update. On CREATE only, and only to file the contact as an application: 'pending' creates the platform user disabled and routes the contact through approve/reject. Ignored on update.
     * @param role The person's role INSIDE its organization, and the only thing permissions are derived from. One of the tenant's own roles (GET /customers/roles); a tenant that never edited the ledger has viewer, requester, buyer, approver, admin. Also the team role on the platform mirror. There is no global role — the same person in two companies is two contacts. A tenant that never edited the ledger has viewer, requester, buyer, approver, admin; a create without a role gets the one flagged as default, and a role the tenant does not keep is a 400.
     * @param status Whether this person may act: 'invited' has been created but has not accepted, 'active' works, 'blocked' cannot log in. A create through the API defaults to 'invited'; a self-registration in an open store lands 'active'. Default 'invited' on create.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersContactsUpdate(
        id: String,
        email: String? = null,
        firstName: String? = null,
        isPrimary: Boolean? = null,
        jobTitle: String? = null,
        lastName: String? = null,
        locale: String? = null,
        orderApprovalLimit: Double? = null,
        organizationId: String? = null,
        phone: String? = null,
        registrationStatus: com.revenexx.enums.CustomersContactsCreateRegistrationStatus? = null,
        role: String? = null,
        status: com.revenexx.enums.ContactStatus? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/contacts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "first_name" to firstName,
            "is_primary" to isPrimary,
            "job_title" to jobTitle,
            "last_name" to lastName,
            "locale" to locale,
            "order_approval_limit" to orderApprovalLimit,
            "organization_id" to organizationId,
            "phone" to phone,
            "registration_status" to registrationStatus,
            "role" to role,
            "status" to status,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Same row as the contact route, reached from the organization. 'contact_id' is required and must belong to THIS organization — the picker offering the contacts is not filtered, so the membership check here is what stops a call with one company being filed under someone else's person.
     *
     * @param organizationId The company the entry is filed under. The `contact_id` in the body has to belong to it.
     * @param contactId The person dealt with. Must be a contact of this organization.
     * @param subject One line a person can scan in a timeline. Required — an entry nobody can read at a glance is not worth the row.
     * @param actor Who logged it (operator id or email). Free text; this app does not resolve it.
     * @param kind What happened. 'system' is deliberately NOT accepted — those rows are the registration decision trail and are written by the approve/reject routes. Default 'note'.
     * @param note The long form. Stored inside the event payload as `note`, not as a column of its own.
     * @param occurredAt When it actually happened. Defaults to now — a call logged on Monday about Friday should say Friday.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun customersOrganizationsEventsCreate(
        organizationId: String,
        contactId: String,
        subject: String,
        actor: String? = null,
        kind: com.revenexx.enums.ContactActivityKind? = null,
        note: String? = null,
        occurredAt: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/organizations/{organization_id}/events"
            .replace("{organizationId}", organizationId)

        val apiParams = mutableMapOf<String, Any?>(
            "actor" to actor,
            "contact_id" to contactId,
            "kind" to kind,
            "note" to note,
            "occurred_at" to occurredAt,
            "subject" to subject,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}