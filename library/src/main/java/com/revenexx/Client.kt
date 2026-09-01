package com.revenexx

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.revenexx.cookies.ListenableCookieJar
import com.revenexx.cookies.stores.SharedPreferencesCookieStore
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.fromJson
import com.revenexx.extensions.toJson
import com.revenexx.models.InputFile
import com.revenexx.models.UploadProgress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.RandomAccessFile
import java.io.IOException
import java.lang.IllegalArgumentException
import java.net.CookieManager
import java.net.CookiePolicy
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

class Client @JvmOverloads constructor(
    context: Context,
    var endpoint: String = "https://api.revenexx.com",
    var endpointRealtime: String? = null,
    private var selfSigned: Boolean = false
) : CoroutineScope {

    companion object {
        /**
         * The size for chunked uploads in bytes.
         */
        internal const val CHUNK_SIZE = 5*1024*1024; // 5MB
        internal const val GLOBAL_PREFS = "com.revenexx"
        internal const val COOKIE_PREFS = "myCookie"
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job = Job()

    internal lateinit var http: OkHttpClient

    internal val headers: MutableMap<String, String>

    val config: MutableMap<String, String>

    internal val cookieJar = ListenableCookieJar(CookieManager(
        SharedPreferencesCookieStore(context.getSharedPreferences(COOKIE_PREFS, Context.MODE_PRIVATE)),
        CookiePolicy.ACCEPT_ALL
    ))

    private val appVersion by lazy {
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return@lazy pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return@lazy ""
        }
    }

    init {
        headers = mutableMapOf(
            "content-type" to "application/json",
            "origin" to "revenexx-android://${context.packageName}",
            "user-agent" to "${context.packageName}/${appVersion}, ${System.getProperty("http.agent")}",
            "x-sdk-name" to "Revenexx Android",
            "x-sdk-platform" to "",
            "x-sdk-language" to "android",
            "x-sdk-version" to "1.9.11"

        )
        config = mutableMapOf()

        setSelfSigned(selfSigned)
    }

    /**
     * Set ApiKeyAuth
     *
     * A gateway-managed scoped API key (rvxk_…).
     *
     * @param {string} apikeyauth
     *
     * @return this
     */
    fun setApiKeyAuth(value: String): Client {
        config["apiKeyAuth"] = value
        addHeader("x-revenexx-api-key", value)
        return this
    }

    /**
     * Set BearerAuth
     *
     * A Zitadel-issued JWT (Cockpit / interactive callers).
     *
     * @param {string} bearerauth
     *
     * @return this
     */
    fun setBearerAuth(value: String): Client {
        config["bearerAuth"] = value
        addHeader("authorization", value)
        return this
    }

    /**
     * Set self Signed
     *
     * @param status
     *
     * @return this
     */
    fun setSelfSigned(status: Boolean): Client {
        selfSigned = status

        val builder = OkHttpClient()
            .newBuilder()
            .cookieJar(cookieJar)

        if (!selfSigned) {
            http = builder.build()
            return this
        }

        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                @Suppress("CustomX509TrustManager")
                object : X509TrustManager {
                    @Suppress("TrustAllX509TrustManager")
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    }
                    @Suppress("TrustAllX509TrustManager")
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    }
                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )
            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }

            http = builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

        return this
    }

    /**
     * Set endpoint and realtime endpoint.
     *
     * @param endpoint
     *
     * @return this
     */
    @Throws(IllegalArgumentException::class)
    fun setEndpoint(endpoint: String): Client {
        require(endpoint.startsWith("http://") || endpoint.startsWith("https://")) {
            "Invalid endpoint URL: $endpoint"
        }

        this.endpoint = endpoint
        this.endpointRealtime = endpoint.replaceFirst("http", "ws")

        return this
    }

    /**
     * Set realtime endpoint
     *
     * @param endpoint
     *
     * @return this
     */
    @Throws(IllegalArgumentException::class)
    fun setEndpointRealtime(endpoint: String): Client {
        require(endpoint.startsWith("ws://") || endpoint.startsWith("wss://")) {
            "Invalid realtime endpoint URL: $endpoint"
        }

        this.endpointRealtime = endpoint
        return this
    }

    /**
     * Set the tenant slug sent on every request via the `X-Revenexx-Tenant` header.
     *
     * @param value
     *
     * @return this
     */
    fun setTenant(value: String): Client {
        addHeader("X-Revenexx-Tenant", value)
        return this
    }

    /**
     * Set Market
     *
     * The market slug to scope requests to, sent as the X-Revenexx-Market
     * header. Optional - omit it to see only global rows.
     *
     * @param value
     *
     * @return this
     */
    fun setMarket(value: String): Client {
        addHeader("X-Revenexx-Market", value)
        return this
    }

    /**
     * Add Header
     *
     * @param key
     * @param value
     *
     * @return this
     */
    fun addHeader(key: String, value: String): Client {
        headers[key] = value
        return this
    }

    /**
     * Sends a "ping" request to Appwrite to verify connectivity.
     *
     * @return String
     */
    suspend fun ping(): String {
        val apiPath = "/ping"
        val apiParams = mutableMapOf<String, Any?>()
        val apiHeaders = mutableMapOf("content-type" to "application/json")

        return call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = String::class.java
        )
    }

    /**
     * Send the HTTP request
     *
     * @param method
     * @param path
     * @param headers
     * @param params
     *
     * @return [T]
     */
    @Throws(RevenexxException::class)
    suspend fun <T> call(
        method: String,
        path: String,
        headers:  Map<String, String> = mapOf(),
        params: Map<String, Any?> = mapOf(),
        responseType: Class<T>,
        converter: ((Any) -> T)? = null
    ): T {
        val filteredParams = params.filterValues { it != null }

        val requestHeaders = this.headers.toHeaders().newBuilder()
            .addAll(headers.toHeaders())
            .build()

        val httpBuilder = (endpoint + path).toHttpUrl().newBuilder()

        if ("GET" == method) {
            filteredParams.forEach {
                when (it.value) {
                    null -> {
                        return@forEach
                    }
                    is List<*> -> {
                        val list = it.value as List<*>
                        for (index in list.indices) {
                            httpBuilder.addQueryParameter(
                                "${it.key}[]",
                                list[index].toString()
                            )
                        }
                    }
                    else -> {
                        httpBuilder.addQueryParameter(it.key, it.value.toString())
                    }
                }
            }
            val request = Request.Builder()
                .url(httpBuilder.build())
                .headers(requestHeaders)
                .get()
                .build()

            return awaitResponse(request, responseType, converter)
        }

        val body = if (MultipartBody.FORM.toString() == headers["content-type"]) {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

            filteredParams.forEach {
                when {
                    it.key == "file" -> {
                        builder.addPart(it.value as MultipartBody.Part)
                    }
                    it.value is List<*> -> {
                        val list = it.value as List<*>
                        for (index in list.indices) {
                            builder.addFormDataPart(
                                "${it.key}[]",
                                list[index].toString()
                            )
                        }
                    }
                    else -> {
                        builder.addFormDataPart(it.key, it.value.toString())
                    }
                }
            }
            builder.build()
        } else {
            filteredParams
                .toJson()
                .toRequestBody("application/json".toMediaType())
        }

        val request = Request.Builder()
            .url(httpBuilder.build())
            .headers(requestHeaders)
            .method(method, body)
            .build()

        return awaitResponse(request, responseType, converter)
    }

    /**
     * Upload a file in chunks
     *
     * @param path
     * @param headers
     * @param params
     *
     * @return [T]
     */
    @Throws(RevenexxException::class)
    suspend fun <T> chunkedUpload(
        path: String,
        headers:  MutableMap<String, String>,
        params: MutableMap<String, Any?>,
        responseType: Class<T>,
        converter: ((Any) -> T)? = null,
        paramName: String,
        idParamName: String? = null,
        onProgress: ((UploadProgress) -> Unit)? = null,
    ): T {
        var file: RandomAccessFile? = null
        val input = params[paramName] as InputFile
        val size: Long = when(input.sourceType) {
            "path", "file" -> {
                file = RandomAccessFile(input.path, "r")
                file.length()
            }
            "bytes" -> {
                (input.data as ByteArray).size.toLong()
            }
            else -> throw UnsupportedOperationException()
        }

        // The API takes one multipart body per upload. It has no chunked or
        // resumable protocol — no content-range, no upload id, no per-chunk
        // endpoint — so the whole file always goes in a single request.
        file?.close()

        val data = when(input.sourceType) {
            "file", "path" -> File(input.path).asRequestBody()
            "bytes" -> (input.data as ByteArray).toRequestBody(input.mimeType.toMediaType())
            else -> throw UnsupportedOperationException()
        }
        params[paramName] = MultipartBody.Part.createFormData(
            paramName,
            input.filename,
            data
        )

        val result = call(
            method = "POST",
            path,
            headers,
            params,
            responseType,
            converter
        )

        onProgress?.invoke(
            UploadProgress(
                id = "",
                progress = 100.0,
                sizeUploaded = size,
                chunksTotal = 1,
                chunksUploaded = 1,
            )
        )

        return result
    }

    /**
     * Await Response
     *
     * @param request
     * @param responseType
     * @param converter
     *
     * @return [T]
     */
    @Throws(RevenexxException::class)
    private suspend fun <T> awaitResponse(
        request: Request,
        responseType: Class<T>,
        converter: ((Any) -> T)? = null
    ) = suspendCancellableCoroutine<T> {
        http.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (it.isCancelled) {
                    return
                }
                it.cancel(e)
            }

            @Suppress("UNCHECKED_CAST")
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    val body = response.body!!
                        .charStream()
                        .buffered()
                        .use(BufferedReader::readText)

                    val error = if (response.headers["content-type"]?.contains("application/json") == true) {
                        val map = body.fromJson<Map<String, Any>>()

                        // The gateway sends a string `code` ("bad_request") and no
                        // numeric one, so casting it to Number throws and the real
                        // error never surfaces. Fall back to the HTTP status, and
                        // carry the string code through as the type.
                        RevenexxException(
                            map["message"] as? String ?: map["error"] as? String ?: "",
                            (map["code"] as? Number)?.toInt() ?: response.code,
                            map["type"] as? String ?: map["code"] as? String ?: "",
                            body
                        )
                    } else {
                        RevenexxException(body, response.code, "", body)
                    }
                    it.cancel(error)
                    return
                }

                val warnings = response.headers["x-revenexx-warning"]
                if (warnings != null) {
                    warnings.split(";").forEach { warning ->
                        System.err.println("Warning: $warning")
                    }
                }

                when {
                    responseType == Boolean::class.java -> {
                        it.resume(true as T)
                        return
                    }
                    responseType == String::class.java -> {
                        val body = response.body!!
                            .charStream()
                            .buffered()
                            .use(BufferedReader::readText)
                        it.resume(body as T)
                        return
                    }
                    responseType == ByteArray::class.java -> {
                        it.resume(response.body!!
                            .byteStream()
                            .buffered()
                            .use(BufferedInputStream::readBytes) as T
                        )
                        return
                    }
                    response.body == null -> {
                        it.resume(true as T)
                        return
                    }
                }
                val body = response.body!!
                    .charStream()
                    .buffered()
                    .use(BufferedReader::readText)
                if (body.isEmpty()) {
                    it.resume(true as T)
                    return
                }

                val map = body.fromJson<Any>()

                it.resume(
                    converter?.invoke(map) ?: map as T
                )
            }
        })
    }
}
