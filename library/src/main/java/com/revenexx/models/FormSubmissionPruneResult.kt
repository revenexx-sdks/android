package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class FormSubmissionPruneResult(
    /**
     * Submissions created before this instant match. It is `now - older_than_days`, computed after the retention floor was applied, so it is the honest answer to "what did this call actually consider".
     */
    @SerializedName("cutoff")
    var cutoff: String?,

    /**
     * How many rows this call actually removed — always 0 on a dry run, and at most the 500-row batch size on a real one.
     */
    @SerializedName("deleted")
    var deleted: Long?,

    /**
     * Whether this call was a preview. True — the default — means nothing was deleted and `matched` is what a real run would take.
     */
    @SerializedName("dry_run")
    var dry_run: Boolean?,

    /**
     * True when the request asked for a shorter age than the floor allows.
     */
    @SerializedName("floor_applied")
    var floor_applied: Boolean?,

    /**
     * How many rows match, ignoring the batch size.
     */
    @SerializedName("matched")
    var matched: Long?,

    /**
     * The threshold actually applied, after the retention floor.
     */
    @SerializedName("older_than_days")
    var older_than_days: Double?,

    /**
     * Matched rows left after this batch — call again. Absent on a dry run, which deletes nothing.
     */
    @SerializedName("remaining")
    var remaining: Long?,

    /**
     * The retention floor this sweep honoured: the LONGEST submission_retention_days configured anywhere in the tenant, baseline or market. Not the value the calling market sees — a tenant-wide sweep has to keep the longest promise anybody was given.
     */
    @SerializedName("retention_days")
    var retention_days: Double?,

    /**
     * The market whose submission_retention_days set the floor — the merchant's own market CODE — or null when the tenant baseline did. It is there so a merchant can see WHY the sweep would not go younger, since the market that bound it is often not the one the request was made from.
     */
    @SerializedName("retention_market")
    var retention_market: String?,

    /**
     * Up to five matching rows (dry runs only) — id, form_slug and created_at, never the submitted data.
     */
    @SerializedName("sample")
    var sample: List<FormSubmissionPruneSample>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cutoff" to cutoff as Any,
        "deleted" to deleted as Any,
        "dry_run" to dry_run as Any,
        "floor_applied" to floor_applied as Any,
        "matched" to matched as Any,
        "older_than_days" to older_than_days as Any,
        "remaining" to remaining as Any,
        "retention_days" to retention_days as Any,
        "retention_market" to retention_market as Any,
        "sample" to sample?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = FormSubmissionPruneResult(
            cutoff = map["cutoff"] as? String,
            deleted = (map["deleted"] as? Number)?.toLong(),
            dry_run = map["dry_run"] as? Boolean,
            floor_applied = map["floor_applied"] as? Boolean,
            matched = (map["matched"] as? Number)?.toLong(),
            older_than_days = (map["older_than_days"] as? Number)?.toDouble(),
            remaining = (map["remaining"] as? Number)?.toLong(),
            retention_days = (map["retention_days"] as? Number)?.toDouble(),
            retention_market = map["retention_market"] as? String,
            sample = (map["sample"] as List<Map<String, Any>>).map { FormSubmissionPruneSample.from(map = it) },
        )
    }
}