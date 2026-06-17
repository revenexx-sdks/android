package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PriceEntriesReplaceRequest(
    /**
     * The complete new entry set (set semantics).
     */
    @SerializedName("entries")
    val entries: List<PriceEntryReplaceItem>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "entries" to entries.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesReplaceRequest(
            entries = (map["entries"] as List<Map<String, Any>>).map { PriceEntryReplaceItem.from(map = it) },
        )
    }
}