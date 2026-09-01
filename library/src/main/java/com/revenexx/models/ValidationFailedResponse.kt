package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.ValidationFailedResponseStatus

/**
 * 
 */
data class ValidationFailedResponse(
    /**
     * 
     */
    @SerializedName("errors")
    var errors: List<String>?,

    /**
     * 
     */
    @SerializedName("status")
    var status: ValidationFailedResponseStatus?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "errors" to errors as Any,
        "status" to status?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = ValidationFailedResponse(
            errors = map["errors"] as? List<String>,
            status = ValidationFailedResponseStatus.values().find { it.value == (map["status"] as? String) } ?: null,
        )
    }
}