package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The same answer for the channel types, which are seeded first because the seeded channel carries one.
 */
data class ChannelTypeDefaults(
    /**
     * Channel type codes this call wrote. A fresh tenant gets all 5; a settled one gets none.
     */
    @SerializedName("created")
    var created: List<String>?,

    /**
     * Seeded type codes that were already there. Note the consequence of "idempotent" being keyed on the code: a seeded type the merchant deliberately retired is re-created by the next call and comes back under `created`. Types the merchant added themselves are never touched.
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
        ) = ChannelTypeDefaults(
            created = map["created"] as? List<String>,
            existing = map["existing"] as? List<String>,
        )
    }
}