package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ContactActivityKind

/**
 * 
 */
data class ContactActivityRequest(
    /**
     * Who logged it (operator id or email). Free text; this app does not resolve it.
     */
    @SerializedName("actor")
    var actor: String?,

    /**
     * What happened. 'system' is deliberately NOT accepted — those rows are the registration decision trail and are written by the approve/reject routes. Default 'note'.
     */
    @SerializedName("kind")
    var kind: ContactActivityKind?,

    /**
     * The long form. Stored inside the event payload as `note`, not as a column of its own.
     */
    @SerializedName("note")
    var note: String?,

    /**
     * When it actually happened. Defaults to now — a call logged on Monday about Friday should say Friday.
     */
    @SerializedName("occurred_at")
    var occurred_at: String?,

    /**
     * One line a person can scan in a timeline. Required — an entry nobody can read at a glance is not worth the row.
     */
    @SerializedName("subject")
    val subject: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "actor" to actor as Any,
        "kind" to kind?.value as Any,
        "note" to note as Any,
        "occurred_at" to occurred_at as Any,
        "subject" to subject as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ContactActivityRequest(
            actor = map["actor"] as? String,
            kind = ContactActivityKind.values().find { it.value == (map["kind"] as? String) } ?: null,
            note = map["note"] as? String,
            occurred_at = map["occurred_at"] as? String,
            subject = map["subject"] as String,
        )
    }
}