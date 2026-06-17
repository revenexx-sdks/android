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

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created" to created as Any,
        "existing" to existing as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ChannelDefaults(
            created = map["created"] as? List<String>,
            existing = map["existing"] as? List<String>,
        )
    }
}