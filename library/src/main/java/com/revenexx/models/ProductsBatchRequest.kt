package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Name the products either way, or both ways. Send at least one non-empty list; the two are unioned and a product named twice comes back once.
 */
data class ProductsBatchRequest(
    /**
     * Product ids, when the caller already holds them.
     */
    @SerializedName("ids")
    var ids: List<String>?,

    /**
     * Product SKUs — the identifier a foreign system carries, which is why this route exists at all.
     */
    @SerializedName("skus")
    var skus: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "ids" to ids as Any,
        "skus" to skus as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ProductsBatchRequest(
            ids = map["ids"] as? List<String>,
            skus = map["skus"] as? List<String>,
        )
    }
}