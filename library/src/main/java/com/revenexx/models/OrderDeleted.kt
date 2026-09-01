package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The row is gone. Deleting is not idempotent here: a second call answers 404, because the row no longer resolves.
 */
data class OrderDeleted(
    /**
     * Always true — a failed delete is a status code, not a false here.
     */
    @SerializedName("deleted")
    var deleted: Boolean?,

    /**
     * The id of the row that was deleted, echoed back.
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
        ) = OrderDeleted(
            deleted = map["deleted"] as? Boolean,
            id = map["id"] as? String,
        )
    }
}