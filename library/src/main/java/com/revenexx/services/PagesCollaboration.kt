package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * The review layer over a page: comment threads pinned to blocks, with @mentions, task checkboxes and resolve/reopen, plus the notification feed those threads and an ownership handover raise, and the user directory a mention is picked from. Comments belong to the PAGE, not to a revision or an edit state, so they outlive publishing and reverting — which is what makes them usable as a review trail. Every write here answers the page's whole comment list rather than the row it touched, so a client can render from one response.
 */
class PagesCollaboration(client: Client) : Service(client) {

    /**
     * The caller's own notifications, newest first, 20 at a time. Paged by an opaque cursor rather than by offset, so new arrivals never shift a page under the reader. It is also the one read in this app that writes: `?markAsRead=true` flags the notifications on the page it just returned as read, which is how a feed that has been looked at empties its badge without a second call — leave it off and reading changes nothing.
     *
     * @param after Continue after this cursor — pass back the `cursor` from the previous page. Omit for the first page. It encodes the last item's timestamp and id, so it is stable while new notifications arrive.
     * @param markAsRead Send the literal `true` to mark the notifications ON THIS PAGE read as a side effect of reading them. Any other value, including `1` and `false`, is accepted and leaves them unread.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesEditorNotificationsList(
        after: String? = null,
        markAsRead: String? = null,
    ): Any {
        val apiPath = "/v1/pages/editor/notifications"

        val apiParams = mutableMapOf<String, Any?>(
            "after" to after,
            "markAsRead" to markAsRead,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Empties the badge in one call. Every unread notification of the CURRENT user is flagged read — the user is the one the request's context token names and there is no body with which to name another. Nothing is deleted: `GET /pages/editor/notifications` still returns the same feed, just with `read` set. The answer is the new unread count, so a client can set the badge straight from it without a second read.
     *
     * @return [Any]
     */
    suspend fun pagesEditorNotificationsMarkAllRead(
    ): Any {
        val apiPath = "/v1/pages/editor/notifications/mark-all-read"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * The cheap poll behind the badge.
     *
     * @return [Any]
     */
    suspend fun pagesEditorNotificationsUnreadCount(
    ): Any {
        val apiPath = "/v1/pages/editor/notifications/unread-count"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * What the @mention picker is filled from. When the identity service cannot be reached this degrades to the authors who have already commented on this tenant's pages rather than answering an error — a mention list that is short is more useful than one that is missing.
     *
     * @return [Any]
     */
    suspend fun pagesEditorUsers(
    ): Any {
        val apiPath = "/v1/pages/editor/users"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Every comment on the page in one flat list, oldest first, roots and replies together and resolved threads included — there is no filter and no paging, because the editor nests and filters them itself from `parentUuid` and pins each root to its blocks with `blockUuids`. Comments hang off the PAGE, not off a revision or an edit state, so publishing and reverting leave them standing; that is what makes them usable as a review trail across several rounds of edits.
     *
     * @param pageId The page being edited.
     * @return [com.revenexx.models.PageCommentList]
     */
    suspend fun pagesEditorCommentsList(
        pageId: String,
    ): com.revenexx.models.PageCommentList {
        val apiPath = "/v1/pages/editor/{page_id}/comments"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PageCommentList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PageCommentList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PageCommentList::class.java,
            converter,
        )
    }


    /**
     * The same route writes both kinds, and which one you get is decided by the body: `blockUuids` starts a new thread pinned to those blocks, `parentUuid` hangs a reply under an existing root. Everyone named with an @mention in the body is notified, and on a reply so is everybody already in the thread — the actor never notifies themselves.
     *
     * @param pageId The page being edited.
     * @param body The comment, as editor HTML. `<span data-type="mention" data-id="USER_ID">` is what this app reads to decide whom to notify; `<li data-type="taskItem" data-checked="false">` makes a checkbox the toggle-task route can flip.
     * @param blockUuids The blocks this thread is about, so the editor can draw a marker next to them. Leave empty for a comment about the page as a whole.
     * @param parentUuid The root comment this replies to. Omit for a new thread — only roots can be resolved.
     * @return [com.revenexx.models.PageCommentList]
     */
    @JvmOverloads
    suspend fun pagesEditorCommentsCreate(
        pageId: String,
        body: String,
        blockUuids: List<String>? = null,
        parentUuid: String? = null,
    ): com.revenexx.models.PageCommentList {
        val apiPath = "/v1/pages/editor/{page_id}/comments"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "blockUuids" to blockUuids,
            "body" to body,
            "parentUuid" to parentUuid,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PageCommentList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PageCommentList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PageCommentList::class.java,
            converter,
        )
    }


    /**
     * A hard delete, and deleting a root takes its replies with it.
     *
     * @param pageId The page being edited.
     * @param uuid The comment id — the `uuid` of a `PageCommentItem`, not a row id of any other shape.
     * @return [com.revenexx.models.PageCommentList]
     */
    suspend fun pagesEditorCommentsDelete(
        pageId: String,
        uuid: String,
    ): com.revenexx.models.PageCommentList {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PageCommentList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PageCommentList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PageCommentList::class.java,
            converter,
        )
    }


    /**
     * Rewrites what a comment says, and only its author may — a comment carries an `author_id` and anybody else is refused with 403. Only the body moves: what the comment is pinned to, whether the thread is resolved and who wrote it are all fixed when it is created. Rewriting a body does NOT re-run the @mention notifications, so mentioning somebody new by editing will not reach them. Answers the page's whole comment list rather than the one row, so a client can re-render from the response.
     *
     * @param pageId The page being edited.
     * @param uuid The comment id — the `uuid` of a `PageCommentItem`, not a row id of any other shape.
     * @param body The comment, as editor HTML. Replaces the old body completely.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesEditorCommentsUpdate(
        pageId: String,
        uuid: String,
        body: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
            "body" to body,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Marks a thread handled, so the editor stops surfacing it on the block it is pinned to. Only a ROOT can be resolved — resolved-ness is a property of the thread and not of a message in it, so pointing this at a reply is refused with 400 rather than quietly resolving its parent. Nothing is deleted, nobody is notified, and the thread stays in the list; `.../unresolve` is the way back. Answers the page's whole comment list.
     *
     * @param pageId The page being edited.
     * @param uuid The comment id — the `uuid` of a `PageCommentItem`, not a row id of any other shape.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesEditorCommentsResolve(
        pageId: String,
        uuid: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}/resolve"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * A comment body may carry a task list. This flips one checkbox by rewriting the body's markup, and answers the single comment rather than the whole list. A `taskIndex` that names no checkbox is refused and nothing is written — the comment's `updated_at` is the editor's "edited" marker, so a call that changes nothing must not move it.
     *
     * @param pageId The page being edited.
     * @param uuid The comment id — the `uuid` of a `PageCommentItem`, not a row id of any other shape.
     * @param taskIndex The task item to toggle, counted in document order from 0. A comment with fewer tasks than that answers 400, and so does anything that is not a whole number at or above 0.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesEditorCommentsToggleTask(
        pageId: String,
        uuid: String,
        taskIndex: Long,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}/toggle-task"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
            "taskIndex" to taskIndex,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * Clears the resolved flag and puts the thread back in front of whoever is editing — the mirror of `.../resolve` in every respect, including that only a root can be reopened and that a reply answers 400. A thread that was already open is accepted and stays open. Answers the page's whole comment list.
     *
     * @param pageId The page being edited.
     * @param uuid The comment id — the `uuid` of a `PageCommentItem`, not a row id of any other shape.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesEditorCommentsUnresolve(
        pageId: String,
        uuid: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}/unresolve"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}