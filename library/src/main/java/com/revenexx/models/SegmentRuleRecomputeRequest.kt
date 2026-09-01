package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class SegmentRuleRecomputeRequest(
    /**
     * Continuation token from a previous response — the id of the last organization the pass touched. Omit to resume or start automatically; pass null to force a restart from the beginning.
     */
    @SerializedName("cursor")
    var cursor: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cursor" to cursor as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentRuleRecomputeRequest(
            cursor = map["cursor"] as? String,
        )
    }
}