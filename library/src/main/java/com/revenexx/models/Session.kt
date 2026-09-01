package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Session
 */
data class Session(
    /**
     * Session creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Session ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Session update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

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
     * Returns true if this the current user session.
     */
    @SerializedName("current")
    val current: Boolean,

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
     * Session expiration date in ISO 8601 format.
     */
    @SerializedName("expire")
    val expire: String,

    /**
     * Returns a list of active session factors.
     */
    @SerializedName("factors")
    val factors: List<String>,

    /**
     * IP in use when the session was created.
     */
    @SerializedName("ip")
    val ip: String,

    /**
     * Most recent date in ISO 8601 format when the session successfully passed MFA challenge.
     */
    @SerializedName("mfaUpdatedAt")
    val mfaUpdatedAt: String,

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
     * Session Provider.
     */
    @SerializedName("provider")
    val provider: String,

    /**
     * Session Provider Access Token.
     */
    @SerializedName("providerAccessToken")
    val providerAccessToken: String,

    /**
     * The date of when the access token expires in ISO 8601 format.
     */
    @SerializedName("providerAccessTokenExpiry")
    val providerAccessTokenExpiry: String,

    /**
     * Session Provider Refresh Token.
     */
    @SerializedName("providerRefreshToken")
    val providerRefreshToken: String,

    /**
     * Session Provider User ID.
     */
    @SerializedName("providerUid")
    val providerUid: String,

    /**
     * Secret used to authenticate the user. Only included if the request was made with an API key
     */
    @SerializedName("secret")
    val secret: String,

    /**
     * User ID.
     */
    @SerializedName("userId")
    val userId: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "clientCode" to clientCode as Any,
        "clientEngine" to clientEngine as Any,
        "clientEngineVersion" to clientEngineVersion as Any,
        "clientName" to clientName as Any,
        "clientType" to clientType as Any,
        "clientVersion" to clientVersion as Any,
        "countryCode" to countryCode as Any,
        "countryName" to countryName as Any,
        "current" to current as Any,
        "deviceBrand" to deviceBrand as Any,
        "deviceModel" to deviceModel as Any,
        "deviceName" to deviceName as Any,
        "expire" to expire as Any,
        "factors" to factors as Any,
        "ip" to ip as Any,
        "mfaUpdatedAt" to mfaUpdatedAt as Any,
        "osCode" to osCode as Any,
        "osName" to osName as Any,
        "osVersion" to osVersion as Any,
        "provider" to provider as Any,
        "providerAccessToken" to providerAccessToken as Any,
        "providerAccessTokenExpiry" to providerAccessTokenExpiry as Any,
        "providerRefreshToken" to providerRefreshToken as Any,
        "providerUid" to providerUid as Any,
        "secret" to secret as Any,
        "userId" to userId as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Session(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            clientCode = map["clientCode"] as String,
            clientEngine = map["clientEngine"] as String,
            clientEngineVersion = map["clientEngineVersion"] as String,
            clientName = map["clientName"] as String,
            clientType = map["clientType"] as String,
            clientVersion = map["clientVersion"] as String,
            countryCode = map["countryCode"] as String,
            countryName = map["countryName"] as String,
            current = map["current"] as Boolean,
            deviceBrand = map["deviceBrand"] as String,
            deviceModel = map["deviceModel"] as String,
            deviceName = map["deviceName"] as String,
            expire = map["expire"] as String,
            factors = map["factors"] as List<String>,
            ip = map["ip"] as String,
            mfaUpdatedAt = map["mfaUpdatedAt"] as String,
            osCode = map["osCode"] as String,
            osName = map["osName"] as String,
            osVersion = map["osVersion"] as String,
            provider = map["provider"] as String,
            providerAccessToken = map["providerAccessToken"] as String,
            providerAccessTokenExpiry = map["providerAccessTokenExpiry"] as String,
            providerRefreshToken = map["providerRefreshToken"] as String,
            providerUid = map["providerUid"] as String,
            secret = map["secret"] as String,
            userId = map["userId"] as String,
        )
    }
}