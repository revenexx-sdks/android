package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * The identity service's answer, forwarded verbatim.
 */
data class AuthMfaChallengeConfirmResponse<T>(
    /**
     * The challenge that was answered.
     */
    @SerializedName("\$id")
    var id: String?,

    /**
     * Additional properties
     */
    @SerializedName("data")
    val data: T
) {
    fun toMap(): Map<String, Any> = mapOf(
        "\$id" to id as Any,
        "data" to data!!.jsonCast(to = Map::class.java)
    )

    companion object {
        operator fun invoke(
            id: String?,
            data: Map<String, Any>
        ) = AuthMfaChallengeConfirmResponse<Map<String, Any>>(
            id,
            data
        )

        @Suppress("UNCHECKED_CAST")
        fun <T> from(
            map: Map<String, Any>,
            nestedType: Class<T>
        ) = AuthMfaChallengeConfirmResponse<T>(
            id = map["\$id"] as? String,
            data = map["data"]?.jsonCast(to = nestedType) ?: map.jsonCast(to = nestedType)
        )
    }
}