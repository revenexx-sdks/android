package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Where to put the undo pointer.
 */
data class PageHistoryRequest(
    /**
     * The position in the mutation log to materialize at. `-1` undoes everything; the last position redoes everything. Values outside the log are clamped rather than refused.
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
        "index" to index as Any,
        "langcode" to langcode as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageHistoryRequest(
            index = (map["index"] as Number).toLong(),
            langcode = map["langcode"] as? String,
        )
    }
}