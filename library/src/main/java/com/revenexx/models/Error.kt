package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Uniform error response. The same shape is emitted by the gateway and by the apps behind it, so one parser covers the whole API.
 */
data class Error(
    /**
     * Machine-readable discriminator, e.g. not_found, invalid_value, unique_violation.
     */
    @SerializedName("code")
    var code: String?,

    /**
     * Human-readable message. Was a boolean on gateway-emitted errors before; it is a string everywhere now.
     */
    @SerializedName("error")
    val error: String,

    /**
     * Deprecated duplicate of `error`, kept so existing readers keep working. Read `error`.
     */
    @SerializedName("message")
    var message: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "error" to error as Any,
        "message" to message as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Error(
            code = map["code"] as? String,
            error = map["error"] as String,
            message = map["message"] as? String,
        )
    }
}