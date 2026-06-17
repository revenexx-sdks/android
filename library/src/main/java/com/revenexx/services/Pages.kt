package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxAPIRevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * 
 */
class Pages(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pagesDeliveryMenus(
    ): Any {
        val apiPath = "/v1/pages/delivery/menus"

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
     * 
     *
     * @return [com.revenexx.models.DeliveryPage]
     */
    suspend fun pagesDeliveryPage(
    ): com.revenexx.models.DeliveryPage {
        val apiPath = "/v1/pages/delivery/page"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.DeliveryPage = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.DeliveryPage.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.DeliveryPage::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pagesDeliveryPages(
    ): Any {
        val apiPath = "/v1/pages/delivery/pages"

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
     * 
     *
     * @param token 
     * @return [com.revenexx.models.DeliveryPage]
     */
    suspend fun pagesDeliveryPreview(
        token: String,
    ): com.revenexx.models.DeliveryPage {
        val apiPath = "/v1/pages/delivery/preview/{token}"
            .replace("{token}", token)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.DeliveryPage = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.DeliveryPage.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.DeliveryPage::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pagesEditorEditStates(
    ): Any {
        val apiPath = "/v1/pages/editor/edit-states"

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
     * 
     *
     * @return [Any]
     */
    suspend fun pagesEditorNotificationsList(
    ): Any {
        val apiPath = "/v1/pages/editor/notifications"

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
     * 
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
     * 
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
     * 
     *
     * @param items 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesEditorTranslate(
        items: List<Any>? = null,
    ): Any {
        val apiPath = "/v1/pages/editor/translate"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
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
     * 
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
     * 
     *
     * @param settings 
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
     * 
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
     * 
     *
     * @param pageId 
     * @return [Any]
     */
    suspend fun pagesEditorCommentsList(
        pageId: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/comments"
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
     * 
     *
     * @param pageId 
     * @param body 
     * @param blockUuids 
     * @param parentUuid 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesEditorCommentsCreate(
        pageId: String,
        body: String,
        blockUuids: List<String>? = null,
        parentUuid: String? = null,
    ): Any {
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
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param pageId 
     * @param uuid 
     * @return [Any]
     */
    suspend fun pagesEditorCommentsDelete(
        pageId: String,
        uuid: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param pageId 
     * @param uuid 
     * @param body 
     * @return [Any]
     */
    suspend fun pagesEditorCommentsUpdate(
        pageId: String,
        uuid: String,
        body: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
            "body" to body,
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
     * 
     *
     * @param pageId 
     * @param uuid 
     * @return [Any]
     */
    suspend fun pagesEditorCommentsResolve(
        pageId: String,
        uuid: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}/resolve"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

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
     * 
     *
     * @param pageId 
     * @param uuid 
     * @param taskIndex 
     * @return [com.revenexx.models.Comment]
     */
    suspend fun pagesEditorCommentsToggleTask(
        pageId: String,
        uuid: String,
        taskIndex: Long,
    ): com.revenexx.models.Comment {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}/toggle-task"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

        val apiParams = mutableMapOf<String, Any?>(
            "taskIndex" to taskIndex,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Comment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Comment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Comment::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param pageId 
     * @param uuid 
     * @return [Any]
     */
    suspend fun pagesEditorCommentsUnresolve(
        pageId: String,
        uuid: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/comments/{uuid}/unresolve"
            .replace("{pageId}", pageId)
            .replace("{uuid}", uuid)

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
     * 
     *
     * @param pageId 
     * @param index 
     * @param langcode 
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
     * 
     *
     * @param pageId 
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
     * 
     *
     * @param pageId 
     * @param enabled 
     * @param index 
     * @param langcode 
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
     * 
     *
     * @param pageId 
     * @param plugin Mutation plugin id (add, move, delete, duplicate, update_field_value, ...).
     * @param langcode 
     * @param payload 
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
     * 
     *
     * @param pageId 
     * @param ttlHours 
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
     * 
     *
     * @param pageId 
     * @param force Publish despite violations.
     * @param label 
     * @return [com.revenexx.models.MutationResponse]
     */
    @JvmOverloads
    suspend fun pagesEditorPublish(
        pageId: String,
        force: Boolean? = null,
        label: String? = null,
    ): com.revenexx.models.MutationResponse {
        val apiPath = "/v1/pages/editor/{page_id}/publish"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "force" to force,
            "label" to label,
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
     * 
     *
     * @param pageId 
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
     * 
     *
     * @param pageId 
     * @param scheduledAt 
     * @return [Any]
     */
    suspend fun pagesEditorSchedule(
        pageId: String,
        scheduledAt: String,
    ): Any {
        val apiPath = "/v1/pages/editor/{page_id}/schedule"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
            "scheduledAt" to scheduledAt,
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
     * 
     *
     * @param pageId 
     * @return [com.revenexx.models.EditorState]
     */
    suspend fun pagesEditorState(
        pageId: String,
    ): com.revenexx.models.EditorState {
        val apiPath = "/v1/pages/editor/{page_id}/state"
            .replace("{pageId}", pageId)

        val apiParams = mutableMapOf<String, Any?>(
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
     * 
     *
     * @param pageId 
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
     * 
     *
     * @param pageId 
     * @param label 
     * @param uuids 
     * @param description 
     * @param fieldName 
     * @param isDefault 
     * @param pageBundle 
     * @return [com.revenexx.models.Template]
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
    ): com.revenexx.models.Template {
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
        val converter: (Any) -> com.revenexx.models.Template = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Template.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Template::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param pageId 
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


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pagesLibraryList(
    ): Any {
        val apiPath = "/v1/pages/library"

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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun pagesLibraryDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/pages/library/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.LibraryItem]
     */
    suspend fun pagesLibraryGet(
        id: String,
    ): com.revenexx.models.LibraryItem {
        val apiPath = "/v1/pages/library/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.LibraryItem = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LibraryItem.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LibraryItem::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param bundle 
     * @param label 
     * @param tree Serialized block tree ({ bundle, props, props_i18n, options, children }).
     * @return [com.revenexx.models.LibraryItem]
     */
    @JvmOverloads
    suspend fun pagesLibraryUpdate(
        id: String,
        bundle: String? = null,
        label: String? = null,
        tree: Any? = null,
    ): com.revenexx.models.LibraryItem {
        val apiPath = "/v1/pages/library/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "bundle" to bundle,
            "label" to label,
            "tree" to tree,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.LibraryItem = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LibraryItem.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LibraryItem::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pagesMenusList(
    ): Any {
        val apiPath = "/v1/pages/menus"

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
     * 
     *
     * @param label 
     * @param menuKey Stable menu identifier, e.g. "main", "footer", "account".
     * @param items Ordered menu entries ({ label, to?, items? }).
     * @return [com.revenexx.models.Menu]
     */
    @JvmOverloads
    suspend fun pagesMenusUpsert(
        label: String,
        menuKey: String,
        items: List<Any>? = null,
    ): com.revenexx.models.Menu {
        val apiPath = "/v1/pages/menus"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "label" to label,
            "menuKey" to menuKey,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Menu = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Menu.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Menu::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun pagesMenusDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/pages/menus/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Menu]
     */
    suspend fun pagesMenusGet(
        id: String,
    ): com.revenexx.models.Menu {
        val apiPath = "/v1/pages/menus/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Menu = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Menu.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Menu::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param items 
     * @param label 
     * @return [com.revenexx.models.Menu]
     */
    @JvmOverloads
    suspend fun pagesMenusUpdate(
        id: String,
        items: List<Any>? = null,
        label: String? = null,
    ): com.revenexx.models.Menu {
        val apiPath = "/v1/pages/menus/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "label" to label,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Menu = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Menu.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Menu::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pagesPagesList(
    ): Any {
        val apiPath = "/v1/pages/pages"

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
     * 
     *
     * @param title 
     * @param bundle 
     * @param hostOptions 
     * @param meta 
     * @param slug 
     * @param sourceLanguage 
     * @return [com.revenexx.models.Page]
     */
    @JvmOverloads
    suspend fun pagesPagesCreate(
        title: String,
        bundle: String? = null,
        hostOptions: Any? = null,
        meta: Any? = null,
        slug: String? = null,
        sourceLanguage: String? = null,
    ): com.revenexx.models.Page {
        val apiPath = "/v1/pages/pages"

        val apiParams = mutableMapOf<String, Any?>(
            "bundle" to bundle,
            "hostOptions" to hostOptions,
            "meta" to meta,
            "slug" to slug,
            "sourceLanguage" to sourceLanguage,
            "title" to title,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Page = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Page.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Page::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun pagesPagesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/pages/pages/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Page]
     */
    suspend fun pagesPagesGet(
        id: String,
    ): com.revenexx.models.Page {
        val apiPath = "/v1/pages/pages/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Page = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Page.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Page::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param bundle 
     * @param meta 
     * @param slug 
     * @param status 
     * @param title 
     * @return [com.revenexx.models.Page]
     */
    @JvmOverloads
    suspend fun pagesPagesUpdate(
        id: String,
        bundle: String? = null,
        meta: Any? = null,
        slug: String? = null,
        status: com.revenexx.enums.PageStatus? = null,
        title: String? = null,
    ): com.revenexx.models.Page {
        val apiPath = "/v1/pages/pages/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "bundle" to bundle,
            "meta" to meta,
            "slug" to slug,
            "status" to status,
            "title" to title,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Page = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Page.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Page::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun pagesPagesRevisions(
        id: String,
    ): Any {
        val apiPath = "/v1/pages/pages/{id}/revisions"
            .replace("{id}", id)

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
     * 
     *
     * @param menus 
     * @param pages 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pagesSeed(
        menus: List<Any>? = null,
        pages: List<Any>? = null,
    ): Any {
        val apiPath = "/v1/pages/seed"

        val apiParams = mutableMapOf<String, Any?>(
            "menus" to menus,
            "pages" to pages,
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
     * 
     *
     * @return [Any]
     */
    suspend fun pagesTemplatesList(
    ): Any {
        val apiPath = "/v1/pages/templates"

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
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun pagesTemplatesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/pages/templates/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Template]
     */
    suspend fun pagesTemplatesGet(
        id: String,
    ): com.revenexx.models.Template {
        val apiPath = "/v1/pages/templates/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Template = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Template.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Template::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param description 
     * @param fieldName 
     * @param isDefault 
     * @param label 
     * @param pageBundle 
     * @param tree Serialized block trees ({ bundle, props, props_i18n, options, children }).
     * @return [com.revenexx.models.Template]
     */
    @JvmOverloads
    suspend fun pagesTemplatesUpdate(
        id: String,
        description: String? = null,
        fieldName: String? = null,
        isDefault: Boolean? = null,
        label: String? = null,
        pageBundle: String? = null,
        tree: List<Any>? = null,
    ): com.revenexx.models.Template {
        val apiPath = "/v1/pages/templates/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "description" to description,
            "field_name" to fieldName,
            "is_default" to isDefault,
            "label" to label,
            "page_bundle" to pageBundle,
            "tree" to tree,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Template = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Template.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Template::class.java,
            converter,
        )
    }


}