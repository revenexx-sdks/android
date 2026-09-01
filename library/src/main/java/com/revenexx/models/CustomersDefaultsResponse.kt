package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class CustomersDefaultsResponse(
    /**
     * One entry per value set, keyed by its route name — `payment-terms`, `address-types`, `lifecycle-stages`, `contact-event-kinds`. Each says what THIS call did: `created` are the codes it inserted, `existing` the seeded codes it found already there and left completely alone (a merchant's rename included). A second call therefore answers with everything under `existing` and nothing under `created`.
     */
    @SerializedName("sets")
    var sets: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "sets" to sets as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CustomersDefaultsResponse(
            sets = map["sets"] as? Any,
        )
    }
}