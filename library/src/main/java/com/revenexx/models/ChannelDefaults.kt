package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class ChannelDefaults(
    /**
     * Channel codes created by this call.
     */
    @SerializedName("created")
    var created: List<String>?,

    /**
     * Default channel codes that already existed.
     */
    @SerializedName("existing")
    var existing: List<String>?,

    /**
     * The same answer for the channel types, which are seeded first because the seeded channel carries one.
     */
    @SerializedName("types")
    var types: ChannelTypeDefaults?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created" to created as Any,
        "existing" to existing as Any,
        "types" to types?.toMap() as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelDefaults(
            created = map["created"] as? List<String>,
            existing = map["existing"] as? List<String>,
            types = ChannelTypeDefaults.from(map = map["types"] as Map<String, Any>),
        )
    }
}