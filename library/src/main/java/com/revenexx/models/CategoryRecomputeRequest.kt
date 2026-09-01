package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Omit the body entirely to resume an unfinished pass, or start a fresh one when the last completed.
 */
data class CategoryRecomputeRequest(
    /**
     * The `cursor` a previous call returned, to continue that pass. Send `null` explicitly to restart from the beginning; omit the field to let the app decide (resume if a pass is in flight, otherwise start fresh). Anything that is not a string or null is a 400.
     */
    @SerializedName("cursor")
    var cursor: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "cursor" to cursor as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = CategoryRecomputeRequest(
            cursor = map["cursor"] as? String,
        )
    }
}