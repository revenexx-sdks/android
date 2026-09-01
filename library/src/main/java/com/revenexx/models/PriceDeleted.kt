package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The row is gone. Deleting a price list cascades to its entries.
 */
data class PriceDeleted(
    /**
     * Always true — a row that was not there answers 404 instead.
     */
    @SerializedName("deleted")
    var deleted: Boolean?,

    /**
     * The row that was removed.
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
        ) = PriceDeleted(
            deleted = map["deleted"] as? Boolean,
            id = map["id"] as? String,
        )
    }
}