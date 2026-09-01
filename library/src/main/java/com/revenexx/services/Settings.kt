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
 * Resolve an app's effective per-tenant / per-market settings (schema defaults merged with stored values; sensitive values masked).
 */
class Settings(client: Client) : Service(client) {

    /**
     * The tenant's effective settings for the app — the declared schema's defaults merged with stored tenant/market values. Sensitive settings are masked (listed in `masked`, omitted from `settings`).
     *
     * @param app App name, e.g. `pages`.
     * @param market Resolve market-scoped settings for this market code; falls back to the tenant value.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun settingsGetAppSettings(
        app: String,
        market: String? = null,
    ): Any {
        val apiPath = "/v1/settings/apps/{app}"
            .replace("{app}", app)

        val apiParams = mutableMapOf<String, Any?>(
            "market" to market,
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