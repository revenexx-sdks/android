package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PaymentErrorRedactRequest(
    /**
     * Write the reclassified values. Defaults to false, which reports what WOULD change and touches nothing.
     */
    @SerializedName("apply")
    var apply: Boolean?,

    /**
     * How many payments to scan, oldest first. Defaults to 500, capped at 5000 — a tenant with more pre-taxonomy rows needs several runs, and re-running is free.
     */
    @SerializedName("limit")
    var limit: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "apply" to apply as Any,
        "limit" to limit as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentErrorRedactRequest(
            apply = map["apply"] as? Boolean,
            limit = (map["limit"] as? Number)?.toLong(),
        )
    }
}