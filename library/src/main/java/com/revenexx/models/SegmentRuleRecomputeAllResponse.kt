package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class SegmentRuleRecomputeAllResponse(
    /**
     * Rule memberships inserted across every segment in THIS call.
     */
    @SerializedName("added")
    var added: Long?,

    /**
     * False when any segment is unfinished or skipped — call again.
     */
    @SerializedName("done")
    var done: Boolean?,

    /**
     * Segments whose own recompute raised — they carry `error` and `status` in `segments` and did not abort the run.
     */
    @SerializedName("failed")
    var failed: Long?,

    /**
     * Ruled segments the run looked at.
     */
    @SerializedName("processed")
    var processed: Long?,

    /**
     * Rule memberships deleted across every segment in THIS call.
     */
    @SerializedName("removed")
    var removed: Long?,

    /**
     * One entry per segment; a failed segment carries `error` and `status` instead of the counters.
     */
    @SerializedName("segments")
    var segments: List<Any>?,

    /**
     * Segments the budget did not reach at all.
     */
    @SerializedName("skipped")
    var skipped: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "added" to added as Any,
        "done" to done as Any,
        "failed" to failed as Any,
        "processed" to processed as Any,
        "removed" to removed as Any,
        "segments" to segments as Any,
        "skipped" to skipped as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = SegmentRuleRecomputeAllResponse(
            added = (map["added"] as? Number)?.toLong(),
            done = map["done"] as? Boolean,
            failed = (map["failed"] as? Number)?.toLong(),
            processed = (map["processed"] as? Number)?.toLong(),
            removed = (map["removed"] as? Number)?.toLong(),
            segments = map["segments"] as? List<Any>,
            skipped = (map["skipped"] as? Number)?.toLong(),
        )
    }
}