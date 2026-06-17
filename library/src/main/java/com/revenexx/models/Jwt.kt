package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * JWT
 */
data class Jwt(
    /**
     * JWT encoded string.
     */
    @SerializedName("jwt")
    val jwt: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "jwt" to jwt as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Jwt(
            jwt = map["jwt"] as String,
        )
    }
}