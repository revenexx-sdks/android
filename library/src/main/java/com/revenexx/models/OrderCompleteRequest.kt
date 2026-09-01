package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * No required fields — send {}.
 */
data class OrderCompleteRequest(
    /**
     * Who closed the order, as the caller reports it. Not stored on the order: it is carried in the order.completed event's payload, which is where the audit trail keeps who did what. Free text, not resolved against a user directory.
     */
    @SerializedName("completed_by")
    var completed_by: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "completed_by" to completed_by as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderCompleteRequest(
            completed_by = map["completed_by"] as? String,
        )
    }
}