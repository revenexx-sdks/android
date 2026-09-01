package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The list as it now stands: everything that was there is gone and these are the rows that took its place.
 */
data class PriceEntriesReplaceResponse(
    /**
     * The complete new entry set, as stored — including the ids and timestamps the database filled in.
     */
    @SerializedName("entries")
    var entries: List<PriceEntry>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "entries" to entries?.map { it.toMap() } as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceEntriesReplaceResponse(
            entries = (map["entries"] as List<Map<String, Any>>).map { PriceEntry.from(map = it) },
        )
    }
}