package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.AuthMailSource

/**
 * The verification token, minus its secret.
 */
data class AuthVerificationResponse<T>(
    /**
     * The verification that was created.
     */
    @SerializedName("\$id")
    var id: String?,

    /**
     * When the link stops working.
     */
    @SerializedName("expire")
    var expire: String?,

    /**
     * Which template the buyer received: 'tenant' is this shop's own, 'platform' the identity service's built-in one — the fallback when messaging could not be reached. The value is the same either way, so the flow works in both cases.
     */
    @SerializedName("mail")
    var mail: AuthMailSource?,

    /**
     * The platform user it belongs to.
     */
    @SerializedName("userId")
    var userId: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$id" to id as Any,
        "expire" to expire as Any,
        "mail" to mail?.value as Any,
        "userId" to userId as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            id: String?,
            expire: String?,
            mail: AuthMailSource?,
            userId: String?,
            data: Map<String, Any>
        ) = AuthVerificationResponse<Map<String, Any>>(
            id,
            expire,
            mail,
            userId,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = AuthVerificationResponse<T>(
            id = map["\$id"] as? String,
            expire = map["expire"] as? String,
            mail = AuthMailSource.values().find { it.value == (map["mail"] as? String) } ?: null,
            userId = map["userId"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}