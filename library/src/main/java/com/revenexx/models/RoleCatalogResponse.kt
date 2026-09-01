package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast
import com.revenexx.enums.RoleCatalogResponseSource

/**
 * 
 */
data class RoleCatalogResponse(
    /**
     * The built-in permission vocabulary, one entry per grant. The authoritative, installed-app-aware list is the platform's permission ledger — this app deliberately does not duplicate it.
     */
    @SerializedName("permissions")
    var permissions: List<Any>?,

    /**
     * Every role a contact of this tenant can hold, least to most privileged.
     */
    @SerializedName("roles")
    var roles: List<Any>?,

    /**
     * 'tenant' — the configured mapping answered. 'defaults' — this tenant has no roles yet, or custom_roles_enabled locks the ledger, and the built-ins answered.
     */
    @SerializedName("source")
    var source: RoleCatalogResponseSource?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "permissions" to permissions as Any,
        "roles" to roles as Any,
        "source" to source?.value as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = RoleCatalogResponse(
            permissions = map["permissions"] as? List<Any>,
            roles = map["roles"] as? List<Any>,
            source = RoleCatalogResponseSource.values().find { it.value == (map["source"] as? String) } ?: null,
        )
    }
}