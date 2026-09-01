package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * A new comment. Send `blockUuids` for a thread anchored to blocks, `parentUuid` for a reply.
 */
data class PageCommentCreateRequest(
    /**
     * The blocks this thread is about, so the editor can draw a marker next to them. Leave empty for a comment about the page as a whole.
     */
    @SerializedName("blockUuids")
    var blockUuids: List<String>?,

    /**
     * The comment, as editor HTML. `<span data-type="mention" data-id="USER_ID">` is what this app reads to decide whom to notify; `<li data-type="taskItem" data-checked="false">` makes a checkbox the toggle-task route can flip.
     */
    @SerializedName("body")
    val body: String,

    /**
     * The root comment this replies to. Omit for a new thread — only roots can be resolved.
     */
    @SerializedName("parentUuid")
    var parentUuid: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "blockUuids" to blockUuids as Any,
        "body" to body as Any,
        "parentUuid" to parentUuid as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageCommentCreateRequest(
            blockUuids = map["blockUuids"] as? List<String>,
            body = map["body"] as String,
            parentUuid = map["parentUuid"] as? String,
        )
    }
}