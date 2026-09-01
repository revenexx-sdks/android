package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CategoryRecomputeResult(
    /**
     * Membership rows inserted with source='rule' by this call.
     */
    @SerializedName("added")
    var added: Long?,

    /**
     * False → the bulk insert was refused and the call fell back to one request per row. A performance fact, not an error.
     */
    @SerializedName("batched")
    var batched: Boolean?,

    /**
     * The category this pass belongs to, echoed back — a caller driving several loops keys its state by it.
     */
    @SerializedName("category_id")
    var category_id: String?,

    /**
     * When the pass completed, and what `categories.rules_computed_at` was stamped with. Null while `done` is false.
     */
    @SerializedName("computed_at")
    var computed_at: String?,

    /**
     * The product id this call reconciled up to, to hand back on the next one. Null when `done`.
     */
    @SerializedName("cursor")
    var cursor: String?,

    /**
     * False → this call spent its budget mid-pass. Send `cursor` back to continue; the counters below are THIS call only, so a caller looping to completion sums them itself.
     */
    @SerializedName("done")
    var done: Boolean?,

    /**
     * Matching products examined by this call.
     */
    @SerializedName("processed")
    var processed: Long?,

    /**
     * Stale rule rows deleted by this call.
     */
    @SerializedName("removed")
    var removed: Long?,

    /**
     * Products the rule currently selects. Null while `done` is false — the pass has not seen the whole catalog yet, so there is no total to report.
     */
    @SerializedName("total")
    var total: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "added" to added as Any,
        "batched" to batched as Any,
        "category_id" to category_id as Any,
        "computed_at" to computed_at as Any,
        "cursor" to cursor as Any,
        "done" to done as Any,
        "processed" to processed as Any,
        "removed" to removed as Any,
        "total" to total as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CategoryRecomputeResult(
            added = (map["added"] as? Number)?.toLong(),
            batched = map["batched"] as? Boolean,
            category_id = map["category_id"] as? String,
            computed_at = map["computed_at"] as? String,
            cursor = map["cursor"] as? String,
            done = map["done"] as? Boolean,
            processed = (map["processed"] as? Number)?.toLong(),
            removed = (map["removed"] as? Number)?.toLong(),
            total = (map["total"] as? Number)?.toLong(),
        )
    }
}