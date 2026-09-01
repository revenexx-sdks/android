package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEntriesBulkMode

/**
 * Counts, not rows: an import chunk of 5000 does not echo 5000 entries back.
 */
data class PriceEntriesBulkResponse(
    /**
     * Rows inserted — rungs this list did not have.
     */
    @SerializedName("created")
    var created: Long?,

    /**
     * The mode actually applied — the request's, or the default `upsert`.
     */
    @SerializedName("mode")
    var mode: PriceEntriesBulkMode?,

    /**
     * Existing rungs rewritten in place (always 0 in append mode).
     */
    @SerializedName("updated")
    var updated: Long?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created" to created as Any,
        "mode" to mode?.value as Any,
        "updated" to updated as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesBulkResponse(
            created = (map["created"] as? Number)?.toLong(),
            mode = PriceEntriesBulkMode.values().find { it.value == (map["mode"] as? String) } ?: null,
            updated = (map["updated"] as? Number)?.toLong(),
        )
    }
}