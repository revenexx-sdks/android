package com.revenexx.services

import android.net.Uri
import com.revenexx.Client
import com.revenexx.Service
import com.revenexx.models.*
import com.revenexx.exceptions.RevenexxAPIRevenexxException
import com.revenexx.extensions.classOf
import okhttp3.Cookie
import java.io.File

/**
 * 
 */
class Customers(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun customersAddressesList(
    ): Any {
        val apiPath = "/v1/customers/addresses"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param city 
     * @param country ISO 3166-1 alpha-2 code.
     * @param street 
     * @param zip 
     * @param company 
     * @param contactId Owning contact (personal address).
     * @param isDefault The default address of its owner and type.
     * @param name Recipient name.
     * @param organizationId Owning organization (company address).
     * @param phone 
     * @param region 
     * @param street2 
     * @param type Default 'shipping'.
     * @return [com.revenexx.models.Address]
     */
    @JvmOverloads
    suspend fun customersAddressesCreate(
        city: String,
        country: String,
        street: String,
        zip: String,
        company: String? = null,
        contactId: String? = null,
        isDefault: Boolean? = null,
        name: String? = null,
        organizationId: String? = null,
        phone: String? = null,
        region: String? = null,
        street2: String? = null,
        type: com.revenexx.enums.AddressType? = null,
    ): com.revenexx.models.Address {
        val apiPath = "/v1/customers/addresses"

        val apiParams = mutableMapOf<String, Any?>(
            "city" to city,
            "company" to company,
            "contact_id" to contactId,
            "country" to country,
            "is_default" to isDefault,
            "name" to name,
            "organization_id" to organizationId,
            "phone" to phone,
            "region" to region,
            "street" to street,
            "street2" to street2,
            "type" to type,
            "zip" to zip,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Address = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Address.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Address::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun customersAddressesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/customers/addresses/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Address]
     */
    suspend fun customersAddressesGet(
        id: String,
    ): com.revenexx.models.Address {
        val apiPath = "/v1/customers/addresses/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Address = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Address.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Address::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param city 
     * @param company 
     * @param contactId Owning contact (personal address).
     * @param country ISO 3166-1 alpha-2 code.
     * @param isDefault The default address of its owner and type.
     * @param name Recipient name.
     * @param organizationId Owning organization (company address).
     * @param phone 
     * @param region 
     * @param street 
     * @param street2 
     * @param type Default 'shipping'.
     * @param zip 
     * @return [com.revenexx.models.Address]
     */
    @JvmOverloads
    suspend fun customersAddressesUpdate(
        id: String,
        city: String? = null,
        company: String? = null,
        contactId: String? = null,
        country: String? = null,
        isDefault: Boolean? = null,
        name: String? = null,
        organizationId: String? = null,
        phone: String? = null,
        region: String? = null,
        street: String? = null,
        street2: String? = null,
        type: com.revenexx.enums.AddressType? = null,
        zip: String? = null,
    ): com.revenexx.models.Address {
        val apiPath = "/v1/customers/addresses/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "city" to city,
            "company" to company,
            "contact_id" to contactId,
            "country" to country,
            "is_default" to isDefault,
            "name" to name,
            "organization_id" to organizationId,
            "phone" to phone,
            "region" to region,
            "street" to street,
            "street2" to street2,
            "type" to type,
            "zip" to zip,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Address = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Address.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Address::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param email 
     * @param password 
     * @return [com.revenexx.models.AuthLoginResponse]
     */
    suspend fun customersAuthLogin(
        email: String,
        password: String,
    ): com.revenexx.models.AuthLoginResponse {
        val apiPath = "/v1/customers/auth/login"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "password" to password,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AuthLoginResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AuthLoginResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AuthLoginResponse::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param sessionId 
     * @param userId 
     * @return [Any]
     */
    suspend fun customersAuthLogout(
        sessionId: String,
        userId: String,
    ): Any {
        val apiPath = "/v1/customers/auth/logout"

        val apiParams = mutableMapOf<String, Any?>(
            "session_id" to sessionId,
            "user_id" to userId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param userId 
     * @param sessionId Optional session to verify — answers 401 when the session is expired or revoked.
     * @return [com.revenexx.models.AuthMeResponse]
     */
    @JvmOverloads
    suspend fun customersAuthMe(
        userId: String,
        sessionId: String? = null,
    ): com.revenexx.models.AuthMeResponse {
        val apiPath = "/v1/customers/auth/me"

        val apiParams = mutableMapOf<String, Any?>(
            "session_id" to sessionId,
            "user_id" to userId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AuthMeResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AuthMeResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AuthMeResponse::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param email 
     * @param url Redirect URL carrying userId + secret.
     * @return [Any]
     */
    suspend fun customersAuthRecovery(
        email: String,
        url: String,
    ): Any {
        val apiPath = "/v1/customers/auth/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "url" to url,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param password 
     * @param secret 
     * @param userId 
     * @return [Any]
     */
    suspend fun customersAuthRecoveryConfirm(
        password: String,
        secret: String,
        userId: String,
    ): Any {
        val apiPath = "/v1/customers/auth/recovery"

        val apiParams = mutableMapOf<String, Any?>(
            "password" to password,
            "secret" to secret,
            "user_id" to userId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param email 
     * @param password 
     * @param firstName 
     * @param lastName 
     * @param locale BCP 47, e.g. de-DE
     * @param organizationId Join an existing organization.
     * @param organizationName Found a new organization; the contact becomes its admin.
     * @return [com.revenexx.models.AuthRegisterResponse]
     */
    @JvmOverloads
    suspend fun customersAuthRegister(
        email: String,
        password: String,
        firstName: String? = null,
        lastName: String? = null,
        locale: String? = null,
        organizationId: String? = null,
        organizationName: String? = null,
    ): com.revenexx.models.AuthRegisterResponse {
        val apiPath = "/v1/customers/auth/register"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "first_name" to firstName,
            "last_name" to lastName,
            "locale" to locale,
            "organization_id" to organizationId,
            "organization_name" to organizationName,
            "password" to password,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.AuthRegisterResponse = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.AuthRegisterResponse.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.AuthRegisterResponse::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun customersContactsList(
    ): Any {
        val apiPath = "/v1/customers/contacts"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param email 
     * @param firstName 
     * @param isPrimary The primary contact of its organization.
     * @param lastName 
     * @param locale BCP 47, e.g. de-DE
     * @param organizationId Owning organization — membership is mirrored to the platform team.
     * @param phone 
     * @param role Default 'buyer' — also the team role on the platform mirror.
     * @param status Default 'invited' on create.
     * @return [com.revenexx.models.Contact]
     */
    @JvmOverloads
    suspend fun customersContactsCreate(
        email: String,
        firstName: String? = null,
        isPrimary: Boolean? = null,
        lastName: String? = null,
        locale: String? = null,
        organizationId: String? = null,
        phone: String? = null,
        role: com.revenexx.enums.ContactRole? = null,
        status: com.revenexx.enums.ContactStatus? = null,
    ): com.revenexx.models.Contact {
        val apiPath = "/v1/customers/contacts"

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "first_name" to firstName,
            "is_primary" to isPrimary,
            "last_name" to lastName,
            "locale" to locale,
            "organization_id" to organizationId,
            "phone" to phone,
            "role" to role,
            "status" to status,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Contact = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Contact.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Contact::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun customersContactsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/customers/contacts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Contact]
     */
    suspend fun customersContactsGet(
        id: String,
    ): com.revenexx.models.Contact {
        val apiPath = "/v1/customers/contacts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Contact = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Contact.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Contact::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param email 
     * @param firstName 
     * @param isPrimary The primary contact of its organization.
     * @param lastName 
     * @param locale BCP 47, e.g. de-DE
     * @param organizationId Owning organization — membership is mirrored to the platform team.
     * @param phone 
     * @param role Default 'buyer' — also the team role on the platform mirror.
     * @param status Default 'invited' on create.
     * @return [com.revenexx.models.Contact]
     */
    @JvmOverloads
    suspend fun customersContactsUpdate(
        id: String,
        email: String? = null,
        firstName: String? = null,
        isPrimary: Boolean? = null,
        lastName: String? = null,
        locale: String? = null,
        organizationId: String? = null,
        phone: String? = null,
        role: com.revenexx.enums.ContactRole? = null,
        status: com.revenexx.enums.ContactStatus? = null,
    ): com.revenexx.models.Contact {
        val apiPath = "/v1/customers/contacts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "email" to email,
            "first_name" to firstName,
            "is_primary" to isPrimary,
            "last_name" to lastName,
            "locale" to locale,
            "organization_id" to organizationId,
            "phone" to phone,
            "role" to role,
            "status" to status,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Contact = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Contact.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Contact::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun customersOrganizationsList(
    ): Any {
        val apiPath = "/v1/customers/organizations"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param name Company name — mirrored to the platform team.
     * @param settings Free-form organization settings.
     * @param status Default 'active'.
     * @param vatId 
     * @return [com.revenexx.models.Organization]
     */
    @JvmOverloads
    suspend fun customersOrganizationsCreate(
        name: String,
        settings: Any? = null,
        status: com.revenexx.enums.OrganizationStatus? = null,
        vatId: String? = null,
    ): com.revenexx.models.Organization {
        val apiPath = "/v1/customers/organizations"

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "settings" to settings,
            "status" to status,
            "vat_id" to vatId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Organization = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Organization.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Organization::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun customersOrganizationsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/customers/organizations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        return client.call(
            "DELETE",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = Any::class.java,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Organization]
     */
    suspend fun customersOrganizationsGet(
        id: String,
    ): com.revenexx.models.Organization {
        val apiPath = "/v1/customers/organizations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Organization = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Organization.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Organization::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param name Company name — mirrored to the platform team.
     * @param settings Free-form organization settings.
     * @param status Default 'active'.
     * @param vatId 
     * @return [com.revenexx.models.Organization]
     */
    @JvmOverloads
    suspend fun customersOrganizationsUpdate(
        id: String,
        name: String? = null,
        settings: Any? = null,
        status: com.revenexx.enums.OrganizationStatus? = null,
        vatId: String? = null,
    ): com.revenexx.models.Organization {
        val apiPath = "/v1/customers/organizations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "name" to name,
            "settings" to settings,
            "status" to status,
            "vat_id" to vatId,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Organization = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Organization.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Organization::class.java,
            converter,
        )
    }


}