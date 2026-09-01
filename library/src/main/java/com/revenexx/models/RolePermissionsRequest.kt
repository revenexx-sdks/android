package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class RolePermissionsRequest(
    /**
     * The complete new set. Duplicates and blanks are ignored; an empty array revokes everything.
     */
    @SerializedName("permissions")
    val permissions: List<String>,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "permissions" to permissions as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RolePermissionsRequest(
            permissions = map["permissions"] as List<String>,
        )
    }
}