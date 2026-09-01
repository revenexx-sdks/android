package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Confirmation that the currency of a market is gone. The row itself is not returned — read it before deleting if you need it.
 */
data class MarketCurrencyDeleted(
    /**
     * Always true — a row that was not there is a 404, not a false.
     */
    @SerializedName("deleted")
    var deleted: Boolean?,

    /**
     * The id of the row that was deleted.
     */
    @SerializedName("id")
    var id: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "deleted" to deleted as Any,
        "id" to id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketCurrencyDeleted(
            deleted = map["deleted"] as? Boolean,
            id = map["id"] as? String,
        )
    }
}