package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.PriceEntriesBulkMode

/**
 * A chunk of an import. Unlike the replace call it never wipes the list.
 */
data class PriceEntriesBulkRequest(
    /**
     * At most 5000 rows per call — send a large book in chunks.
     */
    @SerializedName("entries")
    val entries: List<PriceEntryReplaceItem>,

    /**
     * Default 'upsert': a row naming a rung the list already has (same product/sku AND quantity_min) updates it. 'append' always inserts — a re-run then duplicates the ladder, which is what makes an ambiguous tier table.
     */
    @SerializedName("mode")
    var mode: PriceEntriesBulkMode?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "entries" to entries.map { it.toMap() } as Any,
        "mode" to mode?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesBulkRequest(
            entries = (map["entries"] as List<Map<String, Any>>).map { PriceEntryReplaceItem.from(map = it) },
            mode = PriceEntriesBulkMode.values().find { it.value == (map["mode"] as? String) } ?: null,
        )
    }
}