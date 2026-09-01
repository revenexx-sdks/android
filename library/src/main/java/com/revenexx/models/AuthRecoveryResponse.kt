package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.RecoveryMailSource

/**
 * The identity service's recovery token, minus its secret, plus which mail the customer got. The secret is stripped deliberately — it travels only in the mailed link, and a caller that had both would not need the mail at all. `mail` is `tenant` when this shop's own template went out and `platform` when the messaging service could not be reached and the identity service's built-in mail is the copy the buyer has; the link is the same either way.
 */
data class AuthRecoveryResponse<T>(
    /**
     * The recovery that was created.
     */
    @SerializedName("\$id")
    var id: String?,

    /**
     * When the link stops working. The mail says the same thing in words.
     */
    @SerializedName("expire")
    var expire: String?,

    /**
     * Which template the buyer received: 'tenant' is this shop's own, 'platform' the identity service's built-in one — the fallback when messaging could not be reached. The link is identical either way, so a reset works in both cases.
     */
    @SerializedName("mail")
    var mail: RecoveryMailSource?,

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
            mail: RecoveryMailSource?,
            userId: String?,
            data: Map<String, Any>
        ) = AuthRecoveryResponse<Map<String, Any>>(
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
        ) = AuthRecoveryResponse<T>(
            id = map["\$id"] as? String,
            expire = map["expire"] as? String,
            mail = RecoveryMailSource.values().find { it.value == (map["mail"] as? String) } ?: null,
            userId = map["userId"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}