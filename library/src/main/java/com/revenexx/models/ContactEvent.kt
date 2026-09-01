package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One entry on a customer's timeline: an activity somebody logged (call, visit, note) or a registration decision this app recorded. Append-only — nothing here is ever edited.
 */
data class ContactEvent(
    /**
     * Who logged the entry — free text as the client supplied it (operator id or email). Null for a row the app wrote itself.
     */
    @SerializedName("actor")
    var actor: String?,

    /**
     * The person this entry is about. Always set: even a company-level activity is filed against somebody, so a timeline never has anonymous rows.
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * When the row was written. Together with `occurred_at` this is what tells a late entry from a live one.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * Primary key of the timeline entry.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * What kind of entry this is — one of the tenant's own activity types (GET /customers/contact-event-kinds), seeded with note, call, email, meeting, visit, task. 'system' is reserved: those rows are this app's own registration decision trail and no caller may file one.
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * The event name, and the one vocabulary here that is THIS APP's rather than the tenant's: `registration.submitted` | `registration.approved` | `registration.rejected` for decisions, `activity.<kind>` for everything somebody logged. It is also what travels on the bus as `contact_event.created`.
     */
    @SerializedName("name")
    var name: String?,

    /**
     * When the thing actually HAPPENED, which is not when it was written down: a call logged on Monday about Friday says Friday. Defaults to now.
     */
    @SerializedName("occurred_at")
    var occurred_at: String?,

    /**
     * The company this entry belongs to, DERIVED from the contact and never taken from a request body — which is what stops a call with one company being filed under someone else's person. Null when the contact has no organization.
     */
    @SerializedName("organization_id")
    var organization_id: String?,

    /**
     * The machine-readable body, and its shape follows `name`. `activity.<kind>` carries `{ note }` — the long form of `subject`. `registration.submitted` carries the application itself: email, organization_id, organization_name, role, locale, vat_id, and `notify`, the recipients the approval mail goes to. `registration.approved` carries `{ decided_by }`; `registration.rejected` adds `reason`. Nothing validates it beyond that — a client writing its own entries decides what belongs in here.
     */
    @SerializedName("payload")
    var payload: Any?,

    /**
     * One line a person can scan in a timeline. Required for an activity; a decision row carries the app's own wording.
     */
    @SerializedName("subject")
    var subject: String?,

    /**
     * The tenant this row belongs to — the store slug, not an id. Set by the platform from the authenticated context, never by a caller; a write that carries it is ignored, and no request can read another tenant's rows by sending a different one.
     */
    @SerializedName("tenant_id")
    var tenant_id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "actor" to actor as Any,
        "contact_id" to contact_id as Any,
        "created_at" to created_at as Any,
        "id" to id as Any,
        "kind" to kind as Any,
        "name" to name as Any,
        "occurred_at" to occurred_at as Any,
        "organization_id" to organization_id as Any,
        "payload" to payload as Any,
        "subject" to subject as Any,
        "tenant_id" to tenant_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContactEvent(
            actor = map["actor"] as? String,
            contact_id = map["contact_id"] as? String,
            created_at = map["created_at"] as? String,
            id = map["id"] as? String,
            kind = map["kind"] as? String,
            name = map["name"] as? String,
            occurred_at = map["occurred_at"] as? String,
            organization_id = map["organization_id"] as? String,
            payload = map["payload"] as? Any,
            subject = map["subject"] as? String,
            tenant_id = map["tenant_id"] as? String,
        )
    }
}