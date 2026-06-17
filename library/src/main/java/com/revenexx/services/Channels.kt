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
class Channels(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun channelsList(
    ): Any {
        val apiPath = "/v1/channels"

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
     * @param code Stable channel code, unique per tenant (e.g. shop, punchout-acme).
     * @param name Display name.
     * @param isDefault Mark as the default channel (default false).
     * @param labels Localized display names keyed by locale.
     * @param position Sort position (default 0).
     * @param status Lifecycle status (default 'active').
     * @param type Where business happens (default 'storefront').
     * @return [com.revenexx.models.Channel]
     */
    @JvmOverloads
    suspend fun channelsCreate(
        code: String,
        name: String,
        isDefault: Boolean? = null,
        labels: Any? = null,
        position: Long? = null,
        status: com.revenexx.enums.ChannelStatus? = null,
        type: com.revenexx.enums.ChannelType? = null,
    ): com.revenexx.models.Channel {
        val apiPath = "/v1/channels"

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "status" to status,
            "type" to type,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Channel = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Channel.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Channel::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [com.revenexx.models.ChannelDefaults]
     */
    suspend fun channelsDefaults(
    ): com.revenexx.models.ChannelDefaults {
        val apiPath = "/v1/channels/defaults"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ChannelDefaults = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ChannelDefaults.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ChannelDefaults::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun channelsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/channels/{id}"
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
     * @return [com.revenexx.models.Channel]
     */
    suspend fun channelsGet(
        id: String,
    ): com.revenexx.models.Channel {
        val apiPath = "/v1/channels/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Channel = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Channel.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Channel::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param code Stable channel code, unique per tenant (e.g. shop, punchout-acme).
     * @param isDefault Mark as the default channel (default false).
     * @param labels Localized display names keyed by locale.
     * @param name Display name.
     * @param position Sort position (default 0).
     * @param status Lifecycle status (default 'active').
     * @param type Where business happens (default 'storefront').
     * @return [com.revenexx.models.Channel]
     */
    @JvmOverloads
    suspend fun channelsUpdate(
        id: String,
        code: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        name: String? = null,
        position: Long? = null,
        status: com.revenexx.enums.ChannelStatus? = null,
        type: com.revenexx.enums.ChannelType? = null,
    ): com.revenexx.models.Channel {
        val apiPath = "/v1/channels/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "code" to code,
            "is_default" to isDefault,
            "labels" to labels,
            "name" to name,
            "position" to position,
            "status" to status,
            "type" to type,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Channel = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Channel.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Channel::class.java,
            converter,
        )
    }


}