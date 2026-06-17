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
class Greetings(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun greetingsDigest(
    ): Any {
        val apiPath = "/v1/digest"

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
    suspend fun greetingsList(
    ): Any {
        val apiPath = "/v1/greetings"

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
     * @param name Who to greet
     * @param locale BCP-47 locale
     * @return [Any]
     */
    @JvmOverloads
    suspend fun greetingsCreate(
        name: String,
        locale: String? = null,
    ): Any {
        val apiPath = "/v1/greetings"

        val apiParams = mutableMapOf<String, Any?>(
            "locale" to locale,
            "name" to name,
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
     * @param id 
     * @return [Any]
     */
    suspend fun greetingsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/greetings/{id}"
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
     * @return [com.revenexx.models.Greeting]
     */
    suspend fun greetingsGet(
        id: String,
    ): com.revenexx.models.Greeting {
        val apiPath = "/v1/greetings/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Greeting = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Greeting.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Greeting::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param locale 
     * @param message 
     * @param metadata 
     * @param name 
     * @return [com.revenexx.models.Greeting]
     */
    @JvmOverloads
    suspend fun greetingsUpdate(
        id: String,
        locale: String? = null,
        message: String? = null,
        metadata: Any? = null,
        name: String? = null,
    ): com.revenexx.models.Greeting {
        val apiPath = "/v1/greetings/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "locale" to locale,
            "message" to message,
            "metadata" to metadata,
            "name" to name,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Greeting = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Greeting.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Greeting::class.java,
            converter,
        )
    }


}