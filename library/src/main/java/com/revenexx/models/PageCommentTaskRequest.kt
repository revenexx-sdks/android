package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Which checkbox to flip.
 */
data class PageCommentTaskRequest(
    /**
     * The task item to toggle, counted in document order from 0. A comment with fewer tasks than that answers 400, and so does anything that is not a whole number at or above 0.
     */
    @SerializedName("taskIndex")
    val taskIndex: Long,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "taskIndex" to taskIndex as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageCommentTaskRequest(
            taskIndex = (map["taskIndex"] as Number).toLong(),
        )
    }
}