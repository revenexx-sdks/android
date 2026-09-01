package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class RolesDefaultsResponse(
    /**
     * Role keys created by this call.
     */
    @SerializedName("created")
    var created: List<String>?,

    /**
     * Role keys that were already there and were left untouched, permissions included.
     */
    @SerializedName("existing")
    var existing: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "created" to created as Any,
        "existing" to existing as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RolesDefaultsResponse(
            created = map["created"] as? List<String>,
            existing = map["existing"] as? List<String>,
        )
    }
}