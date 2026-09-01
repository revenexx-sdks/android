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
 * The unpublished side: one page open in the visual editor, held as a server-side mutation log rather than as edited rows. Load the whole editor state in one call, append mutations, walk the undo/redo pointer, disable a single step, then publish — which materializes the log into the canonical blocks and writes a revision — or revert, which throws it away. An edit state has ONE owner at a time and every write asks for it, so taking a page over from a colleague is its own call. Scheduling, share-links for unpublished previews, machine translation and a person's own editor preferences hang off the same session.
 */
class PagesEditor(client: Client) : Service(client) {

    /**
     * The drafts overview — the "what is unpublished right now" list, across every page: who holds it, since when, and whether it is parked for a date. Always newest-first — this route does not read `order`. An edit state whose page has been deleted is dropped from `items` but still counted in `total`.
     *
     * @param status Which kind of working copy to list. Omitted means `active` — the drafts somebody is actually holding, which is what this route is opened for.
     * @param limit Page size (default 50). Unlike the list routes this one applies no ceiling of its own.
     * @param offset Row offset for pagination (default 0).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesEditorEditStates(
        status: com.revenexx.enums.PageEditStateStatus? = null,
        limit: Long? = null,
        offset: Long? = null,
    ): Any {
        val apiPath = "/v1/pages/editor/edit-states"

        val apiParams = mutableMapOf<String, Any?>(
            "status" to status,
            "limit" to limit,
            "offset" to offset,
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
     * The translation is the tenant's provider's, not this app's, and a tenant that has configured none gets no translation at all. The endpoint comes from the tenant setting `translate_endpoint` (PAGES_TRANSLATE_ENDPOINT remains a fallback). The bearer token does NOT: the gateway masks every setting flagged `sensitive`, so a key stored as one could never be read back — it stays the PAGES_TRANSLATE_KEY function secret. This app does not translate anything itself; it forwards `items` and hands the answer back.
     *
     * @param items The strings to translate. This app reads no element of the list — the provider defines the contract, and the blökkli adapter sends the fields below.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesEditorTranslate(
        items: List<Any>? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/translate"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
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
     * Per-user editor preferences — one row per user, scoped to this app. Not tenant configuration: nothing here changes what the API does, only how one person's editor looks.
     *
     * @return [Any]
     */
    suspend fun pagesEditorUserSettingsGet(
    ): Any {
        val apiPath = "/v1/pages/editor/user-settings"

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
     * Replaces the caller's preferences wholesale — this is not a merge, so send the whole bag.
     *
     * @param settings The whole preferences bag — replaced, not merged, so send all of it. Its keys vary by the editor build and this app reads none of them. Null or omitted stores `{}`, which is how a user resets their editor.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesEditorUserSettingsPut(
        settings: Any? = null,
    ): Any {
        val apiPath = "/v1/pages/editor/user-settings"

        val apiParams = mutableMapOf<String, Any?>(
            "settings" to settings,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * Undo and redo. The pointer is the edit state's `current_index`, the position in the mutation log the page is materialized at, and this route is the only thing that moves it — `GET …/state?index=` looks at another position without going there. The log itself is never rewritten — only the pointer moves — so redo stays available until the next change is appended.
     *
     * @param pageId The page being edited.
     * @param index The position in the mutation log to materialize at. `-1` undoes everything; the last position redoes everything. Values outside the log are clamped rather than refused.
     * @param langcode Which language the returned state should be resolved for.
     * @return [com.revenexx.models.MutationResponse]
     */
    @JvmOverloads
    suspend fun pagesEditorHistory(
        pageId: String,
        index: Long,
        langcode: String? = null,
    ): com.revenexx.models.MutationResponse {
        val apiPath = "/v1/pages/editor/{page_id}/history"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "index" to index,
            "langcode" to langcode,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MutationResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MutationResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MutationResponse::class.java,
            converter,
        )
    }


    /**
     * The cheap poll behind "someone else is editing this page": one integer, the moment the open edit state last moved, in epoch seconds rather than as a timestamp so a comparison is a subtraction. Compare it with the `updatedAt` you last saw and re-fetch the state only when it moved.
     *
     * @param pageId The page being edited.
     * @return [Any]
     */
    suspend fun pagesEditorLastChanged(
        pageId: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/last-changed"
            .replace("{pageId}", pageId)

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
     * Take one change out of the replay without deleting it — "what would the page look like without this edit". The entry stays in the history and can be switched back on.
     *
     * @param pageId The page being edited.
     * @param enabled Whether the entry takes part in the replay.
     * @param index The position in the mutation log to switch. Unknown positions answer 404.
     * @param langcode Which language the returned state should be resolved for.
     * @return [com.revenexx.models.MutationResponse]
     */
    @JvmOverloads
    suspend fun pagesEditorMutationStatus(
        pageId: String,
        enabled: Boolean,
        index: Long,
        langcode: String? = null,
    ): com.revenexx.models.MutationResponse {
        val apiPath = "/v1/pages/editor/{page_id}/mutation-status"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "enabled" to enabled,
            "index" to index,
            "langcode" to langcode,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MutationResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MutationResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MutationResponse::class.java,
            converter,
        )
    }


    /**
     * The one way page CONTENT changes. Each call appends one entry to the append-only log and answers the whole re-materialized state, so a client never re-fetches. A page nobody has opened yet needs no separate call to open it: the first mutation creates the edit state and takes ownership of it, and every later one asks for that ownership, so a second person editing the same page is refused until they take it over. Appending while the pointer sits mid-history discards the redo branch, exactly as an editor expects.
     *
     * @param pageId The page being edited.
     * @param plugin Which kind of change this is — `add`, `move`, `delete`, `duplicate`, `update_field_value`, `update_options`, … An id this app does not implement is refused with 400 rather than stored, because the log has to replay.
     * @param langcode Which language the returned state should be resolved for. Not the language the change is written in — that lives in the payload.
     * @param payload The arguments of that change; the keys depend on the plugin (`add` takes `{ bundle, hostEntityType, hostEntityUuid, hostField }`, `move` takes `{ uuid, preceedingUuid }`, and so on). Anything non-deterministic in it — new uuids, a library item's tree, a copied subtree — is resolved once here and stored, so replaying the log is deterministic forever.
     * @return [com.revenexx.models.MutationResponse]
     */
    @JvmOverloads
    suspend fun pagesEditorMutate(
        pageId: String,
        plugin: String,
        langcode: String? = null,
        payload: Any? = null,
    ): com.revenexx.models.MutationResponse {
        val apiPath = "/v1/pages/editor/{page_id}/mutations"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "langcode" to langcode,
            "payload" to payload,
            "plugin" to plugin,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.MutationResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MutationResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MutationResponse::class.java,
            converter,
        )
    }


    /**
     * Mints a link that shows this page's current edit state — the UNPUBLISHED one — to somebody without an editor account. The token is the whole credential — anyone holding it sees the page — so it expires, and a new one is cheap.
     *
     * @param pageId The page being edited.
     * @param ttlHours Hours until the link expires. Defaults to 72. After that `GET /pages/delivery/preview/{token}` answers 410 rather than 404, so the holder can tell "expired" from "wrong link".
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesEditorPreviewGrant(
        pageId: String,
        ttlHours: Long? = null,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/preview-grant"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "ttlHours" to ttlHours,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
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
     * Four things in one call: the mutation log is replayed into a finished block tree, that tree is snapshotted into a new revision, the page's canonical blocks are replaced by it, and the edit state is archived — so the page comes out of this with nothing unpublished and the working copy behind it closed rather than deleted. The revision is written FIRST and the canonical blocks replaced after, so a failure mid-way leaves the page recoverable. Block uuids survive, which is why comments anchored to a block outlive the publish.
     *
     * @param pageId The page being edited.
     * @param force Publish despite violations. Without it a page with unresolved violations answers 422 and nothing is written.
     * @param label What to call this publication in the page's history — "Autumn campaign" rather than a timestamp.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesEditorPublish(
        pageId: String,
        force: Boolean? = null,
        label: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/{page_id}/publish"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "force" to force,
            "label" to label,
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
     * Throws the whole working copy away: the edit state row is deleted and its mutation log with it, so the history goes too — this is not an undo and cannot itself be undone. Unlike publishing, which archives the edit state, nothing of it survives to be reopened. The published page is untouched.
     *
     * @param pageId The page being edited.
     * @return [com.revenexx.models.MutationResponse]
     */
    suspend fun pagesEditorRevert(
        pageId: String,
    ): com.revenexx.models.MutationResponse {
        val apiPath = "/v1/pages/editor/{page_id}/revert"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MutationResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MutationResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MutationResponse::class.java,
            converter,
        )
    }


    /**
     * Gated on the tenant setting `enable_scheduled_publishing`, which is off by default: nothing in the platform publishes a scheduled edit state yet, so a date accepted here would be a promise the app cannot keep. Every editor state carries `features.scheduledPublishing` so the control can be hidden rather than the refusal discovered.
     *
     * @param pageId The page being edited.
     * @param scheduledAt The moment to publish at. Stored on the edit state and echoed back normalized to UTC.
     * @return [com.revenexx.models.Error]
     */
    suspend fun pagesEditorSchedule(
        pageId: String,
        scheduledAt: String,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/{page_id}/schedule"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "scheduledAt" to scheduledAt,
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
     * The one call the visual editor boots on, and the only place the UNPUBLISHED page can be seen whole: the canonical blocks with every enabled mutation of the log replayed over them, the resulting field lists, the mutation history itself, who owns the edit state and where the undo pointer sits, and the tenant's editor feature flags. `langcode` decides which language the props resolve in, falling back to the page's source language. `index` replays the log up to a given position instead of the current one, which is how the editor previews an undo without performing it — it changes nothing, so it is safe to call at any position. Reading this creates nothing either: a page nobody has opened answers with a null `editState`, an empty history, and the published blocks as they stand.
     *
     * @param pageId The page being edited.
     * @param langcode Language to resolve every field for. Falls back to the page's source language, per field, so a half-translated page still comes back whole.
     * @param index Materialize the state at this point of the undo history instead of at the pointer the edit state carries. `-1` is "before the first change". It is how a diff view shows what one step did, and it does NOT move the pointer — `POST …/history` does that.
     * @return [com.revenexx.models.EditorState]
     */
    @JvmOverloads
    suspend fun pagesEditorState(
        pageId: String,
        langcode: String? = null,
        index: Long? = null,
    ): com.revenexx.models.EditorState {
        val apiPath = "/v1/pages/editor/{page_id}/state"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "langcode" to langcode,
            "index" to index,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.EditorState = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.EditorState.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.EditorState::class.java,
            converter,
        )
    }


    /**
     * One page has one writer. This is how the second person gets the pen — the previous owner is notified rather than silently locked out.
     *
     * @param pageId The page being edited.
     * @return [com.revenexx.models.MutationResponse]
     */
    suspend fun pagesEditorTakeOwnership(
        pageId: String,
    ): com.revenexx.models.MutationResponse {
        val apiPath = "/v1/pages/editor/{page_id}/take-ownership"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MutationResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MutationResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MutationResponse::class.java,
            converter,
        )
    }


    /**
     * Freezes a selection into a reusable starting point. The blocks are read out of the page's CURRENT edit state rather than out of what is published, so a template can be cut from work in progress and the uuids you send are the ones the editor is showing. Unlike making a block reusable, this COPIES: pages later made from the template are independent of it and of each other.
     *
     * @param pageId The page being edited.
     * @param label What the template is called in the picker.
     * @param uuids The blocks to serialize into the template, each with its whole subtree. They are read from the CURRENT edit state, so unpublished changes are included.
     * @param description A sentence about when to reach for it.
     * @param fieldName The field this template should be offered in. Null offers it in every field.
     * @param isDefault Whether a new page of that type should start from this template.
     * @param pageBundle The page type this template should be offered on. Omit to take the current page's own type.
     * @return [com.revenexx.models.Error]
     */
    @JvmOverloads
    suspend fun pagesEditorTemplatesCreate(
        pageId: String,
        label: String,
        uuids: List<String>,
        description: String? = null,
        fieldName: String? = null,
        isDefault: Boolean? = null,
        pageBundle: String? = null,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/pages/editor/{page_id}/templates"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "description" to description,
            "fieldName" to fieldName,
            "isDefault" to isDefault,
            "label" to label,
            "pageBundle" to pageBundle,
            "uuids" to uuids,
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
     * Takes a parked edit state back to `active` and clears its date, so the scheduled publication simply does not happen. The work is not touched — the mutation log, the undo position and the owner all stay as they were — and the page can then be published by hand or scheduled again for a different date. Like every other write to an edit state it asks for ownership, and a page with no open edit state answers 404 rather than pretending to have cancelled something.
     *
     * @param pageId The page being edited.
     * @return [Any]
     */
    suspend fun pagesEditorUnschedule(
        pageId: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/unschedule"
            .replace("{pageId}", pageId)

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


}