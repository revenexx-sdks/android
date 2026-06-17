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
 * Outbound messaging: email/push messages, providers, topics, targets.
 */
class Messaging(client: Client) : Service(client) {

    /**
     * Get a list of all messages from the current Revenexx project.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: scheduledAt, deliveredAt, deliveredTotal, status, description, providerType
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.MessageList]
     */
    @JvmOverloads
    suspend fun messagingListMessages(
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.MessageList {
        val apiPath = "/v1/messaging/messages"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "search" to search,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.MessageList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.MessageList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.MessageList::class.java,
            converter,
        )
    }


    /**
     * Create a new email message.
     *
     * @param content Email Content.
     * @param messageId Message ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param subject Email Subject.
     * @param attachments Array of compound ID strings of bucket IDs and file IDs to be attached to the email. They should be formatted as <BUCKET_ID>:<FILE_ID>.
     * @param bcc Array of target IDs to be added as BCC.
     * @param cc Array of target IDs to be added as CC.
     * @param draft Is message a draft
     * @param html Is content of type HTML
     * @param scheduledAt Scheduled delivery time for message in [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format. DateTime value must be in future.
     * @param targets List of Targets IDs.
     * @param topics List of Topic IDs.
     * @param users List of User IDs.
     * @return [com.revenexx.models.Message]
     */
    @JvmOverloads
    suspend fun messagingCreateEmail(
        content: String,
        messageId: String,
        subject: String,
        attachments: List<String>? = null,
        bcc: List<String>? = null,
        cc: List<String>? = null,
        draft: Boolean? = null,
        html: Boolean? = null,
        scheduledAt: String? = null,
        targets: List<String>? = null,
        topics: List<String>? = null,
        users: List<String>? = null,
    ): com.revenexx.models.Message {
        val apiPath = "/v1/messaging/messages/email"

        val apiParams = mutableMapOf<String, Any?>(
            "attachments" to attachments,
            "bcc" to bcc,
            "cc" to cc,
            "content" to content,
            "draft" to draft,
            "html" to html,
            "messageId" to messageId,
            "scheduledAt" to scheduledAt,
            "subject" to subject,
            "targets" to targets,
            "topics" to topics,
            "users" to users,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Message = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Message.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Message::class.java,
            converter,
        )
    }


    /**
     * Update an email message by its unique ID. This endpoint only works on messages that are in draft status. Messages that are already processing, sent, or failed cannot be updated.
     * 
     *
     * @param messageId Message ID.
     * @param attachments Array of compound ID strings of bucket IDs and file IDs to be attached to the email. They should be formatted as <BUCKET_ID>:<FILE_ID>.
     * @param bcc Array of target IDs to be added as BCC.
     * @param cc Array of target IDs to be added as CC.
     * @param content Email Content.
     * @param draft Is message a draft
     * @param html Is content of type HTML
     * @param scheduledAt Scheduled delivery time for message in [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format. DateTime value must be in future.
     * @param subject Email Subject.
     * @param targets List of Targets IDs.
     * @param topics List of Topic IDs.
     * @param users List of User IDs.
     * @return [com.revenexx.models.Message]
     */
    @JvmOverloads
    suspend fun messagingUpdateEmail(
        messageId: String,
        attachments: List<String>? = null,
        bcc: List<String>? = null,
        cc: List<String>? = null,
        content: String? = null,
        draft: Boolean? = null,
        html: Boolean? = null,
        scheduledAt: String? = null,
        subject: String? = null,
        targets: List<String>? = null,
        topics: List<String>? = null,
        users: List<String>? = null,
    ): com.revenexx.models.Message {
        val apiPath = "/v1/messaging/messages/email/{messageId}"
            .replace("{messageId}", messageId)

        val apiParams = mutableMapOf<String, Any?>(
            "attachments" to attachments,
            "bcc" to bcc,
            "cc" to cc,
            "content" to content,
            "draft" to draft,
            "html" to html,
            "scheduledAt" to scheduledAt,
            "subject" to subject,
            "targets" to targets,
            "topics" to topics,
            "users" to users,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Message = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Message.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Message::class.java,
            converter,
        )
    }


    /**
     * Create a new push notification.
     *
     * @param messageId Message ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param action Action for push notification.
     * @param badge Badge for push notification. Available only for iOS Platform.
     * @param body Body for push notification.
     * @param color Color for push notification. Available only for Android Platform.
     * @param contentAvailable If set to true, the notification will be delivered in the background. Available only for iOS Platform.
     * @param critical If set to true, the notification will be marked as critical. This requires the app to have the critical notification entitlement. Available only for iOS Platform.
     * @param data Additional key-value pair data for push notification.
     * @param draft Is message a draft
     * @param icon Icon for push notification. Available only for Android and Web Platform.
     * @param image Image for push notification. Must be a compound bucket ID to file ID of a jpeg, png, or bmp image in Appwrite Storage. It should be formatted as <BUCKET_ID>:<FILE_ID>.
     * @param priority Set the notification priority. "normal" will consider device state and may not deliver notifications immediately. "high" will always attempt to immediately deliver the notification.
     * @param scheduledAt Scheduled delivery time for message in [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format. DateTime value must be in future.
     * @param sound Sound for push notification. Available only for Android and iOS Platform.
     * @param tag Tag for push notification. Available only for Android Platform.
     * @param targets List of Targets IDs.
     * @param title Title for push notification.
     * @param topics List of Topic IDs.
     * @param users List of User IDs.
     * @return [com.revenexx.models.Message]
     */
    @JvmOverloads
    suspend fun messagingCreatePush(
        messageId: String,
        action: String? = null,
        badge: Long? = null,
        body: String? = null,
        color: String? = null,
        contentAvailable: Boolean? = null,
        critical: Boolean? = null,
        data: Any? = null,
        draft: Boolean? = null,
        icon: String? = null,
        image: String? = null,
        priority: com.revenexx.enums.Priority? = null,
        scheduledAt: String? = null,
        sound: String? = null,
        tag: String? = null,
        targets: List<String>? = null,
        title: String? = null,
        topics: List<String>? = null,
        users: List<String>? = null,
    ): com.revenexx.models.Message {
        val apiPath = "/v1/messaging/messages/push"

        val apiParams = mutableMapOf<String, Any?>(
            "action" to action,
            "badge" to badge,
            "body" to body,
            "color" to color,
            "contentAvailable" to contentAvailable,
            "critical" to critical,
            "data" to data,
            "draft" to draft,
            "icon" to icon,
            "image" to image,
            "messageId" to messageId,
            "priority" to priority,
            "scheduledAt" to scheduledAt,
            "sound" to sound,
            "tag" to tag,
            "targets" to targets,
            "title" to title,
            "topics" to topics,
            "users" to users,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Message = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Message.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Message::class.java,
            converter,
        )
    }


    /**
     * Update a push notification by its unique ID. This endpoint only works on messages that are in draft status. Messages that are already processing, sent, or failed cannot be updated.
     * 
     *
     * @param messageId Message ID.
     * @param action Action for push notification.
     * @param badge Badge for push notification. Available only for iOS platforms.
     * @param body Body for push notification.
     * @param color Color for push notification. Available only for Android platforms.
     * @param contentAvailable If set to true, the notification will be delivered in the background. Available only for iOS Platform.
     * @param critical If set to true, the notification will be marked as critical. This requires the app to have the critical notification entitlement. Available only for iOS Platform.
     * @param data Additional Data for push notification.
     * @param draft Is message a draft
     * @param icon Icon for push notification. Available only for Android and Web platforms.
     * @param image Image for push notification. Must be a compound bucket ID to file ID of a jpeg, png, or bmp image in Appwrite Storage. It should be formatted as <BUCKET_ID>:<FILE_ID>.
     * @param priority Set the notification priority. "normal" will consider device battery state and may send notifications later. "high" will always attempt to immediately deliver the notification.
     * @param scheduledAt Scheduled delivery time for message in [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format. DateTime value must be in future.
     * @param sound Sound for push notification. Available only for Android and iOS platforms.
     * @param tag Tag for push notification. Available only for Android platforms.
     * @param targets List of Targets IDs.
     * @param title Title for push notification.
     * @param topics List of Topic IDs.
     * @param users List of User IDs.
     * @return [com.revenexx.models.Message]
     */
    @JvmOverloads
    suspend fun messagingUpdatePush(
        messageId: String,
        action: String? = null,
        badge: Long? = null,
        body: String? = null,
        color: String? = null,
        contentAvailable: Boolean? = null,
        critical: Boolean? = null,
        data: Any? = null,
        draft: Boolean? = null,
        icon: String? = null,
        image: String? = null,
        priority: com.revenexx.enums.Priority? = null,
        scheduledAt: String? = null,
        sound: String? = null,
        tag: String? = null,
        targets: List<String>? = null,
        title: String? = null,
        topics: List<String>? = null,
        users: List<String>? = null,
    ): com.revenexx.models.Message {
        val apiPath = "/v1/messaging/messages/push/{messageId}"
            .replace("{messageId}", messageId)

        val apiParams = mutableMapOf<String, Any?>(
            "action" to action,
            "badge" to badge,
            "body" to body,
            "color" to color,
            "contentAvailable" to contentAvailable,
            "critical" to critical,
            "data" to data,
            "draft" to draft,
            "icon" to icon,
            "image" to image,
            "priority" to priority,
            "scheduledAt" to scheduledAt,
            "sound" to sound,
            "tag" to tag,
            "targets" to targets,
            "title" to title,
            "topics" to topics,
            "users" to users,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Message = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Message.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Message::class.java,
            converter,
        )
    }


    /**
     * Delete a message. If the message is not a draft or scheduled, but has been sent, this will not recall the message.
     *
     * @param messageId Message ID.
     * @return [Any]
     */
    suspend fun messagingDelete(
        messageId: String,
    ): Any {
        val apiPath = "/v1/messaging/messages/{messageId}"
            .replace("{messageId}", messageId)

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
     * Get a message by its unique ID.
     * 
     *
     * @param messageId Message ID.
     * @return [com.revenexx.models.Message]
     */
    suspend fun messagingGetMessage(
        messageId: String,
    ): com.revenexx.models.Message {
        val apiPath = "/v1/messaging/messages/{messageId}"
            .replace("{messageId}", messageId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Message = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Message.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Message::class.java,
            converter,
        )
    }


    /**
     * Get the message activity logs listed by its unique ID.
     *
     * @param messageId Message ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Only supported methods are limit and offset
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.LogList]
     */
    @JvmOverloads
    suspend fun messagingListMessageLogs(
        messageId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.LogList {
        val apiPath = "/v1/messaging/messages/{messageId}/logs"
            .replace("{messageId}", messageId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.LogList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LogList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LogList::class.java,
            converter,
        )
    }


    /**
     * Get a list of the targets associated with a message.
     *
     * @param messageId Message ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: userId, providerId, identifier, providerType
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.TargetList]
     */
    @JvmOverloads
    suspend fun messagingListTargets(
        messageId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.TargetList {
        val apiPath = "/v1/messaging/messages/{messageId}/targets"
            .replace("{messageId}", messageId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.TargetList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.TargetList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.TargetList::class.java,
            converter,
        )
    }


    /**
     * Get a list of all providers from the current Revenexx project.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: name, provider, type, enabled
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.ProviderList]
     */
    @JvmOverloads
    suspend fun messagingListProviders(
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.ProviderList {
        val apiPath = "/v1/messaging/providers"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "search" to search,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ProviderList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ProviderList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ProviderList::class.java,
            converter,
        )
    }


    /**
     * Create a new Mailgun provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param apiKey Mailgun API Key.
     * @param domain Mailgun Domain.
     * @param enabled Set as enabled.
     * @param fromEmail Sender email address.
     * @param fromName Sender Name.
     * @param isEuRegion Set as EU region.
     * @param replyToEmail Email set in the reply to field for the mail. Default value is sender email. Reply to email must have reply to name as well.
     * @param replyToName Name set in the reply to field for the mail. Default value is sender name. Reply to name must have reply to email as well.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateMailgunProvider(
        name: String,
        providerId: String,
        apiKey: String? = null,
        domain: String? = null,
        enabled: Boolean? = null,
        fromEmail: String? = null,
        fromName: String? = null,
        isEuRegion: Boolean? = null,
        replyToEmail: String? = null,
        replyToName: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/mailgun"

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "domain" to domain,
            "enabled" to enabled,
            "fromEmail" to fromEmail,
            "fromName" to fromName,
            "isEuRegion" to isEuRegion,
            "name" to name,
            "providerId" to providerId,
            "replyToEmail" to replyToEmail,
            "replyToName" to replyToName,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a Mailgun provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param apiKey Mailgun API Key.
     * @param domain Mailgun Domain.
     * @param enabled Set as enabled.
     * @param fromEmail Sender email address.
     * @param fromName Sender Name.
     * @param isEuRegion Set as EU region.
     * @param name Provider name.
     * @param replyToEmail Email set in the reply to field for the mail. Default value is sender email.
     * @param replyToName Name set in the reply to field for the mail. Default value is sender name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateMailgunProvider(
        providerId: String,
        apiKey: String? = null,
        domain: String? = null,
        enabled: Boolean? = null,
        fromEmail: String? = null,
        fromName: String? = null,
        isEuRegion: Boolean? = null,
        name: String? = null,
        replyToEmail: String? = null,
        replyToName: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/mailgun/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "domain" to domain,
            "enabled" to enabled,
            "fromEmail" to fromEmail,
            "fromName" to fromName,
            "isEuRegion" to isEuRegion,
            "name" to name,
            "replyToEmail" to replyToEmail,
            "replyToName" to replyToName,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Create a new MSG91 provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param authKey Msg91 auth key.
     * @param enabled Set as enabled.
     * @param senderId Msg91 sender ID.
     * @param templateId Msg91 template ID
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateMsg91Provider(
        name: String,
        providerId: String,
        authKey: String? = null,
        enabled: Boolean? = null,
        senderId: String? = null,
        templateId: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/msg91"

        val apiParams = mutableMapOf<String, Any?>(
            "authKey" to authKey,
            "enabled" to enabled,
            "name" to name,
            "providerId" to providerId,
            "senderId" to senderId,
            "templateId" to templateId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a MSG91 provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param authKey Msg91 auth key.
     * @param enabled Set as enabled.
     * @param name Provider name.
     * @param senderId Msg91 sender ID.
     * @param templateId Msg91 template ID.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateMsg91Provider(
        providerId: String,
        authKey: String? = null,
        enabled: Boolean? = null,
        name: String? = null,
        senderId: String? = null,
        templateId: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/msg91/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "authKey" to authKey,
            "enabled" to enabled,
            "name" to name,
            "senderId" to senderId,
            "templateId" to templateId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Create a new Resend provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param apiKey Resend API key.
     * @param enabled Set as enabled.
     * @param fromEmail Sender email address.
     * @param fromName Sender Name.
     * @param replyToEmail Email set in the reply to field for the mail. Default value is sender email.
     * @param replyToName Name set in the reply to field for the mail. Default value is sender name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateResendProvider(
        name: String,
        providerId: String,
        apiKey: String? = null,
        enabled: Boolean? = null,
        fromEmail: String? = null,
        fromName: String? = null,
        replyToEmail: String? = null,
        replyToName: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/resend"

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "enabled" to enabled,
            "fromEmail" to fromEmail,
            "fromName" to fromName,
            "name" to name,
            "providerId" to providerId,
            "replyToEmail" to replyToEmail,
            "replyToName" to replyToName,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a Resend provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param apiKey Resend API key.
     * @param enabled Set as enabled.
     * @param fromEmail Sender email address.
     * @param fromName Sender Name.
     * @param name Provider name.
     * @param replyToEmail Email set in the Reply To field for the mail. Default value is Sender Email.
     * @param replyToName Name set in the Reply To field for the mail. Default value is Sender Name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateResendProvider(
        providerId: String,
        apiKey: String? = null,
        enabled: Boolean? = null,
        fromEmail: String? = null,
        fromName: String? = null,
        name: String? = null,
        replyToEmail: String? = null,
        replyToName: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/resend/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "enabled" to enabled,
            "fromEmail" to fromEmail,
            "fromName" to fromName,
            "name" to name,
            "replyToEmail" to replyToEmail,
            "replyToName" to replyToName,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Create a new Sendgrid provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param apiKey Sendgrid API key.
     * @param enabled Set as enabled.
     * @param fromEmail Sender email address.
     * @param fromName Sender Name.
     * @param replyToEmail Email set in the reply to field for the mail. Default value is sender email.
     * @param replyToName Name set in the reply to field for the mail. Default value is sender name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateSendgridProvider(
        name: String,
        providerId: String,
        apiKey: String? = null,
        enabled: Boolean? = null,
        fromEmail: String? = null,
        fromName: String? = null,
        replyToEmail: String? = null,
        replyToName: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/sendgrid"

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "enabled" to enabled,
            "fromEmail" to fromEmail,
            "fromName" to fromName,
            "name" to name,
            "providerId" to providerId,
            "replyToEmail" to replyToEmail,
            "replyToName" to replyToName,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a Sendgrid provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param apiKey Sendgrid API key.
     * @param enabled Set as enabled.
     * @param fromEmail Sender email address.
     * @param fromName Sender Name.
     * @param name Provider name.
     * @param replyToEmail Email set in the Reply To field for the mail. Default value is Sender Email.
     * @param replyToName Name set in the Reply To field for the mail. Default value is Sender Name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateSendgridProvider(
        providerId: String,
        apiKey: String? = null,
        enabled: Boolean? = null,
        fromEmail: String? = null,
        fromName: String? = null,
        name: String? = null,
        replyToEmail: String? = null,
        replyToName: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/sendgrid/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "enabled" to enabled,
            "fromEmail" to fromEmail,
            "fromName" to fromName,
            "name" to name,
            "replyToEmail" to replyToEmail,
            "replyToName" to replyToName,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Create a new Telesign provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param apiKey Telesign API key.
     * @param customerId Telesign customer ID.
     * @param enabled Set as enabled.
     * @param from Sender Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateTelesignProvider(
        name: String,
        providerId: String,
        apiKey: String? = null,
        customerId: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/telesign"

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "customerId" to customerId,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
            "providerId" to providerId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a Telesign provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param apiKey Telesign API key.
     * @param customerId Telesign customer ID.
     * @param enabled Set as enabled.
     * @param from Sender number.
     * @param name Provider name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateTelesignProvider(
        providerId: String,
        apiKey: String? = null,
        customerId: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
        name: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/telesign/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "customerId" to customerId,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Create a new Textmagic provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param apiKey Textmagic apiKey.
     * @param enabled Set as enabled.
     * @param from Sender Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @param username Textmagic username.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateTextmagicProvider(
        name: String,
        providerId: String,
        apiKey: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
        username: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/textmagic"

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
            "providerId" to providerId,
            "username" to username,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a Textmagic provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param apiKey Textmagic apiKey.
     * @param enabled Set as enabled.
     * @param from Sender number.
     * @param name Provider name.
     * @param username Textmagic username.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateTextmagicProvider(
        providerId: String,
        apiKey: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
        name: String? = null,
        username: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/textmagic/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
            "username" to username,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Create a new Twilio provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param accountSid Twilio account secret ID.
     * @param authToken Twilio authentication token.
     * @param enabled Set as enabled.
     * @param from Sender Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateTwilioProvider(
        name: String,
        providerId: String,
        accountSid: String? = null,
        authToken: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/twilio"

        val apiParams = mutableMapOf<String, Any?>(
            "accountSid" to accountSid,
            "authToken" to authToken,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
            "providerId" to providerId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a Twilio provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param accountSid Twilio account secret ID.
     * @param authToken Twilio authentication token.
     * @param enabled Set as enabled.
     * @param from Sender number.
     * @param name Provider name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateTwilioProvider(
        providerId: String,
        accountSid: String? = null,
        authToken: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
        name: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/twilio/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "accountSid" to accountSid,
            "authToken" to authToken,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Create a new Vonage provider.
     *
     * @param name Provider name.
     * @param providerId Provider ID. Choose a custom ID or generate a random ID with `ID.unique()`. Valid chars are a-z, A-Z, 0-9, period, hyphen, and underscore. Can't start with a special char. Max length is 36 chars.
     * @param apiKey Vonage API key.
     * @param apiSecret Vonage API secret.
     * @param enabled Set as enabled.
     * @param from Sender Phone number. Format this number with a leading '+' and a country code, e.g., +16175551212.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingCreateVonageProvider(
        name: String,
        providerId: String,
        apiKey: String? = null,
        apiSecret: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/vonage"

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "apiSecret" to apiSecret,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
            "providerId" to providerId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Update a Vonage provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @param apiKey Vonage API key.
     * @param apiSecret Vonage API secret.
     * @param enabled Set as enabled.
     * @param from Sender number.
     * @param name Provider name.
     * @return [com.revenexx.models.Provider]
     */
    @JvmOverloads
    suspend fun messagingUpdateVonageProvider(
        providerId: String,
        apiKey: String? = null,
        apiSecret: String? = null,
        enabled: Boolean? = null,
        from: String? = null,
        name: String? = null,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/vonage/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "apiKey" to apiKey,
            "apiSecret" to apiSecret,
            "enabled" to enabled,
            "from" to from,
            "name" to name,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Delete a provider by its unique ID.
     *
     * @param providerId Provider ID.
     * @return [Any]
     */
    suspend fun messagingDeleteProvider(
        providerId: String,
    ): Any {
        val apiPath = "/v1/messaging/providers/{providerId}"
            .replace("{providerId}", providerId)

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
     * Get a provider by its unique ID.
     * 
     *
     * @param providerId Provider ID.
     * @return [com.revenexx.models.Provider]
     */
    suspend fun messagingGetProvider(
        providerId: String,
    ): com.revenexx.models.Provider {
        val apiPath = "/v1/messaging/providers/{providerId}"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Provider = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Provider.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Provider::class.java,
            converter,
        )
    }


    /**
     * Get the provider activity logs listed by its unique ID.
     *
     * @param providerId Provider ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Only supported methods are limit and offset
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.LogList]
     */
    @JvmOverloads
    suspend fun messagingListProviderLogs(
        providerId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.LogList {
        val apiPath = "/v1/messaging/providers/{providerId}/logs"
            .replace("{providerId}", providerId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.LogList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LogList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LogList::class.java,
            converter,
        )
    }


    /**
     * Get the subscriber activity logs listed by its unique ID.
     *
     * @param subscriberId Subscriber ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Only supported methods are limit and offset
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.LogList]
     */
    @JvmOverloads
    suspend fun messagingListSubscriberLogs(
        subscriberId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.LogList {
        val apiPath = "/v1/messaging/subscribers/{subscriberId}/logs"
            .replace("{subscriberId}", subscriberId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.LogList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LogList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LogList::class.java,
            converter,
        )
    }


    /**
     * Get a list of all topics from the current Revenexx project.
     *
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: name, description, emailTotal, smsTotal, pushTotal
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.TopicList]
     */
    @JvmOverloads
    suspend fun messagingListTopics(
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.TopicList {
        val apiPath = "/v1/messaging/topics"

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "search" to search,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.TopicList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.TopicList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.TopicList::class.java,
            converter,
        )
    }


    /**
     * Create a new topic.
     *
     * @param name Topic Name.
     * @param topicId Topic ID. Choose a custom Topic ID or a new Topic ID.
     * @param subscribe An array of role strings with subscribe permission. By default all users are granted with any subscribe permission. [learn more about roles](https://appwrite.io/docs/permissions#permission-roles). Maximum of 100 roles are allowed, each 64 characters long.
     * @return [com.revenexx.models.Topic]
     */
    @JvmOverloads
    suspend fun messagingCreateTopic(
        name: String,
        topicId: String,
        subscribe: List<String>? = null,
    ): com.revenexx.models.Topic {
        val apiPath = "/v1/messaging/topics"

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "subscribe" to subscribe,
            "topicId" to topicId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Topic = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Topic.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Topic::class.java,
            converter,
        )
    }


    /**
     * Delete a topic by its unique ID.
     *
     * @param topicId Topic ID.
     * @return [Any]
     */
    suspend fun messagingDeleteTopic(
        topicId: String,
    ): Any {
        val apiPath = "/v1/messaging/topics/{topicId}"
            .replace("{topicId}", topicId)

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
     * Get a topic by its unique ID.
     * 
     *
     * @param topicId Topic ID.
     * @return [com.revenexx.models.Topic]
     */
    suspend fun messagingGetTopic(
        topicId: String,
    ): com.revenexx.models.Topic {
        val apiPath = "/v1/messaging/topics/{topicId}"
            .replace("{topicId}", topicId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Topic = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Topic.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Topic::class.java,
            converter,
        )
    }


    /**
     * Update a topic by its unique ID.
     * 
     *
     * @param topicId Topic ID.
     * @param name Topic Name.
     * @param subscribe An array of role strings with subscribe permission. By default all users are granted with any subscribe permission. [learn more about roles](https://appwrite.io/docs/permissions#permission-roles). Maximum of 100 roles are allowed, each 64 characters long.
     * @return [com.revenexx.models.Topic]
     */
    @JvmOverloads
    suspend fun messagingUpdateTopic(
        topicId: String,
        name: String? = null,
        subscribe: List<String>? = null,
    ): com.revenexx.models.Topic {
        val apiPath = "/v1/messaging/topics/{topicId}"
            .replace("{topicId}", topicId)

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "subscribe" to subscribe,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Topic = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Topic.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PATCH",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Topic::class.java,
            converter,
        )
    }


    /**
     * Get the topic activity logs listed by its unique ID.
     *
     * @param topicId Topic ID.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Only supported methods are limit and offset
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.LogList]
     */
    @JvmOverloads
    suspend fun messagingListTopicLogs(
        topicId: String,
        queries: List<String>? = null,
        total: Boolean? = null,
    ): com.revenexx.models.LogList {
        val apiPath = "/v1/messaging/topics/{topicId}/logs"
            .replace("{topicId}", topicId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.LogList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.LogList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.LogList::class.java,
            converter,
        )
    }


    /**
     * Get a list of all subscribers from the current Revenexx project.
     *
     * @param topicId Topic ID. The topic ID subscribed to.
     * @param queries Array of query strings generated using the Query class provided by the SDK. [Learn more about queries](https://appwrite.io/docs/queries). Maximum of 100 queries are allowed, each 4096 characters long. You may filter on the following attributes: name, provider, type, enabled
     * @param search Search term to filter your list results. Max length: 256 chars.
     * @param total When set to false, the total count returned will be 0 and will not be calculated.
     * @return [com.revenexx.models.SubscriberList]
     */
    @JvmOverloads
    suspend fun messagingListSubscribers(
        topicId: String,
        queries: List<String>? = null,
        search: String? = null,
        total: Boolean? = null,
    ): com.revenexx.models.SubscriberList {
        val apiPath = "/v1/messaging/topics/{topicId}/subscribers"
            .replace("{topicId}", topicId)

        val apiParams = mutableMapOf<String, Any?>(
            "queries" to queries,
            "search" to search,
            "total" to total,
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.SubscriberList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.SubscriberList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.SubscriberList::class.java,
            converter,
        )
    }


    /**
     * Create a new subscriber.
     *
     * @param topicId Topic ID. The topic ID to subscribe to.
     * @param subscriberId Subscriber ID. Choose a custom Subscriber ID or a new Subscriber ID.
     * @param targetId Target ID. The target ID to link to the specified Topic ID.
     * @return [com.revenexx.models.Subscriber]
     */
    suspend fun messagingCreateSubscriber(
        topicId: String,
        subscriberId: String,
        targetId: String,
    ): com.revenexx.models.Subscriber {
        val apiPath = "/v1/messaging/topics/{topicId}/subscribers"
            .replace("{topicId}", topicId)

        val apiParams = mutableMapOf<String, Any?>(
            "subscriberId" to subscriberId,
            "targetId" to targetId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Subscriber = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Subscriber.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Subscriber::class.java,
            converter,
        )
    }


    /**
     * Delete a subscriber by its unique ID.
     *
     * @param topicId Topic ID. The topic ID subscribed to.
     * @param subscriberId Subscriber ID.
     * @return [Any]
     */
    suspend fun messagingDeleteSubscriber(
        topicId: String,
        subscriberId: String,
    ): Any {
        val apiPath = "/v1/messaging/topics/{topicId}/subscribers/{subscriberId}"
            .replace("{topicId}", topicId)
            .replace("{subscriberId}", subscriberId)

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
     * Get a subscriber by its unique ID.
     * 
     *
     * @param topicId Topic ID. The topic ID subscribed to.
     * @param subscriberId Subscriber ID.
     * @return [com.revenexx.models.Subscriber]
     */
    suspend fun messagingGetSubscriber(
        topicId: String,
        subscriberId: String,
    ): com.revenexx.models.Subscriber {
        val apiPath = "/v1/messaging/topics/{topicId}/subscribers/{subscriberId}"
            .replace("{topicId}", topicId)
            .replace("{subscriberId}", subscriberId)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Subscriber = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Subscriber.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Subscriber::class.java,
            converter,
        )
    }


}