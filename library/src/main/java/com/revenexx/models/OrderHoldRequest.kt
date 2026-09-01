package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Stop the order. The reason is optional but is what the guard quotes back at whoever tries to ship, so an unexplained hold is a hold nobody can resolve.
 */
data class OrderHoldRequest(
    /**
     * Why the order is held, in the words the shipping guard quotes back. Null when it is not held — releasing a hold clears it.
     */
    @SerializedName("reason")
    var reason: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "reason" to reason as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderHoldRequest(
            reason = map["reason"] as? String,
        )
    }
}