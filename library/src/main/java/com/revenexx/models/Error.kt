package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Uniform gateway error response.
 */
data class Error(
    /**
     * 
     */
    @SerializedName("error")
    val error: Boolean,

    /**
     * 
     */
    @SerializedName("message")
    val message: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "error" to error as Any,
        "message" to message as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Error(
            error = map["error"] as Boolean,
            message = map["message"] as String,
        )
    }
}