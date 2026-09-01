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
 * The tenant's event catalog: every event type its installed apps and platform services declare, what causes each one, and what it carries.
 */
class Events(client: Client) : Service(client) {

    /**
     * Every event type this tenant's installed apps and platform services declare — what can be published and subscribed to, independent of whether one has fired yet. Each entry says what causes it (`trigger`) and what it carries (`sample`, `data_schema`).
     *
     * @param fields Comma-separated keys to keep on each emit. Omit for the full entry. A consumer that reads two fields should say so: the response carries a sample and a JSON Schema per event, and asking for less is the difference between a few kB and tens. An unknown key is ignored; a list naming nothing this response has returns the full entry rather than an empty one.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun eventsGetCatalog(
        fields: String? = null,
    ): Any {
        val apiPath = "/v1/events/catalog"

        val apiParams = mutableMapOf<String, Any?>(
            "fields" to fields,
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


}