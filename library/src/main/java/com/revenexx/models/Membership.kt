package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Membership
 */
data class Membership(
    /**
     * Membership creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * Membership ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * Membership update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * User confirmation status, true if the user has joined the team or false otherwise.
     */
    @SerializedName("confirm")
    val confirm: Boolean,

    /**
     * Date, the user has been invited to join the team in ISO 8601 format.
     */
    @SerializedName("invited")
    val invited: String,

    /**
     * Date, the user has accepted the invitation to join the team in ISO 8601 format.
     */
    @SerializedName("joined")
    val joined: String,

    /**
     * Multi factor authentication status, true if the user has MFA enabled or false otherwise. Hide this attribute by toggling membership privacy in the Console.
     */
    @SerializedName("mfa")
    val mfa: Boolean,

    /**
     * User list of roles
     */
    @SerializedName("roles")
    val roles: List<String>,

    /**
     * Team ID.
     */
    @SerializedName("teamId")
    val teamId: String,

    /**
     * Team name.
     */
    @SerializedName("teamName")
    val teamName: String,

    /**
     * User email address. Hide this attribute by toggling membership privacy in the Console.
     */
    @SerializedName("userEmail")
    val userEmail: String,

    /**
     * User ID.
     */
    @SerializedName("userId")
    val userId: String,

    /**
     * User name. Hide this attribute by toggling membership privacy in the Console.
     */
    @SerializedName("userName")
    val userName: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "confirm" to confirm as Any,
        "invited" to invited as Any,
        "joined" to joined as Any,
        "mfa" to mfa as Any,
        "roles" to roles as Any,
        "teamId" to teamId as Any,
        "teamName" to teamName as Any,
        "userEmail" to userEmail as Any,
        "userId" to userId as Any,
        "userName" to userName as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Membership(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            confirm = map["confirm"] as Boolean,
            invited = map["invited"] as String,
            joined = map["joined"] as String,
            mfa = map["mfa"] as Boolean,
            roles = map["roles"] as List<String>,
            teamId = map["teamId"] as String,
            teamName = map["teamName"] as String,
            userEmail = map["userEmail"] as String,
            userId = map["userId"] as String,
            userName = map["userName"] as String,
        )
    }
}