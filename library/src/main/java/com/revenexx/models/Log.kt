package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Log
 */
data class Log(
    /**
     * Client code name. A short code such as `CH` for Chrome, derived from the request's User-Agent by the core service; the full code list is not part of this API.
     */
    @SerializedName("clientCode")
    val clientCode: String,

    /**
     * Client engine name.
     */
    @SerializedName("clientEngine")
    val clientEngine: String,

    /**
     * Client engine name.
     */
    @SerializedName("clientEngineVersion")
    val clientEngineVersion: String,

    /**
     * Client name.
     */
    @SerializedName("clientName")
    val clientName: String,

    /**
     * Client type.
     */
    @SerializedName("clientType")
    val clientType: String,

    /**
     * Client version.
     */
    @SerializedName("clientVersion")
    val clientVersion: String,

    /**
     * Country two-character ISO 3166-1 alpha code.
     */
    @SerializedName("countryCode")
    val countryCode: String,

    /**
     * Country name.
     */
    @SerializedName("countryName")
    val countryName: String,

    /**
     * Device brand name.
     */
    @SerializedName("deviceBrand")
    val deviceBrand: String,

    /**
     * Device model name.
     */
    @SerializedName("deviceModel")
    val deviceModel: String,

    /**
     * Device name.
     */
    @SerializedName("deviceName")
    val deviceName: String,

    /**
     * Event name.
     */
    @SerializedName("event")
    val event: String,

    /**
     * IP session in use when the session was created.
     */
    @SerializedName("ip")
    val ip: String,

    /**
     * API mode when event triggered.
     */
    @SerializedName("mode")
    val mode: String,

    /**
     * Operating system code name. A short code such as `AND` for Android, derived from the request's User-Agent by the core service; the full code list is not part of this API.
     */
    @SerializedName("osCode")
    val osCode: String,

    /**
     * Operating system name.
     */
    @SerializedName("osName")
    val osName: String,

    /**
     * Operating system version.
     */
    @SerializedName("osVersion")
    val osVersion: String,

    /**
     * Log creation date in ISO 8601 format.
     */
    @SerializedName("time")
    val time: String,

    /**
     * User Email.
     */
    @SerializedName("userEmail")
    val userEmail: String,

    /**
     * User ID.
     */
    @SerializedName("userId")
    val userId: String,

    /**
     * User Name.
     */
    @SerializedName("userName")
    val userName: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "clientCode" to clientCode as Any,
        "clientEngine" to clientEngine as Any,
        "clientEngineVersion" to clientEngineVersion as Any,
        "clientName" to clientName as Any,
        "clientType" to clientType as Any,
        "clientVersion" to clientVersion as Any,
        "countryCode" to countryCode as Any,
        "countryName" to countryName as Any,
        "deviceBrand" to deviceBrand as Any,
        "deviceModel" to deviceModel as Any,
        "deviceName" to deviceName as Any,
        "event" to event as Any,
        "ip" to ip as Any,
        "mode" to mode as Any,
        "osCode" to osCode as Any,
        "osName" to osName as Any,
        "osVersion" to osVersion as Any,
        "time" to time as Any,
        "userEmail" to userEmail as Any,
        "userId" to userId as Any,
        "userName" to userName as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Log(
            clientCode = map["clientCode"] as String,
            clientEngine = map["clientEngine"] as String,
            clientEngineVersion = map["clientEngineVersion"] as String,
            clientName = map["clientName"] as String,
            clientType = map["clientType"] as String,
            clientVersion = map["clientVersion"] as String,
            countryCode = map["countryCode"] as String,
            countryName = map["countryName"] as String,
            deviceBrand = map["deviceBrand"] as String,
            deviceModel = map["deviceModel"] as String,
            deviceName = map["deviceName"] as String,
            event = map["event"] as String,
            ip = map["ip"] as String,
            mode = map["mode"] as String,
            osCode = map["osCode"] as String,
            osName = map["osName"] as String,
            osVersion = map["osVersion"] as String,
            time = map["time"] as String,
            userEmail = map["userEmail"] as String,
            userId = map["userId"] as String,
            userName = map["userName"] as String,
        )
    }
}