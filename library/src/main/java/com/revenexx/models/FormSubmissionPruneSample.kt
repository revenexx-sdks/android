package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One row the sweep would delete, shown so a merchant can recognise what is at stake before turning the preview off. Three columns only — never the submitted data.
 */
data class FormSubmissionPruneSample(
    /**
     * When it arrived — the age this sweep is judging it on.
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * The form's slug as it stood when this submission arrived, copied onto the row: the inbox filters by form without a join, and a submission still says which form collected it after that form has been renamed. It does not outlive a DELETED form — the foreign key cascades and takes the submission with it. On a write the body's value WINS; omit it and the form's own slug is copied in.
     */
    @SerializedName("form_slug")
    var form_slug: String?,

    /**
     * The submission that would be deleted. Fetch it with GET /v1/forms/submissions/{id} to see what it holds.
     */
    @SerializedName("id")
    var id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created_at" to created_at as Any,
        "form_slug" to form_slug as Any,
        "id" to id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormSubmissionPruneSample(
            created_at = map["created_at"] as? String,
            form_slug = map["form_slug"] as? String,
            id = map["id"] as? String,
        )
    }
}