package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.FormStatus

/**
 * 
 */
data class FormDeleteResult(
    /**
     * True when the policy is 'archive' and submissions exist — the form was archived, not deleted.
     */
    @SerializedName("archived")
    var archived: Boolean?,

    /**
     * The form row was removed — and with it, via the cascade, every submission it had. `submissions` below says how many went, and they are not recoverable.
     */
    @SerializedName("deleted")
    var deleted: Boolean?,

    /**
     * The form in the path.
     */
    @SerializedName("id")
    var id: String?,

    /**
     * The form's status after the call. Only present on the archive branch.
     */
    @SerializedName("status")
    var status: FormStatus?,

    /**
     * How many submissions the form had when the call was weighed — and therefore, when `deleted` is true, how many were deleted with it. The whole inbox, across every market: the cascade is a database operation and takes them all, so an active `X-Revenexx-Market` does not narrow this number.
     */
    @SerializedName("submissions")
    var submissions: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "archived" to archived as Any,
        "deleted" to deleted as Any,
        "id" to id as Any,
        "status" to status?.value as Any,
        "submissions" to submissions as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormDeleteResult(
            archived = map["archived"] as? Boolean,
            deleted = map["deleted"] as? Boolean,
            id = map["id"] as? String,
            status = FormStatus.values().find { it.value == (map["status"] as? String) } ?: null,
            submissions = (map["submissions"] as? Number)?.toLong(),
        )
    }
}