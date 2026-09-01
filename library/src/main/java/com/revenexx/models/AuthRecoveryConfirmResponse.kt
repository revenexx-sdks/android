package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The identity service's answer, forwarded verbatim: the spent recovery token. The new password is already in effect when this arrives.
 */
data class AuthRecoveryConfirmResponse<T>(
    /**
     * The recovery that was confirmed.
     */
    @SerializedName("\$id")
    var id: String?,

    /**
     * The platform user whose password was set.
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
        "userId" to userId as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            id: String?,
            userId: String?,
            data: Map<String, Any>
        ) = AuthRecoveryConfirmResponse<Map<String, Any>>(
            id,
            userId,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = AuthRecoveryConfirmResponse<T>(
            id = map["\$id"] as? String,
            userId = map["userId"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}