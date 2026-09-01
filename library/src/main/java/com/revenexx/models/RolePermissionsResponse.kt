package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class RolePermissionsResponse(
    /**
     * The role that was written.
     */
    @SerializedName("key")
    var key: String?,

    /**
     * Its complete new set, after de-duplication.
     */
    @SerializedName("permissions")
    var permissions: List<String>?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "key" to key as Any,
        "permissions" to permissions as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RolePermissionsResponse(
            key = map["key"] as? String,
            permissions = map["permissions"] as? List<String>,
        )
    }
}