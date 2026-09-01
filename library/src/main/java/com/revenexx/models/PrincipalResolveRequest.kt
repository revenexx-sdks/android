package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PrincipalResolveRequest(
    /**
     * The contact the caller is acting for.
     */
    @SerializedName("contact_id")
    val contact_id: String,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "contact_id" to contact_id as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PrincipalResolveRequest(
            contact_id = map["contact_id"] as String,
        )
    }
}