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
class Shipping(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun shippingMethodsList(
    ): Any {
        val apiPath = "/v1/shipping/methods"

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
     * @param code Stable method code, unique per tenant (e.g. standard, express).
     * @param name Display name.
     * @param carrier Carrier anchor for the upcoming carrier connect (dynamic rates, tracking links).
     * @param countries Allowed ISO 3166-1 alpha-2 codes; null or empty = worldwide.
     * @param currency ISO 4217 code (default EUR).
     * @param description 
     * @param enabled Only enabled methods appear in rate responses (default false).
     * @param etaDaysMax Delivery-time estimate for the checkout (days, upper bound).
     * @param etaDaysMin Delivery-time estimate for the checkout (days, lower bound).
     * @param freeAbove Free shipping at or above this order value — wins over every pricing model.
     * @param labels Localized display names keyed by locale (e.g. {de, en}).
     * @param matrixAttribute Attribute name for matrix_basis 'attribute'.
     * @param matrixBasis The measure a matrix method prices over; 'attribute' reads matrix_attribute from the rate request.
     * @param metadata Free-form metadata.
     * @param position Sort order in the checkout (default 0).
     * @param price The fixed price (default 0) — ignored for 'free' and 'matrix'.
     * @param pricingType Pricing model (default 'fixed'): one price, no price, or tiered over a measure.
     * @return [com.revenexx.models.ShippingMethod]
     */
    @JvmOverloads
    suspend fun shippingMethodsCreate(
        code: String,
        name: String,
        carrier: String? = null,
        countries: List<String>? = null,
        currency: String? = null,
        description: String? = null,
        enabled: Boolean? = null,
        etaDaysMax: Long? = null,
        etaDaysMin: Long? = null,
        freeAbove: Double? = null,
        labels: Any? = null,
        matrixAttribute: String? = null,
        matrixBasis: com.revenexx.enums.ShippingMethodMatrixBasis? = null,
        metadata: Any? = null,
        position: Long? = null,
        price: Double? = null,
        pricingType: com.revenexx.enums.ShippingMethodPricingType? = null,
    ): com.revenexx.models.ShippingMethod {
        val apiPath = "/v1/shipping/methods"

        val apiParams = mutableMapOf<String, Any?>(
            "carrier" to carrier,
            "code" to code,
            "countries" to countries,
            "currency" to currency,
            "description" to description,
            "enabled" to enabled,
            "eta_days_max" to etaDaysMax,
            "eta_days_min" to etaDaysMin,
            "free_above" to freeAbove,
            "labels" to labels,
            "matrix_attribute" to matrixAttribute,
            "matrix_basis" to matrixBasis,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "price" to price,
            "pricing_type" to pricingType,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ShippingMethod = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ShippingMethod.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ShippingMethod::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun shippingMethodsDefaults(
    ): Any {
        val apiPath = "/v1/shipping/methods/defaults"

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
    suspend fun shippingMethodsDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/shipping/methods/{id}"
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
     * @return [com.revenexx.models.ShippingMethod]
     */
    suspend fun shippingMethodsGet(
        id: String,
    ): com.revenexx.models.ShippingMethod {
        val apiPath = "/v1/shipping/methods/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ShippingMethod = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ShippingMethod.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ShippingMethod::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param carrier Carrier anchor for the upcoming carrier connect (dynamic rates, tracking links).
     * @param code Stable method code, unique per tenant (e.g. standard, express).
     * @param countries Allowed ISO 3166-1 alpha-2 codes; null or empty = worldwide.
     * @param currency ISO 4217 code (default EUR).
     * @param description 
     * @param enabled Only enabled methods appear in rate responses (default false).
     * @param etaDaysMax Delivery-time estimate for the checkout (days, upper bound).
     * @param etaDaysMin Delivery-time estimate for the checkout (days, lower bound).
     * @param freeAbove Free shipping at or above this order value — wins over every pricing model.
     * @param labels Localized display names keyed by locale (e.g. {de, en}).
     * @param matrixAttribute Attribute name for matrix_basis 'attribute'.
     * @param matrixBasis The measure a matrix method prices over; 'attribute' reads matrix_attribute from the rate request.
     * @param metadata Free-form metadata.
     * @param name Display name.
     * @param position Sort order in the checkout (default 0).
     * @param price The fixed price (default 0) — ignored for 'free' and 'matrix'.
     * @param pricingType Pricing model (default 'fixed'): one price, no price, or tiered over a measure.
     * @return [com.revenexx.models.ShippingMethod]
     */
    @JvmOverloads
    suspend fun shippingMethodsUpdate(
        id: String,
        carrier: String? = null,
        code: String? = null,
        countries: List<String>? = null,
        currency: String? = null,
        description: String? = null,
        enabled: Boolean? = null,
        etaDaysMax: Long? = null,
        etaDaysMin: Long? = null,
        freeAbove: Double? = null,
        labels: Any? = null,
        matrixAttribute: String? = null,
        matrixBasis: com.revenexx.enums.ShippingMethodMatrixBasis? = null,
        metadata: Any? = null,
        name: String? = null,
        position: Long? = null,
        price: Double? = null,
        pricingType: com.revenexx.enums.ShippingMethodPricingType? = null,
    ): com.revenexx.models.ShippingMethod {
        val apiPath = "/v1/shipping/methods/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "carrier" to carrier,
            "code" to code,
            "countries" to countries,
            "currency" to currency,
            "description" to description,
            "enabled" to enabled,
            "eta_days_max" to etaDaysMax,
            "eta_days_min" to etaDaysMin,
            "free_above" to freeAbove,
            "labels" to labels,
            "matrix_attribute" to matrixAttribute,
            "matrix_basis" to matrixBasis,
            "metadata" to metadata,
            "name" to name,
            "position" to position,
            "price" to price,
            "pricing_type" to pricingType,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ShippingMethod = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ShippingMethod.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ShippingMethod::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param methodId 
     * @return [Any]
     */
    suspend fun shippingTiersList(
        methodId: String,
    ): Any {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers"
            .replace("{methodId}", methodId)

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
     * @param methodId 
     * @param fromValue Tier threshold (default 0) — the tier with the highest from_value at or below the measured value wins.
     * @param position Sort order (default 0; bulk replace derives it from the array index).
     * @param price Price of this tier (default 0).
     * @return [com.revenexx.models.ShippingRateTier]
     */
    @JvmOverloads
    suspend fun shippingTiersCreate(
        methodId: String,
        fromValue: Double? = null,
        position: Long? = null,
        price: Double? = null,
    ): com.revenexx.models.ShippingRateTier {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers"
            .replace("{methodId}", methodId)

        val apiParams = mutableMapOf<String, Any?>(
            "from_value" to fromValue,
            "position" to position,
            "price" to price,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ShippingRateTier = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ShippingRateTier.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ShippingRateTier::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param methodId 
     * @param tiers The complete new tier set (set semantics) — positions are derived from the array order.
     * @return [Any]
     */
    suspend fun shippingTiersReplace(
        methodId: String,
        tiers: List<com.revenexx.models.ShippingRateTierReplaceItem>,
    ): Any {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers"
            .replace("{methodId}", methodId)

        val apiParams = mutableMapOf<String, Any?>(
            "tiers" to tiers,
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
     * @param methodId 
     * @param id 
     * @return [Any]
     */
    suspend fun shippingTiersDelete(
        methodId: String,
        id: String,
    ): Any {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers/{id}"
            .replace("{methodId}", methodId)
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
     * @param methodId 
     * @param id 
     * @return [com.revenexx.models.ShippingRateTier]
     */
    suspend fun shippingTiersGet(
        methodId: String,
        id: String,
    ): com.revenexx.models.ShippingRateTier {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers/{id}"
            .replace("{methodId}", methodId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.ShippingRateTier = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ShippingRateTier.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ShippingRateTier::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param methodId 
     * @param id 
     * @param fromValue Tier threshold (default 0) — the tier with the highest from_value at or below the measured value wins.
     * @param position Sort order (default 0; bulk replace derives it from the array index).
     * @param price Price of this tier (default 0).
     * @return [com.revenexx.models.ShippingRateTier]
     */
    @JvmOverloads
    suspend fun shippingTiersUpdate(
        methodId: String,
        id: String,
        fromValue: Double? = null,
        position: Long? = null,
        price: Double? = null,
    ): com.revenexx.models.ShippingRateTier {
        val apiPath = "/v1/shipping/methods/{method_id}/tiers/{id}"
            .replace("{methodId}", methodId)
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "from_value" to fromValue,
            "position" to position,
            "price" to price,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.ShippingRateTier = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.ShippingRateTier.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.ShippingRateTier::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param attributes Measure values for attribute matrices, keyed by attribute name.
     * @param country Destination ISO 3166-1 alpha-2 code — checked against method country restrictions.
     * @param currency Echoed into the rates (default 'EUR').
     * @param marketId Buyer market for tax resolution (else inferred from country, else first market).
     * @param orderValue Order value (default 0) — drives free-above thresholds and order_value matrices.
     * @param quantity Total quantity — measure for quantity matrices.
     * @param weight Total weight — measure for weight matrices.
     * @return [Any]
     */
    @JvmOverloads
    suspend fun shippingRates(
        attributes: Any? = null,
        country: String? = null,
        currency: String? = null,
        marketId: String? = null,
        orderValue: Double? = null,
        quantity: Double? = null,
        weight: Double? = null,
    ): Any {
        val apiPath = "/v1/shipping/rates"

        val apiParams = mutableMapOf<String, Any?>(
            "attributes" to attributes,
            "country" to country,
            "currency" to currency,
            "market_id" to marketId,
            "order_value" to orderValue,
            "quantity" to quantity,
            "weight" to weight,
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