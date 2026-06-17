package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Identity
 */
data class Identity(
    /**
     * Identity creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Identity ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Identity update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Identity Provider.
     */
    @SerializedName("provider")
    val provider: String,

    /**
     * Identity Provider Access Token.
     */
    @SerializedName("providerAccessToken")
    val providerAccessToken: String,

    /**
     * The date of when the access token expires in ISO 8601 format.
     */
    @SerializedName("providerAccessTokenExpiry")
    val providerAccessTokenExpiry: String,

    /**
     * Email of the User in the Identity Provider.
     */
    @SerializedName("providerEmail")
    val providerEmail: String,

    /**
     * Identity Provider Refresh Token.
     */
    @SerializedName("providerRefreshToken")
    val providerRefreshToken: String,

    /**
     * ID of the User in the Identity Provider.
     */
    @SerializedName("providerUid")
    val providerUid: String,

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
        "provider" to provider as Any,
        "providerAccessToken" to providerAccessToken as Any,
        "providerAccessTokenExpiry" to providerAccessTokenExpiry as Any,
        "providerEmail" to providerEmail as Any,
        "providerRefreshToken" to providerRefreshToken as Any,
        "providerUid" to providerUid as Any,
        "userId" to userId as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Identity(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            provider = map["provider"] as String,
            providerAccessToken = map["providerAccessToken"] as String,
            providerAccessTokenExpiry = map["providerAccessTokenExpiry"] as String,
            providerEmail = map["providerEmail"] as String,
            providerRefreshToken = map["providerRefreshToken"] as String,
            providerUid = map["providerUid"] as String,
            userId = map["userId"] as String,
        )
    }
}