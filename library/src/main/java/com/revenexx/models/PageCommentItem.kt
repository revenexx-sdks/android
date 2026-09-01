package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * One comment, in the shape the editor renders — this is not the stored row: the id is `uuid`, the timestamps are `created`/`updated` and the author is nested under `user`.
 */
data class PageCommentItem(
    /**
     * The blocks this thread hangs on, so the editor can draw a marker next to them. Empty for a comment about the page as a whole.
     */
    @SerializedName("blockUuids")
    var blockUuids: List<String>?,

    /**
     * The comment itself, as editor HTML. @mentions are `<span data-type="mention" data-id="…">` — that is what this app reads to decide whom to notify — and task checkboxes are `<li data-type="taskItem" data-checked="…">`.
     */
    @SerializedName("body")
    var body: String?,

    /**
     * When the comment was written.
     */
    @SerializedName("created")
    var created: String?,

    /**
     * The root comment this is a reply to. Absent on a root — and only roots can be resolved.
     */
    @SerializedName("parentUuid")
    var parentUuid: String?,

    /**
     * Whether the thread was marked done. Replies inherit nothing: resolving is a property of the root.
     */
    @SerializedName("resolved")
    var resolved: Boolean?,

    /**
     * When it was last edited. Absent when it never was.
     */
    @SerializedName("updated")
    var updated: String?,

    /**
     * Who wrote it, or `null` when it was written without an identity.
     */
    @SerializedName("user")
    var user: Any?,

    /**
     * The comment id. Every comment route addresses one by it.
     */
    @SerializedName("uuid")
    var uuid: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "blockUuids" to blockUuids as Any,
        "body" to body as Any,
        "created" to created as Any,
        "parentUuid" to parentUuid as Any,
        "resolved" to resolved as Any,
        "updated" to updated as Any,
        "user" to user as Any,
        "uuid" to uuid as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PageCommentItem(
            blockUuids = map["blockUuids"] as? List<String>,
            body = map["body"] as? String,
            created = map["created"] as? String,
            parentUuid = map["parentUuid"] as? String,
            resolved = map["resolved"] as? Boolean,
            updated = map["updated"] as? String,
            user = map["user"] as? Any,
            uuid = map["uuid"] as? String,
        )
    }
}