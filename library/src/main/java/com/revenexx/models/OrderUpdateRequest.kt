package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * Narrow modification — only these columns are touchable, and only until the order is acknowledged. Status moves through the action routes.
 */
data class OrderUpdateRequest(
    /**
     * 
     */
    @SerializedName("billing_address")
    var billing_address: Any?,

    /**
     * 
     */
    @SerializedName("buyer")
    var buyer: Any?,

    /**
     * 
     */
    @SerializedName("customer_order_number")
    var customer_order_number: String?,

    /**
     * Free-form metadata.
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("shipping_address")
    var shipping_address: Any?,

    /**
     * Free-form user data.
     */
    @SerializedName("user_data")
    var user_data: Any?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "billing_address" to billing_address as Any,
        "buyer" to buyer as Any,
        "customer_order_number" to customer_order_number as Any,
        "metadata" to metadata as Any,
        "shipping_address" to shipping_address as Any,
        "user_data" to user_data as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = OrderUpdateRequest(
            billing_address = map["billing_address"] as? Any,
            buyer = map["buyer"] as? Any,
            customer_order_number = map["customer_order_number"] as? String,
            metadata = map["metadata"] as? Any,
            shipping_address = map["shipping_address"] as? Any,
            user_data = map["user_data"] as? Any,
        )
    }
}