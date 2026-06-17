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
class Prices(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pricesListsList(
    ): Any {
        val apiPath = "/v1/prices/lists"

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
     * @param code Unique list code per tenant.
     * @param name 
     * @param channelId Scope: only this channel.
     * @param contactId Scope: only this contact — beats every other scope.
     * @param currency ISO 4217 code (default EUR) — resolution only considers lists matching the requested currency.
     * @param description 
     * @param isDefault Default lists resolve last within their group.
     * @param labels Localised names ({de, en, …}).
     * @param marketId Scope: only this market.
     * @param metadata Free-form metadata.
     * @param organizationId Scope: only this organization.
     * @param priority Tie-breaker within a specificity group (higher wins, default 0).
     * @param status Default 'active' — only active lists resolve.
     * @param taxIncluded Gross (true) or net (false, default) prices.
     * @param validFrom Validity window start.
     * @param validUntil Validity window end.
     * @return [com.revenexx.models.PriceList]
     */
    @JvmOverloads
    suspend fun pricesListsCreate(
        code: String,
        name: String,
        channelId: String? = null,
        contactId: String? = null,
        currency: String? = null,
        description: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        marketId: String? = null,
        metadata: Any? = null,
        organizationId: String? = null,
        priority: Long? = null,
        status: com.revenexx.enums.PriceListStatus? = null,
        taxIncluded: Boolean? = null,
        validFrom: String? = null,
        validUntil: String? = null,
    ): com.revenexx.models.PriceList {
        val apiPath = "/v1/prices/lists"

        val apiParams = mutableMapOf<String, Any?>(
            "channel_id" to channelId,
            "code" to code,
            "contact_id" to contactId,
            "currency" to currency,
            "description" to description,
            "is_default" to isDefault,
            "labels" to labels,
            "market_id" to marketId,
            "metadata" to metadata,
            "name" to name,
            "organization_id" to organizationId,
            "priority" to priority,
            "status" to status,
            "tax_included" to taxIncluded,
            "valid_from" to validFrom,
            "valid_until" to validUntil,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PriceList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PriceList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PriceList::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun pricesListsDefaults(
    ): Any {
        val apiPath = "/v1/prices/lists/defaults"

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
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
     * @param id 
     * @return [Any]
     */
    suspend fun pricesListsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/prices/lists/{id}"
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
     * @return [com.revenexx.models.PriceList]
     */
    suspend fun pricesListsGet(
        id: String,
    ): com.revenexx.models.PriceList {
        val apiPath = "/v1/prices/lists/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PriceList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PriceList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PriceList::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param channelId Scope: only this channel.
     * @param code Unique list code per tenant.
     * @param contactId Scope: only this contact — beats every other scope.
     * @param currency ISO 4217 code (default EUR) — resolution only considers lists matching the requested currency.
     * @param description 
     * @param isDefault Default lists resolve last within their group.
     * @param labels Localised names ({de, en, …}).
     * @param marketId Scope: only this market.
     * @param metadata Free-form metadata.
     * @param name 
     * @param organizationId Scope: only this organization.
     * @param priority Tie-breaker within a specificity group (higher wins, default 0).
     * @param status Default 'active' — only active lists resolve.
     * @param taxIncluded Gross (true) or net (false, default) prices.
     * @param validFrom Validity window start.
     * @param validUntil Validity window end.
     * @return [com.revenexx.models.PriceList]
     */
    @JvmOverloads
    suspend fun pricesListsUpdate(
        id: String,
        channelId: String? = null,
        code: String? = null,
        contactId: String? = null,
        currency: String? = null,
        description: String? = null,
        isDefault: Boolean? = null,
        labels: Any? = null,
        marketId: String? = null,
        metadata: Any? = null,
        name: String? = null,
        organizationId: String? = null,
        priority: Long? = null,
        status: com.revenexx.enums.PriceListStatus? = null,
        taxIncluded: Boolean? = null,
        validFrom: String? = null,
        validUntil: String? = null,
    ): com.revenexx.models.PriceList {
        val apiPath = "/v1/prices/lists/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "channel_id" to channelId,
            "code" to code,
            "contact_id" to contactId,
            "currency" to currency,
            "description" to description,
            "is_default" to isDefault,
            "labels" to labels,
            "market_id" to marketId,
            "metadata" to metadata,
            "name" to name,
            "organization_id" to organizationId,
            "priority" to priority,
            "status" to status,
            "tax_included" to taxIncluded,
            "valid_from" to validFrom,
            "valid_until" to validUntil,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PriceList = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PriceList.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PriceList::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param listId 
     * @return [Any]
     */
    suspend fun pricesEntriesList(
        listId: String,
    ): Any {
        val apiPath = "/v1/prices/lists/{list_id}/entries"
            .replace("{listId}", listId)

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
     * @param listId 
     * @param metadata Free-form metadata.
     * @param priceType Default 'standard'; 'on_request' is the explicit no-price marker — it stops resolution and answers "price on request".
     * @param productId Priced product.
     * @param quantityMin Tier threshold (Staffelpreis): this price applies from this quantity (default 1).
     * @param sku Priced SKU (alternative to product_id).
     * @param unit 
     * @param unitPrice Per-unit price (default 0).
     * @param validFrom Per-entry validity start (promo prices).
     * @param validUntil Per-entry validity end.
     * @return [com.revenexx.models.PriceEntry]
     */
    @JvmOverloads
    suspend fun pricesEntriesCreate(
        listId: String,
        metadata: Any? = null,
        priceType: com.revenexx.enums.PriceEntryType? = null,
        productId: String? = null,
        quantityMin: Double? = null,
        sku: String? = null,
        unit: String? = null,
        unitPrice: Double? = null,
        validFrom: String? = null,
        validUntil: String? = null,
    ): com.revenexx.models.PriceEntry {
        val apiPath = "/v1/prices/lists/{list_id}/entries"
            .replace("{listId}", listId)

        val apiParams = mutableMapOf<String, Any?>(
            "metadata" to metadata,
            "price_type" to priceType,
            "product_id" to productId,
            "quantity_min" to quantityMin,
            "sku" to sku,
            "unit" to unit,
            "unit_price" to unitPrice,
            "valid_from" to validFrom,
            "valid_until" to validUntil,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PriceEntry = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PriceEntry.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PriceEntry::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param listId 
     * @param entries The complete new entry set (set semantics).
     * @return [Any]
     */
    suspend fun pricesEntriesReplace(
        listId: String,
        entries: List<com.revenexx.models.PriceEntryReplaceItem>,
    ): Any {
        val apiPath = "/v1/prices/lists/{list_id}/entries"
            .replace("{listId}", listId)

        val apiParams = mutableMapOf<String, Any?>(
            "entries" to entries,
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
     * @param listId 
     * @param entries The complete new entry set (set semantics).
     * @return [Any]
     */
    suspend fun pricesEntriesBulk(
        listId: String,
        entries: List<com.revenexx.models.PriceEntryReplaceItem>,
    ): Any {
        val apiPath = "/v1/prices/lists/{list_id}/entries/bulk"
            .replace("{listId}", listId)

        val apiParams = mutableMapOf<String, Any?>(
            "entries" to entries,
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
     * @param listId 
     * @param id 
     * @return [Any]
     */
    suspend fun pricesEntriesDelete(
        listId: String,
        id: String,
    ): Any {
        val apiPath = "/v1/prices/lists/{list_id}/entries/{id}"
            .replace("{listId}", listId)
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
     * @param listId 
     * @param id 
     * @return [com.revenexx.models.PriceEntry]
     */
    suspend fun pricesEntriesGet(
        listId: String,
        id: String,
    ): com.revenexx.models.PriceEntry {
        val apiPath = "/v1/prices/lists/{list_id}/entries/{id}"
            .replace("{listId}", listId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.PriceEntry = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PriceEntry.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PriceEntry::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param listId 
     * @param id 
     * @param metadata Free-form metadata.
     * @param priceType Default 'standard'; 'on_request' is the explicit no-price marker — it stops resolution and answers "price on request".
     * @param productId Priced product.
     * @param quantityMin Tier threshold (Staffelpreis): this price applies from this quantity (default 1).
     * @param sku Priced SKU (alternative to product_id).
     * @param unit 
     * @param unitPrice Per-unit price (default 0).
     * @param validFrom Per-entry validity start (promo prices).
     * @param validUntil Per-entry validity end.
     * @return [com.revenexx.models.PriceEntry]
     */
    @JvmOverloads
    suspend fun pricesEntriesUpdate(
        listId: String,
        id: String,
        metadata: Any? = null,
        priceType: com.revenexx.enums.PriceEntryType? = null,
        productId: String? = null,
        quantityMin: Double? = null,
        sku: String? = null,
        unit: String? = null,
        unitPrice: Double? = null,
        validFrom: String? = null,
        validUntil: String? = null,
    ): com.revenexx.models.PriceEntry {
        val apiPath = "/v1/prices/lists/{list_id}/entries/{id}"
            .replace("{listId}", listId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "metadata" to metadata,
            "price_type" to priceType,
            "product_id" to productId,
            "quantity_min" to quantityMin,
            "sku" to sku,
            "unit" to unit,
            "unit_price" to unitPrice,
            "valid_from" to validFrom,
            "valid_until" to validUntil,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.PriceEntry = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.PriceEntry.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.PriceEntry::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param items Items to price (at most 200 per call).
     * @param at Point in time for validity windows (ISO 8601 timestamp, default now).
     * @param channelId Buyer context: channel.
     * @param contactId Buyer context: contact — most specific scope.
     * @param currency ISO 4217 code (default EUR) — only lists in this currency resolve.
     * @param marketId Buyer context: market.
     * @param organizationId Buyer context: organization.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun pricesResolve(
        items: List<com.revenexx.models.PriceResolveItem>,
        at: String? = null,
        channelId: String? = null,
        contactId: String? = null,
        currency: String? = null,
        marketId: String? = null,
        organizationId: String? = null,
    ): Any {
        val apiPath = "/v1/prices/resolve"

        val apiParams = mutableMapOf<String, Any?>(
            "at" to at,
            "channel_id" to channelId,
            "contact_id" to contactId,
            "currency" to currency,
            "items" to items,
            "market_id" to marketId,
            "organization_id" to organizationId,
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


}