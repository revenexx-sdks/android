package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class SegmentRuleRecomputeResponse(
    /**
     * Rule memberships inserted by THIS call.
     */
    @SerializedName("added")
    var added: Long?,

    /**
     * True when every membership insert used a bulk array request; false if any batch fell back to row-at-a-time.
     */
    @SerializedName("batched")
    var batched: Boolean?,

    /**
     * Set when the pass completes.
     */
    @SerializedName("computed_at")
    var computed_at: String?,

    /**
     * Send back on the next call; null when the pass is done.
     */
    @SerializedName("cursor")
    var cursor: String?,

    /**
     * False means work remains — POST again with `cursor`.
     */
    @SerializedName("done")
    var done: Boolean?,

    /**
     * Matching organizations examined by THIS call.
     */
    @SerializedName("processed")
    var processed: Long?,

    /**
     * Rule memberships deleted by THIS call.
     */
    @SerializedName("removed")
    var removed: Long?,

    /**
     * The segment that was recomputed.
     */
    @SerializedName("segment_id")
    var segment_id: String?,

    /**
     * The rule's full match count; null until done.
     */
    @SerializedName("total")
    var total: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "added" to added as Any,
        "batched" to batched as Any,
        "computed_at" to computed_at as Any,
        "cursor" to cursor as Any,
        "done" to done as Any,
        "processed" to processed as Any,
        "removed" to removed as Any,
        "segment_id" to segment_id as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentRuleRecomputeResponse(
            added = (map["added"] as? Number)?.toLong(),
            batched = map["batched"] as? Boolean,
            computed_at = map["computed_at"] as? String,
            cursor = map["cursor"] as? String,
            done = map["done"] as? Boolean,
            processed = (map["processed"] as? Number)?.toLong(),
            removed = (map["removed"] as? Number)?.toLong(),
            segment_id = map["segment_id"] as? String,
            total = (map["total"] as? Number)?.toLong(),
        )
    }
}