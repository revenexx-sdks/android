package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * User
 */
data class User<T>(
    /**
     * User creation date in ISO 8601 format.
     */
    @SerializedName("\$createdAt")
    val createdAt: String,

    /**
     * User ID.
     */
    @SerializedName("\$id")
    val id: String,

    /**
     * User update date in ISO 8601 format.
     */
    @SerializedName("\$updatedAt")
    val updatedAt: String,

    /**
     * Most recent access date in ISO 8601 format. This attribute is only updated again after 24 hours.
     */
    @SerializedName("accessedAt")
    val accessedAt: String,

    /**
     * User email address.
     */
    @SerializedName("email")
    val email: String,

    /**
     * Email verification status.
     */
    @SerializedName("emailVerification")
    val emailVerification: Boolean,

    /**
     * Password hashing algorithm.
     */
    @SerializedName("hash")
    var hash: String?,

    /**
     * Password hashing algorithm configuration.
     */
    @SerializedName("hashOptions")
    var hashOptions: Any?,

    /**
     * Labels for the user.
     */
    @SerializedName("labels")
    val labels: List<String>,

    /**
     * Multi factor authentication status.
     */
    @SerializedName("mfa")
    val mfa: Boolean,

    /**
     * User name.
     */
    @SerializedName("name")
    val name: String,

    /**
     * Hashed user password.
     */
    @SerializedName("password")
    var password: String?,

    /**
     * Password update time in ISO 8601 format.
     */
    @SerializedName("passwordUpdate")
    val passwordUpdate: String,

    /**
     * User phone number in E.164 format.
     */
    @SerializedName("phone")
    val phone: String,

    /**
     * Phone verification status.
     */
    @SerializedName("phoneVerification")
    val phoneVerification: Boolean,

    /**
     * User preferences as a key-value object
     */
    @SerializedName("prefs")
    val prefs: Preferences<T>,

    /**
     * User registration date in ISO 8601 format.
     */
    @SerializedName("registration")
    val registration: String,

    /**
     * User status. Pass `true` for enabled and `false` for disabled.
     */
    @SerializedName("status")
    val status: Boolean,

    /**
     * A user-owned message receiver. A single user may have multiple e.g. emails, phones, and a browser. Each target is registered with a single provider.
     */
    @SerializedName("targets")
    val targets: List<Target>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$createdAt" to createdAt as Any,
        "\$id" to id as Any,
        "\$updatedAt" to updatedAt as Any,
        "accessedAt" to accessedAt as Any,
        "email" to email as Any,
        "emailVerification" to emailVerification as Any,
        "hash" to hash as Any,
        "hashOptions" to hashOptions as Any,
        "labels" to labels as Any,
        "mfa" to mfa as Any,
        "name" to name as Any,
        "password" to password as Any,
        "passwordUpdate" to passwordUpdate as Any,
        "phone" to phone as Any,
        "phoneVerification" to phoneVerification as Any,
        "prefs" to prefs.toMap() as Any,
        "registration" to registration as Any,
        "status" to status as Any,
        "targets" to targets.map { it.toMap() } as Any,
    )

    companion object {
        operator fun invoke(
            createdAt: String,
            id: String,
            updatedAt: String,
            accessedAt: String,
            email: String,
            emailVerification: Boolean,
            hash: String?,
            hashOptions: Any?,
            labels: List<String>,
            mfa: Boolean,
            name: String,
            password: String?,
            passwordUpdate: String,
            phone: String,
            phoneVerification: Boolean,
            prefs: Preferences<Map<String, Any>>,
            registration: String,
            status: Boolean,
            targets: List<Target>,
        ) = User<Map<String, Any>>(
            createdAt,
            id,
            updatedAt,
            accessedAt,
            email,
            emailVerification,
            hash,
            hashOptions,
            labels,
            mfa,
            name,
            password,
            passwordUpdate,
            phone,
            phoneVerification,
            prefs,
            registration,
            status,
            targets,
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = User<T>(
            createdAt = map["\$createdAt"] as String,
            id = map["\$id"] as String,
            updatedAt = map["\$updatedAt"] as String,
            accessedAt = map["accessedAt"] as String,
            email = map["email"] as String,
            emailVerification = map["emailVerification"] as Boolean,
            hash = map["hash"] as? String,
            hashOptions = map["hashOptions"] as? Any,
            labels = map["labels"] as List<String>,
            mfa = map["mfa"] as Boolean,
            name = map["name"] as String,
            password = map["password"] as? String,
            passwordUpdate = map["passwordUpdate"] as String,
            phone = map["phone"] as String,
            phoneVerification = map["phoneVerification"] as Boolean,
            prefs = Preferences.from(map = map["prefs"] as Map<String, Any>, nestedType),
            registration = map["registration"] as String,
            status = map["status"] as Boolean,
            targets = (map["targets"] as List<Map<String, Any>>).map { Target.from(map = it) },
        )
    }
}