package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.FormSubmissionPruneRequestStatus

/**
 * Retention sweep. Previews unless `dry_run` is explicitly false.
 */
data class FormSubmissionPruneRequest(
    /**
     * Default TRUE. Nothing is deleted until this is explicitly false.
     */
    @SerializedName("dry_run")
    var dry_run: Boolean?,

    /**
     * Narrow the sweep to one form.
     */
    @SerializedName("form_slug")
    var form_slug: String?,

    /**
     * Age threshold. Omit to use the retention floor. A value BELOW the floor is raised to it — the setting is the floor, not a default, and the floor is the LONGEST submission_retention_days configured anywhere in the tenant (see the operation description).
     */
    @SerializedName("older_than_days")
    var older_than_days: Long?,

    /**
     * Narrow the sweep to one inbox status, e.g. 'spam'.
     */
    @SerializedName("status")
    var status: FormSubmissionPruneRequestStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "dry_run" to dry_run as Any,
        "form_slug" to form_slug as Any,
        "older_than_days" to older_than_days as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormSubmissionPruneRequest(
            dry_run = map["dry_run"] as? Boolean,
            form_slug = map["form_slug"] as? String,
            older_than_days = (map["older_than_days"] as? Number)?.toLong(),
            status = FormSubmissionPruneRequestStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}