package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class UnauthenticatedResponse(
    /**
     * 
     */
    @SerializedName("message")
    var message: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "message" to message as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = UnauthenticatedResponse(
            message = map["message"] as? String,
        )
    }
}