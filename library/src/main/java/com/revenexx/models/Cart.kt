package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class Cart(
    /**
     * 
     */
    @SerializedName("abandoned_at")
    var abandoned_at: String?,

    /**
     * 
     */
    @SerializedName("channel_id")
    var channel_id: String?,

    /**
     * 
     */
    @SerializedName("contact_id")
    var contact_id: String?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("currency")
    var currency: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("is_current")
    var is_current: Boolean?,

    /**
     * 
     */
    @SerializedName("item_count")
    var item_count: Long?,

    /**
     * 
     */
    @SerializedName("market_id")
    var market_id: String?,

    /**
     * 
     */
    @SerializedName("merged_into_cart_id")
    var merged_into_cart_id: String?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("order_ref")
    var order_ref: String?,

    /**
     * 
     */
    @SerializedName("ordered_at")
    var ordered_at: String?,

    /**
     * 
     */
    @SerializedName("session_key")
    var session_key: String?,

    /**
     * 
     */
    @SerializedName("status")
    var status: String?,

    /**
     * 
     */
    @SerializedName("subtotal")
    var subtotal: Double?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "abandoned_at" to abandoned_at as Any,
        "channel_id" to channel_id as Any,
        "contact_id" to contact_id as Any,
        "created_at" to created_at as Any,
        "currency" to currency as Any,
        "id" to id as Any,
        "is_current" to is_current as Any,
        "item_count" to item_count as Any,
        "market_id" to market_id as Any,
        "merged_into_cart_id" to merged_into_cart_id as Any,
        "metadata" to metadata as Any,
        "name" to name as Any,
        "order_ref" to order_ref as Any,
        "ordered_at" to ordered_at as Any,
        "session_key" to session_key as Any,
        "status" to status as Any,
        "subtotal" to subtotal as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = Cart(
            abandoned_at = map["abandoned_at"] as? String,
            channel_id = map["channel_id"] as? String,
            contact_id = map["contact_id"] as? String,
            created_at = map["created_at"] as? String,
            currency = map["currency"] as? String,
            id = map["id"] as? String,
            is_current = map["is_current"] as? Boolean,
            item_count = (map["item_count"] as? Number)?.toLong(),
            market_id = map["market_id"] as? String,
            merged_into_cart_id = map["merged_into_cart_id"] as? String,
            metadata = map["metadata"] as? Any,
            name = map["name"] as? String,
            order_ref = map["order_ref"] as? String,
            ordered_at = map["ordered_at"] as? String,
            session_key = map["session_key"] as? String,
            status = map["status"] as? String,
            subtotal = (map["subtotal"] as? Number)?.toDouble(),
            updated_at = map["updated_at"] as? String,
        )
    }
}