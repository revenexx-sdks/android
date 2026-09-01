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
 * Gateway liveness and readiness probes. Public: no credential, no tenant.
 */
class Health(client: Client) : Service(client) {

    /**
     * Answers as long as the process is running. Never touches a dependency, so it stays 200 while the gateway is degraded — use readiness to decide whether to send traffic.
     *
     * @return [Any]
     */
    suspend fun healthLive(
    ): Any {
        val apiPath = "/health/live"

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
     * Answers 200 once the gateway's registry source is reachable, 503 until then.
     *
     * @return [Any]
     */
    suspend fun healthReady(
    ): Any {
        val apiPath = "/health/ready"

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


}