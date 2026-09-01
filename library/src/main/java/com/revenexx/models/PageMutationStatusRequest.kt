package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Which entry of the history to switch, and to what.
 */
data class PageMutationStatusRequest(
    /**
     * Whether the entry takes part in the replay.
     */
    @SerializedName("enabled")
    val enabled: Boolean,

    /**
     * The position in the mutation log to switch. Unknown positions answer 404.
     */
    @SerializedName("index")
    val index: Long,

    /**
     * Which language the returned state should be resolved for.
     */
    @SerializedName("langcode")
    var langcode: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "enabled" to enabled as Any,
        "index" to index as Any,
        "langcode" to langcode as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageMutationStatusRequest(
            enabled = map["enabled"] as Boolean,
            index = (map["index"] as Number).toLong(),
            langcode = map["langcode"] as? String,
        )
    }
}