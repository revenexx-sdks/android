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
class Orders(client: Client) : Service(client) {

    /**
     * 
     *
     * @return [Any]
     */
    suspend fun ordersList(
    ): Any {
        val apiPath = "/v1/orders"

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
     * @return [Any]
     */
    suspend fun ordersNumberRangesList(
    ): Any {
        val apiPath = "/v1/orders/number-ranges"

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
     * @param code Range key drawn by the app ('order', 'delivery', 'return') — unique per tenant.
     * @param channelId 
     * @param counter Current counter value (default 0) — the next number draws counter+step.
     * @param metadata Free-form metadata.
     * @param padding Zero-padding width of the counter (default 6).
     * @param positionStep Position numbering increment for order items (default 10).
     * @param prefix Default ''.
     * @param step Counter increment per drawn number (default 1).
     * @param suffix Default ''.
     * @return [com.revenexx.models.NumberRange]
     */
    @JvmOverloads
    suspend fun ordersNumberRangesCreate(
        code: String,
        channelId: String? = null,
        counter: Long? = null,
        metadata: Any? = null,
        padding: Long? = null,
        positionStep: Long? = null,
        prefix: String? = null,
        step: Long? = null,
        suffix: String? = null,
    ): com.revenexx.models.NumberRange {
        val apiPath = "/v1/orders/number-ranges"

        val apiParams = mutableMapOf<String, Any?>(
            "channel_id" to channelId,
            "code" to code,
            "counter" to counter,
            "metadata" to metadata,
            "padding" to padding,
            "position_step" to positionStep,
            "prefix" to prefix,
            "step" to step,
            "suffix" to suffix,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.NumberRange = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.NumberRange.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.NumberRange::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @return [Any]
     */
    suspend fun ordersNumberRangesDefaults(
    ): Any {
        val apiPath = "/v1/orders/number-ranges/defaults"

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
    suspend fun ordersNumberRangesDelete(
        id: String,
    ): Any {
        val apiPath = "/v1/orders/number-ranges/{id}"
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
     * @return [com.revenexx.models.NumberRange]
     */
    suspend fun ordersNumberRangesGet(
        id: String,
    ): com.revenexx.models.NumberRange {
        val apiPath = "/v1/orders/number-ranges/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.NumberRange = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.NumberRange.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.NumberRange::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param channelId 
     * @param code Range key drawn by the app ('order', 'delivery', 'return') — unique per tenant.
     * @param counter Current counter value (default 0) — the next number draws counter+step.
     * @param metadata Free-form metadata.
     * @param padding Zero-padding width of the counter (default 6).
     * @param positionStep Position numbering increment for order items (default 10).
     * @param prefix Default ''.
     * @param step Counter increment per drawn number (default 1).
     * @param suffix Default ''.
     * @return [com.revenexx.models.NumberRange]
     */
    @JvmOverloads
    suspend fun ordersNumberRangesUpdate(
        id: String,
        channelId: String? = null,
        code: String? = null,
        counter: Long? = null,
        metadata: Any? = null,
        padding: Long? = null,
        positionStep: Long? = null,
        prefix: String? = null,
        step: Long? = null,
        suffix: String? = null,
    ): com.revenexx.models.NumberRange {
        val apiPath = "/v1/orders/number-ranges/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "channel_id" to channelId,
            "code" to code,
            "counter" to counter,
            "metadata" to metadata,
            "padding" to padding,
            "position_step" to positionStep,
            "prefix" to prefix,
            "step" to step,
            "suffix" to suffix,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.NumberRange = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.NumberRange.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.NumberRange::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param items The order positions (at most 500).
     * @param billingAddress Frozen billing address.
     * @param buyer Frozen buyer snapshot (name, email, …).
     * @param cartId Source cart (the carts.order hand-over).
     * @param channelId 
     * @param contactId Ordering customer contact.
     * @param currency ISO 4217 code (default EUR).
     * @param customerOrderNumber The buyer's own order/PO number.
     * @param grandTotal Override — computed as subtotal + shipping + tax when omitted.
     * @param marketId 
     * @param metadata Free-form metadata.
     * @param organizationId B2B organization.
     * @param payment Frozen payment snapshot — a known 'payment.status' seeds payment_status (otherwise 'open').
     * @param shipping Frozen shipping snapshot — 'shipping.price' seeds shipping_total.
     * @param shippingAddress Frozen shipping address.
     * @param shippingTotal Shipping total (fallback when 'shipping.price' is absent).
     * @param userData Free-form user data.
     * @return [com.revenexx.models.OrderDetail]
     */
    @JvmOverloads
    suspend fun ordersPlace(
        items: List<com.revenexx.models.OrderItemCreateRequest>,
        billingAddress: Any? = null,
        buyer: Any? = null,
        cartId: String? = null,
        channelId: String? = null,
        contactId: String? = null,
        currency: String? = null,
        customerOrderNumber: String? = null,
        grandTotal: Double? = null,
        marketId: String? = null,
        metadata: Any? = null,
        organizationId: String? = null,
        payment: Any? = null,
        shipping: Any? = null,
        shippingAddress: Any? = null,
        shippingTotal: Double? = null,
        userData: Any? = null,
    ): com.revenexx.models.OrderDetail {
        val apiPath = "/v1/orders/place"

        val apiParams = mutableMapOf<String, Any?>(
            "billing_address" to billingAddress,
            "buyer" to buyer,
            "cart_id" to cartId,
            "channel_id" to channelId,
            "contact_id" to contactId,
            "currency" to currency,
            "customer_order_number" to customerOrderNumber,
            "grand_total" to grandTotal,
            "items" to items,
            "market_id" to marketId,
            "metadata" to metadata,
            "organization_id" to organizationId,
            "payment" to payment,
            "shipping" to shipping,
            "shipping_address" to shippingAddress,
            "shipping_total" to shippingTotal,
            "user_data" to userData,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.OrderDetail = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderDetail.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderDetail::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [com.revenexx.models.OrderDetail]
     */
    suspend fun ordersGet(
        id: String,
    ): com.revenexx.models.OrderDetail {
        val apiPath = "/v1/orders/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
        )
        val apiHeaders = mutableMapOf<String, String>(
        )
        val converter: (Any) -> com.revenexx.models.OrderDetail = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderDetail.from(map = it as Map<String, Any>)
        }
        return client.call(
            "GET",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderDetail::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param billingAddress 
     * @param buyer 
     * @param customerOrderNumber 
     * @param metadata Free-form metadata.
     * @param shippingAddress 
     * @param userData Free-form user data.
     * @return [com.revenexx.models.Order]
     */
    @JvmOverloads
    suspend fun ordersUpdate(
        id: String,
        billingAddress: Any? = null,
        buyer: Any? = null,
        customerOrderNumber: String? = null,
        metadata: Any? = null,
        shippingAddress: Any? = null,
        userData: Any? = null,
    ): com.revenexx.models.Order {
        val apiPath = "/v1/orders/{id}"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "billing_address" to billingAddress,
            "buyer" to buyer,
            "customer_order_number" to customerOrderNumber,
            "metadata" to metadata,
            "shipping_address" to shippingAddress,
            "user_data" to userData,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Order = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Order.from(map = it as Map<String, Any>)
        }
        return client.call(
            "PUT",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Order::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param externalRef The fulfilling system's order reference (e.g. the ERP order number).
     * @return [com.revenexx.models.Order]
     */
    @JvmOverloads
    suspend fun ordersAcknowledge(
        id: String,
        externalRef: String? = null,
    ): com.revenexx.models.Order {
        val apiPath = "/v1/orders/{id}/acknowledge"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "external_ref" to externalRef,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Order = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Order.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Order::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param cancelledBy Acting user/system.
     * @param reason 
     * @return [com.revenexx.models.Order]
     */
    @JvmOverloads
    suspend fun ordersCancel(
        id: String,
        cancelledBy: String? = null,
        reason: String? = null,
    ): com.revenexx.models.Order {
        val apiPath = "/v1/orders/{id}/cancel"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "cancelled_by" to cancelledBy,
            "reason" to reason,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Order = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Order.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Order::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun ordersCommentsList(
        id: String,
    ): Any {
        val apiPath = "/v1/orders/{id}/comments"
            .replace("{id}", id)

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
     * @param body 
     * @param author 
     * @param visibility Default 'internal'.
     * @return [com.revenexx.models.OrderComment]
     */
    @JvmOverloads
    suspend fun ordersCommentsCreate(
        id: String,
        body: String,
        author: String? = null,
        visibility: com.revenexx.enums.OrderCommentVisibility? = null,
    ): com.revenexx.models.OrderComment {
        val apiPath = "/v1/orders/{id}/comments"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "author" to author,
            "body" to body,
            "visibility" to visibility,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.OrderComment = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderComment.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderComment::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @return [Any]
     */
    suspend fun ordersEventsList(
        id: String,
    ): Any {
        val apiPath = "/v1/orders/{id}/events"
            .replace("{id}", id)

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
     * @param reason Why the order is blocked (shown on the shipping guard).
     * @return [com.revenexx.models.Order]
     */
    @JvmOverloads
    suspend fun ordersHold(
        id: String,
        reason: String? = null,
    ): com.revenexx.models.Order {
        val apiPath = "/v1/orders/{id}/hold"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "reason" to reason,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Order = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Order.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Order::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param positions 
     * @param cancelledBy Acting user/system.
     * @param reason 
     * @return [com.revenexx.models.Order]
     */
    @JvmOverloads
    suspend fun ordersItemsCancel(
        id: String,
        positions: List<com.revenexx.models.OrderCancelPosition>,
        cancelledBy: String? = null,
        reason: String? = null,
    ): com.revenexx.models.Order {
        val apiPath = "/v1/orders/{id}/items/cancel"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "cancelled_by" to cancelledBy,
            "positions" to positions,
            "reason" to reason,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Order = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Order.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Order::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param status The new payment dimension value.
     * @param paymentId Reference into the payment system — merged into the order's payment snapshot.
     * @return [com.revenexx.models.Order]
     */
    @JvmOverloads
    suspend fun ordersPaymentStatusUpdate(
        id: String,
        status: com.revenexx.enums.OrderPaymentStatus,
        paymentId: String? = null,
    ): com.revenexx.models.Order {
        val apiPath = "/v1/orders/{id}/payment-status"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "payment_id" to paymentId,
            "status" to status,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Order = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Order.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Order::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param positions 
     * @param metadata Free-form metadata.
     * @param reason 
     * @return [com.revenexx.models.OrderReturn]
     */
    @JvmOverloads
    suspend fun ordersReturn(
        id: String,
        positions: List<com.revenexx.models.OrderReturnPosition>,
        metadata: Any? = null,
        reason: String? = null,
    ): com.revenexx.models.OrderReturn {
        val apiPath = "/v1/orders/{id}/return"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "metadata" to metadata,
            "positions" to positions,
            "reason" to reason,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.OrderReturn = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderReturn.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderReturn::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param rid 
     * @param resolution How the return was settled (refund, replacement, …).
     * @return [com.revenexx.models.OrderReturn]
     */
    @JvmOverloads
    suspend fun ordersReturnsComplete(
        id: String,
        rid: String,
        resolution: String? = null,
    ): com.revenexx.models.OrderReturn {
        val apiPath = "/v1/orders/{id}/returns/{rid}/complete"
            .replace("{id}", id)
            .replace("{rid}", rid)

        val apiParams = mutableMapOf<String, Any?>(
            "resolution" to resolution,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.OrderReturn = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderReturn.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderReturn::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param rid 
     * @param data Request body
     * @return [com.revenexx.models.OrderReturn]
     */
    suspend fun ordersReturnsReceive(
        id: String,
        rid: String,
        data: Any,
    ): com.revenexx.models.OrderReturn {
        val apiPath = "/v1/orders/{id}/returns/{rid}/receive"
            .replace("{id}", id)
            .replace("{rid}", rid)

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.OrderReturn = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderReturn.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderReturn::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param rid 
     * @param reason Fallback for 'resolution'.
     * @param resolution Why the return was rejected.
     * @return [com.revenexx.models.OrderReturn]
     */
    @JvmOverloads
    suspend fun ordersReturnsReject(
        id: String,
        rid: String,
        reason: String? = null,
        resolution: String? = null,
    ): com.revenexx.models.OrderReturn {
        val apiPath = "/v1/orders/{id}/returns/{rid}/reject"
            .replace("{id}", id)
            .replace("{rid}", rid)

        val apiParams = mutableMapOf<String, Any?>(
            "reason" to reason,
            "resolution" to resolution,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.OrderReturn = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.OrderReturn.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.OrderReturn::class.java,
            converter,
        )
    }


    /**
     * 
     *
     * @param id 
     * @param carrier 
     * @param metadata Free-form metadata.
     * @param number Delivery note number — drawn from the 'delivery' range when omitted.
     * @param positions Omitted = every position with open quantity, in full.
     * @param shippedAt Defaults to now.
     * @param trackingCode 
     * @param trackingUrl 
     * @return [Any]
     */
    @JvmOverloads
    suspend fun ordersShip(
        id: String,
        carrier: String? = null,
        metadata: Any? = null,
        number: String? = null,
        positions: List<com.revenexx.models.OrderShipmentPosition>? = null,
        shippedAt: String? = null,
        trackingCode: String? = null,
        trackingUrl: String? = null,
    ): Any {
        val apiPath = "/v1/orders/{id}/ship"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "carrier" to carrier,
            "metadata" to metadata,
            "number" to number,
            "positions" to positions,
            "shipped_at" to shippedAt,
            "tracking_code" to trackingCode,
            "tracking_url" to trackingUrl,
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
     * @param data Request body
     * @return [com.revenexx.models.Order]
     */
    suspend fun ordersUnhold(
        id: String,
        data: Any,
    ): com.revenexx.models.Order {
        val apiPath = "/v1/orders/{id}/unhold"
            .replace("{id}", id)

        val apiParams = mutableMapOf<String, Any?>(
            "data" to data,
        )
        val apiHeaders = mutableMapOf<String, String>(
            "content-type" to "application/json",
        )
        val converter: (Any) -> com.revenexx.models.Order = {
            @Suppress("UNCHECKED_CAST")
            com.revenexx.models.Order.from(map = it as Map<String, Any>)
        }
        return client.call(
            "POST",
            apiPath,
            apiHeaders,
            apiParams,
            responseType = com.revenexx.models.Order::class.java,
            converter,
        )
    }


}