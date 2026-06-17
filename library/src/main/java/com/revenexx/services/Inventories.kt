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
class Inventories(client: Client) : Service(client) {

    /**
     * 
     *
     * @param items The corrections — quantities are SIGNED deltas (at most 200).
     * @param reason Mandatory audit reason — every adjustment is a ledger row.
     * @param locationCode Adjusted location (default 'main').
     * @return [Any]
     */
    @JvmOverloads
    suspend fun inventoriesAdjust(
        items: List<com.revenexx.models.InventoryAdjustItem>,
        reason: String,
        locationCode: String? = null,
    ): Any {
        val apiPath = "/v1/inventories/adjust"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
            "reason" to reason,
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
     * @param items The items to check (batch, at most 200).
     * @param locationCode Restrict the check to one location (default: all enabled locations).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun inventoriesAvailability(
        items: List<com.revenexx.models.InventoryAvailabilityItem>,
        locationCode: String? = null,
    ): Any {
        val apiPath = "/v1/inventories/availability"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
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
     * @param orderRef The order whose active reservations are committed (shipment).
     * @return [Any]
     */
    suspend fun inventoriesCommit(
        orderRef: String,
    ): Any {
        val apiPath = "/v1/inventories/commit"

        val apiParams = mutableMapOf<String, Any?>(
            "order_ref" to orderRef,
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
    suspend fun inventoriesLocationsList(
    ): Any {
        val apiPath = "/v1/inventories/locations"

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
     * @param code Unique location code (per tenant).
     * @param name 
     * @param address 
     * @param enabled Disabled locations are skipped by availability and reserve (default true).
     * @param labels Localised display names ({de, en, …}).
     * @param metadata Free-form metadata.
     * @param priority Sourcing order — lower wins (default 0).
     * @param type Default 'warehouse'.
     * @return [com.revenexx.models.Location]
     */
    @JvmOverloads
    suspend fun inventoriesLocationsCreate(
        code: String,
        name: String,
        address: Any? = null,
        enabled: Boolean? = null,
        labels: Any? = null,
        metadata: Any? = null,
        priority: Long? = null,
        type: com.revenexx.enums.LocationType? = null,
    ): com.revenexx.models.Location {
        val apiPath = "/v1/inventories/locations"

        val apiParams = mutableMapOf<String, Any?>(
            "address" to address,
            "code" to code,
            "enabled" to enabled,
            "labels" to labels,
            "metadata" to metadata,
            "name" to name,
            "priority" to priority,
            "type" to type,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Location = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Location.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Location::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun inventoriesLocationsDefaults(
    ): Any {
        val apiPath = "/v1/inventories/locations/defaults"

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
    suspend fun inventoriesLocationsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/inventories/locations/{id}"
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
     * @return [com.revenexx.models.Location]
     */
    suspend fun inventoriesLocationsGet(
        id: String,
    ): com.revenexx.models.Location {
        val apiPath = "/v1/inventories/locations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Location = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Location.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Location::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param address 
     * @param code Unique location code (per tenant).
     * @param enabled Disabled locations are skipped by availability and reserve (default true).
     * @param labels Localised display names ({de, en, …}).
     * @param metadata Free-form metadata.
     * @param name 
     * @param priority Sourcing order — lower wins (default 0).
     * @param type Default 'warehouse'.
     * @return [com.revenexx.models.Location]
     */
    @JvmOverloads
    suspend fun inventoriesLocationsUpdate(
        id: String,
        address: Any? = null,
        code: String? = null,
        enabled: Boolean? = null,
        labels: Any? = null,
        metadata: Any? = null,
        name: String? = null,
        priority: Long? = null,
        type: com.revenexx.enums.LocationType? = null,
    ): com.revenexx.models.Location {
        val apiPath = "/v1/inventories/locations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "address" to address,
            "code" to code,
            "enabled" to enabled,
            "labels" to labels,
            "metadata" to metadata,
            "name" to name,
            "priority" to priority,
            "type" to type,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Location = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Location.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Location::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun inventoriesMovementsList(
    ): Any {
        val apiPath = "/v1/inventories/movements"

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
     * @param id 
     * @return [com.revenexx.models.StockMovement]
     */
    suspend fun inventoriesMovementsGet(
        id: String,
    ): com.revenexx.models.StockMovement {
        val apiPath = "/v1/inventories/movements/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.StockMovement = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.StockMovement.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.StockMovement::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param items The inbound items (at most 200).
     * @param locationCode Receiving location (default 'main').
     * @param reason Ledger note (e.g. delivery note number).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun inventoriesReceive(
        items: List<com.revenexx.models.InventoryStockItem>,
        locationCode: String? = null,
        reason: String? = null,
    ): Any {
        val apiPath = "/v1/inventories/receive"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
            "reason" to reason,
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
     * @param orderRef The order whose active reservations are released.
     * @return [Any]
     */
    suspend fun inventoriesRelease(
        orderRef: String,
    ): Any {
        val apiPath = "/v1/inventories/release"

        val apiParams = mutableMapOf<String, Any?>(
            "order_ref" to orderRef,
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
    suspend fun inventoriesReservationsList(
    ): Any {
        val apiPath = "/v1/inventories/reservations"

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
     * @param id 
     * @return [com.revenexx.models.Reservation]
     */
    suspend fun inventoriesReservationsGet(
        id: String,
    ): com.revenexx.models.Reservation {
        val apiPath = "/v1/inventories/reservations/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.Reservation = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Reservation.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Reservation::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param items The items to reserve — all-or-nothing (at most 200).
     * @param orderRef The order this reservation belongs to.
     * @param expiresAt Optional reservation expiry.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun inventoriesReserve(
        items: List<com.revenexx.models.InventoryStockItem>,
        orderRef: String,
        expiresAt: String? = null,
    ): Any {
        val apiPath = "/v1/inventories/reserve"

        val apiParams = mutableMapOf<String, Any?>(
            "expires_at" to expiresAt,
            "items" to items,
            "order_ref" to orderRef,
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
     * @param items The returned items (at most 200).
     * @param locationCode Restocking location (default 'main').
     * @param orderRef Originating order (ledger reference).
     * @param reason Ledger note (e.g. return reason).
     * @return [Any]
     */
    @JvmOverloads
    suspend fun inventoriesRestock(
        items: List<com.revenexx.models.InventoryStockItem>,
        locationCode: String? = null,
        orderRef: String? = null,
        reason: String? = null,
    ): Any {
        val apiPath = "/v1/inventories/restock"

        val apiParams = mutableMapOf<String, Any?>(
            "items" to items,
            "location_code" to locationCode,
            "order_ref" to orderRef,
            "reason" to reason,
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
    suspend fun inventoriesStockList(
    ): Any {
        val apiPath = "/v1/inventories/stock"

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
     * @param locationId Owning location.
     * @param metadata Free-form metadata.
     * @param onHand Physical stock (default 0).
     * @param productId Tracked product.
     * @param reorderPoint 
     * @param reserved Reserved stock (default 0) — normally managed by reserve/release/commit.
     * @param sku Tracked SKU (alternative to product_id).
     * @return [com.revenexx.models.StockLevel]
     */
    @JvmOverloads
    suspend fun inventoriesStockCreate(
        locationId: String,
        metadata: Any? = null,
        onHand: Double? = null,
        productId: String? = null,
        reorderPoint: Double? = null,
        reserved: Double? = null,
        sku: String? = null,
    ): com.revenexx.models.StockLevel {
        val apiPath = "/v1/inventories/stock"

        val apiParams = mutableMapOf<String, Any?>(
            "location_id" to locationId,
            "metadata" to metadata,
            "on_hand" to onHand,
            "product_id" to productId,
            "reorder_point" to reorderPoint,
            "reserved" to reserved,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.StockLevel = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.StockLevel.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.StockLevel::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun inventoriesStockDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/inventories/stock/{id}"
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
     * @return [com.revenexx.models.StockLevel]
     */
    suspend fun inventoriesStockGet(
        id: String,
    ): com.revenexx.models.StockLevel {
        val apiPath = "/v1/inventories/stock/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.StockLevel = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.StockLevel.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.StockLevel::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param locationId Owning location.
     * @param metadata Free-form metadata.
     * @param onHand Physical stock (default 0).
     * @param productId Tracked product.
     * @param reorderPoint 
     * @param reserved Reserved stock (default 0) — normally managed by reserve/release/commit.
     * @param sku Tracked SKU (alternative to product_id).
     * @return [com.revenexx.models.StockLevel]
     */
    @JvmOverloads
    suspend fun inventoriesStockUpdate(
        id: String,
        locationId: String? = null,
        metadata: Any? = null,
        onHand: Double? = null,
        productId: String? = null,
        reorderPoint: Double? = null,
        reserved: Double? = null,
        sku: String? = null,
    ): com.revenexx.models.StockLevel {
        val apiPath = "/v1/inventories/stock/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "location_id" to locationId,
            "metadata" to metadata,
            "on_hand" to onHand,
            "product_id" to productId,
            "reorder_point" to reorderPoint,
            "reserved" to reserved,
            "sku" to sku,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.StockLevel = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.StockLevel.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.StockLevel::class.java,
            converter,
        )
    }


}