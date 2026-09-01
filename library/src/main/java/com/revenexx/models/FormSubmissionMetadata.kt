package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.FormNotifySource

/**
 * Free-form metadata, plus what this app stamped on at insert. The recipient is resolved ONCE, here, because this row is the payload of `form.submitted` — a workflow reads the address off the event instead of re-resolving a form's settings that may since have changed.
 */
data class FormSubmissionMetadata<T>(
    /**
     * The resolved notification recipient, or null when neither the form nor the tenant names one.
     */
    @SerializedName("notify_email")
    var notify_email: String?,

    /**
     * Which of the two configured recipients won: the form's own, or the tenant setting.
     */
    @SerializedName("notify_source")
    var notify_source: FormNotifySource?,

    /**
     * Present only on a submission the honeypot caught: 'honeypot'.
     */
    @SerializedName("spam_reason")
    var spam_reason: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "notify_email" to notify_email as Any,
        "notify_source" to notify_source?.value as Any,
        "spam_reason" to spam_reason as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            notify_email: String?,
            notify_source: FormNotifySource?,
            spam_reason: String?,
            data: Map<String, Any>
        ) = FormSubmissionMetadata<Map<String, Any>>(
            notify_email,
            notify_source,
            spam_reason,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = FormSubmissionMetadata<T>(
            notify_email = map["notify_email"] as? String,
            notify_source = FormNotifySource.values().find { it.value == (map["notify_source"] as? String) } ?: null,
            spam_reason = map["spam_reason"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}