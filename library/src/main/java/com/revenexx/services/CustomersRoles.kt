package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * The role catalogue and the tenant's own role-to-permission mapping. A role is held by a CONTACT and applies inside that contact's organization; there is no global customer role. Permissions are DERIVED from the role on every read and never stored per contact, so a role change takes effect immediately and can never leave a stale grant behind. Five built-in roles answer for a tenant that has written none of its own down; seeding them and replacing a role's permission set are the two writes. What one PERSON ends up holding is read in Contacts.
 */
class CustomersRoles(client: Client) : Service(client) {

    /**
     * The whole catalogue in one read: every role a contact of this tenant can hold, the permissions each one grants, and the built-in permission vocabulary those grants are drawn from. Roles are held by a CONTACT and apply inside that contact's organization; there is no global customer role. Permissions are derived from the role at read time and never stored per contact, so a role change takes effect immediately and cannot leave a stale grant. The role to permission MAPPING is per tenant and configurable (PUT /customers/roles/{key}/permissions); a tenant that has not configured anything gets the built-ins and 'source' says which of the two answered. Built-in roles, least to most privileged: viewer (Viewer), requester (Requester), buyer (Buyer), approver (Approver), admin (Administrator). The permission KEYS themselves come from the cross-app ledger — every installed app declares what it enforces — so a tenant may grant a key this list does not mention.
     *
     * @return [com.revenexx.models.RoleCatalogResponse]
     */
    suspend fun customersRolesList(
    ): com.revenexx.models.RoleCatalogResponse {
        val apiPath = "/v1/customers/roles"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.RoleCatalogResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.RoleCatalogResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.RoleCatalogResponse::class.java,
            converter,
        )
    }


    /**
     * Idempotent: a role that already exists is left completely alone, its permissions included, so re-seeding never undoes a merchant's edits. Creates viewer, requester, buyer, approver, admin with the built-in mapping. A tenant that never calls this still behaves correctly — the catalogue and every permission read fall back to the same built-ins.
     *
     * @param data Request body
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersRolesDefaults(
        data: Any,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/roles/defaults"

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


    /**
     * The whole new set in one call — the shape a role editor actually produces, and the one that cannot leave a half-applied grant behind if a second call fails. Seeds the built-in roles first when the tenant has none, so editing works without calling /defaults. Permission keys are free text on purpose: they belong to whichever app declared them, and a grant for an app that is not installed simply has nothing to act on.
     *
     * @param key The role key — one of the tenant's own roles (GET /customers/roles).
     * @param permissions The complete new set. Duplicates and blanks are ignored; an empty array revokes everything.
     * @return [com.revenexx.models.Error]
     */
    suspend fun customersRolesPermissionsReplace(
        key: String,
        permissions: List<String>,
    ): com.revenexx.models.Error {
        val apiPath = "/v1/customers/roles/{key}/permissions"
            .replace("{key}", key)

        val apiParams = mutableMapOf<String, Any?>(
            "permissions" to permissions,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Error = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Error.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Error::class.java,
            converter,
        )
    }


}