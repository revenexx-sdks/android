package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Confirmation that the tax class of a market is gone. The row itself is not returned — read it before deleting if you need it.
 */
data class MarketTaxClassDeleted(
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

    /**
     * False when the cross-app usage question could not be asked (shipping not installed, or unreachable) — the row was deleted without that guarantee.
     */
    @SerializedName("usage_checked")
    var usage_checked: Boolean?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "deleted" to deleted as Any,
        "id" to id as Any,
        "usage_checked" to usage_checked as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = MarketTaxClassDeleted(
            deleted = map["deleted"] as? Boolean,
            id = map["id"] as? String,
            usage_checked = map["usage_checked"] as? Boolean,
        )
    }
}