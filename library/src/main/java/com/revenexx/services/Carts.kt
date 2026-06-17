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
class Carts(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun cartsList(
    ): Any {
        val apiPath = "/v1/carts"

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
     * @param channelId 
     * @param contactId Owning customer contact.
     * @param currency ISO 4217 code (default EUR).
     * @param isCurrent Make this THE current cart of its owner.
     * @param marketId 
     * @param metadata Free-form metadata.
     * @param name Display name (default 'Cart').
     * @param sessionKey Owning guest session.
     * @return [com.revenexx.models.Cart]
     */
    @JvmOverloads
    suspend fun cartsCreate(
        channelId: String? = null,
        contactId: String? = null,
        currency: String? = null,
        isCurrent: Boolean? = null,
        marketId: String? = null,
        metadata: Any? = null,
        name: String? = null,
        sessionKey: String? = null,
    ): com.revenexx.models.Cart {
        val apiPath = "/v1/carts"

        val apiParams = mutableMapOf<String, Any?>(
            "channel_id" to channelId,
            "contact_id" to contactId,
            "currency" to currency,
            "is_current" to isCurrent,
            "market_id" to marketId,
            "metadata" to metadata,
            "name" to name,
            "session_key" to sessionKey,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Cart = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Cart.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Cart::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param contactId Contact taking ownership.
     * @param sessionKey Guest session whose active carts are handed over.
     * @param targetCartId Merge the session carts into this cart instead of adopting them.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun cartsClaim(
        contactId: String,
        sessionKey: String,
        targetCartId: String? = null,
    ): Any {
        val apiPath = "/v1/carts/claim"

        val apiParams = mutableMapOf<String, Any?>(
            "contact_id" to contactId,
            "session_key" to sessionKey,
            "target_cart_id" to targetCartId,
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
     * @param contactId Owner of a newly created cart.
     * @param csv Raw CSV content (alternative to payload for csv profiles).
     * @param name Name for a newly created cart.
     * @param payload The import payload: '{cart, items}' object, or a raw JSON/CSV string in the profile's format.
     * @param profileId Import profile to run; ad-hoc import when omitted.
     * @param sessionKey Guest owner of a newly created cart.
     * @param targetCartId Existing active cart to import into.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun cartsImport(
        contactId: String? = null,
        csv: String? = null,
        name: String? = null,
        payload: Any? = null,
        profileId: String? = null,
        sessionKey: String? = null,
        targetCartId: String? = null,
    ): Any {
        val apiPath = "/v1/carts/import"

        val apiParams = mutableMapOf<String, Any?>(
            "contact_id" to contactId,
            "csv" to csv,
            "name" to name,
            "payload" to payload,
            "profile_id" to profileId,
            "session_key" to sessionKey,
            "target_cart_id" to targetCartId,
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
     * @return [Any]
     */
    suspend fun cartsIoProfilesList(
    ): Any {
        val apiPath = "/v1/carts/io/profiles"

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
     * @param direction 
     * @param name 
     * @param applyMode Default 'insert'.
     * @param entity Default 'carts'.
     * @param format Default 'json'.
     * @param isTemplate 
     * @param mapping Column mapping (Baseline-IO-compatible).
     * @param options 
     * @return [com.revenexx.models.IoProfile]
     */
    @JvmOverloads
    suspend fun cartsIoProfilesCreate(
        direction: com.revenexx.enums.CartIoDirection,
        name: String,
        applyMode: com.revenexx.enums.CartIoApplyMode? = null,
        entity: com.revenexx.enums.CartIoEntity? = null,
        format: com.revenexx.enums.CartIoFormat? = null,
        isTemplate: Boolean? = null,
        mapping: Any? = null,
        options: Any? = null,
    ): com.revenexx.models.IoProfile {
        val apiPath = "/v1/carts/io/profiles"

        val apiParams = mutableMapOf<String, Any?>(
            "apply_mode" to applyMode,
            "direction" to direction,
            "entity" to entity,
            "format" to format,
            "is_template" to isTemplate,
            "mapping" to mapping,
            "name" to name,
            "options" to options,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.IoProfile = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.IoProfile.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.IoProfile::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun cartsIoProfilesDefaults(
    ): Any {
        val apiPath = "/v1/carts/io/profiles/defaults"

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
    suspend fun cartsIoProfilesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/carts/io/profiles/{id}"
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
     * @return [com.revenexx.models.IoProfile]
     */
    suspend fun cartsIoProfilesGet(
        id: String,
    ): com.revenexx.models.IoProfile {
        val apiPath = "/v1/carts/io/profiles/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.IoProfile = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.IoProfile.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.IoProfile::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param applyMode Default 'insert'.
     * @param direction 
     * @param entity Default 'carts'.
     * @param format Default 'json'.
     * @param isTemplate 
     * @param mapping Column mapping (Baseline-IO-compatible).
     * @param name 
     * @param options 
     * @return [com.revenexx.models.IoProfile]
     */
    @JvmOverloads
    suspend fun cartsIoProfilesUpdate(
        id: String,
        applyMode: com.revenexx.enums.CartIoApplyMode? = null,
        direction: com.revenexx.enums.CartIoDirection? = null,
        entity: com.revenexx.enums.CartIoEntity? = null,
        format: com.revenexx.enums.CartIoFormat? = null,
        isTemplate: Boolean? = null,
        mapping: Any? = null,
        name: String? = null,
        options: Any? = null,
    ): com.revenexx.models.IoProfile {
        val apiPath = "/v1/carts/io/profiles/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "apply_mode" to applyMode,
            "direction" to direction,
            "entity" to entity,
            "format" to format,
            "is_template" to isTemplate,
            "mapping" to mapping,
            "name" to name,
            "options" to options,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.IoProfile = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.IoProfile.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.IoProfile::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param sourceCartId Cart whose lines move into the target (becomes status merged).
     * @param targetCartId Receiving cart (must be active).
     * @return [Any]
     */
    suspend fun cartsMerge(
        sourceCartId: String,
        targetCartId: String,
    ): Any {
        val apiPath = "/v1/carts/merge"

        val apiParams = mutableMapOf<String, Any?>(
            "source_cart_id" to sourceCartId,
            "target_cart_id" to targetCartId,
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
     * @param cartId 
     * @return [Any]
     */
    suspend fun cartsItemsList(
        cartId: String,
    ): Any {
        val apiPath = "/v1/carts/{cart_id}/items"
            .replace("{cartId}", cartId)

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
     * @param cartId 
     * @param configuration Free-form configuration — configured lines never merge.
     * @param currency Defaults to the cart's currency.
     * @param metadata Free-form metadata.
     * @param name Falls back to 'sku' when omitted.
     * @param position 
     * @param productId 
     * @param quantity Default 1.
     * @param sku 
     * @param snapshot Loose product snapshot at add-time (price, name, image, …).
     * @param taxRate 
     * @param type Line type (default 'product'). Plain product lines merge by product+price; configurations always stand alone.
     * @param unit 
     * @param unitPrice Per-unit net price — line_total is always derived.
     * @return [com.revenexx.models.CartItem]
     */
    @JvmOverloads
    suspend fun cartsItemsCreate(
        cartId: String,
        configuration: Any? = null,
        currency: String? = null,
        metadata: Any? = null,
        name: String? = null,
        position: Long? = null,
        productId: String? = null,
        quantity: Double? = null,
        sku: String? = null,
        snapshot: Any? = null,
        taxRate: Double? = null,
        type: com.revenexx.enums.CartItemType? = null,
        unit: String? = null,
        unitPrice: Double? = null,
    ): com.revenexx.models.CartItem {
        val apiPath = "/v1/carts/{cart_id}/items"
            .replace("{cartId}", cartId)

        val apiParams = mutableMapOf<String, Any?>(
            "configuration" to configuration,
            "currency" to currency,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "product_id" to productId,
            "quantity" to quantity,
            "sku" to sku,
            "snapshot" to snapshot,
            "tax_rate" to taxRate,
            "type" to type,
            "unit" to unit,
            "unit_price" to unitPrice,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.CartItem = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.CartItem.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.CartItem::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param cartId 
     * @param items The complete new item set (set semantics).
     * @return [Any]
     */
    suspend fun cartsItemsReplace(
        cartId: String,
        items: List<com.revenexx.models.CartItemCreateRequest>,
    ): Any {
        val apiPath = "/v1/carts/{cart_id}/items"
            .replace("{cartId}", cartId)

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
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
     * @param cartId 
     * @param id 
     * @return [Any]
     */
    suspend fun cartsItemsDelete(
        cartId: String,
        id: String,
    ): Any {
        val apiPath = "/v1/carts/{cart_id}/items/{id}"
            .replace("{cartId}", cartId)
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
     * @param cartId 
     * @param id 
     * @return [com.revenexx.models.CartItem]
     */
    suspend fun cartsItemsGet(
        cartId: String,
        id: String,
    ): com.revenexx.models.CartItem {
        val apiPath = "/v1/carts/{cart_id}/items/{id}"
            .replace("{cartId}", cartId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.CartItem = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.CartItem.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.CartItem::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param cartId 
     * @param id 
     * @param configuration Free-form configuration — configured lines never merge.
     * @param currency Defaults to the cart's currency.
     * @param metadata Free-form metadata.
     * @param name Falls back to 'sku' when omitted.
     * @param position 
     * @param productId 
     * @param quantity Default 1.
     * @param sku 
     * @param snapshot Loose product snapshot at add-time (price, name, image, …).
     * @param taxRate 
     * @param type Line type (default 'product'). Plain product lines merge by product+price; configurations always stand alone.
     * @param unit 
     * @param unitPrice Per-unit net price — line_total is always derived.
     * @return [com.revenexx.models.CartItem]
     */
    @JvmOverloads
    suspend fun cartsItemsUpdate(
        cartId: String,
        id: String,
        configuration: Any? = null,
        currency: String? = null,
        metadata: Any? = null,
        name: String? = null,
        position: Long? = null,
        productId: String? = null,
        quantity: Double? = null,
        sku: String? = null,
        snapshot: Any? = null,
        taxRate: Double? = null,
        type: com.revenexx.enums.CartItemType? = null,
        unit: String? = null,
        unitPrice: Double? = null,
    ): com.revenexx.models.CartItem {
        val apiPath = "/v1/carts/{cart_id}/items/{id}"
            .replace("{cartId}", cartId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "configuration" to configuration,
            "currency" to currency,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "product_id" to productId,
            "quantity" to quantity,
            "sku" to sku,
            "snapshot" to snapshot,
            "tax_rate" to taxRate,
            "type" to type,
            "unit" to unit,
            "unit_price" to unitPrice,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.CartItem = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.CartItem.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.CartItem::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun cartsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/carts/{id}"
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
     * @return [com.revenexx.models.Cart]
     */
    suspend fun cartsGet(
        id: String,
    ): com.revenexx.models.Cart {
        val apiPath = "/v1/carts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Cart = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Cart.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Cart::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param channelId 
     * @param currency ISO 4217 code.
     * @param marketId 
     * @param metadata Free-form metadata.
     * @param name 
     * @return [com.revenexx.models.Cart]
     */
    @JvmOverloads
    suspend fun cartsUpdate(
        id: String,
        channelId: String? = null,
        currency: String? = null,
        marketId: String? = null,
        metadata: Any? = null,
        name: String? = null,
    ): com.revenexx.models.Cart {
        val apiPath = "/v1/carts/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "channel_id" to channelId,
            "currency" to currency,
            "market_id" to marketId,
            "metadata" to metadata,
            "name" to name,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Cart = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Cart.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Cart::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Cart]
     */
    suspend fun cartsAbandon(
        id: String,
    ): com.revenexx.models.Cart {
        val apiPath = "/v1/carts/{id}/abandon"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Cart = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Cart.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Cart::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Cart]
     */
    suspend fun cartsActivate(
        id: String,
    ): com.revenexx.models.Cart {
        val apiPath = "/v1/carts/{id}/activate"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Cart = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Cart.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Cart::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param format Ad-hoc export format (only without profile_id).
     * @param profileId Export profile to run; ad-hoc JSON/CSV export when omitted.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun cartsExport(
        id: String,
        format: com.revenexx.enums.CartExportFormat? = null,
        profileId: String? = null,
    ): Any {
        val apiPath = "/v1/carts/{id}/export"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "format" to format,
            "profile_id" to profileId,
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
     * @param id 
     * @param orderRef External order reference from order management.
     * @return [com.revenexx.models.Cart]
     */
    @JvmOverloads
    suspend fun cartsOrder(
        id: String,
        orderRef: String? = null,
    ): com.revenexx.models.Cart {
        val apiPath = "/v1/carts/{id}/order"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "order_ref" to orderRef,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Cart = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Cart.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Cart::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.Cart]
     */
    suspend fun cartsReopen(
        id: String,
    ): com.revenexx.models.Cart {
        val apiPath = "/v1/carts/{id}/reopen"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Cart = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Cart.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Cart::class.java,
            converter,
        )
    }


}