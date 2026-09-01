package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * What seeding found and what it had to write. Idempotent twice over: by code, and by the existence of ANY default list — so changing default_price_list_code later never produces a second default.
 */
data class PriceListDefaultsResponse(
    /**
     * Codes of the lists this call created — empty on a tenant that was already seeded.
     */
    @SerializedName("created")
    var created: List<String>?,

    /**
     * Codes of the lists that were already there, so nothing was written for them.
     */
    @SerializedName("existing")
    var existing: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created" to created as Any,
        "existing" to existing as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PriceListDefaultsResponse(
            created = map["created"] as? List<String>,
            existing = map["existing"] as? List<String>,
        )
    }
}