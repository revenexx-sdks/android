package com.revenexx.models

import com.google.gson.annotations.SerializedName
import com.revenexx.extensions.jsonCast

/**
 * 
 */
data class PaymentMethod(
    /**
     * 
     */
    @SerializedName("code")
    var code: String?,

    /**
     * 
     */
    @SerializedName("countries")
    var countries: Any?,

    /**
     * 
     */
    @SerializedName("created_at")
    var created_at: String?,

    /**
     * 
     */
    @SerializedName("description")
    var description: String?,

    /**
     * 
     */
    @SerializedName("enabled")
    var enabled: Boolean?,

    /**
     * 
     */
    @SerializedName("fee_amount")
    var fee_amount: Double?,

    /**
     * 
     */
    @SerializedName("fee_currency")
    var fee_currency: String?,

    /**
     * 
     */
    @SerializedName("fee_type")
    var fee_type: String?,

    /**
     * 
     */
    @SerializedName("id")
    var id: String?,

    /**
     * 
     */
    @SerializedName("kind")
    var kind: String?,

    /**
     * 
     */
    @SerializedName("labels")
    var labels: Any?,

    /**
     * 
     */
    @SerializedName("max_order_value")
    var max_order_value: Double?,

    /**
     * 
     */
    @SerializedName("metadata")
    var metadata: Any?,

    /**
     * 
     */
    @SerializedName("min_order_value")
    var min_order_value: Double?,

    /**
     * 
     */
    @SerializedName("name")
    var name: String?,

    /**
     * 
     */
    @SerializedName("position")
    var position: Long?,

    /**
     * 
     */
    @SerializedName("provider")
    var provider: String?,

    /**
     * 
     */
    @SerializedName("provider_method")
    var provider_method: String?,

    /**
     * 
     */
    @SerializedName("updated_at")
    var updated_at: String?,

) {
    fun toMap(): Map<String, Any> = mapOf(
        "code" to code as Any,
        "countries" to countries as Any,
        "created_at" to created_at as Any,
        "description" to description as Any,
        "enabled" to enabled as Any,
        "fee_amount" to fee_amount as Any,
        "fee_currency" to fee_currency as Any,
        "fee_type" to fee_type as Any,
        "id" to id as Any,
        "kind" to kind as Any,
        "labels" to labels as Any,
        "max_order_value" to max_order_value as Any,
        "metadata" to metadata as Any,
        "min_order_value" to min_order_value as Any,
        "name" to name as Any,
        "position" to position as Any,
        "provider" to provider as Any,
        "provider_method" to provider_method as Any,
        "updated_at" to updated_at as Any,
    )

    companion object {

        @Suppress("UNCHECKED_CAST")
        fun from(
            map: Map<String, Any>,
        ) = PaymentMethod(
            code = map["code"] as? String,
            countries = map["countries"] as? Any,
            created_at = map["created_at"] as? String,
            description = map["description"] as? String,
            enabled = map["enabled"] as? Boolean,
            fee_amount = (map["fee_amount"] as? Number)?.toDouble(),
            fee_currency = map["fee_currency"] as? String,
            fee_type = map["fee_type"] as? String,
            id = map["id"] as? String,
            kind = map["kind"] as? String,
            labels = map["labels"] as? Any,
            max_order_value = (map["max_order_value"] as? Number)?.toDouble(),
            metadata = map["metadata"] as? Any,
            min_order_value = (map["min_order_value"] as? Number)?.toDouble(),
            name = map["name"] as? String,
            position = (map["position"] as? Number)?.toLong(),
            provider = map["provider"] as? String,
            provider_method = map["provider_method"] as? String,
            updated_at = map["updated_at"] as? String,
        )
    }
}