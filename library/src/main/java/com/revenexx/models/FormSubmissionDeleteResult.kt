package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FormSubmissionDeleteResult(
    /**
     * Always true — the row is gone. A submission that was not there answers 404 instead, so this is never false.
     */
    @SerializedName("deleted")
    var deleted: Boolean?,

    /**
     * The submission that was removed, echoed from the path.
     */
    @SerializedName("id")
    var id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "deleted" to deleted as Any,
        "id" to id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormSubmissionDeleteResult(
            deleted = map["deleted"] as? Boolean,
            id = map["id"] as? String,
        )
    }
}